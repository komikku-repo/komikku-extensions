package eu.kanade.tachiyomi.extension.en.dynasty
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import eu.kanade.tachiyomi.network.GET
import eu.kanade.tachiyomi.source.model.FilterList
import eu.kanade.tachiyomi.source.model.Page
import eu.kanade.tachiyomi.source.model.SChapter
import eu.kanade.tachiyomi.source.model.SManga
import eu.kanade.tachiyomi.util.asJsoup
import okhttp3.Request
import okhttp3.Response
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class DynastyDoujins : DynastyScans() {

    override val name = "Dynasty-Doujins"

    override val searchPrefix = "doujins"

    override val categoryPrefix = "Doujin"
    override fun popularMangaInitialUrl() = ""

    override fun popularMangaFromElement(element: Element): SManga {
        return super.popularMangaFromElement(element).apply {
            thumbnail_url = element.select("img").attr("abs:src").let {
                if (it.contains("cover_missing")) {
                    null
                } else {
                    it
                }
            }
        }
    }

    override fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request {
        return GET("$baseUrl/search?q=$query&classes%5B%5D=Doujin&sort=&page=$page", headers)
    }

    override fun mangaDetailsParse(document: Document): SManga {
        val manga = SManga.create().apply {
            title = document.selectFirst("div#main > h2 > b")!!.text().substringAfter("Doujins › ")
            description = document.select("div#main > div.description").text()
            thumbnail_url = document.select("a.thumbnail img").firstOrNull()?.attr("abs:src")
                ?.replace("/thumb/", "/medium/")
        }
        parseGenres(document, manga)
        return manga
    }

    override fun chapterListSelector() = "div#main > dl.chapter-list > dd"

    private fun doujinChapterParse(document: Document): List<SChapter> {
        return try {
            document.select(chapterListSelector()).map { chapterFromElement(it) }
        } catch (e: IndexOutOfBoundsException) {
            emptyList()
        }
    }

    override fun chapterListParse(response: Response): List<SChapter> {
        val document = response.asJsoup()
        val chapters = mutableListOf<SChapter>()
        var page = 1

        document.select("a.thumbnail img").let { images ->
            if (images.isNotEmpty()) {
                chapters.add(
                    SChapter.create().apply {
                        name = "Images"
                        setUrlWithoutDomain(document.location() + "/images")
                    },
                )
            }
        }
        chapters.addAll(doujinChapterParse(document))

        var hasNextPage = popularMangaNextPageSelector().let { selector ->
            document.select(selector).first()
        } != null

        while (hasNextPage) {
            page += 1
            val doujinURL = document.location() + "?page=$page"

            val newRequest = GET(doujinURL, headers)
            val newResponse = client.newCall(newRequest).execute()

            if (!newResponse.isSuccessful) {
                /*
                TODO: Toast to notify chapter parsing aborted.
                      Add possible retry logic.
                 */
                return chapters
            }

            val newDocument = newResponse.asJsoup()
            chapters.addAll(doujinChapterParse(newDocument))

            hasNextPage = popularMangaNextPageSelector().let { selector ->
                newDocument.select(selector).first()
            } != null
        }
        return chapters
    }

    override fun pageListParse(document: Document): List<Page> {
        return if (document.location().endsWith("/images")) {
            document.select("a.thumbnail").mapIndexed { i, element ->
                Page(i, element.attr("abs:href"))
            }
        } else {
            super.pageListParse(document)
        }
    }

    override fun imageUrlParse(document: Document): String {
        return document.select("div.image img").attr("abs:src")
    }
}
