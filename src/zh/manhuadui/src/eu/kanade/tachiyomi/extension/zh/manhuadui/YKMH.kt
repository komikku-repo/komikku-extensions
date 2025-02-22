package eu.kanade.tachiyomi.extension.zh.manhuadui
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import eu.kanade.tachiyomi.multisrc.sinmh.SinMH
import eu.kanade.tachiyomi.source.model.SChapter
import org.jsoup.nodes.Document

// This site blocks IP outside China
class YKMH : SinMH("优酷漫画", "https://www.ykmh.net") {
    override val id = 1637952806167036168

    override fun mangaDetailsParse(document: Document) = mangaDetailsParseDMZJStyle(document, hasBreadcrumb = false)

    override fun List<SChapter>.sortedDescending() = this
}
