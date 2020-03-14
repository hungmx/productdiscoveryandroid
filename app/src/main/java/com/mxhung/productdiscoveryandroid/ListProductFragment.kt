package com.mxhung.productdiscoveryandroid

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mxhung.productdiscoveryandroid.binding.FragmentDataBindingComponent
import com.mxhung.productdiscoveryandroid.databinding.FragmentListProductBinding
import com.mxhung.productdiscoveryandroid.di.Injectable
import com.mxhung.productdiscoveryandroid.util.autoCleared
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ListProductFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val searchViewModel: SearchViewModel by viewModels {
        viewModelFactory
    }
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    var binding by autoCleared<FragmentListProductBinding>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list_product,
            container,
            false,
            dataBindingComponent
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.results.observe(viewLifecycleOwner, Observer {result ->
            Log.d("hungmx ", "${result.data}")
        })
    }
}
