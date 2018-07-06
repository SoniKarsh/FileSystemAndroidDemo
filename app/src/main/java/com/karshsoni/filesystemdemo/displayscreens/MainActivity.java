package com.karshsoni.filesystemdemo.displayscreens;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.karshsoni.filesystemdemo.displayscreens.storageoptions.CacheStorageDemo;
import com.karshsoni.filesystemdemo.displayscreens.storageoptions.ExternalStorageDemo;
import com.karshsoni.filesystemdemo.displayscreens.storageoptions.InternalStorageDemo;
import com.sriyanksiddhartha.filesystemdemo.R;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 999);
        }

	}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 28:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this, "Permission Granted!!!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Permission Denied!!!", Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void openInternalStorageDemoActivity(View view) {

		Intent intent = new Intent(this, InternalStorageDemo.class);
		startActivity(intent);
	}

	public void openCacheStorageDemoActivity(View view) {

		Intent intent = new Intent(this, CacheStorageDemo.class);
		startActivity(intent);
	}

	public void openExternalStorageDemoActivity(View view) {

		Intent intent = new Intent(this, ExternalStorageDemo.class);
		startActivity(intent);
	}
}
