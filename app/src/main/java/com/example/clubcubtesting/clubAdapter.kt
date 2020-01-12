package com.example.clubcubtesting

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_layout.view.*

class clubAdapter : RecyclerView.Adapter<clubAdapter.Companion.Holder> {

    companion object {
        class Holder: RecyclerView.ViewHolder
        {
            lateinit var clubName_textView:TextView
            lateinit var imgPath: ImageView

            val clubName = itemView.clubName_textView

            constructor(rv: View) : super(rv)
            {
                imgPath = rv.findViewById(R.id.clubImageView) as ImageView
                clubName_textView = rv.findViewById(R.id.clubName_textView) as TextView
            }
        }

    }

    var list: MutableList<club> = mutableListOf()
    var con:Context

    constructor(list: MutableList<club>, con: Context) : super() {
        this.list = list
        this.con = con
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var rv: View
        var holder: Holder
        rv = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        holder = Holder(rv)
        return holder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var club: club
        club = list.get(position)
        holder.clubName_textView.setText(club.getClubname())
        holder.imgPath.setImageResource(club.getPath())

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ClubMainActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }

    }

}