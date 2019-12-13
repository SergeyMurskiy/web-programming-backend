package com.murskiy.sergey.webprogramming.services.document

import com.murskiy.sergey.webprogramming.models.Document
import org.springframework.web.multipart.MultipartFile

interface DocumentService {
    fun getAllDocuments(): List<Document>

    fun getDocumentById(id: String): Document

    fun deleteAllDocuments()

    fun addDocument(document: MultipartFile)
}