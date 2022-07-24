package uz.direction.news.retrofit

import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Call
import retrofit2.CallAdapter
import uz.direction.news.Resource
import java.lang.reflect.Type

class ResourceCallAdapter<R>(
    private val responseType: Type
) : CallAdapter<R, Call<Resource<R>>> {

    override fun responseType() = responseType

    @ExperimentalCoroutinesApi
    override fun adapt(call: Call<R>) = ResultCall(call)
}