package pt.unl.fct.iadi.bookstore.controller.dto

data class BookResponse (
    @Schema(example = "9780134685991")
    val isbn: String,
    @Schema(example = "Effective Java")
    val title: String,
    @Schema(example = "Joshua Bloch")
    val author: String,
    @Schema(example = "45.50")
    val price: Double,
    @Schema(example = "https://example.com/book.jpg")
    val image: String
)