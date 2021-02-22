package kodman.reddit_kotlin.repository


class TopRepository(
    private val api: RedditApiService
) : SafeApiRequest() {

    suspend fun getPosts() = apiRequest { api.getPosts() }

}