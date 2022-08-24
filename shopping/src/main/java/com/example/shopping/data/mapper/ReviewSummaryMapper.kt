package com.example.shopping.data.mapper

import com.example.core.mapper.Mapper
import com.example.shopping.data.model.ReviewSummaryEntity
import com.example.shopping.domain.model.ReviewSummary
import javax.inject.Inject

class ReviewSummaryMapper @Inject constructor() :
    Mapper<ReviewSummaryEntity, ReviewSummary> {

    override fun map(input: ReviewSummaryEntity): ReviewSummary {
        return ReviewSummary(
            reviewAverage = input.reviewAverage,
            reviewCount = input.reviewCount
        )
    }
}
