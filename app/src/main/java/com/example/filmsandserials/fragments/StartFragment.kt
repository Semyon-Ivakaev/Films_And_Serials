package com.example.filmsandserials.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.filmsandserials.databinding.StartFragmentBinding
import com.example.filmsandserials.interfaces.StartFragmentInterface

class StartFragment: Fragment() {
    lateinit var binding: StartFragmentBinding

    var startFragmentInterface: StartFragmentInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StartFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        with(binding) {
            topCircle.apply {
                alpha = 0f
                translationY = -500f
                animate()?.alpha(1f)?.translationYBy(500f)?.duration = 1500
                setOnClickListener {
                    startFragmentInterface?.onFilmsButtonClicked()
                }
            }
            bottomCircle.apply {
                alpha = 0f
                translationY = 500f
                animate()?.alpha(1f)?.translationYBy(-500f)?.duration = 1500
                setOnClickListener {
                    startFragmentInterface?.onSerialsButtonClicked()
                }
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is StartFragmentInterface) {
            startFragmentInterface = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        startFragmentInterface = null
    }
}