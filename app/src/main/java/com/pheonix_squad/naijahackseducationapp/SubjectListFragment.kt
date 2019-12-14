package com.pheonix_squad.naijahackseducationapp


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_subject_list.*
import kotlin.system.exitProcess

/**
 * A simple [Fragment] subclass.
 */
class SubjectListFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val dialog = MaterialAlertDialogBuilder(context).apply {
                    setMessage("Are you sure you want to exit?")
                    setPositiveButton("YES") { dialogInterface, i ->
                        dialogInterface.dismiss()
                        exitProcess(0)
                    }
                    setNegativeButton("NO") { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                }
                dialog.create().show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this@SubjectListFragment,
            onBackPressedCallback
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_subject_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val argsBunle = Bundle()
        val maths = "Mathemathics"
        val english = "english"
        argsBunle.putString("maths_key", english)
        val fragment = SubjectDetailFragment()
        fragment.arguments = argsBunle


        card_maths.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_subjectListFragment_to_subjectDetailFragment)
        }

        card_english.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_subjectListFragment_to_subjectDetailFragment, argsBunle)

        }

    }
}
