package com.hnrylvo.inmomarket.data.repository

import com.hnrylvo.inmomarket.data.utils.ApiResult
import com.hnrylvo.inmomarket.data.utils.Failure
import com.hnrylvo.inmomarket.data.utils.HttpError
import com.hnrylvo.inmomarket.data.utils.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {

    /**
     * Use this when communicating only with the api service
     */
    protected suspend fun <T : Any> fetchData(
        dataProvider: suspend () -> T
    ): Flow<ApiResult<T>> = invokeDataProvider(dataProvider)


    private suspend fun <T : Any> invokeDataProvider(dataProvider: suspend () -> T) =
        flow<ApiResult<T>> {
            try {
                emit(Success(dataProvider()))
            } catch (ex: IOException) {
                emit(Failure(HttpError(ex)))
            } catch (ex: HttpException) {
                emit(Failure(HttpError(ex, ex.code())))
            } catch (ex: Exception) {
                emit(Failure(HttpError(ex)))
            }
        }

}
