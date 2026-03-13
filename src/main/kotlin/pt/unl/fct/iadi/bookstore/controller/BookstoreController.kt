package org.pt.unl.fct.iadi.bookstore.controller

import org.pt.unl.fct.iadi.bookstore.domain.Book
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class BookstoreController : BookstoreAPI {
    override fun addBook(book: Book): ResponseEntity<Void> {
        // TODO("Not yet implemented")
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(book.id)
            .toUri()
        return ResponseEntity.created(location).build()
    }
}