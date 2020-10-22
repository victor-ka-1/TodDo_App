package com.kasko.victor.testapp.toddoapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kasko.victor.testapp.toddoapp.R
import com.kasko.victor.testapp.toddoapp.ui.viewmodels.ToDoViewModel
import com.kasko.victor.testapp.toddoapp.ui.adapters.FragmentsAdapter
import com.kasko.victor.testapp.toddoapp.ui.adapters.Tab
import com.kasko.victor.testapp.toddoapp.ui.adapters.getTabByPosition
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Math.atan
import java.lang.Math.tan


class MainActivity : AppCompatActivity() {
    private val viewModel: ToDoViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.TodDoApp)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        viewModel.errorLiveData.observe(this){errorMsg ->
            Toast.makeText(this,errorMsg,Toast.LENGTH_LONG).show()
        }

        viewPager.adapter = FragmentsAdapter(supportFragmentManager,lifecycle)

        TabLayoutMediator(tab_layout, viewPager){ tab: TabLayout.Tab, position: Int ->
            val tabType  = getTabByPosition(position)
            tab.text = tabType.getTabName()
        }.attach()
    }

}