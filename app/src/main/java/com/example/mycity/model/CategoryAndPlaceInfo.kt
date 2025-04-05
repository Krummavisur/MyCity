package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CategoryInfo (
    @StringRes val title: Int,
    @DrawableRes val categoryImage: Int,
    val places: List<PlaceInfo>,
)

data class PlaceInfo(
    @StringRes val name: Int,
    @StringRes val details: Int,
    @DrawableRes val image: Int
)