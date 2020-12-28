package devx.app.datalistapp.data.repository

import devx.app.datalistapp.data.remote.RemoteDataSource

class RepositoryImpl(val remoteDataSource: RemoteDataSource) : Repository {

    override suspend fun getData(type: String) = remoteDataSource.getData(type);
}