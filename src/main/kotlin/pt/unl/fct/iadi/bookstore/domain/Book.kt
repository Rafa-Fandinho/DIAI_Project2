package pt.unl.fct.iadi.bookstore.domain
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.*

data class Book(
    @field:NotBlank
    @field:Schema(description = "ISBN must have 13 digits", pattern = "^\\d{13}$")
    val isbn: String,
    @field:NotBlank
    @field:Size(min = 1, max = 120)
    var title: String,
    @field:NotBlank
    @field:Size(min = 1, max = 80)
    var author: String,
    @field:Schema(description = "Price must be greater than zero", exclusiveMinimum = true, minimum = "0.0")
    var price: Double,
    @field:NotBlank
    @field:Pattern(regexp = "https?://.*")
    @field:Schema(description = "URL to the book cover image", example = "https://example.com/book.jpg")
    var image: String
)