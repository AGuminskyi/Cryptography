//package com.android.huminskiy1325.creptography1;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Environment;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//
//public class MainActivity extends Activity {
//
//    private Cryptography cryptography;
//
//    private static EditText mKey;
//    private static EditText mEditText;
//    private static EditText mFileNameInput;
//    private static String mInputText;
//    private static String mFileName;
//    private static int key;
//
//    private static final String mTitleText = "Ваше расшифрованное значение";
//    private static final String TAG = "myLogs";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.cryptography_caesar_fragment);
//
//        mEditText = (EditText) findViewById(R.id.editText);
//        mKey = (EditText) findViewById(R.id.input_key);
//
//        Button mEncryptButton;
//        mEncryptButton = (Button) findViewById(R.id.encrept_button);
//        mEncryptButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(getInputData())
//                    mEditText.setText(cryptography.encrypt(mInputText));
//
//                Log.i(TAG,getApplicationContext().getCacheDir().toString());
//
//            }
//        });
//        Button mDiscryptButton;
//        mDiscryptButton = (Button) findViewById(R.id.discrypt_button);
//        mDiscryptButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(getInputData())
//                    mEditText.setText(cryptography.decrypt(mInputText));
//            }
//        });
//
//        mFileNameInput = (EditText) findViewById(R.id.file_name_input);
//
//        Button mWriteToFileButton;
//        mWriteToFileButton = (Button) findViewById(R.id.write_to_file_button);
//        mWriteToFileButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    saveData();
//                } catch (Exception e) {
//                    Toast.makeText(getApplication(), "Error  saving data", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//
//        Button mReadFromFileButton;
//        mReadFromFileButton = (Button) findViewById(R.id.read_from_file_button);
//        mReadFromFileButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    mEditText.setText(loadData());
//                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(), "Error loading data", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        ImageButton mClearButton;
//        mClearButton = (ImageButton)findViewById(R.id.cleanInformation);
//        mClearButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditText.setText("");
//            }
//        });
//    }
//
//    public boolean getInputData() {
//        mInputText = mEditText.getText().toString();
//        if (mKey.getText().toString().isEmpty() || mKey.getText().toString().length() >= 8) {
//            Toast.makeText(getApplicationContext(), "Please, input correct KEY number", Toast.LENGTH_SHORT).show();
//            return false;
//        } else {
//            key = Integer.parseInt(mKey.getText().toString());
//            cryptography = new CryptographyCaesar(key);
//            return true;
//        }
//    }
//
//    public void saveData() throws IOException {
//        Writer writer = null;
//        mFileName = mFileNameInput.getText().toString();
//        if(mFileName.isEmpty()){
//            Toast.makeText(getApplicationContext(),"Please enter name of file", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        try {
//            OutputStream outputStream = null;
//            if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
//                outputStream = getApplicationContext().openFileOutput(Environment.getExternalStorageDirectory().getAbsolutePath()
//                        + "/Android/data/com.android.huminskiy1325.creptography1/" + mFileName + ".txt", Context.MODE_PRIVATE);
//            } else
//                outputStream = getApplicationContext().openFileOutput(mFileName +".txt", Context.MODE_PRIVATE);
//            writer = new OutputStreamWriter(outputStream);
//            writer.write(mEditText.getText().toString());
//            Toast.makeText(getApplicationContext(), "Data saves to file", Toast.LENGTH_SHORT).show();
//        } finally {
//            if (writer != null)
//                writer.close();
//        }
//    }
//
//    public String loadData() throws IOException {
//        mFileName = mFileNameInput.getText().toString();
//        BufferedReader reader = null;
//        String finalLine = null;
//        try {
//            InputStream inputStream = null;
//            if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED))
//                inputStream = getApplicationContext().openFileInput(Environment.getExternalStorageDirectory().getAbsolutePath() +
//                        "/Android/data/com.android.huminskiy1325.creptography1/" + mFileName + ".txt");
//            else
//                inputStream = getApplicationContext().openFileInput(mFileName +".txt");
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//            StringBuilder lineString = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null) {
//                lineString.append(line);
//            }
//            finalLine = lineString.toString();
//        } catch (FileNotFoundException e) {
//
//        } finally {
//            if (reader != null)
//                reader.close();
//        }
//
//        return finalLine;
//    }
//}
