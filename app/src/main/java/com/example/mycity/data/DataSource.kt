package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.CategoryInfo
import com.example.mycity.model.PlaceInfo

object DataSource {
    val defaultCategory = getCategoryData()[0]

    fun getPlaces(categoryIndex: Int): CategoryInfo {
        return getCategoryData()[categoryIndex]
    }

    fun getCategoryData(): List<CategoryInfo> {
        return listOf(
            CategoryInfo(
                title = R.string.coffee_category,
                places = listOf(
                    PlaceInfo(
                        name = R.string.blackberry,
                        details = R.string.blackberry_details,
                        image = R.drawable.bb
                    ),
                    PlaceInfo(
                        name = R.string.tvoya_chashka,
                        details = R.string.chaska_details,
                        image = R.drawable.i
                    ),
                    PlaceInfo(
                        name = R.string.coffee_people,
                        details = R.string.coffee_people_details,
                        image = R.drawable.cf
                    ),
                    PlaceInfo(
                        name = R.string.garage,
                        details = R.string.garage_description,
                        image = R.drawable.s
                    )
                )
            ),
            CategoryInfo(
                title = R.string.landmarks_category,
                places = listOf(
                    PlaceInfo(
                        name = R.string.botanGarden,
                        details = R.string.botanGarden_details,
                        image = R.drawable.bs
                    ),
                    PlaceInfo(
                        name = R.string.chaes,
                        details = R.string.chaes_details,
                        image = R.drawable.chaes
                    ),
                    PlaceInfo(
                        name = R.string.han,
                        details = R.string.han_details,
                        image = R.drawable.han
                    ),
                    PlaceInfo(
                        name = R.string.lenin,
                        details = R.string.lenin_details,
                        image = R.drawable.len
                    )
                )
            ),
            CategoryInfo(
                title = R.string.clubs_category,
                places = listOf(
                    PlaceInfo(
                        name = R.string.Crystal,
                        details = R.string.Crystal_det,
                        image = R.drawable.xxl_height
                    ),
                    PlaceInfo(
                        name = R.string.Forsage,
                        details = R.string.Forsage_det,
                        image = R.drawable.orig
                    ),
                    PlaceInfo(
                        name = R.string.Plazma,
                        details = R.string.Plazma_det,
                        image = R.drawable.xxl
                    )
                )
            ),
        )
    }
}