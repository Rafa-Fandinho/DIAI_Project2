package pt.unl.fct.iadi.bookstore.service

import org.springframework.stereotype.Service
import pt.unl.fct.iadi.bookstore.controller.dto.BookResponse
import pt.unl.fct.iadi.bookstore.controller.dto.CreateBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.CreateReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.PatchBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.PatchReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceBookRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReplaceReviewRequest
import pt.unl.fct.iadi.bookstore.controller.dto.ReviewResponse
import pt.unl.fct.iadi.bookstore.domain.Book
import pt.unl.fct.iadi.bookstore.domain.Review
import java.util.concurrent.atomic.AtomicLong

@Service
class BookstoreService {
    private val books = mutableMapOf<String, Book>()
    private val reviews = mutableMapOf<String, MutableList<Review>>()
    private val reviewIdGenerator = AtomicLong(1)

    fun listBooks(): List<BookResponse> {
        return books.values.map {
            BookResponse(
                it.isbn,
                it.title,
                it.author,
                it.price,
                it.image
            )
        }
    }
    fun createBook(request: CreateBookRequest){
        if(!books.containsKey(request.isbn)){
            throw BookAlreadyExistsException(request.isbn)
        }

        val book = Book(
            request.isbn,
            request.title,
            request.author,
            request.price,
            request.image
        )
        books[request.isbn] = book
        reviews[request.isbn] = mutableListOf()
    }

    fun getBook(isbn: String, language: String?): BookResponse {
        val book = books[isbn]
            ?: throw BookNotFoundException(isbn,language)
        return book.toResponse()
    }

    fun replaceBook(isbn: String, request: ReplaceBookRequest){
        val book = Book(
            request.isbn,
            request.title,
            request.author,
            request.price,
            request.image
        )
        if(isbn != request.isbn){
            if(!books.containsKey(request.isbn)){
                books.putIfAbsent(request.isbn,book)
                books.remove(isbn)
            }
            else{
                throw BookAlreadyExistsException(request.isbn)
            }
        }
        else{
            books[isbn] = book
        }

        reviews.putIfAbsent(isbn, mutableListOf())
    }

    fun updateBook(isbn: String, request: PatchBookRequest){
        val book = books[isbn]
        ?: throw BookNotFoundException(isbn)
        val updated = book.copy(
            title = request.title ?: book.title,
            author = request.author ?: book.author,
            price = request.price ?: book.price,
            image = request.image ?: book.image
        )

        books[isbn] = updated
    }

    fun deleteBook(isbn: String){
        if(!books.containsKey(isbn)){
            throw BookNotFoundException(isbn)
        }
        books.remove(isbn)
        reviews.remove(isbn)
    }

    fun listReviews(isbn: String): List<ReviewResponse>{
        if(!books.containsKey(isbn)){
            throw BookNotFoundException(isbn)
        }
        return reviews[isbn]!!.map { it.toResponse() }
    }

    fun createReview(isbn: String, request: CreateReviewRequest): Long{
        if(!books.containsKey(isbn)){
            throw BookNotFoundException(isbn)
        }
        val iid = reviewIdGenerator.getAndIncrement()
        val review = Review(
            iid,
            request.rating,
            request.comment
        )
        reviews[isbn]!!.add(review)
        return iid
    }

    fun replaceReview(isbn: String, id: Long, request: ReplaceReviewRequest){
        val reviewList = reviews[isbn] ?: throw BookNotFoundException(isbn)
        val review = reviewList.find {it.id == id}
            ?: throw ReviewNotFoundException(id)
        reviewList[reviewList.indexOf(review)] = Review(
            id,
            request.rating,
            request.comment
        )
    }

    fun updateReview(isbn: String, id: Long, request: PatchReviewRequest){
        val reviewList = reviews[isbn] ?: throw BookNotFoundException(isbn)
        val review = reviewList.find {it.id == id}
            ?: throw ReviewNotFoundException(id)
        val updated = review.copy(
            rating = request.rating ?: review.rating,
            comment = request.comment ?: review.comment
        )
        reviewList[reviewList.indexOf(review)] = updated
    }

    fun deleteReview(isbn: String, id: Long){
        val reviewList = reviews[isbn] ?:  throw BookNotFoundException(isbn)
        val removed = reviewList.removeIf {it.id == id}
        if(!removed){
            throw ReviewNotFoundException(id)
        }
    }
    private fun Book.toResponse() = BookResponse(isbn,title,author,price,image)

    private fun Review.toResponse() = ReviewResponse(id,rating,comment)
}