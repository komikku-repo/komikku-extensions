package eu.kanade.tachiyomi.extension.en.mangahereonl
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import eu.kanade.tachiyomi.multisrc.mangahub.MangaHub

class MangaHereOnl : MangaHub("MangaHere.onl", "https://mangahere.onl", "en", "mh01")
