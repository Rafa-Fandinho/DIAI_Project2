package pt.unl.fct.iadi.bookstore.domain
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.*

data class Review(
    val id: Long,
    @field:Min(1)
    @field:Max(5)
    @field:Schema(description = "Rating from 1 to 5")
    var rating: Int,
    @field:Size(max = 500)
    var comment: String?
)