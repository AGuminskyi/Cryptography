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
import com.android.huminskiy1325.creptography1.Cryptography.CryptographyCaesar;
import com.android.huminskiy1325.creptography1.Cryptography.CryptographyGamma;

import com.android.huminskiy1325.creptography1.Data.Data;
import com.android.huminskiy1325.creptography1.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import ir.sohreco.androidfilechooser.ExternalStorageNotAvailableException;
import ir.sohreco.androidfilechooser.FileChooserDialog;

/**
 * Created by cubru on 26.04.2017.
 */

public class GammaFragment extends Fragment {
    private Cryptography cryptography;

    private EditText mKeyPvch;
    private EditText mEditText;
    private EditText mFileNameInput;
//    private EditText param_T;
    private String mInputText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cryptography_gamma_fragment, container, false);

        mEditText = (EditText) view.findViewById(R.id.editText);
        mInputText = mEditText.getText().toString();
        mKeyPvch = (EditText) view.findViewById(R.id.input_key);


        Button mEncryptButton;
        mEncryptButton = (Button) view.findViewById(R.id.encrept_button);
        mEncryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getInputData()) {
                    try {
                        mEditText.setText(cryptography.encrypt(mInputText));
                    } catch (BadPaddingException | IllegalBlockSizeException | UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button mDiscryptButton;
        mDiscryptButton = (Button) view.findViewById(R.id.discrypt_button);
        mDiscryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getInputData())
                    try {
                        mEditText.setText(cryptography.decrypt(mInputText));
                    } catch (BadPaddingException | IllegalBlockSizeException | IOException e) {
                        e.printStackTrace();
                    }
            }
        });

        mFileNameInput = (EditText) view.findViewById(R.id.file_name_input);

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
                loadData();
            }
        });

        ImageButton mClearButton;
        mClearButton = (ImageButton) view.findViewById(R.id.cleanInformation);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
            }
        });

        return view;
    }

    public void saveData() {
        String mFileName = mFileNameInput.getText().toString();
        Data data = new Data(mFileName);
        data.setmFileData(mEditText.getText().toString());
        data.saveData(getActivity());
        Toast.makeText(getActivity(), "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
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
                    mEditText.setText(txtStringBuilder.toString());
                    mFileNameInput.setText(fileName);
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

    public boolean getInputData() {
        mInputText = mEditText.getText().toString();

        if (mKeyPvch.getText().toString().isEmpty() || mKeyPvch.getText().toString().length() >= 8) {
            Toast.makeText(getActivity(), "Please, input correct KEY number", Toast.LENGTH_SHORT).show();
            return false;
        } else {
//            int param = Integer.parseInt(param_T.getText().toString());
//            if(param <= mInputText.length()){
//                Toast.makeText(getActivity(),"Incorrect T- parametr", Toast.LENGTH_SHORT).show();
//                return false;
//            }

            int key = Integer.parseInt(mKeyPvch.getText().toString());
            cryptography = new CryptographyGamma(key);
            return true;
        }
    }
}
