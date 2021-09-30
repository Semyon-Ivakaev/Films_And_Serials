package com.example.filmsandserials.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.filmsandserials.databinding.SearchFragmentBinding
import com.example.filmsandserials.fragments.viewpager.ViewPagerAdapter
import com.example.filmsandserials.interfaces.SearchFragmentInterface
import com.example.filmsandserials.viewmodels.SearchViewModel
import com.example.filmsandserials.viewmodels.SharedViewModel

class SearchFragment:Fragment() {
    lateinit var binding: SearchFragmentBinding
    private var searchFragmentInterface: SearchFragmentInterface? = null
    private val model: SharedViewModel by activityViewModels()
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        initViews()



        return view
    }

    private fun initViews() {
        with(binding) {
            backButton.setOnClickListener {
                searchFragmentInterface?.onBackButtonClicked()
            }
            searchView.isSubmitButtonEnabled = true
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    // Api работает только с English словами, переводим на English
//                    val request = searchFragmentPresenter.translateRequest(query)
                    // Храним список, [0]-ru для вывода в snackBar, [1]-en значение в API
                    // пока не используется

                    model.select(query)
                    // Сворачиваем клавиатуру при сабмите поиска
                    val hideKeyboard = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    hideKeyboard?.hideSoftInputFromWindow(view?.windowToken, 0)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
            searchViewPager.adapter = ViewPagerAdapter(childFragmentManager, searchViewModel)
            tabLayout.setupWithViewPager(searchViewPager)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SearchFragmentInterface) {
            searchFragmentInterface = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        searchFragmentInterface = null
    }
}