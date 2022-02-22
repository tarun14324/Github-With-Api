package com.example.githubsearch.utilities

class TextTitle(
    val text: String,
    val title: String,
    val name: String
) {
    data class Builder(
        var text: String = "",
        var title: String = "",
        var name: String = ""
    ) {
        fun Title(myTitle: String) = apply {
            this.title = myTitle
        }

        fun Text(myText: String) = apply {
            this.text = myText
        }

        fun Name(name: String) = apply {
            this.name = name
        }

        fun build(): TextTitle {
            return TextTitle(text, title, name)
        }
    }
}


