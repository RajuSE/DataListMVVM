package devx.app.datalistapp.base
import android.app.Application
import android.content.Context
import devx.app.datalistapp.di.networkModule
import devx.app.datalistapp.di.remoteDataSourceModule
import devx.app.datalistapp.di.repositoryModule
import devx.app.datalistapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class DataListApp : Application() {

    companion object {
        lateinit var applicationCtx: Context
    }

    override fun onCreate() {
        super.onCreate()
        applicationCtx = applicationContext
        startKoin {
            androidContext(this@DataListApp)
            modules(remoteDataSourceModule)
            modules(repositoryModule)
            modules(viewModelModule)
            modules(networkModule)
        }
    }

}