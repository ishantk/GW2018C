package com.auribises.gw2018c;

import android.app.ProgressDialog;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class SpeechRecognitionActivity extends AppCompatActivity
        implements View.OnClickListener, RecognitionListener{

    TextView txtVoiceData;
    Button btnSpeak;

    SpeechRecognizer speechRecognizer;

    ProgressDialog progressDialog;

    HashMap<String,String> queAns;
    TextToSpeech textToSpeech;

    void initViews(){
        txtVoiceData = findViewById(R.id.textViewVoiceData);
        btnSpeak = findViewById(R.id.buttonSpeak);
        btnSpeak.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Listening...");



        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(this);
    }

    void prepareAueAns(){
        queAns = new HashMap<>();
        queAns.put("How are you","I am fine. Thank you !!");
        queAns.put("Call My Dad","Calling Dad !!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_recognition);
        initViews();
        prepareAueAns();
    }

    @Override
    public void onClick(View v) {

        Intent intent = RecognizerIntent.getVoiceDetailsIntent(this);
        speechRecognizer.startListening(intent);

    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {
        progressDialog.show();
    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {

    }

    @Override
    public void onResults(Bundle results) {

        ArrayList<String> voiceTextResults = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

        if(voiceTextResults !=null && voiceTextResults.size()>0){
            String closestMatch = voiceTextResults.get(0);
            txtVoiceData.setText(closestMatch);
        }else{
            txtVoiceData.setText("Sorry!! I Could'nt Understand !!");
        }

        progressDialog.dismiss();
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
