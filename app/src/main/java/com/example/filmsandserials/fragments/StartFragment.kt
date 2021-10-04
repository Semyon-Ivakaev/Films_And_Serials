package com.example.filmsandserials.fragments

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import com.example.filmsandserials.databinding.StartFragmentBinding
import com.example.filmsandserials.interfaces.StartFragmentInterface
import com.example.filmsandserials.utils.InternetConnections
import com.example.filmsandserials.utils.NetworkDialogFragment

class StartFragment: Fragment() {
    lateinit var binding: StartFragmentBinding

    var startFragmentInterface: StartFragmentInterface? = null
    private var internetConnections: InternetConnections? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StartFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        internetConnections = InternetConnections(requireContext())

        with(binding) {
            topCircle.apply {
                alpha = 0f
                translationY = -500f
                animate()?.alpha(1f)?.translationYBy(500f)?.duration = 1500
                setOnClickListener {
                    startFragmentInterface?.onTypeContentButtonClicked("FILM")
                }
            }
            bottomCircle.apply {
                alpha = 0f
                translationY = 500f
                animate()?.alpha(1f)?.translationYBy(-500f)?.duration = 1500
                setOnClickListener {
                    startFragmentInterface?.onTypeContentButtonClicked("SERIAL")
                }
            }
        }

        checkInternetConnections()
        return view
    }

    private fun checkInternetConnections() {
        if (internetConnections?.checkInternetConnections() == true) {
            NetworkDialogFragment().show(parentFragmentManager, "TAG")
            setupNetworkDialog()
        }
    }

    private fun setupNetworkDialog() {
        parentFragmentManager.setFragmentResultListener(NetworkDialogFragment.REQUEST_KEY, viewLifecycleOwner, FragmentResultListener { _, result ->
            val whitch = result.getInt(NetworkDialogFragment.KEY_RESPONSE)
            when (whitch) {
                DialogInterface.BUTTON_POSITIVE -> checkInternetConnections()
                DialogInterface.BUTTON_NEGATIVE -> startFragmentInterface?.onCloseButtonClicked()
            }
        })
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