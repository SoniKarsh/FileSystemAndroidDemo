package com.karshsoni.filesystemdemo.displayscreens.storageoptions;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sriyanksiddhartha.filesystemdemo.R;
import com.karshsoni.filesystemdemo.displayscreens.InternalStorageDisplay;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class InternalStorageDemo extends AppCompatActivity {

	private EditText etFileName, etMessage, etFileToDelete;
	private TextView txvInternalStoragePath, txvFilesList;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.internal_storage);

		etFileName 		= findViewById(R.id.etFileName);
		etMessage 		= findViewById(R.id.etMessage);
		etFileToDelete 	= findViewById(R.id.etFileToDelete);

		txvInternalStoragePath 	= findViewById(R.id.txvInternalStoragePath);
		txvFilesList 			= findViewById(R.id.txvFilesList);
	}

	public void saveToInternalStorage(View view) {

		String fileName = etFileName.getText().toString();
		String message = etMessage.getText().toString();

        FileOutputStream fos = null;
        try {
//            fos = new FileOutputStream(fileName, true);
            fos = openFileOutput(fileName, Context.MODE_PRIVATE); // Q-2
            fos.write(message.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

	public void moveToDisplayScreen(View view) {
		Intent intent = new Intent(this, InternalStorageDisplay.class);
		startActivity(intent);
	}

	public void showInternalStoragePath(View view) {
        String path = getFilesDir().toString();
        txvInternalStoragePath.setText(path);
	}

	public void showFilesList(View view) {
        String[] listOfFile = fileList();

        StringBuilder stringBuilder = new StringBuilder();

        for (String file: listOfFile) {
            stringBuilder.append(file).append(", ");
        }

        txvFilesList.setText(stringBuilder);

	}

	public void deleteFileByName(View view) {

	    boolean isDeleted = deleteFile(etFileToDelete.getText().toString());

	    if(isDeleted){
            Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Failed to Delete", Toast.LENGTH_LONG).show();
        }
    }
}
