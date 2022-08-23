package com.example.shopping.remote.mapper

import com.example.core.exception.EssentialParam
import com.example.core.exception.requireEssentialParams
import com.example.core.mapper.Mapper
import com.example.shopping.data.model.ReviewInformationEntity
import com.example.shopping.remote.model.ReviewInformationRemote
import javax.inject.Inject

class ReviewInformationEntityMapper @Inject constructor(
    private val reviewSummaryEntityMapper: ReviewSummaryEntityMapper
) : Mapper<ReviewInformationRemote?, ReviewInformationEntity?> {

    override fun map(input: ReviewInformationRemote?): ReviewInformationEntity? {

       return input?.let {
           assertEssentialParams(it)

           ReviewInformationEntity(
               reviewSummaryEntity = reviewSummaryEntityMapper.map(it.reviewSummaryRemote!!)
           )
       }
    }

    private fun assertEssentialParams(remote: ReviewInformationRemote) {
        val essentialParams = listOf(
            EssentialParam(remote.reviewSummaryRemote, "reviewSummaryRemote"),
        )

        requireEssentialParams(rawObject = remote, essentialParams = essentialParams)
    }
}