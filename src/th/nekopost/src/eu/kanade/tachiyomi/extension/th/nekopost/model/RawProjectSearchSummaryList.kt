package eu.kanade.tachiyomi.extension.th.nekopost.model
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import kotlinx.serialization.Serializable

@Serializable
data class RawProjectSearchSummaryList(
    val listProject: List<RawProjectSearchSummary>,
    val totalRecord: String,
)
