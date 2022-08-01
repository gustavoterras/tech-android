package com.picpay.desafio.android.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presentation.UserListViewModel.UserListViewState.Success
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<UserListViewModel>()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val adapter by lazy { UserListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupView()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userListState.collect { userListState ->
                    when (userListState) {
                        is Success -> showUserList(userListState.userList)
                        is Error -> showError()
                    }
                }
            }
        }
    }

    private fun setupView() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showUserList(userList: List<User>) {
        binding.progressBar.visibility = View.GONE

        adapter.users = userList
        binding.recyclerView.adapter = adapter
    }

    private fun showError() {
        val message = getString(R.string.error)

        binding.progressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
