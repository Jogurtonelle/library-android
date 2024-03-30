package com.jogurtonelle.library.data

import com.jogurtonelle.library.R
import com.jogurtonelle.library.model.Book
import com.jogurtonelle.library.model.BookItem
import com.jogurtonelle.library.model.BookStatus
import com.jogurtonelle.library.model.User

object Data{
    val books : List<Book> = listOf(
        Book(
            id = 1,
            title = "The Great Gatsby",
            author = "F. Scott Fitzgerald",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverId = R.drawable.monet
        ),
        Book(
            id = 2,
            title = "To Kill a Mockingbird",
            author = "Harper Lee",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverId = R.drawable.monet
        ),
        Book(
            id = 3,
            title = "1984",
            author = "George Orwell",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverId = R.drawable.monet
        ),
        Book(
            id = 4,
            title = "Pride and Prejudice",
            author = "Jane Austen",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverId = R.drawable.monet
        ),
        Book(
            id = 5,
            title = "The Catcher in the Rye",
            author = "J.D. Salinger",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverId = R.drawable.monet
        ),
        Book(
            id = 6,
            title = "The Hobbit",
            author = "J.R.R. Tolkien",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person",
            coverId = R.drawable.monet
        ),
        Book(
            id = 7,
            title = "1984",
            author = "George Orwell",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverId = R.drawable.monet
        ),
        Book(
            id = 8,
            title = "Pride and Prejudice",
            author = "Jane Austen",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverId = R.drawable.monet
        ),
        Book(
            id = 9,
            title = "The Catcher in the Rye",
            author = "J.D. Salinger",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverId = R.drawable.monet
        ),
        Book(
            id = 10,
            title = "The Hobbit",
            author = "J.R.R. Tolkien",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverId = R.drawable.monet
        ),
        Book(
            id = 11,
            title = "The Lord of the Rings",
            author = "J.R.R. Tolkien",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverId = R.drawable.monet
        ),
        Book(
            id = 12,
            title = "The Little Prince",
            author = "Antoine de Saint-Exupéry",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverId = R.drawable.monet
        ),
        Book(
            id = 13,
            title = "Alice's Adventures in Wonderland",
            author = "Lewis Carroll",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverId = R.drawable.monet
        ),
        Book(
            id = 14,
            title = "The Chronicles of Narnia",
            author = "C.S. Lewis",
            description = "The Chronicles of Narnia is a series of seven high fantasy novels by C. S. Lewis. It is considered a classic of children's literature and is the author's best-known work, having sold over 100 million copies in 47 languages. Written by Lewis between 1949 and 1954, The Chronicles of Narnia has been adapted several times, complete or in part, for radio, television, the stage, and film.",
            coverId = R.drawable.monet
        )
    )

    val bookItems : List<BookItem> = listOf(
        BookItem(
            id = 1,
            bookId = 1,
            libraryBranchId = 1,
            status = BookStatus.AVAILABLE,
            yearOfPublication = 1925,
            dateOfReturn = null
        ),
        BookItem(
            id = 2,
            bookId = 1,
            libraryBranchId = 0,
            status = BookStatus.AVAILABLE,
            yearOfPublication = 1960,
            dateOfReturn = null
        ),
        BookItem(
            id = 3,
            bookId = 1,
            libraryBranchId = 1,
            status = BookStatus.BORROWED,
            yearOfPublication = 1980,
            dateOfReturn = "2024-12-31"
        ),
        BookItem(
            id = 4,
            bookId = 2,
            libraryBranchId = 2,
            status = BookStatus.AVAILABLE,
            yearOfPublication = 1960,
            dateOfReturn = null
        )
    )

    val user = User(
        id = 12345678910,
        name = "John Doe",
        email = "",
        password = ""
    )
}