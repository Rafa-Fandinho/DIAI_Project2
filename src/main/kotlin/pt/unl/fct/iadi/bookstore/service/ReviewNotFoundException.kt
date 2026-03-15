package pt.unl.fct.iadi.bookstore.service

class ReviewNotFoundException(id: Long): RuntimeException("Review with id $id not found")