package com.example.shopping.remote.mapper

import com.example.core.exception.EssentialParam
import com.example.core.exception.requireEssentialParams
import com.example.core.mapper.Mapper
import com.example.shopping.data.model.ReviewSummaryEntity
import com.example.shopping.remote.model.ReviewSummaryRemote
import javax.inject.Inject

class ReviewSummaryEntityMapper @Inject constructor() :
    Mapper<ReviewSummaryRemote, ReviewSummaryEntity> {

    override fun map(input: ReviewSummaryRemote): ReviewSummaryEntity {

        assertEssentialParams(input)

        return ReviewSummaryEntity(
            reviewAverage = input.reviewAverage!!,
            reviewCount = input.reviewCount!!
        )
    }

    private fun assertEssentialParams(remote: ReviewSummaryRemote) {

        val essentialParams = listOf(
            EssentialParam(remote.reviewAverage, "reviewAverage"),
            EssentialParam(remote.reviewCount, "reviewCount"),
        )

        requireEssentialParams(rawObject = remote, essentialParams = essentialParams)
    }
}
