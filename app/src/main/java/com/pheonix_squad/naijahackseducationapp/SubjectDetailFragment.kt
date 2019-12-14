package com.pheonix_squad.naijahackseducationapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.pheonix_squad.naijahackseducationapp.adapter.YoutubeRecyclerAdapter
import com.pheonix_squad.naijahackseducationapp.subject.Maths
import kotlinx.android.synthetic.main.fragment_subject_detail.*

/**
 * A simple [Fragment] subclass.
 */
class SubjectDetailFragment : Fragment() {

    val mMovies = listOf(
        Maths("Learn Numbers 1", "yT3jh2aZN68"),
        Maths("Learn Numbers 2", "NFJdIK6Ydgc")
    )
    val mEnglish = listOf(
        Maths("The English Alphabet (Beginner, Level 1))", "EgzHCuzVKb8"),
        Maths("Subject Pronouns (Beginner, Level 1)", "QcqPN727NZA"),
        Maths("There is / There are (Beginner English) with Teacher Tom", "3nNSba8P4mw")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_subject_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val compare = arguments!!.getString("maths_key")
        recycler_topic.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = if (compare == "english") {
                YoutubeRecyclerAdapter(mEnglish)
            } else {
                YoutubeRecyclerAdapter(mMovies)
            }
        }
    }

}
