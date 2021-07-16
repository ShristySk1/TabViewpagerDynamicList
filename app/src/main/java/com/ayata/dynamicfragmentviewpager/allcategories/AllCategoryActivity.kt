package com.ayata.dynamicfragmentviewpager.allcategories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ayata.dynamicfragmentviewpager.allcategories.model.Category
import com.ayata.dynamicfragmentviewpager.allcategories.model.Item
import com.ayata.dynamicfragmentviewpager.allcategories.model.ServerModel
import com.ayata.dynamicfragmentviewpager.databinding.ActivityAllCategoryBinding
import com.google.android.material.tabs.TabLayoutMediator

class AllCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityAllCategoryBinding
    val servermodel = ServerModel(
        listOf(
            Category("Cat1", listOf(Item("1", "cat1name1"), Item("2", "cat1name2"))),
            Category("Cat2", listOf(Item("1", "cat2name1"))),
            Category("Cat3", listOf(Item("1", "cat3name1"))),
            Category("Cat4", listOf(Item("1", "cat4name1")))

        ), "2"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        first method
        val list = mutableListOf<DummyModel>()
        for (i in 0 until servermodel.categories.size) {
//            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("tab$i"))
            list.add(DummyModel(servermodel.categories[i].items))
        }
        var pagerViewAdapter =
            PagerAdapter(this, list)
        binding.viewpager.adapter = pagerViewAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = servermodel.categories[position].categoryName
        }.attach()

    }

}