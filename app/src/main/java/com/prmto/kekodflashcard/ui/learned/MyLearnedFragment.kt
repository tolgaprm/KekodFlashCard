package com.prmto.kekodflashcard.ui.learned

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.databinding.FragmentMyLearnedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyLearnedFragment : Fragment(R.layout.fragment_my_learned) {

    private var _binding: FragmentMyLearnedBinding? = null
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMyLearnedBinding.bind(view)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}