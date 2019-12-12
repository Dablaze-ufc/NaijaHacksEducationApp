package com.pheonix_squad.naijahackseducationapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pheonix_squad.naijahackseducationapp.adapter.YoutubeRecyclerAdapter
import com.pheonix_squad.naijahackseducationapp.subject.Maths
import kotlinx.android.synthetic.main.fragment_subject_detail.*

/**
 * A simple [Fragment] subclass.
 */
class SubjectDetailFragment : Fragment() {

    val mMovies = listOf(
        Maths("Learn Numbers 1", "bfJiks_rCY8"),
        Maths("Learn Numbers 2", "CRN7rwblanK")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_subject_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_topic.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = YoutubeRecyclerAdapter(mMovies)
        }
    }

    companion object {
        fun newInstace(): SubjectDetailFragment = SubjectDetailFragment()
    }



}
