package pt.unl.fct.iadi.bookstore.controller.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CreateBookRequest(
    @field:NotBlank
    @field:Pattern(regexp = "^\\d{13}$")
    val isbn: String,
    @field:NotBlank
    @field:Size(min = 1, max = 120)
    val title: String,
    @field:NotBlank
    @field:Size(min = 1, max = 80)
    val author: String,
    @field:Schema(description = "Price must be greater than zero", exclusiveMinimum = true, minimum = "0.0")
    val price: Double,
    @field:NotBlank
    @field:Pattern(regexp = "https?://.*")
    val image: String
)