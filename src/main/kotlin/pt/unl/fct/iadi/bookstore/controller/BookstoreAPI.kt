package org.pt.unl.fct.iadi.bookstore.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.pt.unl.fct.iadi.bookstore.domain.Book
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

interface BookstoreAPI {
    @Operation(
    summary = "Add a new book to the store",
    operationId = "addBook",
    tags = ["book"]
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
                )]
            ),
            ApiResponse(responseCode = "400", description = "Invalid input")
        ]
    )
    @RequestMapping(
        value = ["/books"],
        produces = ["application/json"],
        consumes = ["application/json"],
        method = [RequestMethod.POST]
    )
    fun addBook(
        @Parameter(
            description = "Book object that needs to be added to the store",
            required = true
        ) @Valid @RequestBody book: Book
    ): ResponseEntity<Void>
}

//The code here is a copy-paste of the slides, not part of the project