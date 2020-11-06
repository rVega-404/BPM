package com.example.bpm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class Info_act extends AppCompatActivity {
    private VideoView vd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_act);

        vd = (VideoView)findViewById(R.id.vd);
        String ruta = "android.resource://" + getPackageName() + "/" + R.raw.video;

        Uri uri = Uri.parse(ruta);
        vd.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        vd.setMediaController(mediaController);
    }

    public void Maps(View view) {
        Intent intent = new Intent(this, Maps_act.class);
        startActivity(intent);
    }
}