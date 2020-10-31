package com.devesh.mvvmbasics.ui.main.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.devesh.mvvmbasics.R
import com.devesh.mvvmbasics.data.room.entity.ResultsItem
import com.devesh.mvvmbasics.databinding.MainFragmentBinding
import com.devesh.mvvmbasics.ui.main.MainActivity
import com.devesh.mvvmbasics.ui.main.fragment.adapter.MainAdapter
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainFragment : Fragment(R.layout.main_fragment) {

    @Inject
    lateinit var viewModelFactory: MainViewModel.MainViewModelFactory

    private val viewModel: MainViewModel by viewModels { viewModelFactory }
    private val binding by viewBinding<MainFragmentBinding>()
    private val adapter: MainAdapter = MainAdapter(arrayListOf())

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).mainComponent.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpUI()
        setUpObservers()
    }

    private fun setUpUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setUpObservers() {
        viewModel.uiStateLiveData.observe(viewLifecycleOwner, { uiState ->
            when (uiState) {
                is MainViewModel.UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    retrieveList(uiState.data)
                }
                is MainViewModel.UiState.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showNoDataError("\uD83D\uDE28 Wooops! There are no articles")
                }
                is MainViewModel.UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun retrieveList(item: List<ResultsItem>) {
        adapter.apply {
            addMovieReviewList(item)
            notifyDataSetChanged()
        }
    }

    private fun showNoDataError(errorMessage: String) {
        val snackBar = view?.let {
            Snackbar.make(
                it, errorMessage,
                Snackbar.LENGTH_LONG
            ).setAction("Action", null)
        }
        snackBar?.setActionTextColor(Color.BLUE)
        val snackBarView = snackBar?.view
        snackBarView?.setBackgroundColor(Color.CYAN)
        val textView =
            snackBarView?.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.BLUE)
        snackBar.show()
    }

}