package com.jogurtonelle.library.data

import com.jogurtonelle.library.model.BookCopy
import com.jogurtonelle.library.model.BookStatus
import com.jogurtonelle.library.model.BookTitle
import com.jogurtonelle.library.model.User

object Data{
    val bookTitles : List<BookTitle> = listOf(
        BookTitle(
            id = 1,
            title = "Rodzina Monet. Skarb",
            author = "W. A. Marczak",
            description = "Zagubiona i odnaleziona jesteś jak skarb.\n" +
                    "Hailie Monet ma niespełna piętnaście lat, gdy w wypadku samochodowym traci dwie najukochańsze osoby: mamę i babcię. Ze skromnego, ale pełnego miłości i ciepła domu trafia do luksusowej willi w Pensylwanii zamieszkanej przez pięciu władczych i zdystansowanych mężczyzn. Oni raczej chłodno przyjmują nastolatkę.\n" +
                    "Will, Vincent, Dylan, Shane i Tony to starsi bracia Hailie, o których istnieniu dziewczyna nie miała pojęcia. Tęsknota za ukochaną mamą, zagubienie w obcej rzeczywistości i brak zrozumienia ze strony rodzeństwa są trudne do udźwignięcia. I choć w nowym domu jest wszystko, o czym może marzyć nastolatka, prywatne liceum jest najlepsze w stanie, a stylowy mundurek i drogie ubrania leżą idealnie, Hailie czuje się bardzo samotna. Jakby tego było mało, z każdym dniem odkrywa, że życie jej braci pełne jest mrocznych sekretów, których będą strzec, zwłaszcza przed swoją młodszą siostrzyczką.\n" +
                    "Pierwszy tom serii, która podbiła serca czytelników polskiego Wattpada.\n" +
                    "Oto wciągająca historia, od której nie sposób się oderwać.",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5088000/5088986/1103946-352x500.jpg",
            ISBN = "9788328729650"
        ),
        BookTitle(
            id = 2,
            title = "To Kill a Mockingbird",
            author = "Harper Lee",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5103000/5103991/1137237-352x500.jpg",
            ISBN = "9788328729759"
        ),
        BookTitle(
            id = 3,
            title = "Animal Farm",
            author = "George Orwell",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5088000/5088986/1103946-352x500.jpg",
            ISBN = "9788328729759"
        ),
        BookTitle(
            id = 4,
            title = "Pride and Prejudice",
            author = "Jane Austen",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5089000/5089389/1104506-352x500.jpg",
            ISBN = "9788328729759"
        ),
        BookTitle(
            id = 5,
            title = "The Catcher in the Rye",
            author = "J.D. Salinger",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5089000/5089389/1104506-352x500.jpg",
            ISBN = "9788328729759"
        ),
        BookTitle(
            id = 6,
            title = "The Hobbit",
            author = "J.R.R. Tolkien",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5089000/5089389/1104506-352x500.jpg",
            ISBN = "9788328729759"
        ),
        BookTitle(
            id = 7,
            title = "1984",
            author = "George Orwell",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5089000/5089389/1104506-352x500.jpg",
            ISBN = "9788328729759"
        ),
        BookTitle(
            id = 8,
            title = "Romeo and Juliet",
            author = "William Shakespeare",
            description = "Romeo and Juliet is a tragedy written by William Shakespeare early in his career about two young star-crossed lovers whose deaths ultimately reconcile their feuding families. It was among Shakespeare's most popular plays during his lifetime and along with Hamlet, is one of his most frequently performed plays. Today, the title characters are regarded as archetypal young lovers.",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5089000/5089389/1104506-352x500.jpg",
            ISBN = "9788328729759"
        ),
        BookTitle(
            id = 9,
            title = "The Picture of Dorian Gray",
            author = "J.D. Salinger",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5089000/5089389/1104506-352x500.jpg",
            ISBN = "9788328729759"
        ),
        BookTitle(
            id = 10,
            title = "The Da Vinci Code",
            author = "J.R.R. Tolkien",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5089000/5089389/1104506-352x500.jpg",
            ISBN = "9788328729759"
        ),
        BookTitle(
            id = 11,
            title = "The Lord of the Rings",
            author = "J.R.R. Tolkien",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5089000/5089389/1104506-352x500.jpg",
            ISBN = "9788328729759"
        ),
        BookTitle(
            id = 12,
            title = "The Little Prince",
            author = "Antoine de Saint-Exupéry",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5089000/5089389/1104506-352x500.jpg",
            ISBN = "9788328729759"
        ),
        BookTitle(
            id = 13,
            title = "Alice's Adventures in Wonderland",
            author = "Lewis Carroll",
            description = "The Great Gatsby is a 1925 novel by American writer F. Scott Fitzgerald. Set in the Jazz Age on Long Island, near New York City, the novel depicts first-person narrator Nick Carraway's interactions with mysterious millionaire Jay Gatsby and Gatsby's obsession to reunite with his former lover, Daisy Buchanan.",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5089000/5089389/1104506-352x500.jpg",
            ISBN = "9788328729759"
        ),
        BookTitle(
            id = 14,
            title = "The Chronicles of Narnia",
            author = "C.S. Lewis",
            description = "The Chronicles of Narnia is a series of seven high fantasy novels by C. S. Lewis. It is considered a classic of children's literature and is the author's best-known work, having sold over 100 million copies in 47 languages. Written by Lewis between 1949 and 1954, The Chronicles of Narnia has been adapted several times, complete or in part, for radio, television, the stage, and film.",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5089000/5089389/1104506-352x500.jpg",
            ISBN = "9788328729759"
        )
    )

    val bookItems : List<BookCopy> = listOf(
        BookCopy(
            id = 1,
            bookTitleId = 1,
            libraryBranchId = 1,
            status = BookStatus.AVAILABLE,
            yearOfPublication = 1925,
            dateOfReturn = null
        ),
        BookCopy(
            id = 2,
            bookTitleId = 1,
            libraryBranchId = 0,
            status = BookStatus.AVAILABLE,
            yearOfPublication = 1960,
            dateOfReturn = null
        ),
        BookCopy(
            id = 3,
            bookTitleId = 1,
            libraryBranchId = 1,
            status = BookStatus.BORROWED,
            yearOfPublication = 1980,
            dateOfReturn = "2024-12-31"
        ),
        BookCopy(
            id = 4,
            bookTitleId = 2,
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

    val favourites = mutableListOf<BookTitle>(bookTitles[0], bookTitles[1], bookTitles[2])
}