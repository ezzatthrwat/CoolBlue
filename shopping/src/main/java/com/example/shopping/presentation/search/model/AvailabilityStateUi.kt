package com.example.shopping.presentation.search.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.shopping.R

enum class AvailabilityStateUi(
    @DrawableRes val icon: Int,
    @StringRes val text: Int,
    @ColorRes val textColor: Int
) {
    AVAILABLE(R.drawable.ic_check, R.string.delivered_tomorrow, com.example.core.R.color.green),
    SOLD_OUT(-1, R.string.temporarily_sold_out, R.color.gray)
}
