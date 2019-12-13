package com.murskiy.sergey.webprogramming.controllers.search

import com.murskiy.sergey.webprogramming.models.Document
import com.murskiy.sergey.webprogramming.services.search.SearchService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/search")
class SearchController(@Autowired private var searchService: SearchService) {
    private val logger = LoggerFactory.getLogger(SearchController::class.java)

    @GetMapping("/")
    fun search(query: String): List<Document> {
        logger.info("GET request /search with param: query=$query")
        return searchService.search(query)
    }
}
