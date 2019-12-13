package com.murskiy.sergey.webprogramming.services.document

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*

@Service
class DocumentReader {
    private val logger = LoggerFactory.getLogger(DocumentReader::class.java)

    fun getDocumentContent(document: MultipartFile): Optional<String> {
        logger.info("Reading document: ${document.originalFilename}")
        var fileContent: String? = null

        try {
            fileContent = String(document.bytes)
        } catch (e: IOException) {
            logger.error("Can not read document: ${document.originalFilename}")
            e.printStackTrace()
        }

        return Optional.ofNullable(fileContent)
    }
}