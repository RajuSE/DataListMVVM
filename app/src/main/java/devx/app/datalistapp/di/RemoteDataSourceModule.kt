package devx.app.datalistapp.di

import devx.app.datalistapp.data.remote.RemoteDataSource
import devx.app.datalistapp.data.remote.RemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module { // <RemoteDataSource>RemoteDataSourceImpl <- DataApiService
    single<RemoteDataSource> { RemoteDataSourceImpl(service = get()) }
}