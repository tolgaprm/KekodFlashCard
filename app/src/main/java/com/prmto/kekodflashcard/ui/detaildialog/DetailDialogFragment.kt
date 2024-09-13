package com.prmto.kekodflashcard.ui.detaildialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.databinding.FragmentDialogDetailBinding

class DetailDialogFragment : DialogFragment(R.layout.fragment_dialog_detail) {

    private var _binding: FragmentDialogDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailDialogFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDialogDetailBinding.bind(view)
        setupView()
        setClickListeners()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    private fun setupView() {
        val wordUiItem = args.wordUiItem

        binding.apply {
            ivWordImage.load(wordUiItem.imageUrl)
            tvEnglishWord.text = wordUiItem.englishWord
            tvTurkishMean.text = wordUiItem.turkishMean
            tvFrenchMean.text = wordUiItem.frenchMean
        }
    }


    private fun setClickListeners() {
        binding.cvListenEnglish.setOnClickListener {

        }

        binding.cvListenFrench.setOnClickListener {

        }

        binding.cvLearned.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}