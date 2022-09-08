package com.jaehyeon.sharedpreferencehelpertest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.jaehyeon.sharedpreferencehelpertest.R
import com.jaehyeon.sharedpreferencehelpertest.databinding.FragmentMainBinding
import com.jaehyeon.sharedpreferencehelpertest.util.EventObserver
import com.jaehyeon.sharedpreferencehelpertest.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Jaehyeon on 2022/09/08.
 */
@AndroidEntryPoint
class MainFragment: Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        binding.button.setOnClickListener {
            MainBottomSheet(viewModel.getWeight()) { value ->
                viewModel.setWeightLocalStorage(value)
            }.also {
                if (it.isAdded || it.isVisible) it.dismiss()
                childFragmentManager.beginTransaction().add(it, "TAG").commitNowAllowingStateLoss()
            }
        }

        // Test
        binding.button.setOnLongClickListener {
            viewModel.clearLocalStorage()
            true
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.error.observe(viewLifecycleOwner, EventObserver {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        })

        viewModel.success.observe(viewLifecycleOwner, EventObserver {
            Snackbar.make(binding.root, "성공 했으니 다음 화면으로 이동.", Snackbar.LENGTH_SHORT).show()
        })
    }

}