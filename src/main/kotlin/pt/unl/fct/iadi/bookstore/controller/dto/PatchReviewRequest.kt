package pt.unl.fct.iadi.bookstore.controller.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

data class PatchReviewRequest(
    @field:Min(1)
    @field:Max(5)
    val rating: Int?,
    @field:Size(max = 500)
    val comment: String?
)