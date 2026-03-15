package pt.unl.fct.iadi.bookstore.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class BookResponse (
    @field:NotBlank
    @field:Schema(pattern = "^\\d{13}$")
    val isbn: String,
    @field:NotBlank
    @field:Size(min = 1, max = 120)
    var title: String,
    @field:NotBlank
    @field:Size(min = 1, max = 80)
    var author: String,
    @field:Schema(exclusiveMinimum = true, minimum = "0.0")
    var price: Double,
    @field:NotBlank
    @field:Pattern(regexp = "https?://.*")
    var image: String
)