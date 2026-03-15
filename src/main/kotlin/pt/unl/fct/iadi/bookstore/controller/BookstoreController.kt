package pt.unl.fct.iadi.bookstore.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import pt.unl.fct.iadi.bookstore.controller.dto.BookResponse
import pt.unl.fct.iadi.bookstore.controller.dto.CreateBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.CreateReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.PatchBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.PatchReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReviewResponse
import pt.unl.fct.iadi.bookstore.service.BookstoreService
import java.net.URI

@RestController
class BookstoreController(private val service: BookstoreService) : BookstoreAPI {
    override fun listBooks(): ResponseEntity<List<BookResponse>> {
        val books = service.listBooks()
        return ResponseEntity.ok(books)
    }

    override fun createBook(book: CreateBookRequest): ResponseEntity<Unit> {
        service.createBook(book)
        val location = URI.create("/books/${book.isbn}")
        return ResponseEntity.created(location).build()
    }

    override fun getBook(isbn: String, language: String?): ResponseEntity<BookResponse> {
        val book = service.getBook(isbn, language ?: "en")
        return ResponseEntity.ok().header("Content-Language",language ?: "en").body(book)
    }

    override fun replaceBook(isbn: String, request: ReplaceBookRequest): ResponseEntity<Unit>{
        service.replaceBook(isbn, request)
        return ResponseEntity.ok().build()
    }

    override fun updateBook(isbn: String, request: PatchBookRequest): ResponseEntity<Unit> {
        service.updateBook(isbn, request)
        return ResponseEntity.ok().build()
    }

    override fun deleteBook(isbn: String): ResponseEntity<Unit> {
        service.deleteBook(isbn)
        return ResponseEntity.noContent().build()
    }

    override fun listReviews(isbn: String): ResponseEntity<List<ReviewResponse>> {
        val reviews = service.listReviews(isbn)
        return ResponseEntity.ok().body(reviews)
    }

    override fun createReview(isbn: String, request: CreateReviewRequest): ResponseEntity<Unit> {
        val iid = service.createReview(isbn, request)
        val location = URI.create("/books/$isbn/reviews/$iid")
        return ResponseEntity.created(location).build()
    }

    override fun replaceReview(isbn: String, id: Long, request: ReplaceReviewRequest): ResponseEntity<Unit> {
        service.replaceReview(isbn, id, request)
        return ResponseEntity.ok().build()
    }

    override fun updateReview(isbn: String, id: Long, request: PatchReviewRequest): ResponseEntity<Unit> {
        service.updateReview(isbn, id, request)
        return ResponseEntity.ok().build()
    }

    override fun deleteReview(isbn: String, id: Long): ResponseEntity<Unit> {
        service.deleteReview(isbn, id)
        return ResponseEntity.noContent().build()
    }


}