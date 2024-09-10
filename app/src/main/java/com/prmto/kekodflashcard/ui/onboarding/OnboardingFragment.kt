package com.prmto.kekodflashcard.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.common.collectEvent
import com.prmto.kekodflashcard.common.collectFlow
import com.prmto.kekodflashcard.databinding.FragmentOnboardingBinding
import com.prmto.kekodflashcard.ui.onboarding.model.OnboardingItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment(R.layout.fragment_onboarding) {

    private lateinit var _binding: FragmentOnboardingBinding
    private val binding get() = _binding

    private val viewModel by viewModels<OnboardingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnboardingBinding.bind(view)
        setBtnListeners()
        collectFlow(viewModel.onboardingItems, ::setViewPager)
        collectEvent(viewModel.viewEvent, ::handleViewEvent)
    }

    private fun handleViewEvent(viewEvent: OnboardingEvent) {
        when (viewEvent) {
            is OnboardingEvent.FinishOnboarding -> {
                findNavController().setGraph(R.navigation.main_nav_graph)
            }
        }
    }

    private fun setBtnListeners() {
        binding.btBack.setOnClickListener {
            binding.vpOnboarding.currentItem -= 1
        }
        binding.btNext.setOnClickListener {
            binding.vpOnboarding.currentItem += 1
        }
        binding.btFinish.setOnClickListener {
            viewModel.finishOnboarding()
        }
    }

    private fun setViewPager(onboardingItems: List<OnboardingItem>) {
        val fragments = onboardingItems.map { OnboardingItemFragment.newInstance(it) }
        binding.vpOnboarding.adapter = OnboardingAdapter(this, fragments)
        binding.vpOnboarding.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                onPageSelected(position, fragments)
                super.onPageSelected(position)
            }
        })
        binding.wdiOnboarding.attachTo(binding.vpOnboarding)
    }

    private fun onPageSelected(position: Int, fragments: List<OnboardingItemFragment>) {
        binding.btBack.isVisible = position != 0
        binding.btNext.isVisible = position != fragments.size - 1
        binding.wdiOnboarding.isVisible = position != fragments.size - 1
        binding.btFinish.isVisible = position == fragments.size - 1
    }
}