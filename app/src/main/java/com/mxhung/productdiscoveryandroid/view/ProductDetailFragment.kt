package com.mxhung.productdiscoveryandroid.view

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.mxhung.productdiscoveryandroid.R
import com.mxhung.productdiscoveryandroid.adapter.CustomPagerAdapter
import com.mxhung.productdiscoveryandroid.adapter.ProductAdapter
import com.mxhung.productdiscoveryandroid.adapter.ProductHorizontalAdapter
import com.mxhung.productdiscoveryandroid.adapter.SectionsPagerAdapter
import com.mxhung.productdiscoveryandroid.binding.FragmentDataBindingComponent
import com.mxhung.productdiscoveryandroid.databinding.FragmentProductDetailBinding
import com.mxhung.productdiscoveryandroid.di.Injectable
import com.mxhung.productdiscoveryandroid.model.Products
import com.mxhung.productdiscoveryandroid.util.autoCleared
import com.mxhung.productdiscoveryandroid.viewmodel.ProductDetailViewModel
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class ProductDetailFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val productDetailViewModel: ProductDetailViewModel by viewModels {
        viewModelFactory
    }
    var binding by autoCleared<FragmentProductDetailBinding>()
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    private val params by navArgs<ProductDetailFragmentArgs>()
    private var isLastPage: Boolean = false
    private val adapter = ProductHorizontalAdapter()
    private val images: IntArray = intArrayOf(
        R.drawable.tivi_lg,
        R.drawable.tivi_samsung,
        R.drawable.ic_thumnail_default
    )
    private lateinit var dots: Array<TextView?>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding = DataBindingUtil.inflate<FragmentProductDetailBinding>(
            inflater,
            R.layout.fragment_product_detail,
            container,
            false,
            dataBindingComponent
        )
        binding = dataBinding
        binding.rlSameType.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addBottomDots(0)
        binding.args = params
        productDetailViewModel.setProductSku(params.sku.toString())
        activity?.let {
            binding.viewPager.adapter = CustomPagerAdapter(it, images)

            val sectionsPagerAdapter = SectionsPagerAdapter(it, it.supportFragmentManager)
            binding.viewPagerInformation.adapter = sectionsPagerAdapter
            binding.tabs.setupWithViewPager(binding.viewPagerInformation)
        }

        productDetailViewModel.results.observe(viewLifecycleOwner, Observer {result ->
            Log.d("hungmx ", "ProductDetailFragment ${result.data}")
            binding.product = result.data
            val productListTest = mutableListOf<Products>()
            result.data?.let {
                for (product in 1..10){
                    productListTest.add(it)
                }
            }
            adapter.submitList(productListTest)
        })

        binding.viewPager.addOnPageChangeListener(
            object : ViewPager.OnPageChangeListener {
                override fun onPageSelected(position: Int) {
                    addBottomDots(position)
                }

                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                }

                override fun onPageScrollStateChanged(p0: Int) {
                    if (p0 == ViewPager.SCROLL_STATE_IDLE)
                        isLastPage = binding.viewPager.currentItem == images.size - 1
                }
            }
        )

        binding.imgBack.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun addBottomDots(currentPage: Int) {
        dots = arrayOfNulls(images.size)
        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInactive = resources.getIntArray(R.array.array_dot_inactive)
        binding.layoutDots.removeAllViews()
        for (i in dots.indices) {
            activity?.let {
                dots[i] = TextView(it)
                dots[i]?.text = Html.fromHtml("&#8226;")
                dots[i]?.textSize = 35F
                dots[i]?.setTextColor(colorsInactive[currentPage])
                binding.layoutDots.addView(dots.get(i))
            }

        }
        if (dots.isNotEmpty()) dots[currentPage]?.setTextColor(colorsActive[currentPage])
    }

}
