package eu.kanade.tachiyomi.extension.all.comicsvalley
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import eu.kanade.tachiyomi.multisrc.madara.Madara

class ComicsValley : Madara(
    "Comics Valley",
    "https://comicsvalley.com",
    "all",
) {
    override val mangaSubString = "comics-new"
    override val useNewChapterEndpoint = true
    override val useLoadMoreRequest = LoadMoreStrategy.Always
    override val id = 1103204227230640533
}
