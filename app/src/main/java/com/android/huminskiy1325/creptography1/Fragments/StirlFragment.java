package com.android.huminskiy1325.creptography1.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.huminskiy1325.creptography1.Cryptography.Cryptography;
import com.android.huminskiy1325.creptography1.Cryptography.CryptographyStirl;
import com.android.huminskiy1325.creptography1.Data.Data;
import com.android.huminskiy1325.creptography1.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import ir.sohreco.androidfilechooser.ExternalStorageNotAvailableException;
import ir.sohreco.androidfilechooser.FileChooserDialog;

/**
 * Created by cubru on 18.05.2017.
 */

public class StirlFragment extends Fragment {
    private CryptographyStirl cryptography;

    private EditText mEditText;
    private EditText mFileNameInput;
    private String mInputText;
    private EditText mKey;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.cryptography_stirl_fragment, container, false);

        mEditText = (EditText) view.findViewById(R.id.editText);
        mKey = (EditText) view.findViewById(R.id.input_key);
       // mInputText = mEditText.getText().toString();
        mFileNameInput = (EditText) view.findViewById(R.id.file_name_input);

        Button readTxtFromFile = (Button) view.findViewById(R.id.read_text_from_file_button);
        readTxtFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData(true);
            }
        });

        Button mEncryptButton = (Button) view.findViewById(R.id.encrept_button);
        mEncryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cryptography = new CryptographyStirl();
                mInputText = mEditText.getText().toString();
                String key = getFormatText();
                cryptography.setInputKey(key);
                String string = cryptography.encrypt(mInputText);
                if (string.length() == 1) {
                    Toast.makeText(getActivity(), "В вашем литературном тексте отсутствует " +
                            "символ \"" + string + "\"", Toast.LENGTH_LONG).show();
                } else {
                    mEditText.setText(cryptography.encrypt(mInputText));
                }
            }
        });

        Button mDiscryptButton;
        mDiscryptButton = (Button) view.findViewById(R.id.discrypt_button);
        mDiscryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cryptography = new CryptographyStirl();
                mInputText = mEditText.getText().toString();
                String key = getFormatText();
                cryptography.setInputKey(key);
                mEditText.setText(cryptography.decrypt(mInputText));
            }
        });

        ImageButton mClearButton1;
        mClearButton1 = (ImageButton) view.findViewById(R.id.cleanInformation);
        mClearButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });

        ImageButton mClearButton2 = (ImageButton) view.findViewById(R.id.cleanInformation_text);
        mClearButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKey.setText("");
            }
        });


        Button mWriteToFileButton;
        mWriteToFileButton = (Button) view.findViewById(R.id.write_to_file_button);
        mWriteToFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        Button mReadFromFileButton;
        mReadFromFileButton = (Button) view.findViewById(R.id.read_from_file_button);
        mReadFromFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData(false);
            }
        });


        return view;
    }

    private String getFormatText() {
        StringBuilder builder = new StringBuilder();
        char[] key = mKey.getText().toString().toCharArray();
        int lenth = 0;
        for (char format : key) {
            if (lenth % 30 == 0 && lenth != 0) {
                builder.append('\n');
                lenth++;
            }
            builder.append(format);
            lenth++;
        }
        return builder.toString();
    }

    private void saveData() {
        String mFileName = mFileNameInput.getText().toString();
        Data data = new Data(mFileName);
        data.setmFileData(mEditText.getText().toString());
        data.saveData(getActivity());
        Toast.makeText(getActivity(), "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData(boolean flag) {
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
                    if (true) {
                        mKey.setText(txtStringBuilder.toString());
                    } else {
                        mEditText.setText(txtStringBuilder.toString());
                        mFileNameInput.setText(fileName);
                    }
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
