package com.example.apiintegration.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.apiintegration.R
class RickMortyDetailFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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


        return view
    }

}