package com.murskiy.sergey.webprogramming.services.search

import com.murskiy.sergey.webprogramming.models.Document
import com.murskiy.sergey.webprogramming.repositories.DocumentRepository
import com.murskiy.sergey.webprogramming.repositories.IndexRepository
import com.murskiy.sergey.webprogramming.services.Tokenizer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.streams.toList

@Service
class SearchServiceImpl(@Autowired private var tokenizer: Tokenizer,
                        @Autowired private var indexRepository: IndexRepository,
                        @Autowired private var documentRepository: DocumentRepository) : SearchService {

    private val logger = LoggerFactory.getLogger(SearchServiceImpl::class.java)

    override fun search(query: String): List<Document> {
        return findDocumentsIds(query).stream()
                .map { id -> documentRepository.findByDocumentId(id).orElse(null) }
                .filter { Objects.nonNull(it) }
                .toList()
    }

    private fun findDocumentsIds(query: String): Set<String> {
        val queryWords = tokenizer.getWords(query)
        logger.info("Search document by words: $queryWords")
        val documentsIds = HashSet<String>()

        queryWords.forEach { word ->
            val indexEntityOptional = indexRepository.findByWord(word)
            if (indexEntityOptional.isPresent) {
                if (documentsIds.isEmpty()) {
                    documentsIds.addAll(indexEntityOptional.get().documentsIds)
                } else {
                    documentsIds.retainAll(indexEntityOptional.get().documentsIds)
                }
            } else {
                logger.info("Documents not found")
                return emptySet()
            }
        }
        logger.info("Result size: ${documentsIds.size}, Documents ids: $documentsIds")
        return documentsIds
    }
}