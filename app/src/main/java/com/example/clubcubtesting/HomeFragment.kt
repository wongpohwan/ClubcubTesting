package com.example.clubcubtesting


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 */

class HomeFragment : Fragment() {

    lateinit var recycleView: RecyclerView
    var list:MutableList<club> = mutableListOf()
    lateinit var layoutManager:RecyclerView.LayoutManager

    var img_id: IntArray = intArrayOf(R.drawable.ic_sports_white_24dp, R.drawable.ic_arts_white_24dp ,R.drawable.ic_school_white_24dp,
        R.drawable.ic_videogame_white_24dp, R.drawable.ic_environmental_24dp, R.drawable.ic_others_white_24dp)

    var clubname: Array<String> = arrayOf("Fitness club", "Music and Art club", "Programming club", "Gaming club", "Save the earth club", "Others club")

    lateinit var adapter: clubAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        activity?.setTitle("Homepage")

        recycleView = view?.findViewById(R.id.clubRecycleView) as RecyclerView
        layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL,false) // note: not sure
        recycleView.layoutManager = layoutManager
        recycleView.setHasFixedSize(true)
        var count: Int = 0
        for(name: String in clubname) {
            var c: club = club(clubname[count], img_id[count])
            list.add(c)
            count++
        }
        adapter = clubAdapter(list, view.context)
        recycleView.adapter = adapter

        return view

    }
}
