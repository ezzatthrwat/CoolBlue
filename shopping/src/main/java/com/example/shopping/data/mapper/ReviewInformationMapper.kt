package com.example.shopping.data.mapper

import com.example.core.mapper.Mapper
import com.example.shopping.data.model.ReviewInformationEntity
import com.example.shopping.domain.model.ReviewInformation
import javax.inject.Inject

class ReviewInformationMapper @Inject constructor(
    private val reviewSummaryMapper: ReviewSummaryMapper
) : Mapper<ReviewInformationEntity, ReviewInformation> {

    override fun map(input: ReviewInformationEntity): ReviewInformation {
       return ReviewInformation(
           reviewSummary = reviewSummaryMapper.map(input.reviewSummaryEntity)
       )
    }
}
