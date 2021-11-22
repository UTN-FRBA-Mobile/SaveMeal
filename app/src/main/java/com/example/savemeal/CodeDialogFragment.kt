package com.example.savemeal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.savemeal.databinding.FragmentCodePopupBinding
import com.example.savemeal.databinding.FragmentProductViewBinding

class CodeDialogFragment: DialogFragment() {

    private var _binding: FragmentCodePopupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCodePopupBinding.inflate(inflater, container, false)
        return binding.root
    }
}