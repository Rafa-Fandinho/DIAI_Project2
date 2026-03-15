package pt.unl.fct.iadi.bookstore

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(info = Info(title = "Bookstore API", version = "1.0", description = "Bookstore API, supporting catalog and reviews"),
    tags = [Tag(name = "books", description = "Operations related to books and book catalog"),
            Tag(name="reviews", description = "Operations related to reviews")])

class OpenAPIConfig