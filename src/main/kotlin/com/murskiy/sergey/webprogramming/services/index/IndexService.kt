package com.murskiy.sergey.webprogramming.services.index

import com.murskiy.sergey.webprogramming.models.IndexEntity

interface IndexService {
    fun getAllIndex(): List<IndexEntity>

    fun getIndexEntityByWord(word: String): IndexEntity

    fun deleteAllIndex()
}