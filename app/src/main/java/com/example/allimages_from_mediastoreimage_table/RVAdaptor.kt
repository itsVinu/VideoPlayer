package com.example.allimages_from_mediastoreimage_table

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.ads.interactivemedia.v3.internal.it
import kotlinx.android.synthetic.main.cardview1.view.*

class RVAdaptor(var list:List<Images>) : RecyclerView.Adapter<RVAdaptor.ItemViewHolder>() {

    var onItemClick: ((user: Images ) -> Unit)? = null

    inner class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(user: Images) {
            itemView.apply{
                // TODO: Bind the data with View
                tv_name!!.text = user.name
                tv_size!!.text = user.size.toString()

                Glide.with(this)
                    .load(user.uri)
                    .into(iv!!)
                setOnClickListener {
                    // TODO: Handle on click
                    onItemClick?.invoke(user)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.cardview1, parent, false).let {
            return ItemViewHolder(it)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

}