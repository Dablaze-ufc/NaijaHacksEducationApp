package com.pheonix_squad.naijahackseducationapp.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import com.pheonix_squad.naijahackseducationapp.MainActivity
import com.pheonix_squad.naijahackseducationapp.R
import com.pheonix_squad.naijahackseducationapp.subject.Maths


class YoutubeRecyclerAdapter(private val mathsList: List<Maths>) :
    RecyclerView.Adapter<YouTubeViewHolder>() {
    val TAG: String = "Youtube Error"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YouTubeViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return YouTubeViewHolder(inflater, parent)
    }


    override fun getItemCount(): Int = mathsList.size

    override fun onBindViewHolder(holder: YouTubeViewHolder, position: Int) {
        val maths: Maths = mathsList[position]
        holder.bind(maths)
        holder.mTittle!!.text = maths.topicTitle
        holder.mThumbnail!!.initialize(
            MainActivity().APIKEY,
            object : YouTubeThumbnailView.OnInitializedListener {
                override fun onInitializationSuccess(
                    youTubeThumbnailView: YouTubeThumbnailView,
                    youTubeThumbnailLoader: YouTubeThumbnailLoader
                ) {
                    youTubeThumbnailLoader.setVideo(maths.youtubeThumbnail)
                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(object :
                        YouTubeThumbnailLoader.OnThumbnailLoadedListener {
                        override fun onThumbnailLoaded(
                            youTubeThumbnailView: YouTubeThumbnailView?,
                            s: String?
                        ) {
                            youTubeThumbnailLoader.release()
                        }

                        override fun onThumbnailError(
                            youTubeThumbnailView: YouTubeThumbnailView?,
                            errorReason: YouTubeThumbnailLoader.ErrorReason?
                        ) {
                            Log.d(TAG, "YouTube Thumbnail Error")
                        }
                    })
                }

                override fun onInitializationFailure(
                    youTubeThumbnailView: YouTubeThumbnailView?,
                    p1: YouTubeInitializationResult?
                ) {
                    Log.d(TAG, "Youtube Initialization Failure")
                }
            })
    }

}

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
        youtubeCardView!!.setOnClickListener { view: View ->

        }

    }
}
