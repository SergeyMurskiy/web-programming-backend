package com.murskiy.sergey.webprogramming.controllers.document

import com.murskiy.sergey.webprogramming.models.Document
import com.murskiy.sergey.webprogramming.services.document.DocumentService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@CrossOrigin
@RequestMapping("/document")
class DocumentController(@Autowired private val documentService: DocumentService) {
    private val logger = LoggerFactory.getLogger(DocumentController::class.java)

    @GetMapping("/all")
    fun getAllDocuments(): List<Document> {
        logger.info("GET request /document/all")
        return documentService.getAllDocuments()
    }

    @GetMapping("/{id}")
    fun getDocumentById(@PathVariable("id") id: String): Document {
        logger.info("GET request /document/ with param: id=$id")
        return documentService.getDocumentById(id)
    }

    @PostMapping("/add")
    fun addDocument(@RequestParam("file") document: MultipartFile) {
        logger.info("POST request /document/add with body: ${document.originalFilename}")
        documentService.addDocument(document)
    }

    @DeleteMapping("/delete/all")
    fun deleteAllDocuments() {
        logger.info("DELETE request /document/delete/all")
        documentService.deleteAllDocuments()
    }
}