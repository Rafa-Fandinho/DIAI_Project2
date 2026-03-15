package pt.unl.fct.iadi.bookstore.controller.dto

data class ReviewResponse(
    val id: Long,
    @Schema(example = "5")
    val rating: Int,
    @Schema(example = "Excellent book")
    val comment: String?
)