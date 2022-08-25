package com.example.shopping.test.factory

import com.example.core_testing.factory.DataFactory.randomFloat
import com.example.core_testing.factory.DataFactory.randomInt
import com.example.shopping.data.model.ReviewInformationEntity
import com.example.shopping.data.model.ReviewSummaryEntity
import com.example.shopping.domain.model.ReviewInformation
import com.example.shopping.domain.model.ReviewSummary
import com.example.shopping.remote.model.ReviewInformationRemote
import com.example.shopping.remote.model.ReviewSummaryRemote

object ReviewInformationFactory {

    fun makeReviewInformation() = ReviewInformation(
        reviewSummary = ReviewSummary(
            reviewAverage = randomFloat(),
            reviewCount = randomInt()
        )
    )
    fun makeReviewInformationEntity() = ReviewInformationEntity(
        reviewSummaryEntity = ReviewSummaryEntity(
            reviewAverage = randomFloat(),
            reviewCount = randomInt()
        )
    )
    fun makeReviewInformationRemote() = ReviewInformationRemote(
        reviewSummaryRemote = ReviewSummaryRemote(
            reviewAverage = randomFloat(),
            reviewCount = randomInt()
        )
    )
}
