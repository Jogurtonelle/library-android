package com.jogurtonelle.library.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.jogurtonelle.library.model.BookCopy
import com.jogurtonelle.library.model.BookStatus
import com.jogurtonelle.library.model.BookTitle
import com.jogurtonelle.library.model.Notification
import com.jogurtonelle.library.model.NotificationType
import com.jogurtonelle.library.model.PromotionRow
import com.jogurtonelle.library.model.User
import com.jogurtonelle.library.ui.LibraryScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LibraryViewModel : ViewModel(){
    //UI State
    private val _uiState = MutableStateFlow(LibraryUiState())
    val uiState  = _uiState.asStateFlow()

    //Firebase components
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    //User details
    var user = User()
        private set

    /** The current query for the search bar */
    var _searchQuery: String by mutableStateOf("")
        private set
    //TODO??
    var _searchBarFocused : Boolean by mutableStateOf(false)
        private set

    var _promotionRows: List<PromotionRow> = listOf()
        private set

    private var _prevSearchResults: List<BookTitle> = listOf()
    private var _prevSearchQuerry: String = ""

    fun onQueryChanged(query: String) {
        _searchQuery = query

        Thread {
            Thread.sleep(500)
            if (query == _searchQuery){
                searchBooks(query)
            }
        }.start()
    }

    private fun searchBooks(query: String){
        var searchResults: List<BookTitle> = listOf()

        if (query.isNotEmpty()){
            //New search results contain the previous search results
            if (_prevSearchQuerry.isNotEmpty() && _prevSearchResults.size < 15 && query.contains(_prevSearchQuerry)){
                searchResults = _prevSearchResults.filter { it.title.contains(query, ignoreCase = true) }.take(15)

                //Searching for author
                if (searchResults.isEmpty() || searchResults.size < 15){
                    searchResults = searchResults + _prevSearchResults.filter { it.author.contains(query, ignoreCase = true) && !searchResults.contains(it)}.take(15 - searchResults.size)
                }
            }
            else{
                runBlocking {
                    //Searching for title
                    if (searchResults.isEmpty()){
                        searchResults = db.collection("BookTitles").whereGreaterThanOrEqualTo("title", query).whereLessThanOrEqualTo("title", query + "\uf8ff").limit(15).get().await().toObjects(BookTitle::class.java)
                    }

                    //Searching for author
                    if (searchResults.isEmpty() || searchResults.size < 15){
                        searchResults = searchResults + db.collection("BookTitles").whereGreaterThanOrEqualTo("author", query).whereLessThanOrEqualTo("author", query + "\uf8ff").limit(
                            (15 - searchResults.size).toLong()
                        ).get().await().toObjects(BookTitle::class.java)
                    }
                }

            }

            if (searchResults.isEmpty() && query.all{ it.isDigit()}) {
                runBlocking{
                    searchResults =
                        db.collection("BookTitles").whereEqualTo("isbn", query).limit(15).get().await().toObjects(BookTitle::class.java)
                }
            }

            _uiState.update {
                it.copy(showSearchResults = true, searchResults = searchResults)
            }

            _prevSearchResults = searchResults
            _prevSearchQuerry = query
        }
        else{
            _uiState.update {
                it.copy(showSearchResults = false, searchResults = listOf())
            }

            _prevSearchResults = listOf()
            _prevSearchQuerry = ""
        }
    }

    //Book selection
    fun selectBookHomeScreen(book: BookTitle) {
        _uiState.value.selectedBookTitleHomeScreen = book
    }

    fun selectBookFavouritesScreen(book: BookTitle) {
        _uiState.value.selectedBookTitleFavouritesScreen = book
    }

    fun changeBarcodeSheetVisibility(isVisible: Boolean) {
        _uiState.update {
            it.copy(showQrCodeBottomSheet = isVisible)}
    }

    fun onSearchBarFocusChange(isFocused: Boolean) {
        _searchBarFocused = isFocused
        if (!isFocused){
            _searchQuery = ""
        }
    }

    fun addPrevSearch(search: String) {
        _uiState.update {
            it.copy(prevSearches = it.prevSearches + search)
        }
    }

    fun setCurrentScreen(screen: LibraryScreen){
        if (screen == LibraryScreen.BOOK_HOME) {
            _uiState.update {
                it.copy(
                    currentScreen = screen,
                    bookScreenMainActive = true
                )
            }
            return
        }

        if (screen == LibraryScreen.BOOK_FAV) {
            _uiState.update {
                it.copy(
                    currentScreen = screen,
                    bookScreenFavActive = true
                )
            }
            return
        }

        if (screen == LibraryScreen.HOME && _uiState.value.bookScreenMainActive) {
            _uiState.update {
                it.copy(
                    currentScreen = screen,
                    bookScreenMainActive = false
                )
            }
            onSearchBarFocusChange(false)
            onQueryChanged("")
            return
        }

        if (screen == LibraryScreen.FAV && _uiState.value.bookScreenFavActive) {
            _uiState.update {
                it.copy(
                    currentScreen = screen,
                    bookScreenFavActive = false
                )
            }
            return
        }

        _uiState.update {
            it.copy(
                currentScreen = screen
            )
        }
    }

    fun editFavourites(bookISBN: String){
        var book : BookTitle? = null
        runBlocking{
            book = getBookTitle(bookISBN)
        }

        if (book != null){
            if (!user.favourites.contains(book)){
                user.favourites.add(book!!)

            }
            else{
                user.favourites.remove(book)
            }

            val favsISBN = user.favourites.map { it.isbn }
            db.collection("Users").document(auth.currentUser!!.uid).update("favourites", favsISBN)
        }
    }

    suspend fun getBookTitle(bookISBN: String): BookTitle{
        val document = db.collection("BookTitles").document(bookISBN).get().await()
        return document.toObject(BookTitle::class.java)!!
    }

    suspend fun getBookCopies(bookISBN: String): List<BookCopy>{
        val bookCopies = mutableListOf<BookCopy>()
        db.collection("BookCopies").whereEqualTo("bookTitleISBN", bookISBN).get().await().forEach{
            document ->
            val bookCopy = BookCopy(
                id = document.id.toInt(),
                bookTitleISBN = document.data["bookTitleISBN"] as String,
                libraryBranchId = (document.data["libraryBranchId"] as Long).toInt(),
                yearOfPublication = (document.data["yearOfPublication"] as Long).toInt(),
                status = if (document.data["status"] as String == "AVAILABLE") BookStatus.AVAILABLE else BookStatus.BORROWED,
                dateOfReturn = document.data["dateOfReturn"] as String?
            )
            bookCopies.add(bookCopy)
        }
        return bookCopies
    }

    suspend fun getPromotionRows(): List<PromotionRow>{
        val promotions = mutableListOf<PromotionRow>()
        db.collection("PromotionRows").get().await().forEach{
            document ->
            val promotion = PromotionRow(
                id = document.id.toInt(),
                name = document.data["name"] as String,
                books = getPromotionRowBooks(document.id.toInt())
            )
            promotions.add(promotion)
        }
        return promotions
    }

    suspend fun getPromotionRowBooks(ID: Int): List<BookTitle>{
        val books = mutableListOf<BookTitle>()
        db.collection("BookTitles").whereArrayContains("promotionRows", ID).get().await().forEach{
            document ->
            val book = BookTitle(
                isbn = document.id,
                title = document.data["title"] as String,
                author = document.data["author"] as String,
                description = document.data["description"] as String,
                coverURL = document.data["coverURL"] as String
            )
            books.add(book)
        }
        return books
    }

    private fun addBookTitleToDB(bookTitle: BookTitle){
        db.collection("BookTitles").document(bookTitle.isbn).get().addOnSuccessListener{
            document ->
            if (document.data == null){
                Log.d("LibraryViewModel", "Adding book title to the database")
                db.collection("BookTitles").document(bookTitle.isbn).set(bookTitle)
            }
            else{
                throw Exception("Book title with ISBN ${bookTitle.isbn} already exists in the database")
            }
        }
    }

    private fun addBookCopyToDB(bookItem: BookCopy){
        db.collection("BookCopies").document(bookItem.id.toString()).get().addOnSuccessListener{
            document ->
            if (document.data == null){
                Log.d("LibraryViewModel", "Adding book copy to the database")
                db.collection("BookCopies").document(bookItem.id.toString()).set(bookItem)
            }
            else{
                throw Exception("Book copy with ISBN ${bookItem.id} already exists in the database")
            }
        }
    }

    private suspend fun getNotification(id: String): Notification{
        val document = db.collection("Notifications").document(id).get().await()
        return document.toObject(Notification::class.java)!!
    }

    /** Get user data from the database. Function sets ViewModel parameter user and UIState parameter currentScreen to proper values*/
    private fun getUserData(){
        _uiState.update {
            it.copy(
                userLoggedIn = (auth.currentUser != null),
                currentScreen = if (auth.currentUser != null) LibraryScreen.HOME else LibraryScreen.LOGIN
            )
        }

        if (auth.currentUser != null){
            val uid = runBlocking {
                auth.currentUser!!.uid
            }

            runBlocking {
                db.collection("Users").document(uid).get().addOnSuccessListener {
                    document ->
                    if (document.data != null){
                        val bookISBNlist = document.data!!["favourites"] as List<String>
                        val booksList = mutableListOf<BookTitle>()

                        for (bookISBN in bookISBNlist){
                            runBlocking {
                                booksList.add(getBookTitle(bookISBN))
                            }
                        }

                        val notificationsListRaw = document.data!!["notifications"] as List<String>
                        val notificationsList = mutableListOf<Notification>()

                        for (notification in notificationsListRaw){
                            runBlocking { notificationsList.add(getNotification(notification)) }
                        }

                        user = User(
                            uid = document.data!!["uid"] as String,
                            cardID = document.data!!["cardID"] as String,
                            favourites = booksList,
                            notifications = notificationsList
                        )
                    }
                }.await()
            }
        }
    }

    fun login(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            getUserData()
        }
    }

    fun logout(){
        auth.signOut()
        getUserData()
    }

    fun reserveBook(bookCopy: BookCopy){
        runBlocking {
            db.collection("BookCopies").document(bookCopy.id.toString()).get()
                .addOnSuccessListener { document ->
                    if (document.data != null) {
                        if (document.data!!["status"] as String == "AVAILABLE") {
                            bookCopy.status = BookStatus.BORROWED
                            val dateOfReturn = LocalDate.now().plusMonths(1)
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                            bookCopy.dateOfReturn = dateOfReturn.toString()
                            val notification = Notification(
                                id = bookCopy.id.toString(),
                                type = NotificationType.ORDER_IN_PROGRESS,
                                book = runBlocking { getBookTitle(bookCopy.bookTitleISBN) },
                                branchID = bookCopy.libraryBranchId,
                                dateOfReturn = dateOfReturn
                            )
                            Thread {
                                db.collection("BookCopies").document(bookCopy.id.toString())
                                    .update("status", "BORROWED")
                                db.collection("BookCopies").document(bookCopy.id.toString())
                                    .update("dateOfReturn", dateOfReturn.toString())
                                user.notifications.add(notification)
                                db.collection("Notifications").document(bookCopy.id.toString())
                                    .set(notification)
                                db.collection("Users").document(auth.currentUser!!.uid)
                                    .update("notifications", user.notifications.map { it.id })
                            }.start()
                        }
                    }
                }.await()
        }
    }

    fun getNotifications(): List<Notification> {
        return user.notifications
    }

    init{
        getUserData()
        runBlocking {
            _promotionRows = getPromotionRows()
        }
    }

    /*        db.collection("BookTitles").get().addOnSuccessListener {
//            result ->
//            val books = result.toObjects(BookTitle::class.java)
//            val randomBranch = Random(0)
//            val randomYear = Random(12)
//            val randomAmount = Random(144)
//            var i = 0
//            for(book in books){
//                for (j in 0..randomAmount.nextInt(1, 4)){
//                    val BookCopy = BookCopy(
//                        id = i,
//                        bookTitleISBN = book.isbn,
//                        libraryBranchId = randomBranch.nextInt(0, 4),
//                        yearOfPublication = randomYear.nextInt(2000, 2022),
//                        status = BookStatus.AVAILABLE,
//                        dateOfReturn = null
//                    )
//                    addBookCopyToDB(BookCopy)
//                    i += 1
//                }
//            }
//        }

    init{
        val item1 = BookTitle(
            title = "Świat Maryjnych Objawień",
            author = "Wincenty Łaszewski",
            description = "Jedyna taka książka na świecie. 100 najważniejszych objawień maryjnych w historii ludzkości zebranych w jednym tomie.\n" +
                    "Ponad tysiąc stron porywającego tekstu. Wincenty Łaszewski dotarł do historycznych relacji z wydarzeń i wypowiedzi wizjonerów. Ich wizje po stuleciach spełniają się właśnie na naszych oczach. Autor, znany polski mariolog, analizuje i przystępnie wyjaśnia przesłania, z którymi na Ziemię przychodziła Główna Bohaterka Książki.\n" +
                    "Ponad 2600 zdjęć i ilustracji. Współczesne, bajecznie kolorowe zdjęcia z sanktuariów i miejsc objawień, materiały historyczne, dokumenty i reprodukcje najwspanialszych dzieł sztuki sakralnej. Nasza książka pokazuje wszystko to, co inne próbują tylko opisać.\n" +
                    "Ponad 200 map ukazujących położenie miejsc kultu i objawień, pomagających zrozumieć tło historyczne wydarzeń. Dzięki zapierającej dech w piersiach szacie graficznej książka łączy cechy leksykonu, powieści sensacyjnej i efektownego albumu.\n" +
                    "W chwili, gdy czytasz te słowa, trwa kilkadziesiąt objawień – na całym świecie. Lawina „informacji z Nieba” narasta. Jeśli chcesz wiedzieć, przed czym ostrzega Maryja i na co powinniśmy się przygotować – ta książka jest właśnie dla Ciebie.\n" +
                    "Jakość na pokolenia. Książka idealna na niepowtarzalny prezent.\n" +
                    "Luksusowe wydanie w eleganckim, kolekcjonerskim, lakierowanym pudełku zabezpieczającym książkę przed uszkodzeniem.\n" +
                    "„Do kongregacji dociera coraz więcej informacji o objawieniach Maryi.... Na pewno nie możemy przeszkadzać Bogu w mówieniu do dzisiejszego świata przez osoby prywatne i nadzwyczajne łaski”.\n" +
                    "kard. Joseph Ratzinger, przewodniczący Doktryny Wiary, 1984\n" +
                    "„Jezus chce, abym była bardziej znana i miłowana”\n" +
                    "Matka Boża, Fatima 1917",
            ISBN = "9788380799370",
            coverURL = "https://s.lubimyczytac.pl/upload/books/5102000/5102665/1126851-352x500.jpg"
        )

        val item2 = BookTitle(
            title = "365 dni z ojcem Pio",
            author = "św. Ojciec Pio",
            description = "Teksty zawarte w tej książce pochodzą z bardzo osobistych pism o. Pio – z jego listów do przyjaciół i osób, dla których był duchowym przewodnikiem. Te krótkie rozważania i rady o. Pio trafiają wprost do serca czytelnika i mówią mu o Bogu. Pulsuje w nich energia świętego, który pragnie pomagać ludziom prowadzić życie autentycznie chrześcijańskie i szczęśliwe. Książka ta przeznaczona jest dla wszystkich: świeckich, kapłanów, zakonników, niewierzących, dla ludzi doświadczonych życiowo, jak również dla młodych, którzy dopiero szukają swojej życiowej drogi.",
            ISBN = "9788370336943",
            coverURL = "https://s.lubimyczytac.pl/upload/books/75000/75836/789330-352x500.jpg"
        )

        val item3 = BookTitle(
            title = "365 dni z miłosierdziem Bożym",
            author = "Małgorzata Pabis, Stanisław Staśko",
            description = "365 dni z miłosierdziem Bożym zawiera wybór tekstów o miłosierdziu na każdy dzień, które ubogacił cennymi refleksjami ks. Stanisław Staśko. Postawione pytania są równocześnie zachętą do własnych przemyśleń i modlitwy.\n",
            ISBN = "9788374223874",
            coverURL = "https://s.lubimyczytac.pl/upload/books/151000/151099/352x500.jpg"
        )

        val item4 = BookTitle(
            title = "365 DNI NA DZIAŁCE I W OGRODZIE",
            author = "praca zbiorowa",
            description = "Ta książka nie posiada jeszcze opisu.",
            ISBN = "9788365222718",
            coverURL = "https://s.lubimyczytac.pl/upload/books/4859000/4859301/683867-352x500.jpg"
        )

        val item5 = BookTitle(
            title = "365 dni ze świętym Janem Pawłem II",
            author = "Dariusz Jaskólski",
            description = "Ta książka nie posiada jeszcze opisu.",
            ISBN = "9788382218565",
            coverURL = "https://s.lubimyczytac.pl/upload/books/4960000/4960353/885401-352x500.jpg"
        )
        val list = listOf(item1, item2, item3, item4, item5)
        for (item in list){
           // addBookTitleToDB(item)
        }

        val i : List<PromotionRow>
        runBlocking { i = getPromotionRows() }
        for (item in i){
            Log.d("LibraryViewModel", item.name + " " + item.id)
        }



        //val search = db.collection("BookTitles").whereGreaterThanOrEqualTo("title", "rod").whereLessThanOrEqualTo("title", "ROD").limit(15).get().addOnSuccessListener {
        //    result ->
        //    val books = result.toObjects(BookTitle::class.java)
        //    for(book in books){
        //        Log.d("LibraryViewModel", book.title)
        //    }
        //}*/
}