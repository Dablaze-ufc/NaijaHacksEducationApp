package com.pheonix_squad.naijahackseducationapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_subject_list.*

/**
 * A simple [Fragment] subclass.
 */
class SubjectListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_subject_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        card_maths.setOnClickListener { view ->
            view.findNavController()
                .navigate(R.id.action_subjectListFragment_to_subjectDetailFragment)
        }

        card_english.setOnClickListener { view ->
            view.findNavController()
                .navigate(R.id.action_subjectListFragment_to_playVideoActivity)
        }
    }


}
