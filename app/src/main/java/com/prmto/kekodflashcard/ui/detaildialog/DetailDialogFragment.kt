package com.prmto.kekodflashcard.ui.detaildialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.databinding.FragmentDialogDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class DetailDialogFragment : DialogFragment(R.layout.fragment_dialog_detail) {

    private var _binding: FragmentDialogDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailDialogFragmentArgs by navArgs()

    private val viewModel: DetailDialogViewModel by viewModels()

    private lateinit var textToSpeech: TextToSpeech

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDialogDetailBinding.bind(view)
        setupView()
        setClickListeners()
        textToSpeech = TextToSpeech(requireContext()) {}
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

            val cvLearnedTextResId = if (args.isFromLearnedFragment) {
                R.string.remove_from_learned
            } else {
                R.string.add_to_learned
            }

            binding.cvLearned.setDescription(getString(cvLearnedTextResId))
        }
    }


    private fun setClickListeners() {
        binding.cvListenEnglish.setOnClickListener {
            if (textToSpeech.isSpeaking) {
                textToSpeech.stop()
            }

            textToSpeech.language = Locale.UK
            textToSpeech.speak(
                args.wordUiItem.englishWord,
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        binding.cvListenFrench.setOnClickListener {
            if (textToSpeech.isSpeaking) {
                textToSpeech.stop()
            }

            textToSpeech.language = Locale.FRENCH
            textToSpeech.speak(
                args.wordUiItem.frenchMean  ,
                TextToSpeech.QUEUE_FLUSH,
                null,
                null
            )
        }

        binding.cvLearned.setOnClickListener {
            viewModel.onLearnedItemClicked(args.wordUiItem, args.isFromLearnedFragment)
            Toast.makeText(
                requireContext(),
                getString(R.string.successfuly_saved), Toast.LENGTH_SHORT
            ).show()
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.stop()
        textToSpeech.shutdown()
    }
}