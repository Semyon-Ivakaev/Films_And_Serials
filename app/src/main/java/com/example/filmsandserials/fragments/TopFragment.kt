package com.example.filmsandserials.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmsandserials.databinding.TopFragmentBinding
import com.example.filmsandserials.interfaces.TopFragmentClickListener

class TopFragment:Fragment() {
    lateinit var binding: TopFragmentBinding
    private var topFragmentClickListener: TopFragmentClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TopFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val typeData = arguments?.getString("TAG")

        initViews()

        return view
    }

    private fun initViews() {
        with(binding) {
            backButton.setOnClickListener {
                Log.v("AppVerbose", "Click on Back")
                topFragmentClickListener?.onBackButtonClicked()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TopFragmentClickListener) {
            topFragmentClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        topFragmentClickListener = null
    }

    companion object {
        fun newInstance(buttonTag: String): TopFragment {
            val args = Bundle()
            args.putString("TAG", buttonTag)
            val fragment = TopFragment()
            fragment.arguments = args
            return fragment
        }
    }
}