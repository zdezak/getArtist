package com.zdez.getartist.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.zdez.getartist.R
import com.zdez.getartist.adapter.AlbumsAdapter
import com.zdez.getartist.databinding.AlbumsFragmentBinding

class AlbumsFragment : Fragment() {

    companion object {
        fun newInstance() = AlbumsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val apiKey = getString(R.string.api_key)
        val args = AlbumsFragmentArgs.fromBundle(requireArguments())
        val binding: AlbumsFragmentBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.albums_fragment, container, false)
        val viewModel =
            ViewModelProvider(
                this,
                AlbumsViewModelFactory(
                    api_key = apiKey,
                    artistId = args.id,
                    artistName = args.artist
                )
            ).get(AlbumsViewModel::class.java)
        val adapter = AlbumsAdapter()
        val manager = GridLayoutManager(activity, 3)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.albumsList.adapter = adapter
        binding.albumsList.layoutManager = manager


        viewModel.albums.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}