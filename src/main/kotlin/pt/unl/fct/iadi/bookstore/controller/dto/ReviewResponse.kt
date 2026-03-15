package pt.unl.fct.iadi.bookstore.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

data class ReviewResponse(
    val id: Long,
    @field:Schema(example = "5")
    val rating: Int,
    @field:Schema(example = "Excellent book")
    val comment: String?
)