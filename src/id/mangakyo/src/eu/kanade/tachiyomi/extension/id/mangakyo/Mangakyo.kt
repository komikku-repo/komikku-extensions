package eu.kanade.tachiyomi.extension.id.mangakyo
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import eu.kanade.tachiyomi.multisrc.mangathemesia.MangaThemesia
import eu.kanade.tachiyomi.network.interceptor.rateLimit
import okhttp3.OkHttpClient
import java.text.SimpleDateFormat
import java.util.Locale

class Mangakyo : MangaThemesia(
    "Mangakyo",
    "https://mangakyo.vip",
    "id",
    "/komik",
    SimpleDateFormat("MMM d, yyyy", Locale("id")),
) {

    override val client: OkHttpClient = super.client.newBuilder()
        .rateLimit(4)
        .build()

    override val seriesTitleSelector = ".ts-breadcrumb li:last-child span"
    override val seriesAuthorSelector = ".infotable tr:contains(Pengarang) td:last-child"
    override val seriesDescriptionSelector = ".entry-content[itemprop=description] p:not(:contains(melapor ke fanspage))"
    override val seriesAltNameSelector = ".infotable tr:contains(Alternatif) td:last-child"
    override val seriesTypeSelector = ".infotable tr:contains(Tipe) td:last-child"
}
