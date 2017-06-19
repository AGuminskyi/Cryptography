//package com.android.huminskiy1325.creptography1;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.ListFragment;
//import android.view.LayoutInflater;
//import android.view.TextureView;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by cubru on 11.03.2017.
// */
//
//public class DataListFragment extends ListFragment {
//
//    private static List<Data> mData;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mData = DataSaver.get(getActivity()).getDatas();
//
////        ArrayAdapter<Data> arrayAdapter = new ArrayAdapter<Data>(getActivity(), android.R.layout.simple_list_item_1, mData);
//        DataAdapter arrayAdapter = new DataAdapter(mData);
//        setListAdapter(arrayAdapter);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);
//
//
//        return super.onCreateView(inflater, container, savedInstanceState);
//    }
//
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        Data data = ((DataAdapter) getListAdapter()).getItem(position);
//    }
//
//    private class DataAdapter extends ArrayAdapter<Data> {
//
//        public DataAdapter(List<Data> datas) {
//            super(getActivity(), 0, datas);
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null)
//                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_data, null);
//
//            Data data = getItem(position);
//
//            TextView fileNameTextView = (TextView) convertView.findViewById(R.id.file_name_tv);
//            fileNameTextView.setText(data.getmFileDataName());
//            TextView fileDataTextView = (TextView) convertView.findViewById(R.id.file_data_tv);
//            fileDataTextView.setText(data.getmFileData());
//            return convertView;
//        }
//    }
//}
