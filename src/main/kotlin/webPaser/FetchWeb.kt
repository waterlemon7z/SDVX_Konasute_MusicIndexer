package webPaser

import entity.MusicEntity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class FetchWeb(private val countrySel:Int) {
    private val _totalPageCount: Int = getMusicPageCount()
    private var curPage :Int = 0
    val totalPageCount: Int get() = _totalPageCount
    fun getMusicPage(num: Int): List<MusicEntity> {
        curPage = num
        val rst = mutableListOf<MusicEntity>()
        val postData = hashMapOf(
            "search_category" to "",
            "search_name" to "",
            "search_level" to "",
            "search_condition" to "",
            "page" to num.toString()
        )
        val basicUrl = when (countrySel) {
            1 -> "https://p.eagate.573.jp/game/eacsdvx/vi/music/index.html"
            else -> "https://usta.kr/sdvx/music/index.php"
        }
        val doc: Document = Jsoup.connect(basicUrl)
            .data(postData)
            .post()

        doc.getElementById("music-result")
            ?.getElementsByClass("music")?.forEach { itMusic ->
                try {
                    val genre: String = itMusic.getElementsByClass("genre")[0].text()
                    val name: String
                    val author: String
                    val level: MutableMap<String, Int> = mutableMapOf()

                    val musicInfo: Element = itMusic.getElementsByClass("cat")[0]
                        .getElementsByClass("inner")[0]
                        .getElementsByClass("info")[0]
                    val levelInfo: Elements = musicInfo
                        .getElementsByClass("level")[0]
                        .getElementsByTag("p")

                    name = musicInfo
                        .getElementsByTag("p")[0]
                        .text()
                    author = musicInfo
                        .getElementsByTag("p")[1]
                        .text()
                    levelInfo.forEach {
                        val levelName = it.classNames().stream().toArray()[0].toString()
                        level[levelName] = it.getElementsByTag("p")[0].text().toInt()
                    }
                    rst.add(MusicEntity(genre, name, author, level))
                } catch (_: IndexOutOfBoundsException) {
                }
            }
        return rst
    }

    private fun getMusicPageCount(): Int {

        val doc: Document = Jsoup.connect("https://usta.kr/sdvx/music/index.php")
            .get()
        val ids = doc.getElementById("search_page") ?: return -1
        //Todo: Exception
        return ids.allElements[ids.allElements.size - 1].text().toInt()
    }

}