package com.zdez.getartist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zdez.getartist.databinding.ItemFragmentBinding
import com.zdez.getartist.main.Artist


class ArtistAdapter(private val clickListener: ArtistListener) :
    ListAdapter<Artist, ArtistAdapter.ViewHolder>(ArtistDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(clickListener, item)
    }

    class ViewHolder private constructor(private val binding: ItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: ArtistListener, item: Artist) {
            binding.artists = item
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
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem == newItem
    }

}

class ArtistListener(val clickListener: (id: String) -> Unit) {
    fun onClick(artist: Artist) = clickListener(artist.id)
}