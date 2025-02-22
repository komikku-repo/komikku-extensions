package eu.kanade.tachiyomi.extension.all.comicfury
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import eu.kanade.tachiyomi.source.Source
import eu.kanade.tachiyomi.source.SourceFactory

class ComicFuryFactory : SourceFactory {
    override fun createSources(): List<Source> = listOf(
        ComicFury("all"),
        ComicFury("en"),
        ComicFury("es"),
        ComicFury("pt-BR", "pt"),
        ComicFury("de"),
        ComicFury("fr"),
        ComicFury("it"),
        ComicFury("pl"),
        ComicFury("ja"),
        ComicFury("zh"),
        ComicFury("ru"),
        ComicFury("fi"),
        ComicFury("other"),
        ComicFury("other", "notext", " (No Text)"),
    )
}
