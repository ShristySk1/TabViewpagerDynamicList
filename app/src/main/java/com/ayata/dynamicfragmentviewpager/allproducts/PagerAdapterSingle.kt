package com.ayata.dynamicfragmentviewpager.allproducts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayata.dynamicfragmentviewpager.databinding.LayoutItemBinding
import com.ayata.dynamicfragmentviewpager.allproducts.model.MyServerDataItem

class PagerAdapterSingle(private val context: Context, private val words: List<MyServerDataItem>) :
    RecyclerView.Adapter<PagerAdapterSingle.PageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder =
        PageHolder(
            LayoutItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        holder.binding.name.text = words[position].name
        holder.binding.id.text = words[position].id.toString()
        holder.itemView.setOnClickListener {
            myitemClick?.let {
                it(words[position])
            }
        }
    }

    override fun getItemCount(): Int = words.size

    inner class PageHolder(val binding: LayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    var myitemClick: ((MyServerDataItem) -> Unit)? = null
    fun setClickListener(myitemClick: (MyServerDataItem) -> Unit) {
        this.myitemClick = myitemClick
    }
}