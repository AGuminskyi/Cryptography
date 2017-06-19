package com.android.huminskiy1325.creptography1.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.huminskiy1325.creptography1.Cryptography.CryptographyDES;
import com.android.huminskiy1325.creptography1.Data.Data;
import com.android.huminskiy1325.creptography1.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import ir.sohreco.androidfilechooser.ExternalStorageNotAvailableException;
import ir.sohreco.androidfilechooser.FileChooserDialog;

/**
 * Created by cubru on 22.05.2017.
 */

public class DesFragment extends android.support.v4.app.Fragment {

    private EditText mKey;
    private EditText mEditText;
    private EditText mFileNameInput;
    private static String mInputText;

    private static SecretKey key;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cryptography_des_fragment, null, false);

        mEditText = (EditText) view.findViewById(R.id.editText);
        mKey = (EditText) view.findViewById(R.id.input_key);
        mFileNameInput = (EditText) view.findViewById(R.id.file_name_input);

        Button mEncryptButton;
        mEncryptButton = (Button) view.findViewById(R.id.encrept_button);
        mEncryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(mKey.getText().toString().length() < 4){
                        Toast.makeText(getActivity(),"Ваш ключ должен быть больше 3 символов", Toast.LENGTH_LONG).show();
                        throw new InvalidKeyException("Key is so short");
                    }
                    key = setKey(mKey.getText().toString());
                    CryptographyDES styrl = new CryptographyDES(key);
                    mEditText.setText(styrl.encrypt(mEditText.getText().toString()));
                } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
                    e.printStackTrace();
                }
            }
        });

        Button mDiscryptButton;
        mDiscryptButton = (Button) view.findViewById(R.id.discrypt_button);
        mDiscryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    key = setKey(mKey.getText().toString());
                    CryptographyDES styrl = new CryptographyDES(key);
                    mEditText.setText(styrl.decrypt(mEditText.getText().toString()));
                } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
                    e.printStackTrace();
                }
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
        return view;
    }

    private SecretKey setKey(String key){
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey sk = skf.generateSecret(dks);
            return sk;
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveData() {
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
}
