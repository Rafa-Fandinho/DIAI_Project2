package pt.unl.fct.iadi.bookstore.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

data class BookResponse (
    @field:Schema(example = "9780134685991")
    val isbn: String,
    @field:Schema(example = "Effective Java")
    val title: String,
    @field:Schema(example = "Joshua Bloch")
    val author: String,
    @field:Schema(example = "45.50")
    val price: Double,
    @field:Schema(example = "https://example.com/book.jpg")
    val image: String
)