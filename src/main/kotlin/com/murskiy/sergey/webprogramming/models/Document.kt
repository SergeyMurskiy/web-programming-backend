package com.murskiy.sergey.webprogramming.models

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "inverted-search", type = "document")
data class Document(
    @Id
    var documentId: String = "",
    var documentName: String = "",
    var documentContent: String = "")