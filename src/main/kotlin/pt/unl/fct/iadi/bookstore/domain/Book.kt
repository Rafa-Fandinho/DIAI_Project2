package pt.unl.fct.iadi.bookstore.domain
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.*

data class Book(
    @field:NotBlank
    @field:Schema(description = "ISBN must have 13 digits", pattern = "^\\d{13}$")
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
    @field:Schema(description = "URL to the book cover image", example = "https://example.com/book.jpg")
    val image: String
)