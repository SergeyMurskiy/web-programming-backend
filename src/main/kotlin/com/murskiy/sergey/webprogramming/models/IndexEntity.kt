package com.murskiy.sergey.webprogramming.models

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "inverted-search", type = "index")
data class IndexEntity(
    @Id
    var word: String = "",

    @Field(type = FieldType.Text, includeInParent = true, store = true)
    var documentsIds: MutableList<String> = mutableListOf()
)
