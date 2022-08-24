package com.example.shopping.domain.usecase

import androidx.annotation.VisibleForTesting
import com.example.core.exception.ParamMissingException
import com.example.core.scheduler.SchedulerProvider
import com.example.core.usecase.ObservableUseCase
import com.example.shopping.domain.model.Product
import com.example.shopping.domain.model.ProductsResult
import com.example.shopping.domain.repository.ProductsRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductsRepository,
    private val schedulerProvider: SchedulerProvider
) : ObservableUseCase<GetProductsUseCase.Result, GetProductsUseCase.Params>(
    schedulerProvider.io(),
    schedulerProvider.ui()
) {

    private lateinit var pagination: BehaviorSubject<Int>

    private lateinit var resultObservable: BehaviorSubject<Result>

    private val results = mutableSetOf<Product>()

    private var page = 1

    var hasMore = false
        private set

    override fun buildUseCaseObservable(params: Params?): Observable<Result> {
        if (params == null) {
            throw ParamMissingException()
        }

        pagination = BehaviorSubject.create()
        resultObservable = BehaviorSubject.create()
        page = 1
        hasMore = false
        results.clear()
        pagination.onNext(page)

        return pagination.concatMap { page ->
            repository.getProducts(
                query = params.searchQuery,
                page = page,
            ).toObservable()
                .map<Result> { result -> Result.Success(result) }
                .onErrorReturn { throwable -> Result.Error(throwable) }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
        }.switchMap {
            if (it is Result.Success) {
                hasMore = it.data.currentPage < it.data.pageCount

                results.addAll(it.data.products)
                resultObservable.onNext(Result.Success(it.data.copy(products = results.toList())))
            } else {
                resultObservable.onNext(it)
            }

            resultObservable
        }
    }

    fun nextPage() {
        page++
        pagination.onNext(page)
    }

    @VisibleForTesting
    fun currentList(): List<Product> {
        return results.toList()
    }

    data class Params(val searchQuery: String)

    sealed class Result {

        data class Success(val data: ProductsResult) : Result()

        data class Error(val throwable: Throwable) : Result()
    }
}
