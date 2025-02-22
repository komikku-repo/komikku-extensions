package eu.kanade.tachiyomi.extension.fr.flamescansfr
import keiyoushi.utils.getPreferencesLazy
import keiyoushi.utils.getPreferences
import keiyoushi.utils.parseAs
import keiyoushi.utils.tryParse
import keiyoushi.utils.firstInstance
import keiyoushi.utils.firstInstanceOrNull

import eu.kanade.tachiyomi.source.model.Filter

class SelectFilter(name: String, val field: String, values: Array<String>) :
    Filter.Select<String>(name, values) {
    fun selectedValue() = values[state]
}

class GenreCheckBox(name: String) : Filter.CheckBox(name)

class GenreList(title: String, genres: List<String>) : Filter.Group<GenreCheckBox>(
    title,
    genres.map(::GenreCheckBox),
)

val typesList = arrayOf("", "Manga", "Manhua", "Manhwa", "One shot")

val statusList = arrayOf("", "En cours", "Terminé", "Annulé", "En pause", "Abandonné")

val genresList = listOf(
    "Action",
    "Aventure",
    "Arts Martiaux",
    "Combat",
    "Comédie",
    "Drame",
    "Fantastique",
    "Science-fiction",
    "Shonen",
    "Amitié",
    "Amour",
    "Romance",
    "Shôjo",
    "Tranches de vie",
    "Harem",
    "Surnaturel",
    "Guerre",
    "Mystère",
    "Fantaisie",
    "Psychologique",
    "Mature",
    "Tragédie",
    "Webtoons",
    "Seinen",
    "Historique",
    "Vie scolaire",
    "magie",
    "One Shot",
    "Shôjo Ai",
    "Ecchi",
    "Horreur",
    "Gender Bender",
    "Adulte",
    "Josei",
    "Returner",
    "comedy",
    "Sports",
    "Monstres",
    "Realité Virtuel",
    "Mecha",
    "isekaï",
    "Jeux vidéo",
    "Inconnu",
    "Doujinshi",
    "Policier",
    "Réincarnation",
    "Yuri",
    "Sport",
    "crime",
    "Gangster",
    "Police",
    "Organisation secrète",
    "Magical Girls",
    "Bromance",
    "Adventure",
    "Necromancer",
    "Shônen Ai",
    "Boxe",
    "Parodie",
    "Hentai",
    "4-koma",
    "Voyage Temporel",
    "vampires",
    "Super héros",
    "A",
    "c",
    "t",
    "i",
    "o",
    "n",
    ",",
    " ",
    "r",
    "s",
    "M",
    "a",
    "u",
    "x",
    "v",
    "e",
    "C",
    "m",
    "b",
    "S",
    "h",
    "Smut",
    "démons",
    "Virtuel world",
    "Vengeance",
)
