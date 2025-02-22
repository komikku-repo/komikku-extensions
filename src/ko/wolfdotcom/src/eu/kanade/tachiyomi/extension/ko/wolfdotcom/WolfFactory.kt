package eu.kanade.tachiyomi.extension.ko.wolfdotcom
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import eu.kanade.tachiyomi.source.SourceFactory
import eu.kanade.tachiyomi.source.model.FilterList
import org.jsoup.nodes.Document

class WolfFactory : SourceFactory {
    override fun createSources() = listOf(
        Wolf("웹툰", "ing", "list", "view"), // webtoon
        Wolf("만화책", "cm", "cl", "cv"), // comic book
        object : Wolf("포토툰", "pt", "list", "view") { // phototoon
            override fun getFilterList(): FilterList {
                return FilterList()
            }

            override fun parseSearchFilters(document: Document) {
                return
            }
        },
    )
}
