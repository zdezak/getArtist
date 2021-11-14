package com.zdez.getartist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zdez.getartist.API.Artist
import com.zdez.getartist.databinding.ItemFragmentBinding

class ArtistAdapter(val clickListener: ArtistListener) :
    ListAdapter<Artist, ArtistAdapter.ViewHolder>(ArtistDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArtistAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    class ViewHolder private constructor(val binding: ItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: ArtistListener, item: Artist) {
            binding.artist = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemFragmentBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }

        }

    }
}


class ArtistDiffCallback : DiffUtil.ItemCallback<Artist>() {
    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem.mbid == newItem.mbid
    }

    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem == newItem
    }

}

class ArtistListener(val clickListener: (mbid: String) -> Unit) {
    fun onClick(artist: Artist) = clickListener(artist.mbid)
}