package org.pt.unl.fct.iadi.bookstore.domain

data class Review(
    val id: Long,
    @field:Schema(description = "Rating from 1 to 5", exclusiveMinimum = true, minimum = "1", exclusiveMaximum = true, maximum = "5")
    val rating: Int,
    val comment: String
) {
}

//Missing value restrictions