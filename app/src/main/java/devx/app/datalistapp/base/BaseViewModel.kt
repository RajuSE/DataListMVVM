package devx.app.datalistapp.base
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import devx.app.datalistapp.base.ToastUtil.Companion.showShort
import devx.app.datalistapp.data.service.MyInterceptor
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.net.UnknownHostException
import kotlin.coroutines.coroutineContext

abstract class BaseViewModel : ViewModel() {

    open fun launchViewModelScope(doWork: suspend () -> Unit) : Job {
        return viewModelScope.launch(viewModelScope.coroutineContext + Dispatchers.IO) {
            doWork()
        }
    }

    open val isLoading = MutableLiveData(View.GONE)
    open fun showLoading() = isLoading.postValue(View.VISIBLE)
    open fun hideLoading() = isLoading.postValue(View.GONE)

    open val isNoData = MutableLiveData(View.GONE)
    open fun showNoData() = isNoData.postValue(View.VISIBLE)
    open fun hideNoData() = isNoData.postValue(View.GONE)

    open val isRefreshing = MutableLiveData(View.GONE)
    open fun showRefreshing() = isRefreshing.postValue(View.VISIBLE)
    open fun stopRefreshing() = isRefreshing.postValue(View.GONE)

    open fun onError(t: Throwable) {
        viewModelScope.launch {
            when (t) {
                is MyInterceptor.NoConnectivityException ->
                    showShort("No internet")
                is HttpException -> showShort("Unable to connect to server")
                is UnknownHostException -> {
                    showShort("Unable to connect to server. Please check your internet connection.")
                }else -> {
                    showShort("Something went wrong")
                    Log.e("ERROR", "${t.message}")
                }
            }
            hideLoading()
        }
    }

    suspend fun <T> handle(call: suspend () -> T): T? {
        return withContext(CoroutineScope(coroutineContext).coroutineContext) {
            call.runCatching { this.invoke() }
                .getOrElse {
                    onError(it)
                    null
                }
        }
    }

}