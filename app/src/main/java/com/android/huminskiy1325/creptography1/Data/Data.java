package com.android.huminskiy1325.creptography1.Data;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

import ir.sohreco.androidfilechooser.ExternalStorageNotAvailableException;
import ir.sohreco.androidfilechooser.FileChooserDialog;

/**
 * Created by cubru on 11.03.2017.
 */

public class Data {

    private String mFileData;
    private String mFileDataName;

    private String getmFileDataName() {
        return mFileDataName;
    }


    public Data(String mFileDataName) {
        this.mFileDataName = mFileDataName;
    }

    private String getmFileData() {
        return mFileData;
    }

    public void setmFileData(String mFileData) {
        this.mFileData = mFileData;
    }

    public void saveData(Context context){
            File testFile = new File(context.getExternalFilesDir(null),getmFileDataName() + ".txt");
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(testFile);
            outputStream.write(getmFileData().getBytes(Charset.forName("UTF-8")));
            outputStream.close();
        } catch (IOException e) {
            Toast.makeText(context, "ERROR with saving data",Toast.LENGTH_SHORT).show();
            String DATA_TAG = "DATA_TAG";
            Log.i(DATA_TAG, e.toString());
        }
    }


}

