package devx.app.datalistapp.ui.home

import androidx.lifecycle.MutableLiveData
import devx.app.datalistapp.base.BaseViewModel
import devx.app.datalistapp.data.repository.Repository
import devx.app.datalistapp.model.home.MyData

class HomeViewModel(val type: String, private val repository: Repository) : BaseViewModel() {

    var dataList = MutableLiveData<List<MyData>>()

    init {
        getData()
    }

    fun getData() {
        showLoading()
        hideRetryBtn()
        hideNoData()
        launchViewModelScope {
            dataList.postValue(
                handle {
                    repository.getData(type)
                }
            )
            hideLoading()
        }
    }

}