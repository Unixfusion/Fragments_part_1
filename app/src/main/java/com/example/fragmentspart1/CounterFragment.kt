package com.example.fragmentspart1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentspart1.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {
    private lateinit var binding: FragmentCounterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCounterBinding.inflate(layoutInflater, container, false)

        binding.tvScreenNumber.text = getString(R.string.screen_label, getCounterValue() )

        binding.tvQuote.text = getQuote()

        binding.btnNext.setOnClickListener { onNextPressed() }
        binding.btnBack.setOnClickListener { onBackPressed() }
        return binding.root
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun onNextPressed() {
        val fragment = CounterFragment.newInstance(
            counterValue = (requireActivity() as MainActivity).getScreensCount() + 1,
            quote = (requireActivity() as MainActivity).createQuote()
        )
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun goBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun getCounterValue(): Int = requireArguments().getInt(ARG_COUNTER_VALUE)

    private fun getQuote(): String = requireArguments().getString(ARG_QUOTE)!!

    companion object {

        @JvmStatic private val ARG_COUNTER_VALUE = "ARG_COUNTER_VALUE"
        @JvmStatic private val ARG_QUOTE = "ARG_QUOTE"

        @JvmStatic fun newInstance(counterValue: Int, quote: String): CounterFragment {
            val args = Bundle().apply {
                putInt(ARG_COUNTER_VALUE, counterValue)
                putString(ARG_QUOTE, quote)
            }
            val fragment = CounterFragment()
            fragment.arguments = args
            return fragment
        }
    }
}