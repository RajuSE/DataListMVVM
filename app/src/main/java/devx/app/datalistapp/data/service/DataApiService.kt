package devx.app.datalistapp.data.service

import devx.app.datalistapp.model.home.MyData
import retrofit2.http.GET
import retrofit2.http.Path

interface DataApiService {

    @GET("v1/{type}")
    suspend fun getData(@Path("type") type: String): List<MyData>

}