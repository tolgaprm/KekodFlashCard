package com.prmto.kekodflashcard.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.databinding.FragmentOnboardingItemBinding
import com.prmto.kekodflashcard.ui.onboarding.model.OnboardingItem

class OnboardingItemFragment : Fragment(R.layout.fragment_onboarding_item) {

    private lateinit var _binding: FragmentOnboardingItemBinding
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnboardingItemBinding.bind(view)
        getArgs()
    }


    fun getArgs() {
        arguments?.let {
            val onboardingItem = BundleCompat.getParcelable(
                it,
                OnboardingArgumentKey.ONBOARDING,
                OnboardingItem::class.java
            )
            binding.tvTitle.text = onboardingItem?.title?.asString(requireContext())
            binding.tvDescription.text = onboardingItem?.description?.asString(requireContext())
            binding.lottieAnimationView.setAnimationFromUrl(onboardingItem?.lottieUrl)
        }
    }

    override fun onResume() {
        binding.lottieAnimationView.playAnimation()
        super.onResume()
    }

    override fun onPause() {
        binding.lottieAnimationView.pauseAnimation()
        super.onPause()
    }

    companion object {
        fun newInstance(onboardingItem: OnboardingItem) = OnboardingItemFragment().apply {
            arguments = Bundle().apply {
                putParcelable(OnboardingArgumentKey.ONBOARDING, onboardingItem)
            }
        }
    }
}