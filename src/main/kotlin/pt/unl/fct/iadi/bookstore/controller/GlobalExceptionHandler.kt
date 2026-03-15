package pt.unl.fct.iadi.bookstore.controller

import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import pt.unl.fct.iadi.bookstore.controller.dto.ErrorResponse
import pt.unl.fct.iadi.bookstore.service.BookAlreadyExistsException
import pt.unl.fct.iadi.bookstore.service.BookNotFoundException
import pt.unl.fct.iadi.bookstore.service.ReviewNotFoundException

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException::class)
    fun handleBookNotFoundException(ex: BookNotFoundException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            error = "NOT_FOUND",
            message = ex.message ?: "Book not found",
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error)
    }

    @ExceptionHandler (BookAlreadyExistsException::class)
    fun handleBookAlreadyExistsException(ex: BookAlreadyExistsException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            error = "CONFLICT",
            message = ex.message ?: "Book already exists"
        )
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error)
    }

    @ExceptionHandler(ReviewNotFoundException::class)
    fun handleReviewNotFoundException(ex: ReviewNotFoundException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            error = "NOT_FOUND",
            message = ex.message ?: "Review not found"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error)
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            error = ex.message ?: "INTERNAL_SERVER_ERROR",
            message = ex.message ?: "Unexpected error"
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error)
    }

}