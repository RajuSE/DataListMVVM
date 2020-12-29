package devx.app.datalistapp.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import devx.app.datalistapp.R
import devx.app.datalistapp.base.BaseActivity
import devx.app.datalistapp.base.InternetUtil
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

            val dividerItemDecoration = DividerItemDecoration(
                homeRecyclerView!!.getContext(),
                LinearLayoutManager.VERTICAL
            )
            if (homeRecyclerView.itemDecorationCount == 0)
                homeRecyclerView.addItemDecoration(dividerItemDecoration)

            retryButton.setOnClickListener {
                viewModel.getData()
            }
        }


        viewModel.dataList.removeObservers(this)
        viewModel.dataList.observe(this@HomeActivity,
            Observer<List<MyData>> { dataList ->
                if (dataList.isNullOrEmpty()) {
                    isNoData()
                } else {
                    viewModel.hideNoData()
                    viewModel.hideRetryBtn()
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
        viewModel.showRetryBtn()
        if (!InternetUtil.isConnectionOn(baseContext)) return
        viewModel.showNoData()
    }
}