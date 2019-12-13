package com.murskiy.sergey.webprogramming.services.search

import com.murskiy.sergey.webprogramming.models.Document

interface SearchService {

    fun search(query: String): List<Document>
}