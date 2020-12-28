package devx.app.datalistapp.di

import devx.app.datalistapp.data.service.DataApiService
import devx.app.datalistapp.data.service.MyInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 20L
private const val WRITE_TIMEOUT = 1L
private const val READ_TIMEOUT = 20L
private const val BASE_URL = "http://api.mocki.io/"

/*

               OkHttpClient -> Retrofit -> DataApiService                                           :: networkModule
 <RemoteDataSource>RemoteDataSourceImpl <- DataApiService                                           :: remoteDataSourceModule
             <Repository>RepositoryImpl <- RemoteDataSource                                         :: repositoryModule
                          HomeViewModel <- Repository                                               :: viewModelModule

*/


val networkModule = module {// OkHttpClient -> Retrofit -> DataApiService

    single {
        OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addNetworkInterceptor(MyInterceptor(androidContext()))
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }

    single {
        Retrofit.Builder()
                .client(get<OkHttpClient>())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    single { get<Retrofit>().create(DataApiService::class.java) }

}