package com.pheonix_squad.naijahackseducationapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SubjectView(private var context: Context, val subject: List<Subject>) :
    RecyclerView.Adapter<SubjectView.ViewHolder>() {

    private var layoutInflater = LayoutInflater.from(context)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageIcon = itemView.findViewById<ImageView>(R.id.roundedImageView)
        val subjectList = itemView.findViewById<TextView>(R.id.text_subject_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.video_list, parent, false)
        return ViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }
}