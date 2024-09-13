package com.prmto.kekodflashcard.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.prmto.kekodflashcard.R
import com.prmto.kekodflashcard.databinding.WordViewBinding

class WordView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: WordViewBinding =
        WordViewBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)

        val styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.WordView)

        styledAttributes.apply {
            val baseIcon = getResourceId(R.styleable.WordView_baseIcon, 0)
            val baseIconTint = getColor(R.styleable.WordView_baseIconTint, 0)
            val flagIcon = getResourceId(R.styleable.WordView_flagIcon, 0)
            val description = getString(R.styleable.WordView_description) ?: ""

            binding.ivBaseIcon.setImageResource(baseIcon)
            binding.tvDescription.text = description

            if (baseIconTint != 0) {
                binding.ivBaseIcon.setColorFilter(baseIconTint)
            }

            if (flagIcon != 0) {
                binding.ivFlag.setImageResource(flagIcon)
            }else{
                binding.ivFlag.visibility = GONE
            }
        }
        styledAttributes.recycle()
    }

    fun setDescription(description: String) {
        binding.tvDescription.text = description
    }
}