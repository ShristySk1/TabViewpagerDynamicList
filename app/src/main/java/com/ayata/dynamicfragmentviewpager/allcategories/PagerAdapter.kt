package com.ayata.dynamicfragmentviewpager.allcategories

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayata.dynamicfragmentviewpager.databinding.LayoutCategoryTextBinding

class PagerAdapter(private val context: Context, private val words: MutableList<DummyModel>) :
    RecyclerView.Adapter<PagerAdapter.PageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder =
        PageHolder(
            LayoutCategoryTextBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        holder.binding.tvText.setText(words[position].item.toString())
    }

    override fun getItemCount(): Int = words.size

    inner class PageHolder(val binding: LayoutCategoryTextBinding) :
        RecyclerView.ViewHolder(binding.root)
}