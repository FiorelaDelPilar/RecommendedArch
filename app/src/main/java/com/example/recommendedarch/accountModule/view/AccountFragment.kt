package com.example.recommendedarch.accountModule.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.recommendedarch.BR
import com.example.recommendedarch.R
import com.example.recommendedarch.accountModule.viewModel.AccountViewModel
import com.example.recommendedarch.common.utils.Constants
import com.example.recommendedarch.databinding.FragmentAccountBinding
import com.example.recommendedarch.mainModule.view.MainActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

/****
 * Project: Wines
 * From: com.cursosant.wines
 * Created by Alain Nicolás Tello on 06/02/24 at 20:23
 * All rights reserved 2024.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupIntents()
        setupObservers()
    }

    private fun setupViewModel() {
        val vm: AccountViewModel by inject()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm)
    }

    private fun setupObservers() {
        binding.viewModel?.let { vm ->
            vm.snackbarMsg.observe(viewLifecycleOwner) { resMsg ->
                resMsg?.let { showMsg(resMsg) }
            }

            vm.isSignOut.observe(viewLifecycleOwner) { isSignOut ->
                if (isSignOut) {
                    (requireActivity() as MainActivity).apply {
                        setupNavView(false)
                        launchLoginUI()
                    }
                }
            }
        }
    }

    private fun setupIntents() {
        binding.tvEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Constants.DATA_MAIL_TO.toUri()
                putExtra(Intent.EXTRA_EMAIL, arrayOf(binding.tvEmail.text.toString()))
                putExtra(Intent.EXTRA_SUBJECT, "From kotlin architectures course")
            }
            launchIntent(intent)
        }

        binding.tvPhone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                val phone = (it as TextView).text
                data = "${Constants.DATA_TEL}$phone".toUri()
            }
            launchIntent(intent)
        }
    }

    private fun launchIntent(intent: Intent) {
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(
                requireActivity(),
                getString(R.string.account_error_no_resolve),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showMsg(msgRes: Int) {
        Snackbar.make(binding.root, msgRes, Snackbar.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        binding.viewModel?.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}