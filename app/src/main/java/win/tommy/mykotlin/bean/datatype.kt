package win.tommy.mykotlin.bean


data class Cover( val coverUrl: String?=null, val title: String?=null, val link: String?=null)
data class News(val title: String, val createdTime: String, val link: String)
data class NewsContainer(val title: String, val newsList: List<News>)
data class Comic(val comicUrl: String)
data class BookInfo(val updateTime: String, val description: String)
data class Page(val title: String, val link: String)
data class BookDetail(val pages: List<Page>, val info: BookInfo) {
    operator fun get(position: Int) = pages[position]
    val size
        get() = pages.size
}