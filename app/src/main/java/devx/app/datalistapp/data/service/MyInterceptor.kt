package devx.app.datalistapp.data.service

import android.content.Context
import android.util.Log
import devx.app.datalistapp.base.InternetUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

open class MyInterceptor constructor(
    private val context: Context
) : Interceptor {

    val tag = "MyInterceptor::"

    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!InternetUtil.isConnectionOn(context)) {
            Log.i(tag, "noconnect")
            throw NoConnectivityException()
        } else {
            Log.i(tag, "making api call")
            chain.proceed(chain.request())
        }
    }

    class NoConnectivityException : IOException() {
        override val message: String
            get() = "NoConnectivityException"
    }
}