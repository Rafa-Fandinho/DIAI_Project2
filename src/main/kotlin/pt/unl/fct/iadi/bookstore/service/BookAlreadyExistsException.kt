package pt.unl.fct.iadi.bookstore.service

class BookAlreadyExistsException(isbn: String): RuntimeException("A book with isbn $isbn already exists")