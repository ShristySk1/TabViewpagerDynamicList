package com.ayata.dynamicfragmentviewpager.allproducts

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ayata.dynamicfragmentviewpager.databinding.ActivityMainBinding
import com.ayata.dynamicfragmentviewpager.allproducts.model.MyServerData
import com.ayata.dynamicfragmentviewpager.allproducts.model.MyServerDataItem
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), (MyServerDataItem) -> Unit {
    lateinit var binding: ActivityMainBinding
//    val servermodel = ServerModel(
//        listOf(
//            Category("Cat1", listOf(Item("1", "cat1name1"), Item("2", "cat1name2"))),
//            Category("Cat2", listOf(Item("1", "cat2name1"))),
//            Category("Cat3", listOf(Item("1", "cat3name1"))),
//            Category("Cat4", listOf(Item("1", "cat4name1")))
//
//        ), "2"
//    )

    val servermodel2 = MyServerData(
        listOf(
            MyServerDataItem("cat1", 1, "rice"),
            MyServerDataItem("cat2", 2, "lemonjuice"),
            MyServerDataItem("cat2", 3, "orangejuice"),
            MyServerDataItem("cat3", 4, "vodka"),
            MyServerDataItem("cat1", 5, "pulse"),
            MyServerDataItem("cat4", 6, "blanket")


        )
    )
    val filteredData = servermodel2.data.groupBy { it.category }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //first method
//        val list = mutableListOf<DummyModel>()
//        for (i in 0 until servermodel.categories.size) {
////            binding.tabLayout.addTab(binding.tabLayout.newTab().setText("tab$i"))
//            list.add(DummyModel(servermodel.categories[i].items))
//        }
//        var pagerViewAdapter =
//            PagerAdapter(this, list)
//        binding.viewpager.adapter = pagerViewAdapter
//        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
//            tab.text = servermodel.categories[position].categoryName
//        }.attach()


        //next method
        val list = mutableListOf<DummyModel2>()
        filteredData.forEach { entry ->
            list.add(DummyModel2(entry.value))
        }
        var pagerViewAdapter =
            PagerAdapter2(this, list, this)
        binding.viewpager.adapter = pagerViewAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = filteredData.keys.elementAt(position)
        }.attach()

//common not needed
//        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabReselected(p0: TabLayout.Tab?) {
//
//            }
//
//            override fun onTabSelected(p0: TabLayout.Tab?) {
//                binding.viewpager.currentItem = p0!!.position
//            }
//
//            override fun onTabUnselected(p0: TabLayout.Tab?) {
//
//            }
//        })

    }

    //click listener
    override fun invoke(p1: MyServerDataItem) {
        Toast.makeText(this, p1.name, Toast.LENGTH_SHORT).show()
    }
}