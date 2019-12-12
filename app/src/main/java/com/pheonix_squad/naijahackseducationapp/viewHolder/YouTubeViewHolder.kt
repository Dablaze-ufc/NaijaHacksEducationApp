package com.pheonix_squad.naijahackseducationapp.viewHolder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeThumbnailView
import com.pheonix_squad.naijahackseducationapp.R
import com.pheonix_squad.naijahackseducationapp.subject.Maths

class YouTubeViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.subject_list, parent, false
    )
) {
    var mTittle: TextView? = null
    var mThumbnail: YouTubeThumbnailView? = null
    var youtubeCardView: CardView? = null

    init {
        mTittle = itemView.findViewById(R.id.text_tittle)
        mThumbnail = itemView.findViewById(R.id.youTube_thumb)
        youtubeCardView = itemView.findViewById(R.id.card_topic)
    }

    fun bind(math: Maths) {
        mTittle?.text = math.topicTitle
    }


}