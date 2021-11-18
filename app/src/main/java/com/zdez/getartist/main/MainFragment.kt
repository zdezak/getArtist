package com.zdez.getartist.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zdez.getartist.R
import com.zdez.getartist.adapter.ArtistAdapter
import com.zdez.getartist.adapter.ArtistListener
import com.zdez.getartist.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val apiKey = getString(R.string.api_key)
        val binding: MainFragmentBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.main_fragment, container, false)
        val viewModel =
            ViewModelProvider(this, MainViewModelFactory(apiKey)).get(MainViewModel::class.java)
        val adapter = ArtistAdapter(ArtistListener { id ->
            viewModel.onArtistClicked(id)
        })
        binding.ItemList.adapter = adapter
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.artist.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })
        viewModel.navigateToAlbums.observe(viewLifecycleOwner, Observer {
            it?.let{
                this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToAlbumsFragment(it))
                viewModel.onNavigateToAlbumsComplited()
            }
        })

        return binding.root
    }
}