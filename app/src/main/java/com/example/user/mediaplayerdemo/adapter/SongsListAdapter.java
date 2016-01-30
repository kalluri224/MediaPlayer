package com.example.user.mediaplayerdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.mediaplayerdemo.R;
import com.example.user.mediaplayerdemo.activity.SongsListActivity;
import com.example.user.mediaplayerdemo.model.SongsModel;
import java.util.ArrayList;


public class SongsListAdapter extends BaseAdapter {
    private final ArrayList<SongsModel>songsModels;
    private final Activity activity;
    private LayoutInflater inflate;
    public SongsListAdapter(SongsListActivity songsListActivity, ArrayList<SongsModel> songsModels) {
        this.activity=songsListActivity;
        this.songsModels=songsModels;
    }

    @Override
    public int getCount() {
        return songsModels.size();
    }

    @Override
    public Object getItem(int position) {
        return songsModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            inflate = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            view = inflate.inflate(R.layout.media_list_item_view, null);
        TextView lblFileName = (TextView) view.findViewById(R.id.file_name);
        TextView lblFineNumber= (TextView) view.findViewById(R.id.file_number);
        final SongsModel mediaFileListModel = songsModels.get(position);
        lblFileName.setText(mediaFileListModel.getFileName());
        lblFineNumber.setText(String.valueOf(mediaFileListModel.getSong_number()));
        return view;
    }
}
