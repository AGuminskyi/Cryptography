//package com.android.huminskiy1325.creptography1;
//
//import android.app.Application;
//import android.content.Context;
//import android.os.Environment;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.lang.reflect.Type;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
///**
// * Created by cubru on 11.03.2017.
// */
//
//public class DataSaver {
//
//    private static final String TAG = "TAG";
//    private List<Data> datas;
//    private Context mAppContext;
//    private static DataSaver sDataSaver;
//    private static String FILE_NAME = "creptography1.json";
//
//    private DataSaver(Context mContext) {
//        mAppContext = mContext;
//        try {
//            datas = loadData();
//        } catch (Exception e) {
//            Log.i(TAG, "ERROR LOADING DATA" + e.toString());
//            Toast.makeText(mAppContext, "Error loading data", Toast.LENGTH_SHORT).show();
//            datas = new ArrayList<Data>();
//        }
//    }
//
//    public void addData(Data data) {
//        datas.add(data);
//    }
//
//    public void deleteData(Data data) {
//        datas.remove(data);
//    }
//
//    public List<Data> getDatas() {
//        return datas;
//    }
//
//    static DataSaver get(Context context) {
//        if (sDataSaver == null) {
//            sDataSaver = new DataSaver(context.getApplicationContext());
//        }
//        return sDataSaver;
//    }
//
//    public Data getData(UUID id) {
//        for (Data data : datas)
//            if (data.getmID().equals(id)) {
//                return data;
//            }
//        return null;
//    }
//
//
//
//    /* public void saveData() throws IOException {
//        Writer writer = null;
//
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        String dir = Environment.getExternalStorageDirectory()+File.separator+ "lol";
//        File testFile = new File(mAppContext.getExternalFilesDir(null),"lol.txt");
//        Log.i(TAG,"Test file path = " + testFile.getAbsolutePath());
//        FileOutputStream outputStream = null;
//        outputStream = new FileOutputStream(testFile);
//        outputStream.write("Hello from android app ".getBytes(Charset.forName("UTF-8")));
//        outputStream.close();
//
//        try {
//            FileOutputStream out = null;
//            if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
//                out = mAppContext.openFileOutput(Environment.getExternalStorageDirectory().getAbsolutePath() +
//                        "/Android/data/com.android.huminskiy1325.creptography1/" + FILE_NAME, Context.MODE_PRIVATE);
//                Log.i(TAG, "External envtiroment" + Environment.getExternalStorageDirectory().toString());
//            } else
//                out = mAppContext.openFileOutput(FILE_NAME, mAppContext.MODE_PRIVATE);
//            writer = new OutputStreamWriter(out);
//            writer.write(gson.toJson(datas));
//            Log.i(TAG, gson.toJson(datas));
//        } catch (IOException e) {
//            Log.i(TAG, e.toString());
//            Toast.makeText(mAppContext, "Failed with saving data", Toast.LENGTH_SHORT).show();
//        } finally {
//            if (writer != null)
//                writer.flush();
//                writer.close();
//        }
//    }
//    */
//
//    public List<Data> loadData() {
//        List<Data> datas = new ArrayList<>();
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        BufferedReader reader = null;
//        File mFile = new File(mAppContext.getExternalFilesDir(null), "123.txt");
//        try {
//            FileInputStream in = null;
//            if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)) {
//                mAppContext.openFileInput(Environment.getExternalStorageDirectory().getAbsolutePath() +
//                        "/Android/data/com.android.huminskiy1325.creptography1/" + FILE_NAME);
//                Log.i(TAG, "External envtiroment" + Environment.getExternalStorageDirectory().toString());
//            } else
//                in = mAppContext.openFileInput(FILE_NAME);
//            reader = new BufferedReader(new InputStreamReader(in));
//            StringBuilder jsonString = new StringBuilder();
//            String line = null;
//            while ((line = reader.readLine()) != null)
//                jsonString.append(line);
//
//            line = jsonString.toString();
//
//            Type item = new TypeToken<List<Data>>() {
//            }.getType();
//            datas = (gson.fromJson(line, item));
//            Log.i(TAG, "DATAS: " + datas.toString());
//        } catch (FileNotFoundException ignored) {
//
//        } catch (IOException e) {
//            Log.i(TAG, e.toString());
//        }
//
//        return datas;
//    }
//
//}
