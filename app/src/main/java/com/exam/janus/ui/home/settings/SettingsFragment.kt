package com.exam.janus.ui.home.settings

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.exam.janus.MainActivity
import com.exam.janus.R
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class SettingsFragment : Fragment() {

    val REQUEST_IMAGE = 99
    val PERMISSION_CAMERA = 100
    private lateinit var currentPhotoPath: String
    private lateinit var mPhotoUri : Uri

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dispatchTakePictureIntent()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE ) {
            val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, mPhotoUri)
            (activity as MainActivity).imageResult = bitmap
        }
    }

    private fun dispatchTakePictureIntent(){
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
            startCamera()
        } else {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), PERMISSION_CAMERA)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            PERMISSION_CAMERA -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    startCamera()
            }
        }
    }

    private fun startCamera(){
        mPhotoUri = requireActivity().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                ContentValues())!!
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri)
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE)
            }
        }
    }

}