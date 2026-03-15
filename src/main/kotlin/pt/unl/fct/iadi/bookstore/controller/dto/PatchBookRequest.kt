package pt.unl.fct.iadi.bookstore.controller.dto

data class PatchBookRequest(
    @field:Size(min = 1, max = 120)
    val title: String?,
    @field:Size(min = 1, max = 80)
    val author: String?,
    @field:DecimalMin(value = "0.0", inclusive = false)
    val price: Double?,
    @field:Pattern(regexp = "https?://.*")
    val image: String?
)