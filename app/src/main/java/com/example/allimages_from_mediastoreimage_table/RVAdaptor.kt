package com.example.allimages_from_mediastoreimage_table

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RVAdaptor(var c: Context,var list:MutableList<Images>) : RecyclerView.Adapter<RVAdaptor.ItemViewHolder>() {

    var onItemClick: ((user: Images ) -> Unit)? = null

    inner class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var iv: ImageView? = null
        var tv_name: TextView? = null
        var tv_size: TextView? = null

        init {
            iv = v.findViewById(R.id.iv)
            tv_name = v.findViewById(R.id.tv_name)
            tv_size = v.findViewById(R.id.tv_size)
        }

        fun bind(user: Images) {
            itemView.apply{
                // TODO: Bind the data with View
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
        list.get(position).let {
            holder.run {
//                iv!!.setImageURI(it.uri)
                tv_name!!.text = it.name
                tv_size!!.text = it.size.toString()

                Glide.with(c)
                    .load(it.uri)
                    .into(iv!!)
            }
        }
    }

}