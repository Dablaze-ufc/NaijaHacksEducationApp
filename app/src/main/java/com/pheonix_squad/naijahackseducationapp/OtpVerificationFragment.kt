package com.pheonix_squad.naijahackseducationapp


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.pheonix_squad.naijahackseducationapp.sharedClasses.AppSharedPreference
import kotlinx.android.synthetic.main.fragment_otp_verification.*
import kotlinx.android.synthetic.main.fragment_phone_number_auth.*

/**
 * A OtpVerificationFragment subclass.
 */
class OtpVerificationFragment : Fragment() {

    private val TAG = "OtpVerificationFragment"
    private lateinit var auth: FirebaseAuth
    private lateinit var pref: AppSharedPreference
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

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
        sign_in_button.setOnClickListener {
            val code = otp_et_1.text.toString().plus(otp_et_2.text).plus(otp_et_3.text).plus(otp_et_4.text).plus(otp_et_5.text).plus(otp_et_6.text)
            val credential = PhoneAuthProvider.getCredential(pref.getVerificationId()!!, code)
            signInWithPhoneAuthCredential(credential)
        }
    }

    // [START sign_in_with_phone]
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = task.result?.user
                    findNavController().navigate(R.id.subjectListFragment)
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(context, (task.exception as FirebaseAuthInvalidCredentialsException).localizedMessage, Toast.LENGTH_LONG).show()
                    }
                }
            }
    }
}
