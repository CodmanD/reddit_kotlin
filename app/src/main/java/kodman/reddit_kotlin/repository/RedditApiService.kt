package kodman.reddit_kotlin.repository


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


import kodman.reddit_kotlin.model.Top


interface RedditApiService {

    @GET("top.json")
    suspend fun getPosts(): Response<Top>

    /**
     * https://www.reddit.com/top.json
     */

    companion object {
        operator fun invoke(): RedditApiService {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.reddit.com/")
                .build()
                .create(RedditApiService::class.java)
        }
    }

}