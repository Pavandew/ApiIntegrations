package com.example.apiintegration.ui

import android.health.connect.datatypes.units.Length
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiintegration.R
import com.example.apiintegration.adapter.RickMortyAdapter
import com.example.apiintegration.uiState.RickMortyUiState
import com.example.apiintegration.viewModel.RickMortyViewModel
import kotlinx.coroutines.launch

class RickMortyFragment : Fragment() {

    private lateinit var viewModel: RickMortyViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RickMortyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RickMortyViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rick_morty, container, false)

        recyclerView = view.findViewById(R.id.rick_morty_recyclerView)


        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = RickMortyAdapter{clickedItem ->
            Toast.makeText(
                requireContext(),
                "Clicked: ${clickedItem.name}",
                Toast.LENGTH_SHORT
            ).show()

            val action  = RickMortyFragmentDirections.actionRickMortyFragmentToDetailFragment()
            findNavController().navigate(action)

        }

        recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.rickMortyData.collect { state ->
                    when (state) {

                        is RickMortyUiState.Loading -> {
                            // Show Something
                        }

                        is RickMortyUiState.Success -> {
                            // Show Something
                            state.data.results?.let { list ->
                                adapter.submitList(list)
                            }
                            state.data.results?.let(adapter::submitList)
                        }
                        is RickMortyUiState.Error -> {
                            Toast.makeText(requireContext(), "Error: ", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        return view
    }

}