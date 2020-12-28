package devx.app.datalistapp.data.remote

import devx.app.datalistapp.model.home.MyData


interface RemoteDataSource {

    suspend fun getData(type: String): List<MyData>

}