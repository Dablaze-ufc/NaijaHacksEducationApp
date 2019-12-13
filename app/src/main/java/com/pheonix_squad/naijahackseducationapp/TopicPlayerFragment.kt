package com.pheonix_squad.naijahackseducationapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import java.security.Provider

/**
 * A simple [Fragment] subclass.
 */
class TopicPlayerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_subject_list, container, false)

        val youTubePlayerSupportFragment: YouTubePlayerSupportFragment =
            fragmentManager!!.findFragmentById(R.id.topicPlayerFragment) as YouTubePlayerSupportFragment
        youTubePlayerSupportFragment.initialize(MainActivity().APIKEY,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider?,
                    player: YouTubePlayer?,
                    isRestored: Boolean
                ) {
                    if (!isRestored) {
                        player!!.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                        player.cueVideo(" ")
                    }
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })


        return view
    }


}











