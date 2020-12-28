package devx.app.datalistapp.di

import devx.app.datalistapp.data.repository.RepositoryImpl
import devx.app.datalistapp.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {// <Repository> RepositoryImpl <- RemoteDataSource
    single<Repository> { RepositoryImpl(remoteDataSource = get()) }
}