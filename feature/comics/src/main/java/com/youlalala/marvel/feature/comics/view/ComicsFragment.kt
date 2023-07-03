package com.youlalala.marvel.feature.comics.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.youlalala.marvel.feature.comics.ComicsAdapter
import com.youlalala.marvel.feature.comics.databinding.FragmentComicsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicsFragment : Fragment() {

    private lateinit var binding : FragmentComicsBinding
    private val comicsViewModel: ComicsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComicsBinding.inflate(layoutInflater)

        val adapter = ComicsAdapter()
        binding.comicRecyclerview.adapter = adapter
        subscribeUi(adapter)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        comicsViewModel.getComics()
    }

    private fun subscribeUi(adapter: ComicsAdapter) {
        comicsViewModel.comicsListLiveData.observe(viewLifecycleOwner) { comics ->
            Log.i("TEST",comics.toString())
            adapter.submitList(comics)
        }
    }
}