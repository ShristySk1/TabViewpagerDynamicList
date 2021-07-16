package com.ayata.dynamicfragmentviewpager.allproducts

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayata.dynamicfragmentviewpager.databinding.LayoutCategorySingleBinding
import com.ayata.dynamicfragmentviewpager.allproducts.model.MyServerDataItem

class PagerAdapter2(
    private val context: Context,
    private val words: MutableList<DummyModel2>,
    private val listener: (MyServerDataItem) -> Unit
) :
    RecyclerView.Adapter<PagerAdapter2.PageHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder =
        PageHolder(
            LayoutCategorySingleBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        var adapterItem = PagerAdapterSingle(context, words[position].item)
        holder.binding.recyclerContainer.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = adapterItem
        }
        adapterItem.setClickListener { data ->
            Log.d("frominsideadapter", "onBindViewHolder: " + data.name);
            listener.invoke(data)
        }
    }
    override fun getItemCount(): Int = words.size

    inner class PageHolder(val binding: LayoutCategorySingleBinding) :
        RecyclerView.ViewHolder(binding.root)
}