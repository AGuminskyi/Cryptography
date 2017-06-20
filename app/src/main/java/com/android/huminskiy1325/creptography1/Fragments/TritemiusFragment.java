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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.huminskiy1325.creptography1.Cryptography.CryptographyTritemius;

import com.android.huminskiy1325.creptography1.Data.Data;
import com.android.huminskiy1325.creptography1.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import ir.sohreco.androidfilechooser.ExternalStorageNotAvailableException;
import ir.sohreco.androidfilechooser.FileChooserDialog;

/**
 * Created by cubru on 18.03.2017.
 */

public class TritemiusFragment extends Fragment {

    private EditText input_Text_Cryptography;
    private EditText parametr_A;
    private EditText parametr_B;
    private EditText parametr_C;
    private EditText parametr_Phrase;
    private EditText fileNameInput;

    static final String DECRYPT = "decrypt";
    static final String ENCRYPT = "encrypt";

    private RadioButton phrase_RadioButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cryptography_tritemius_fragment, container,false);

        input_Text_Cryptography = (EditText) view.findViewById(R.id.editText);

        parametr_A = (EditText) view.findViewById(R.id.parametr_A);
        parametr_B = (EditText) view.findViewById(R.id.parametr_B);
        parametr_C = (EditText) view.findViewById(R.id.parametr_C);
        parametr_Phrase = (EditText) view.findViewById(R.id.phraseEditText);


        phrase_RadioButton = (RadioButton) view.findViewById(R.id.phraseRadioButton);

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.linear_equationRadioButton:
                        set_EditText_Clikable(parametr_B, true);
                        set_EditText_Clikable(parametr_C, true);

                        set_EditText_Clikable(parametr_A, false);
                        parametr_A.setText("");
                        set_EditText_Clikable(parametr_Phrase, false);
                        parametr_Phrase.setText("");
                        break;
                    case R.id.not_linear_equationRadioButton:
                        set_EditText_Clikable(parametr_A, true);
                        set_EditText_Clikable(parametr_B, true);
                        set_EditText_Clikable(parametr_C, true);

                        set_EditText_Clikable(parametr_Phrase, false);
                        parametr_Phrase.setText("");
                        break;
                    case R.id.phraseRadioButton:
                        set_EditText_Clikable(parametr_A, false);
                        parametr_A.setText("");
                        set_EditText_Clikable(parametr_B, false);
                        parametr_B.setText("");
                        set_EditText_Clikable(parametr_C, false);
                        parametr_C.setText("");

                        set_EditText_Clikable(parametr_Phrase, true);
                        break;
                }
            }
        });

        Button encrypt_Button = (Button) view.findViewById(R.id.encrept_button);
        encrypt_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataConfiguration(ENCRYPT);
            }
        });

        Button decrypt_Button = (Button) view.findViewById(R.id.discrypt_button);
        decrypt_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDataConfiguration(DECRYPT);
            }
        });

        ImageButton clear_Input_Text_Cryptography = (ImageButton) view.findViewById(R.id.cleanInformation);
        clear_Input_Text_Cryptography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_Text_Cryptography.setText("");
            }
        });

        ImageButton clearPhraseInput = (ImageButton) view.findViewById(R.id.cleanPhraseInformation);
        clearPhraseInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parametr_Phrase.setText("");
            }
        });

        fileNameInput = (EditText) view.findViewById(R.id.file_name_input);
        Button saveFileButton = (Button) view.findViewById(R.id.write_to_file_button);
        saveFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        Button loadFileButton = (Button) view.findViewById(R.id.read_from_file_button);
        loadFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        return view;
    }

    private void setDataConfiguration(String method) {
        CryptographyTritemius cryptographyTritemius;
        if (phrase_RadioButton.isChecked()) {
            cryptographyTritemius = new CryptographyTritemius(CryptographyTritemius.KeyChooser.Phrase_Choose);
            cryptographyTritemius.setKey(0, 0, 0, parametr_Phrase.getText().toString());
        } else {
            cryptographyTritemius = new CryptographyTritemius(CryptographyTritemius.KeyChooser.NotLinear_Choose);
            Integer A = 0;
            if (!parametr_A.getText().toString().isEmpty()) {
                A = Integer.parseInt(parametr_A.getText().toString());
            }
            Integer B = Integer.parseInt(parametr_B.getText().toString());
            Integer C = Integer.parseInt(parametr_C.getText().toString());
            cryptographyTritemius.setKey(A, B, C, null);
        }
        if (Objects.equals(method, DECRYPT)) {
            input_Text_Cryptography.setText(cryptographyTritemius.decrypt(input_Text_Cryptography.getText().toString()));
        } else {
            input_Text_Cryptography.setText(cryptographyTritemius.encrypt(input_Text_Cryptography.getText().toString()));
        }

    }

    private void set_EditText_Clikable(EditText editText, boolean isCheked) {
        editText.setFocusableInTouchMode(isCheked);
        editText.setFocusable(isCheked);
        editText.setCursorVisible(isCheked);
    }

    public void saveData() {
        String mFileName = fileNameInput.getText().toString();
        Data data = new Data(mFileName);
        data.setmFileData(input_Text_Cryptography.getText().toString());
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
                    input_Text_Cryptography.setText(txtStringBuilder.toString());
                    fileNameInput.setText(fileName);
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
