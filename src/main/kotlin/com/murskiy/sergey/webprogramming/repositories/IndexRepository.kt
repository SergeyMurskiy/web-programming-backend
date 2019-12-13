package com.murskiy.sergey.webprogramming.repositories

import com.murskiy.sergey.webprogramming.models.IndexEntity
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import java.util.*

interface IndexRepository: ElasticsearchRepository<IndexEntity, String> {
    fun findByWord(word: String): Optional<IndexEntity>
}