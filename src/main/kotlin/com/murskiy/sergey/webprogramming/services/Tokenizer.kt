package com.murskiy.sergey.webprogramming.services

import org.springframework.stereotype.Service

@Service
class Tokenizer {
    fun getWords(content: String) : List<String> {
        return content.toLowerCase()
                .split("[\\p{P}\\u00A0\\u2028\n ]".toRegex())
                .filter { word -> word != "" && word != " " }
    }
}