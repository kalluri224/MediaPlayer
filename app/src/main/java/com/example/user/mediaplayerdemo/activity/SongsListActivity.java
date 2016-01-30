package com.example.user.mediaplayerdemo.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.user.mediaplayerdemo.R;
import com.example.user.mediaplayerdemo.adapter.SongsListAdapter;
import com.example.user.mediaplayerdemo.model.SongsModel;

import java.util.ArrayList;


public class SongsListActivity extends AppCompatActivity {
    private ArrayList<SongsModel>songsModels;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs_list);
        ListView listView = (ListView) findViewById(R.id.audio_listView);
        songsModels=new ArrayList<>();
        getMusicList();
        SongsListAdapter songsListAdapter = new SongsListAdapter(this, songsModels);
        listView.setAdapter(songsListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SongsModel model = songsModels.get(position);
                Intent intent = new Intent(getApplicationContext(), MediaPlayerActivity.class);
                intent.putExtra("filePath", model.getFilePath());
                intent.putExtra("fileName", model.getFileName());
                intent.putExtra("fileNumber", model.getSong_number());
                startActivity(intent);
            }
        });
    }
    private void getMusicList() {
        @SuppressWarnings("deprecation") final Cursor mCursor = managedQuery(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media.DISPLAY_NAME, MediaStore.Audio.Media.DATA}, null, null,
                "LOWER(" + MediaStore.Audio.Media.TITLE + ") ASC");
        Log.d("audio list", "" + mCursor.getCount());
        if (mCursor.moveToFirst()) {
            int i=1;
            do {
                SongsModel mediaFileListModel = new SongsModel();
                mediaFileListModel.setFileName(mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
                mediaFileListModel.setFilePath(mCursor.getString(mCursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                mediaFileListModel.setSong_number(i);
                songsModels.add(mediaFileListModel);
                i++;
            } while (mCursor.moveToNext());
        }
        mCursor.close();
    }

}