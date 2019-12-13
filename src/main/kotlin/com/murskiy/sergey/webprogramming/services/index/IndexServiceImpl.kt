package com.murskiy.sergey.webprogramming.services.index

import com.murskiy.sergey.webprogramming.models.IndexEntity
import com.murskiy.sergey.webprogramming.repositories.DocumentRepository
import com.murskiy.sergey.webprogramming.repositories.IndexRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IndexServiceImpl(@Autowired private val indexRepository: IndexRepository,
                       @Autowired private val documentRepository: DocumentRepository): IndexService {

    private val logger = LoggerFactory.getLogger(IndexServiceImpl::class.java)

    override fun getAllIndex(): List<IndexEntity> {
        val indexEntitiesList = indexRepository.findAll().toList()
        logger.info("Find all indexEntities, result size: " + indexEntitiesList.size)
        return indexEntitiesList
    }

    override fun getIndexEntityByWord(word: String): IndexEntity {
        val indexEntityOptional = indexRepository.findByWord(word)
        return if (indexEntityOptional.isPresent) {
            logger.info("Find indexEntity by word: $word, result size: 1")
            indexEntityOptional.get()
        } else {
            logger.info("Find document by word: $word, result size: 0")
            IndexEntity()
        }
    }

    override fun deleteAllIndex() {
        logger.info("Delete all index")
        indexRepository.deleteAll()

        logger.info("Delete all documents")
        documentRepository.deleteAll()
    }
}