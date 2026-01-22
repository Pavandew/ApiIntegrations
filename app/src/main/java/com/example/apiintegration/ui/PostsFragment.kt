package com.example.apiintegration.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiintegration.R
import com.example.apiintegration.adapter.PostAdapter
import com.example.apiintegration.adapter.PostsDataAdapter
import com.example.apiintegration.uiState.PostsDataUiState
import com.example.apiintegration.viewModel.PostsDataViewModel
import kotlinx.coroutines.launch


class PostsFragment : Fragment() {

    private lateinit var  viewModel: PostsDataViewModel
    private lateinit var  adapter: PostsDataAdapter
    private lateinit var  recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[PostsDataViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_posts, container, false)

        Log.d("PostsFragment", "Posts fragment screen")
        recyclerView = view.findViewById(R.id.posts_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = PostsDataAdapter()
        Log.d("PostsFragment", "Calling Adapter")
        recyclerView.adapter = adapter

        viewModel.fetchingPosts()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                Log.d("PostsFragment", "Calling viewModel")
                viewModel.postsData.collect { state ->
                    when(state) {

                        is PostsDataUiState.Loading -> {
                            Log.d("PostsFragment", "loading...")
                        }

                        is PostsDataUiState.Success -> {
                            Log.d("PostsFragment", "fragment screen Success")

                            state.post.posts?.let { list ->
                                adapter.submitList(list)
                            }
                        }

                        is PostsDataUiState.Error -> {
                            Log.d("PostsFragment", "Getting Error!")
                        }
                    }
                }
            }
        }
        return view
    }

}