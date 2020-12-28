package devx.app.datalistapp.data.remote

import devx.app.datalistapp.data.service.DataApiService
import devx.app.datalistapp.model.home.MyData

class RemoteDataSourceImpl(private val service: DataApiService) : RemoteDataSource {
    override suspend fun getData(type: String): List<MyData> {
        return service.getData(type)
    }
}