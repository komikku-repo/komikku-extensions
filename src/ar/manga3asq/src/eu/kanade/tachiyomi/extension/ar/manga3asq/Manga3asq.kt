package eu.kanade.tachiyomi.extension.ar.manga3asq
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import eu.kanade.tachiyomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class Manga3asq : Madara(
    "مانجا العاشق",
    "https://3asq.org",
    "ar",
    // \u060c (،) U+060C : ARABIC COMMA
    dateFormat = SimpleDateFormat("d MMM\u060c yyy", Locale("ar")),
) {
    override val useNewChapterEndpoint: Boolean = true

    override val popularMangaUrlSelector = "div.post-title a:not([target])"
}
