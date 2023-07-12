package com.keshav.internproject.Activity

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.keshav.internproject.R
import com.keshav.internproject.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity() {
    private var binding : ActivityUploadBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        window.statusBarColor = resources.getColor((R.color.white),this.theme)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setSupportActionBar(binding?.toolbarUpload)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbarUpload?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.btnSelectImage?.setOnClickListener {
                    chooseFromGallery()

        }
        binding?.ivCross?.setOnClickListener{
            binding?.ivSelectedImage?.setImageResource(R.drawable.bro)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            if(requestCode == GalleryCode){
                if(data!= null){
                    val contentUri = data.data
                    val SelectedImageBitmap  =MediaStore.Images.Media.getBitmap(this.contentResolver,contentUri)
                    binding?.ivSelectedImage?.setImageBitmap(SelectedImageBitmap)
                }
            }
        }
    }
    private fun chooseFromGallery() {
        //Toast.makeText(this,"Upload thi",Toast.LENGTH_LONG).show()
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                        val GalleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

                        startActivityForResult(GalleryIntent, GalleryCode)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>,
                    token: PermissionToken
                ) {
                    showRationalDialog()
                }
            }).onSameThread().check()
    }
    private fun showRationalDialog() {
AlertDialog.Builder(this@UploadActivity).setMessage(
    "You have not selected Storage Permission"
)
    .setPositiveButton("Go To Setting"){
        _,_->
        try {
            val intentSetting = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package",packageName,null)
            intentSetting.data = uri
            startActivity(intentSetting)
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
    .setNegativeButton("cancel"){
        dialog,_->
            dialog.dismiss()


    }.show()
    }
        companion object{
            private const val GalleryCode =1
        }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}