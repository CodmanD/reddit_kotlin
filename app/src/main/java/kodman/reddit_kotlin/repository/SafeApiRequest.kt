package kodman.reddit_kotlin.repository


import android.util.Log

import retrofit2.Response
import java.io.IOException


abstract class SafeApiRequest {

    suspend fun<Top> apiRequest(call: suspend () -> Response<Top>) : Top{
        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        }else{
            //@todo handle api exception
            throw ApiException(response.code().toString())
        }
    }

}

class ApiException(message: String): IOException(message)