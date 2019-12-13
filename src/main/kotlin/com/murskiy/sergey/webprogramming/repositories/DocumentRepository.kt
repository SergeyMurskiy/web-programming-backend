package com.murskiy.sergey.webprogramming.repositories

import com.murskiy.sergey.webprogramming.models.Document
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DocumentRepository: ElasticsearchRepository<Document, String> {
    fun findByDocumentId(id: String): Optional<Document>
}