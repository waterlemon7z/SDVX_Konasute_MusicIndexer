package webPaser

import entity.MusicEntity
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class FetchWeb {
    private val _totalPageCount: Int = getMusicPageCount()
    val totalPageCount: Int get() = _totalPageCount
    var curPage :Int = 0
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
        val doc: Document = Jsoup.connect("https://usta.kr/sdvx/music/index.php")
            .data(postData)
            .post()

        val musicDivs: Elements? = doc.getElementById("music-result")
            ?.getElementsByClass("music")
        if (musicDivs != null) {
            musicDivs.forEach {
                try {
                    val genre: String = it.getElementsByClass("genre")[0].text()
                    val name: String
                    val author: String
                    val level: MutableMap<String, Int> = mutableMapOf()

                    val musicInfo: Element = it.getElementsByClass("cat")[0]
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
        }
        return rst
    }

    private fun getMusicPageCount(): Int {

        val doc: Document = Jsoup.connect("https://usta.kr/sdvx/music/index.php")
            .get()
        val ids = doc.getElementById("search_page")
        if (ids == null)
            return -1 //Todo: Exception
        return ids.allElements[ids.allElements.size - 1].text().toInt()
    }

}