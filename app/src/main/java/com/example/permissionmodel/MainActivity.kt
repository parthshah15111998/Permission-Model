package com.example.permissionmodel

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.permissionmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var CAMERA_PERMISSION_CODE=123
    private var STORAGE_PERMISSION_CODE=123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cameraPermission.setOnClickListener {
           checkPermission(Manifest.permission.CAMERA,CAMERA_PERMISSION_CODE)
        }
        binding.storagePermission.setOnClickListener {
            checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE,STORAGE_PERMISSION_CODE)
        }

    }

    private fun checkPermission(permission:String,requestCode:Int){
        if (ContextCompat.checkSelfPermission(this@MainActivity,permission)==PackageManager.PERMISSION_DENIED){

            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission),requestCode)
        }else
        {
            Toast.makeText(this@MainActivity,"Permission Granted Already",Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_PERMISSION_CODE){
//            binding.cameraPermission.setText("Permission Grant")
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this@MainActivity,"Camera Permission Granted",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity,"Camera Permission Denied",Toast.LENGTH_LONG).show()
            }
        }else if (requestCode == STORAGE_PERMISSION_CODE){
//            binding.storagePermission.setText("Permission Grant")
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this@MainActivity,"Storage Permission Granted",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity,"Storage Permission Denied",Toast.LENGTH_LONG).show()
            }
        }

    }


}