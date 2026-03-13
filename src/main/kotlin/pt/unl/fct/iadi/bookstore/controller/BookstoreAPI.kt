package pt.unl.fct.iadi.bookstore.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.headers.Header
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import pt.unl.fct.iadi.bookstore.controller.dto.BookResponse
import pt.unl.fct.iadi.bookstore.controller.dto.CreateBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.CreateReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ErrorResponse
import pt.unl.fct.iadi.bookstore.controller.dto.PatchBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReviewResponse
import javax.sound.midi.Patch

interface BookstoreAPI {

    @Operation(
        summary = "List all books in the catalog",
        operationId = "listBooks",
        tags = ["books"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Retrieved book catalog successfully",
                content = [Content(schema = Schema(implementation = BookResponse::class))]),
        ]
    )
    @GetMapping("/books")
    fun listBooks(): ResponseEntity<BookResponse>

    @Operation(
        summary = "Register a new book in the catalog",
        operationId = "createBook",
        tags = ["books"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Book created successfully",
                headers = [Header(
                    name = "Location",
                    description = "URI of the newly created book",
                    schema = Schema(type = "string", format = "uri")
                )]),
            ApiResponse(responseCode = "400", description = "Invalid input",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
            ApiResponse(responseCode = "409", description = "Book with this ISBN already exists",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))])
        ]
    )
    @RequestMapping(value = ["/books"], consumes = ["application/json"], method = [RequestMethod.POST])
    fun createBook(@Valid @RequestBody book: CreateBookRequest): ResponseEntity<Unit>

    @Operation(
        summary = "Retrieve a book with a specific ISBN from the catalog",
        operationId = "getBook",
        tags = ["books"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Book found successfully",
                content = [Content(schema = Schema(implementation = BookResponse::class))]),
            ApiResponse(responseCode = "404", description = "Book with this ISBN not found",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]) //Need the language thing, no idea how to
        ]
    )
    @GetMapping("/books/{isbn}")
    fun getBook(
        @Parameter(description = "ISBN of the book", required = true)
        @PathVariable isbn: String,
        @RequestHeader(name = "Accept-Language", required = false)
        language: String?
    ): ResponseEntity<BookResponse>

    @Operation(
        summary = "Fully replace a book's information",
        operationId = "replaceBook",
        tags = ["books"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Book updated successfully",
                content = [Content(schema = Schema(implementation = ReplaceBookRequest::class))]),
            ApiResponse(responseCode = "400", description = "Invalid input, must include all mandatory fields",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]),
            ApiResponse(responseCode = "404", description = "Book not found",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))])
        ]
    )
    @PatchMapping("/books/{isbn}")
    fun replaceBook(
        @Parameter(description = "ISBN of the book", required = true)
        @PathVariable isbn: String,
        //Might be missing to include how all parameters to be updated are necessary input
    ): ResponseEntity<Unit>

    @Operation(
        summary = "Partially update a book's information",
        operationId = "updateBook",
        tags = ["books"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Book updated successfully",
                content = [Content(schema = Schema(implementation = PatchBookRequest::class))]),
            ApiResponse(responseCode = "404", description = "Book not found",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))])
        ]
    )
    @PatchMapping("/books/{isbn}")
    fun updateBook(
        @Parameter(description = "ISBN of the book", required = true)
        @PathVariable isbn: String
    ): ResponseEntity<Unit>

    @Operation(
        summary = "Delete a book from the catalog",
        operationId = "deleteBook",
        tags = ["books"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Book deleted successfully",
                content = [Content(schema = Schema(implementation = ReplaceBookRequest::class))]), //should be deleteBookRequest?
            ApiResponse(responseCode = "404", description = "Book not found",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))])
        ]
    )
    @DeleteMapping("/books/{isbn}")
    fun deleteBook(
        @Parameter(description = "ISBN of the book", required = true)
        @PathVariable isbn: String
    ): ResponseEntity<Unit>

    @Operation(
        summary = "List all reviews of a book",
        operationId = "listReviews",
        tags = ["reviews"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Retrieved reviews successfully",
                content = [Content(schema = Schema(implementation = ReviewResponse::class))]),
            ApiResponse(responseCode = "404", description = "Book not found",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))])
        ]
    )
    @GetMapping("/books/{isbn}/reviews")
    fun listReviews(
        @Parameter(description = "ISBN of the book", required = true)
        @PathVariable isbn: String
    ): ResponseEntity<ReviewResponse>

    @Operation(
        summary = "Create a new review a book",
        operationId = "createReview",
        tags = ["reviews"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Review successfully created",
                content = [Content(schema = Schema(implementation = CreateReviewRequest::class))]),
            ApiResponse(responseCode = "404", description = "Book not found",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))])
        ]
    )
    @GetMapping("/books/{isbn}")
    fun createReview(
        @Parameter(description = "ISBN of the book", required = true)
        @PathVariable isbn: String
    ): ResponseEntity<Unit>

    @Operation(
        summary = "List all reviews of a book",
        operationId = "listReviews",
        tags = ["reviews"]
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Review successfully replaced",
                content = [Content(schema = Schema(implementation = CreateReviewRequest::class))]),
            ApiResponse(responseCode = "404", description = "Book not found",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))])
        ]
    )
    @GetMapping("/books/{isbn}")
    fun replaceReview(
        @Parameter(description = "ISBN of the book", required = true)
        @PathVariable isbn: String
    ): ResponseEntity<Unit>
}

//The code here is a copy-paste of the slides, not part of the project