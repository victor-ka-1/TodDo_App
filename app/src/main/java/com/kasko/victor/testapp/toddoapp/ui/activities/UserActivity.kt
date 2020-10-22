package com.kasko.victor.testapp.toddoapp.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kasko.victor.testapp.toddoapp.R
import com.kasko.victor.testapp.toddoapp.db.entity.UserLocal
import com.kasko.victor.testapp.toddoapp.ui.viewmodels.ToDoViewModel
import com.kasko.victor.testapp.toddoapp.ui.adapters.TaskRecyclerViewAdapter
import com.kasko.victor.testapp.toddoapp.ui.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_user.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModel()

    private lateinit var rwAdapter: TaskRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val user :UserLocal = intent.getSerializableExtra(getString(R.string.user_bundle)) as UserLocal

        profile_id.text = getString(R.string.id_title, user.id)
        profile_bs.text = getString(R.string.bs_value, user.company.bs)
        profile_catchPhrase.text = getString(R.string.prhase_text, user.company.catchPhrase)
        profile_company.text = getString(R.string.company_text, user.company.companyName)
        profile_email.text = user.email
        profile_website.text = user.website
        profile_latitude.text = getString(R.string.lat_value, user.address.geoPosition.latitude)
        profile_longitude.text = getString(R.string.lng_value, user.address.geoPosition.longitude)
        profile_phone.text = user.phone
        profile_username.text = user.username
        profile_name.text = user.name
        profile_address.text = String.format(getString(R.string.address_values),
            user.address.city,user.address.street,user.address.suite,user.address.zipCode)

        setupUI()
        setupObservers()
        viewModel.getTasksForUser(user.id)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupUI(){

        val rwLayoutManager = LinearLayoutManager(this)
        rwAdapter = TaskRecyclerViewAdapter()
        recyclerView_userTasks.apply{
            layoutManager = rwLayoutManager
            adapter = rwAdapter
        }

    }
    private fun setupObservers() {
        viewModel.userTasksLiveData.observe(this){ tasksList ->
            rwAdapter.updateRecyclerViewList(tasksList)
        }
    }
}