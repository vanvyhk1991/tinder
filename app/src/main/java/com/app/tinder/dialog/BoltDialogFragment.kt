package com.app.tinder.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.app.tinder.R
import com.app.tinder.databinding.FragmentBoltDialogBinding

class BoltDialogFragment : DialogFragment() {
    private val width: Int
        get() = WindowManager.LayoutParams.MATCH_PARENT

    private val height: Int
        get() = WindowManager.LayoutParams.MATCH_PARENT
    private lateinit var binding: FragmentBoltDialogBinding

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.setLayout(width, height)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoltDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }
}