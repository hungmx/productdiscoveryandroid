package com.mxhung.productdiscoveryandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mxhung.productdiscoveryandroid.di.Injectable
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val searchViewModel: SearchViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchViewModel.results
        searchViewModel.results.observe(this, Observer { result ->
            Log.d("hungmx", "${result?.data}")
        })
    }
}
