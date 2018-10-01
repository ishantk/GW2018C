package com.auribises.gw2018c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.textViewSongName)
    TextView txtSongName;

    @BindView(R.id.buttonPlay)
    Button btnPlay;

    @BindView(R.id.buttonStop)
    Button btnStop;

    String songToPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ButterKnife.bind(this);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        Intent rcv = getIntent();
        songToPlay = rcv.getStringExtra("keySong");
        txtSongName.setText(songToPlay);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        Intent intent = new Intent(PlayActivity.this,MyMusicService.class);
        intent.putExtra("keySong",songToPlay);

        if(id == R.id.buttonPlay){
            startService(intent);
        }else{
            stopService(intent);
        }

    }
}
