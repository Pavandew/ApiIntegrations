package com.example.apiintegration.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavArgs
import androidx.navigation.NavHost
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.apiintegration.R
import com.example.apiintegration.uiState.RickMortyDetailUiState
import com.example.apiintegration.uiState.RickMortyUiState
import com.example.apiintegration.viewModel.RickMortyDetailViewModel
import com.example.apiintegration.viewModel.RickMortyViewModel
import kotlinx.coroutines.launch

class RickMortyDetailFragment : Fragment() {

    private lateinit var viewModel: RickMortyDetailViewModel
    private val args: RickMortyDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RickMortyDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rick_morty_detail, container, false)

        val image = view.findViewById<ImageView>(R.id.iv_detail_Image)
        val name = view.findViewById<TextView>(R.id.tv_detail_Name)
        val status = view.findViewById<TextView>(R.id.tv_detail_Status)
        val gender = view.findViewById<TextView>(R.id.tv_detail_Gender)
        val species = view.findViewById<TextView>(R.id.tv_detail_Species)
        val location = view.findViewById<TextView>(R.id.tv_detail_Location)

        val characterId = args.id

        viewModel.fetchRickMortyDetailData(characterId)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.allData.collect {state ->
                    when(state) {

                        is RickMortyDetailUiState.Loading -> {

                        }

                        is RickMortyDetailUiState.Success -> {
                            val character = state.data
                            name.text = character.name
                            status.text = character.status
                            gender.text = character.gender
                            species.text = character.species
//                            location.text = character.location

                            image.load(character.image)



                        }

                        is RickMortyDetailUiState.Error -> {

                        }

                    }
                }
            }
        }

        return view
    }

}