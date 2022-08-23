package com.example.shopping.data.mapper

import com.example.core.mapper.Mapper
import com.example.shopping.data.model.PromoIconEntity
import com.example.shopping.domain.model.PromoIcon
import javax.inject.Inject

class PromoIconMapper @Inject constructor() : Mapper<PromoIconEntity?, PromoIcon?> {

    override fun map(input: PromoIconEntity?): PromoIcon? {
       return input?.let {
           PromoIcon(
                text = it.text,
                type = it.type
            )
        }
    }
}
