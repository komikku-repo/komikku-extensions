package eu.kanade.tachiyomi.extension.pt.hqnow
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HqNowComicBookDto(
    @SerialName("capitulos") val chapters: List<HqNowChapterDto> = emptyList(),
    @SerialName("hqCover") val cover: String? = "",
    val id: Int,
    val name: String,
    val publisherName: String? = "",
    val status: String? = "",
    val synopsis: String? = "",
)

@Serializable
data class HqNowChapterDto(
    val id: Int = 0,
    val name: String,
    val number: String,
    val pictures: List<HqNowPageDto> = emptyList(),
)

@Serializable
data class HqNowPageDto(
    val pictureUrl: String,
)
