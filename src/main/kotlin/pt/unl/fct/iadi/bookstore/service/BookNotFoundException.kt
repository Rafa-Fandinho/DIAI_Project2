package pt.unl.fct.iadi.bookstore.service

class BookNotFoundException(isbn: String, language: String? = "en"): RuntimeException(
    if (language == "pt")
        "Livro com isbn $isbn não encontrado"
    else
        "Book with isbn $isbn not found")