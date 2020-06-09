package com.example.allimages_from_mediastoreimage_table

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class RVAdaptor(var c: Context,var list:MutableList<Images>) : RecyclerView.Adapter<RVAdaptor.VH>() {

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        var iv: ImageView? = null
        var tv_name: TextView? = null
        var tv_size: TextView? = null

        init {
            iv = v.findViewById(R.id.iv)
            tv_name = v.findViewById(R.id.tv_name)
            tv_size = v.findViewById(R.id.tv_size)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        LayoutInflater.from(parent.context).inflate(R.layout.cardview1, parent, false).let {
            return VH(it)
        }
    }

    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        list.get(position).let {
            holder.run {
                iv!!.setImageURI(it.uri)
                tv_name!!.text = it.name
                tv_size!!.text = it.size.toString()
            }
        }
    }

}