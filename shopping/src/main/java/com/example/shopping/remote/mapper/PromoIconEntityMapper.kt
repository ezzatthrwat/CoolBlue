package com.example.shopping.remote.mapper

import com.example.core.exception.EssentialParam
import com.example.core.exception.requireEssentialParams
import com.example.core.mapper.Mapper
import com.example.shopping.data.model.PromoIconEntity
import com.example.shopping.remote.model.PromoIconRemote
import javax.inject.Inject

class PromoIconEntityMapper @Inject constructor() : Mapper<PromoIconRemote?, PromoIconEntity?> {

    override fun map(input: PromoIconRemote?): PromoIconEntity? {
        return input?.let {
            assertEssentialParams(it)
            PromoIconEntity(
                text = it.text!!,
                type = it.type!!
            )
        }
    }

    private fun assertEssentialParams(remote: PromoIconRemote) {

        val essentialParams = listOf(
            EssentialParam(remote.text, "text"),
            EssentialParam(remote.type, "type"),
        )

        requireEssentialParams(rawObject = remote, essentialParams = essentialParams)
    }
}
