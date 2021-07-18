package com.ayata.dynamicfragmentviewpager.allproducts

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ayata.dynamicfragmentviewpager.allproducts.model.MyServerData
import com.ayata.dynamicfragmentviewpager.allproducts.model.MyServerDataItem
import com.ayata.dynamicfragmentviewpager.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), (MyServerDataItem) -> Unit {
    lateinit var binding: ActivityMainBinding
    val tabTitles: MutableList<String> by lazy { mutableListOf() }
    val servermodel2 = MyServerData(
        listOf(
            MyServerDataItem("cat1", 1, "rice"),
            MyServerDataItem("cat2", 2, "lemonjuice"),
            MyServerDataItem("cat2", 3, "orangejuice"),
            MyServerDataItem("cat3", 4, "vodka"),
            MyServerDataItem("cat1", 5, "pulse"),
            MyServerDataItem("cat4", 6, "blanket"),
            MyServerDataItem("cat4", 6, "pillowCase")
        )
    )
    val filteredData = servermodel2.data.groupBy { it.category }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //next method
        val list = mutableListOf<DummyModel2>()
        filteredData.forEach { entry ->
            list.add(DummyModel2(entry.value))
        }
        var pagerViewAdapter =
            PagerAdapter2(this, list, this)
        binding.viewpager.adapter = pagerViewAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewpager, false, true) { tab, position ->
            tab.text = filteredData.keys.elementAt(position)
            tabTitles.add(filteredData.keys.elementAt(position))
        }.attach()
        //to set certain tab selected according to tab title
        getBundle()


    }

    fun getBundle() {
        val alreadySelectedCategory = "cat3"
        val pos = tabTitles.indexOf(alreadySelectedCategory)
        Log.d("getBundle", "getBundle: " + pos);
        /**
         *         here smooth scroll takes place
         */
//        binding.tabLayout.setScrollPosition(pos, 0f, false)
//        binding.tabLayout.getTabAt(pos)?.select();
//        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(pos))
        /**
         *         here smooth scroll does not takes place
         */
        binding.viewpager.setCurrentItem(pos, false);

    }

    //click listener
    override fun invoke(p1: MyServerDataItem) {
        Toast.makeText(this, p1.name, Toast.LENGTH_SHORT).show()
    }
}