package devx.app.datalistapp.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import devx.app.datalistapp.R
import devx.app.datalistapp.base.BaseActivity
import devx.app.datalistapp.base.DummyUtil
import devx.app.datalistapp.base.ToastUtil
import devx.app.datalistapp.databinding.ActivityHomeBinding
import devx.app.datalistapp.model.home.MyData
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeActivity : BaseActivity() {
    private val binding: ActivityHomeBinding by binding(R.layout.activity_home)
    var items: MutableList<MyData> = mutableListOf()
    private val successType by lazy { "19d74f05" }
    private val emptyResponseTest by lazy { "95a52980" }// 500 Internal Server ERR :: EMPTY ARRAY

    private val viewModel: HomeViewModel by viewModel() { parametersOf(successType) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@HomeActivity
            homeViewModel = this@HomeActivity.viewModel
        }

        viewModel.dataList.removeObservers(this)
        viewModel.dataList.observe(this@HomeActivity,
            Observer<List<MyData>> { dataList ->
                if (dataList.isNullOrEmpty()) {
                    isNoData()
                } else {
                    viewModel.hideNoData()
                    items = dataList as MutableList<MyData>
                    binding.homeAdapter = HomeAdapter({ dataTextInfoString ->
                        Toast.makeText(baseContext, dataTextInfoString, Toast.LENGTH_SHORT)
                            .show()
                    }, items)
                }
            })
    }

    private fun isNoData() {
        binding.homeAdapter?.let {
            if (binding.homeAdapter!!.itemCount != 0) return
        }
        viewModel.hideLoading()
        viewModel.stopRefreshing()
        viewModel.showNoData()
    }
}