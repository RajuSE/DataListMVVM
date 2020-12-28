package devx.app.datalistapp.di

import devx.app.datalistapp.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {// HomeViewModel(String) <- Repository
    viewModel { (type: String) ->
        HomeViewModel(type, repository = get())
    }
}