package com.jaehyeon.sharedpreferencehelpertest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jaehyeon.sharedpreferencehelpertest.R
import com.jaehyeon.sharedpreferencehelpertest.databinding.BottomSheetMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Jaehyeon on 2022/09/08.
 */
@AndroidEntryPoint
class MainBottomSheet(
    private val weight: Float,
    val onClick: (String) -> Unit
): BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_main, container, false)

        if (weight != 0.0f)
            binding.appCompatEditText.setText("$weight")

        binding.button2.setOnClickListener {
            onClick(binding.appCompatEditText.text.toString())
            dismiss()
        }
        return binding.root
    }

}