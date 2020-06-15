package com.toninelli.ton_store.ui.main.search

import androidx.lifecycle.ViewModel
import com.toninelli.ton_store.business.TestUseCase
import com.toninelli.ton_store.business.exec

class SearchViewModel (private val testUseCase: TestUseCase): ViewModel() {

    init {
       exec(testUseCase)
    }

    val testResult = testUseCase.result
}