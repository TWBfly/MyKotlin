package win.tommy.mykotlin.domain.net
import org.jsoup.Jsoup
import win.tommy.mykotlin.bean.BookDetail
import win.tommy.mykotlin.bean.BookInfo
import win.tommy.mykotlin.bean.Page
import win.tommy.mykotlin.util.getHtml
import java.util.*

/**
 * @author wupanjie
 */
class BookDetailSource : Source<BookDetail> {
  override fun obtain(url: String): BookDetail {
    val html = getHtml(url)
    val doc = Jsoup.parse(html)

    val pages = ArrayList<Page>()
    val elements = doc.select("div.volumeControl").select("a")

    for (element in elements) {
      val title = element.text()
      val link = "http://ishuhui.net/" + element.attr("href")
      val page = Page(title, link)
      pages.add(page)
    }

    val updateTime = doc.select("div.mangaInfoDate").text()
    val detail = doc.select("div.mangaInfoTextare").text()
    val info = BookInfo(updateTime, detail)

    return BookDetail(pages, info)
  }

}