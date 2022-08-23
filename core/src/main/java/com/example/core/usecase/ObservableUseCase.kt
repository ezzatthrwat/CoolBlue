package com.example.core.usecase

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.observers.DisposableObserver

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a [DisposableObserver]
 * that will execute its job in a background thread and will post the result in the UI thread.
 *
 * This use case is to be used when we expect multiple values to be emitted via an [Observable].
 */
abstract class ObservableUseCase<Results, in Params>(
    private val threadExecutor: Scheduler,
    private val postExecutionThread: Scheduler
) {

    /**
     * Builds an [Observable] which will be used when executing the current [ObservableUseCase].
     */
    abstract fun buildUseCaseObservable(params: Params? = null): Observable<Results>

    /**
     * Executes the current use case.
     *
     * @param params   Parameters (Optional) used to build/execute this use case.
     */
    open fun execute(params: Params? = null): Observable<Results> {
        return buildUseCaseObservable(params)
            .subscribeOn(threadExecutor)
            .observeOn(postExecutionThread)
    }
}
