package devx.app.datalistapp.data.repository

import devx.app.datalistapp.model.home.MyData


interface Repository {

    suspend fun getData(type: String): List<MyData>

}