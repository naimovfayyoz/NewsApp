package uz.direction.news.retrofit

import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import uz.direction.news.Resource
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class FlowResourceCallAdapterFactory(
    private val isSelfExceptionHandling: Boolean = false
) : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ) = when (getRawType(returnType)) {
        Call::class.java -> {
            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            when (getRawType(callType)) {
                Resource::class.java -> {
                    val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                    ResourceCallAdapter<Any>(resultType)
                }
                else -> null
            }
        }
        Flow::class.java -> {
            val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
            val rawObservableType = getRawType(observableType)
            require(rawObservableType == Resource::class.java) { "type must be a resource" }
            require(observableType is ParameterizedType) { "resource must be parameterized" }
            val bodyType = getParameterUpperBound(0, observableType)
            FlowResourceCallAdapter<Any>(bodyType, isSelfExceptionHandling)
        }
        else -> null
    }
}