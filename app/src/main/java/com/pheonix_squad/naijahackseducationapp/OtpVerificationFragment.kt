package com.pheonix_squad.naijahackseducationapp


import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.pheonix_squad.naijahackseducationapp.sharedClasses.AppSharedPreference
import kotlinx.android.synthetic.main.fragment_otp_verification.*

/**
 * Created by SegunFrancis
 */
class OtpVerificationFragment : Fragment() {

    private val TAG = "OtpVerificationFragment"
    private lateinit var auth: FirebaseAuth
    private lateinit var pref: AppSharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = AppSharedPreference(requireContext())
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_otp_verification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        otp_et_1.addTextChangedListener(
            GenericTextWatcher(
                otp_et_1,
                otp_et_1,
                otp_et_2,
                otp_et_3,
                otp_et_4,
                otp_et_5,
                otp_et_6
            )
        )
        otp_et_2.addTextChangedListener(
            GenericTextWatcher(
                otp_et_2,
                otp_et_1,
                otp_et_2,
                otp_et_3,
                otp_et_4,
                otp_et_5,
                otp_et_6
            )
        )
        otp_et_3.addTextChangedListener(
            GenericTextWatcher(
                otp_et_3,
                otp_et_1,
                otp_et_2,
                otp_et_3,
                otp_et_4,
                otp_et_5,
                otp_et_6
            )
        )
        otp_et_4.addTextChangedListener(
            GenericTextWatcher(
                otp_et_4,
                otp_et_1,
                otp_et_2,
                otp_et_3,
                otp_et_4,
                otp_et_5,
                otp_et_6
            )
        )
        otp_et_5.addTextChangedListener(
            GenericTextWatcher(
                otp_et_5,
                otp_et_1,
                otp_et_2,
                otp_et_3,
                otp_et_4,
                otp_et_5,
                otp_et_6
            )
        )
        otp_et_6.addTextChangedListener(
            GenericTextWatcher(
                otp_et_6,
                otp_et_1,
                otp_et_2,
                otp_et_3,
                otp_et_4,
                otp_et_5,
                otp_et_6
            )
        )

        sign_in_button.setOnClickListener {
            if (!(TextUtils.isEmpty(otp_et_1.text) || TextUtils.isEmpty(otp_et_2.text) || TextUtils.isEmpty(
                    otp_et_3.text
                ) || TextUtils.isEmpty(otp_et_4.text) || TextUtils.isEmpty(otp_et_5.text) || TextUtils.isEmpty(
                    otp_et_6.text
                ))
            ) {
                showProgressBar(otp_progress_bar)
                hideButton(sign_in_button)
                val code =
                    otp_et_1.text.toString().plus(otp_et_2.text).plus(otp_et_3.text)
                        .plus(otp_et_4.text)
                        .plus(otp_et_5.text).plus(otp_et_6.text)
                val credential = PhoneAuthProvider.getCredential(pref.getVerificationId()!!, code)
                signInWithPhoneAuthCredential(credential)
            } else {
                Toast.makeText(requireContext(), "Some fields are empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // [START sign_in_with_phone]
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    hideProgressBar(otp_progress_bar)
                    hideButton(sign_in_button)
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = task.result?.user
                    findNavController().navigate(R.id.subjectListFragment)
                } else {
                    hideProgressBar(otp_progress_bar)
                    showButton(sign_in_button)
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(
                            context,
                            (task.exception as FirebaseAuthInvalidCredentialsException).localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
    }

    private fun showProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.GONE
    }

    private fun showButton(button: MaterialButton) {
        button.visibility = View.VISIBLE
    }

    private fun hideButton(button: MaterialButton) {
        button.visibility = View.GONE
    }

    class GenericTextWatcher constructor(
        private val view: View,
        private val et1: EditText,
        private val et2: EditText,
        private val et3: EditText,
        private val et4: EditText,
        private val et5: EditText,
        private val et6: EditText
    ) : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            val text = editable.toString()
            when (view.id) {
                R.id.otp_et_1 -> {
                    if (text.length == 1) {
                        et2.requestFocus()
                    }
                }
                R.id.otp_et_2 -> {
                    if (text.length == 1) {
                        et3.requestFocus()
                    } else if (text.length == 0) {
                        et1.requestFocus()
                    }
                }
                R.id.otp_et_3 -> {
                    if (text.length == 1) {
                        et4.requestFocus()
                    } else if (text.length == 0) {
                        et2.requestFocus()
                    }
                }
                R.id.otp_et_4 -> {
                    if (text.length == 1) {
                        et5.requestFocus()
                    } else if (text.length == 0) {
                        et3.requestFocus()
                    }
                }
                R.id.otp_et_5 -> {
                    if (text.length == 1) {
                        et6.requestFocus()
                    } else if (text.length == 0) {
                        et4.requestFocus()
                    }
                }
                R.id.otp_et_6 -> {
                    if (text.length == 0) {
                        et5.requestFocus()
                    }
                }
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
    }
}
