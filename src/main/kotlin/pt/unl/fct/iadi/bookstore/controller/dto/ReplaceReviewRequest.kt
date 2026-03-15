package pt.unl.fct.iadi.bookstore.controller.dto

data class ReplaceReviewRequest(
    @field:Min(1)
    @field:Max(5)
    val rating: Int,
    @field:Size(max = 500)
    val comment: String?
)