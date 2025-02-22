package eu.kanade.tachiyomi.extension.en.sitemanga
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import eu.kanade.tachiyomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class SiteManga : Madara(
    "Site Manga",
    "https://sitemanga.com",
    "en",
    dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US),
) {
    override val useNewChapterEndpoint = true

    override val useLoadMoreRequest = LoadMoreStrategy.Always

    override val filterNonMangaItems = false
}
