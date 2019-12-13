package com.murskiy.sergey.webprogramming.services.document

import com.murskiy.sergey.webprogramming.models.Document
import com.murskiy.sergey.webprogramming.models.IndexEntity
import com.murskiy.sergey.webprogramming.repositories.DocumentRepository
import com.murskiy.sergey.webprogramming.repositories.IndexRepository
import com.murskiy.sergey.webprogramming.services.Tokenizer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.lang.Exception
import java.util.*
import kotlin.collections.HashMap

@Service
class DocumentServiceImpl(@Autowired private var documentRepository: DocumentRepository,
                          @Autowired private var indexRepository: IndexRepository,
                          @Autowired private var tokenizer: Tokenizer,
                          @Autowired private var documentReader: DocumentReader) : DocumentService {

    private val logger = LoggerFactory.getLogger(DocumentServiceImpl::class.java)

    override fun getAllDocuments(): List<Document> {
        val documentsList = documentRepository.findAll().toList()
        logger.info("Find all documents, result size: " + documentsList.size)
        return documentRepository.findAll().toList()
    }

    override fun getDocumentById(id: String): Document {
        val documentOptional = documentRepository.findByDocumentId(id)
        return if (documentOptional.isPresent) {
            logger.info("Find document by id: $id, result size: 1")
            documentOptional.get()
        } else {
            logger.info("Find document by id: $id, result size: 0")
            Document()
        }
    }

    override fun deleteAllDocuments() {
        logger.info("Delete all documents")
        documentRepository.deleteAll()

        logger.info("Delete all index")
        indexRepository.deleteAll()
    }

    override fun addDocument(document: MultipartFile) {
        logger.info("Add document: " + document.originalFilename)

        val documentContent = documentReader.getDocumentContent(document)
        if (documentContent.isPresent) {
            val documentId = UUID.randomUUID().toString()
            val documentToSave = Document(documentId, document.originalFilename.orEmpty(), documentContent.get())

            try {
                logger.info("Save document: " + document.originalFilename + " with id = $documentId")
                documentRepository.save(documentToSave)
            } catch (e: Exception) {
                logger.error("Can not save document: " + document.originalFilename)
                e.printStackTrace()
            }

            addDocumentToIndex(documentContent.get(), documentId)
        }
    }

    private fun addDocumentToIndex(documentContent: String, id: String) {
        logger.info("Indexing document with documentId = $id")

        val documentWords = tokenizer.getWords(documentContent)

        val indexEntitiesToSave = HashMap<String, IndexEntity>()

        documentWords.forEach { documentWord ->
            if (!indexEntitiesToSave.containsKey(documentWord)) {
                val indexEntityOptional: Optional<IndexEntity>

                try {
                    indexEntityOptional = indexRepository.findByWord(documentWord)
                } catch (e: Exception) {
                    logger.error("Can not index document with id = $id. Error finding indexEntity by word = $documentWord")
                    documentRepository.deleteById(id)
                    e.printStackTrace()
                    return
                }

                if (indexEntityOptional.isPresent) {
                    val indexEntity = indexEntityOptional.get()
                    indexEntity.documentsIds.add(id)
                    indexEntitiesToSave[documentWord] = indexEntity
                } else {
                    indexEntitiesToSave[documentWord] = IndexEntity(documentWord, arrayListOf(id))
                }
            }
        }

        try {
            logger.info("Save indexing document with documentId = $id")
            indexRepository.saveAll(indexEntitiesToSave.values)
        } catch (e: Exception) {
            logger.error("Can not save indexing document with documentId = $id\"")
            documentRepository.deleteById(id)
            e.printStackTrace()
        }
    }
}