package eu.kanade.tachiyomi.extension.all.hentaiera

import eu.kanade.tachiyomi.multisrc.galleryadults.GalleryAdults
import eu.kanade.tachiyomi.multisrc.galleryadults.Genre
import eu.kanade.tachiyomi.multisrc.galleryadults.SearchFlagFilter
import eu.kanade.tachiyomi.multisrc.galleryadults.SortOrderFilter
import eu.kanade.tachiyomi.multisrc.galleryadults.imgAttr
import eu.kanade.tachiyomi.source.model.FilterList
import okhttp3.Request
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class HentaiEra(
    lang: String = "all",
    override val mangaLang: String = LANGUAGE_MULTI,
) : GalleryAdults(
    "HentaiEra",
    "https://hentaiera.com",
    lang = lang,
) {
    override val supportsLatest = true
    override val useIntermediateSearch: Boolean = true
    override val supportSpeechless: Boolean = true

    override fun Element.mangaTitle(selector: String): String? =
        mangaFullTitle(selector.replace(".caption", ".gallery_title")).let {
            if (preferences.shortTitle) it?.shortenTitle() else it
        }

    override fun Element.mangaLang() =
        select("a:has(.g_flag)").attr("href")
            .removeSuffix("/").substringAfterLast("/")
            .let {
                // Include Speechless in search results
                if (it == LANGUAGE_SPEECHLESS) mangaLang else it
            }

    override fun popularMangaRequest(page: Int): Request {
        return if (mangaLang.isBlank()) {
            // Popular browsing for LANGUAGE_MULTI
            val popularFilter = SortOrderFilter(getSortOrderURIs())
                .apply {
                    state = 0
                }
            if (useBasicSearch) {
                basicSearchRequest(page, "", FilterList(popularFilter))
            } else {
                searchMangaRequest(page, "", FilterList(popularFilter))
            }
        } else {
            // Popular browsing for other languages: using source's popular page
            super.popularMangaRequest(page)
        }
    }

    /* Details */
    override fun Element.getInfo(tag: String): String {
        return select("li:has(.tags_text:contains($tag)) a.tag")
            .joinToString {
                val name = it.selectFirst(".item_name")?.ownText() ?: ""
                if (tag.contains(regexTag)) {
                    genres[name] = it.attr("href")
                        .removeSuffix("/").substringAfterLast('/')
                }
                listOf(
                    name,
                    it.select(".split_tag").text()
                        .trim()
                        .removePrefix("| "),
                )
                    .filter { s -> s.isNotBlank() }
                    .joinToString()
            }
    }

    override fun Element.getCover() =
        selectFirst(".left_cover img")?.imgAttr()

    /* Filters */
    override fun tagsParser(document: Document): List<Genre> {
        return document.select(".galleries .gallery_title a")
            .mapNotNull {
                Genre(
                    it.ownText(),
                    it.attr("href")
                        .removeSuffix("/").substringAfterLast('/'),
                )
            }
    }

    override val mangaDetailInfoSelector = ".gallery_first"

    /* Pages */
    override val thumbnailSelector = ".gthumb"
    override val pageUri = "view"

    override fun getCategoryURIs() = listOf(
        SearchFlagFilter("Manga", "mg"),
        SearchFlagFilter("Doujinshi", "dj"),
        SearchFlagFilter("Western", "ws"),
        SearchFlagFilter("Image Set", "is"),
        SearchFlagFilter("Artist CG", "ac"),
        SearchFlagFilter("Game CG", "gc"),
    )

    override fun relatedMangaListSelector() = ".related_section ${popularMangaSelector()}"
}
