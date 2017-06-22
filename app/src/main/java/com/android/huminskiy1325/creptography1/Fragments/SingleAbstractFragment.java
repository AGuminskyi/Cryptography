package com.android.huminskiy1325.creptography1.Fragments;

import android.app.Activity;

import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.Toast;

import com.android.huminskiy1325.creptography1.Data.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import ir.sohreco.androidfilechooser.ExternalStorageNotAvailableException;
import ir.sohreco.androidfilechooser.FileChooserDialog;

/**
 * Created by cubru on 21.06.2017.
 */

public class SingleAbstractFragment extends Fragment {

    protected void saveData(EditText mFileNameInput, EditText mEditText) {
        String mFileName = mFileNameInput.getText().toString();
        Data data = new Data(mFileName);
        data.setmFileData(mEditText.getText().toString());
        data.saveData(getActivity());
        Toast.makeText(getActivity(), "Data saved", Toast.LENGTH_SHORT).show();
    }

    protected void loadData(final EditText mKey, final EditText mEditText) {
        FileChooserDialog.Builder builder = new FileChooserDialog.Builder(FileChooserDialog.ChooserType.FILE_CHOOSER, new FileChooserDialog.ChooserListener() {
            @Override
            public void onSelect(String path) {

                File file = new File(path);
                String fileName = file.getName();

                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    StringBuilder txtStringBuilder = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null)
                        txtStringBuilder.append(line);

                    reader.close();
                    fileName = fileName.substring(0, fileName.indexOf("."));
                    mKey.setText(txtStringBuilder.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            builder.build().show(getActivity().getSupportFragmentManager(), null);
        } catch (ExternalStorageNotAvailableException e) {
            e.printStackTrace();
        }
    }
}
