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

import com.android.huminskiy1325.creptography1.Cryptography.CryptographyStirl;
import com.android.huminskiy1325.creptography1.R;

/**
 * Created by cubru on 18.05.2017.
 */

public class StirlFragment extends SingleAbstractFragment {
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
                loadData(mKey, mEditText);
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
                saveData(mFileNameInput, mEditText);
            }
        });

        Button mReadFromFileButton;
        mReadFromFileButton = (Button) view.findViewById(R.id.read_from_file_button);
        mReadFromFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData(mKey, mEditText);
            }
        });


        return view;
    }

    @Override
    protected void loadData(EditText mKey, EditText mEditText) {
        super.loadData(mKey, mEditText);
    }

    @Override
    protected void saveData(EditText mFileNameInput, EditText mEditText) {
        super.saveData(mFileNameInput, mEditText);
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


}
