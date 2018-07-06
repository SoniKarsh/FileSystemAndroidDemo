package com.karshsoni.filesystemdemo.displayscreens.storageoptions;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sriyanksiddhartha.filesystemdemo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ExternalStorageDemo extends AppCompatActivity {

	private EditText etExternalPrivate, etExternalPublic;
	private TextView txvExternalPrivate, txvExternalPublic;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.external_storage);

		etExternalPrivate = (EditText) findViewById(R.id.etExternalPrivate);
		etExternalPublic = (EditText) findViewById(R.id.etExternalPublic);

		txvExternalPrivate = (TextView) findViewById(R.id.txvExternalPrivate);
		txvExternalPublic = (TextView) findViewById(R.id.txvExternalPublic);
	}

	public void saveToExternalPrivate(View view) {

		String data = etExternalPrivate.getText().toString();

		if(isExternalStorageWritable()){
            File file = getExternalFilesDir("New Folder");

            File childFile = new File(file, "First.txt");

            writeToFile(childFile, data);
        }

	}

	public void saveToExternalPublic(View view) {

	    String data = etExternalPublic.getText().toString();

	    if(isExternalStorageWritable()){
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            Toast.makeText(this, "Directory Created" + file, Toast.LENGTH_SHORT).show();
            Log.d("Created", "saveToExternalPublic: "+file);

            File folder = new File(file, "New Folder/123/12/1/0");

            if(folder.mkdirs()){
                Toast.makeText(this, "Directory Created", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Directory is not Created", Toast.LENGTH_SHORT).show();
            }

            File childFile = new File(folder, "First.txt");

            writeToFile(childFile, data);
        }

	}

	public void loadFromExternalPrivate(View view) {
	    if(isExternalStorageReadable()){
            File file = getExternalFilesDir(null);

            File childFile = new File(file, "First.txt");

            txvExternalPrivate.setText(readFromFile(childFile));
        }

	}

	public void loadFromExternalPublic(View view) {

        if(isExternalStorageReadable()){
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            File folder = new File(file, "New Folder");

            File childFile = new File(folder, "First.txt");

            txvExternalPublic.setText(readFromFile(childFile));
        }

	}

	boolean isExternalStorageWritable(){

	    String state = Environment.getExternalStorageState();

	    if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

    boolean isExternalStorageReadable(){

        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
            return true;
        }
        return false;
    }

    StringBuilder readFromFile(File file){
        FileInputStream fis = null;

        int read;

        StringBuilder stringBuilder = new StringBuilder();

        try {
            fis = new FileInputStream(file);
            while((read = fis.read()) != -1){
                stringBuilder.append((char)read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder;
    }

    void writeToFile(File file, String data){
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
