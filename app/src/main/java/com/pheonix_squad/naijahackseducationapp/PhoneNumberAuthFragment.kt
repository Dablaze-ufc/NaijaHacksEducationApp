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
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.pheonix_squad.naijahackseducationapp.sharedClasses.AppSharedPreference
import kotlinx.android.synthetic.main.fragment_otp_verification.*
import kotlinx.android.synthetic.main.fragment_phone_number_auth.*
import java.util.concurrent.TimeUnit

/**
 * Created by SegunFrancis
 */
class PhoneNumberAuthFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var pref: AppSharedPreference
    private val TAG = "PhoneNumberAuthFragment"
    private var verificationInProgress = false
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = AppSharedPreference(requireContext())
        auth = FirebaseAuth.getInstance()
    }

    override fun onResume() {
        super.onResume()
        if (auth.currentUser != null) {
            findNavController().navigate(R.id.subjectListFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_phone_number_auth, container, false)
        if (savedInstanceState != null) {
            onViewStateRestored(savedInstanceState)
        }

        // Initialize phone auth callbacks
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")
                verificationInProgress = false
                pref.setCredential(credential)
                findNavController().navigate(R.id.subjectListFragment)
            }

            override fun onVerificationFailed(e: FirebaseException?) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)
                verificationInProgress = false
                hideProgressBar(auth_progress_bar)
                showButton(verify_phone_number_button)
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    phone_number_et.error = "Invalid phone number."
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    Snackbar.make(
                        root.findViewById(android.R.id.content), "Quota exceeded.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCodeSent(
                verificationId: String?,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationId, token)

                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token

                pref.setVerificationId(verificationId)
                pref.setAuthToken(token)
                findNavController().navigate(R.id.action_phoneNumberAuthFragment_to_otpVerificationFragment)
                hideProgressBar(auth_progress_bar)
                showButton(verify_phone_number_button)
            }
        }
        return root
    }

    /* override fun onStart() {
         super.onStart()
         // Check if user is signed in (non-null) and update UI accordingly.
         val currentUser = auth.currentUser
         //updateUI(currentUser)

         // [START_EXCLUDE]
         if (verificationInProgress && validatePhoneNumber()) {
             startPhoneNumberVerification(phone_number_et.text.toString())
         }
     }*/


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        country_picker.registerPhoneNumberTextView(phone_number_et)

        verify_phone_number_button.setOnClickListener {
            if (validatePhoneNumber()) {
                val countryCode = country_picker.selectedCountryCode
                val number = phone_number_et.text.toString()
                val phoneNumber = "+".plus(countryCode.plus(number.removePrefix("0")))
                Log.d("PhoneNumber", phoneNumber)
                startPhoneNumberVerification(phoneNumber)
                showProgressBar(auth_progress_bar)
                hideButton(verify_phone_number_button)
            }
        }
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber, // Phone number to verify
            120, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            requireActivity(), // Activity (for callback binding)
            callbacks
        )// OnVerificationStateChangedCallbacks
        Log.d(TAG, "Phone Verification Started")
        verificationInProgress = true
    }

    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber, // Phone number to verify
            120, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            requireActivity(), // Activity (for callback binding)
            callbacks, // OnVerificationStateChangedCallbacks
            token
        ) // ForceResendingToken from callbacks
    }

    private fun validatePhoneNumber(): Boolean {
        val phoneNumber = phone_number_et.text.toString()
        if (TextUtils.isEmpty(phoneNumber)) {
            phone_number_et.error = "Invalid phone number"
            return false
        }
        return true
    }

    private fun showProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(progressBar: ProgressBar) {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showButton(button: MaterialButton) {
        button.visibility = View.VISIBLE
    }

    private fun hideButton(button: MaterialButton) {
        button.visibility = View.GONE
    }
}
