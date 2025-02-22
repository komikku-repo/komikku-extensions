package eu.kanade.tachiyomi.extension.id.katakomik
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import eu.kanade.tachiyomi.multisrc.mangathemesia.MangaThemesia

class KataKomik : MangaThemesia("KataKomik", "https://katakomik.my.id", "id")
