package pt.unl.fct.iadi.bookstore.controller

import pt.unl.fct.iadi.bookstore.domain.Book
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import pt.unl.fct.iadi.bookstore.controller.dto.CreateBookRequest

@RestController
class BookstoreController : BookstoreAPI {
    override fun createBook(book: CreateBookRequest): ResponseEntity<Unit> {
        // TODO("Not yet implemented")
        val location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(book.id)
            .toUri()
        return ResponseEntity.created(location).build()
    }
}