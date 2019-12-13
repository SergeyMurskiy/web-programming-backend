package com.murskiy.sergey.webprogramming

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
open class Web

fun main(args: Array<String>) {
	runApplication<Web>(*args)
}
