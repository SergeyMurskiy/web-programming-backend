package com.murskiy.sergey.webprogramming.controllers.index

import com.murskiy.sergey.webprogramming.models.IndexEntity
import com.murskiy.sergey.webprogramming.services.index.IndexService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RequestMapping("/index")
@RestController
class IndexController(@Autowired private var indexService: IndexService) {
    private val logger = LoggerFactory.getLogger(IndexController::class.java)

    @GetMapping("/all")
    fun getAllIndex(): List<IndexEntity> {
        logger.info("GET request /index/all")
        return indexService.getAllIndex().toList()
    }

    @GetMapping("/word/{word}")
    fun getIndexEntityByWord(word: String): IndexEntity {
        logger.info("GET request /index/word with param: word=$word")
        return indexService.getIndexEntityByWord(word)
    }

    @DeleteMapping("/delete/all")
    fun deleteAllIndex() {
        logger.info("DELETE request /index/delete/all")
        indexService.deleteAllIndex()
    }
}