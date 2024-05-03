package com.mainapp.mainapp.utils
import com.mainapp.domain.model.Character


fun Character.getImageUrl(): String{
    return thumbnail?.path?.replace(
        "http",
        "https"
    ) + "." + thumbnail?.extension
}