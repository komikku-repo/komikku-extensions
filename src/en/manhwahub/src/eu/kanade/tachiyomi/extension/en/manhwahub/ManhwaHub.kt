package eu.kanade.tachiyomi.extension.en.manhwahub
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import eu.kanade.tachiyomi.multisrc.manhwaz.ManhwaZ

class ManhwaHub : ManhwaZ(
    "ManhwaHub",
    "https://manhwahub.net",
    "en",
)
