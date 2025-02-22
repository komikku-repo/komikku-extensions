package eu.kanade.tachiyomi.extension.pt.mugiwarasoficial
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import eu.kanade.tachiyomi.multisrc.madara.Madara
import java.text.SimpleDateFormat
import java.util.Locale

class MugiwarasOficial : Madara(
    "Mugiwaras Oficial",
    "https://mugiwarasoficial.com",
    "pt-BR",
    SimpleDateFormat("d 'de' MMM 'de' yyyy", Locale("pt", "BR")),
) {
    override val useNewChapterEndpoint = true
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val mangaDetailsSelectorStatus = "div.summary-heading:contains(Estado) + .summary-content"
}
