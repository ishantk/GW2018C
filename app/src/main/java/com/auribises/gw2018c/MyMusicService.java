package com.auribises.gw2018c;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class MyMusicService extends Service {

    String songToPlay;
    MediaPlayer mediaPlayer;

    public MyMusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyMusicService","==onCreate==");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyMusicService","==onStartCommand==");

        songToPlay = intent.getStringExtra("keySong");
        String path = Environment.getExternalStorageDirectory().getPath()+"/Music/"+songToPlay;

        String url = "https://somedomain.com/somesong.mp3";

        mediaPlayer = new MediaPlayer();

        try{
            mediaPlayer.setDataSource(path);

            //mediaPlayer.setDataSource(this, Uri.parse(url));

            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (Exception e){
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        Log.i("MyMusicService","==onDestroy==");
    }
}
