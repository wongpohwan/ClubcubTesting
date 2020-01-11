package com.example.clubcubtesting


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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

        // pointing to unique user id in users database
        val cUser = FirebaseAuth.getInstance().currentUser
        val databaseReference = FirebaseDatabase.getInstance().reference.child("users").child(cUser!!.uid)

        // find text view
        val nickname_tv = view?.findViewById(R.id.home_nickname_textView) as TextView
        val description_tv = view?.findViewById(R.id.home_profileDescription_textView) as TextView

        // display nickname and description in home fragment
        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(view.context, p0.message, Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                var nickname = p0.child("nickname").value.toString()
                var description = p0.child("description").value.toString()

                nickname_tv.text = nickname
                description_tv.text = description
            }

        })

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
