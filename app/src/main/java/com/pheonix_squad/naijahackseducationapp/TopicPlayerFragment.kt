package com.pheonix_squad.naijahackseducationapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import java.security.Provider

/**
 * A simple [Fragment] subclass.
 */
class TopicPlayerFragment : Fragment() {
    private val youtubePlayerView: YouTubePlayerSupportFragment = YouTubePlayerSupportFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_subject_list, container, false)


        val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
//        transaction.add(R.id.youTubePlayerView,youtubePlayerView).commit
//        youtubePlayerView.initialize(MainActivity().APIKEY, OnInitializedListener {
//            object : OnInitializedListener
//                    override fun onInitializationSuccess(provider: Provider, youTubePlayer: YouTubePlayer, wasRestored: Boolean){
//                        if (!wasRestored){
//                            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
//                            youTubePlayer.loadVideo("")
//                            youTubePlayer.play()
//                        }
//
//
//        }
//        })
        return view
    }


}











