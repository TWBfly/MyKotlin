package win.tommy.mykotlin.domain.net

import org.jsoup.Jsoup
import win.tommy.mykotlin.bean.Comic
import win.tommy.mykotlin.util.getHtml
import java.util.*

/**
 * @author wupanjie
 */
class ComicSource : Source<ArrayList<Comic>> {
  override fun obtain(url: String): ArrayList<Comic> {
    val html = getHtml(url)
    val doc = Jsoup.parse(html)

    val elements = doc.select("div.mangaContentMainImg").select("img")
    val list = ArrayList<Comic>()

    for (element in elements) {
      var comicUrl: String
      val temp = element.attr("src")
      if (temp.contains(".png") || temp.contains(".jpg") || temp.contains(".JPEG")) {
        comicUrl = temp
      } else if (!"".equals(element.attr("data-original"))) {
        comicUrl = element.attr("data-original")
      } else {
        continue
      }

      val comic = Comic(comicUrl)
      list.add(comic)
    }
    return list
  }
}