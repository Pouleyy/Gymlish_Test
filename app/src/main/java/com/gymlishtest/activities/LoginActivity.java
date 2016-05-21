package com.gymlishtest.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.gymlishtest.R;


/**
 * Created by Kévin on 20/05/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private String pseudo;
    private String mdp;
    private String refPseudo;
    private String refmdp;
    private EditText pseudoEdit;
    private EditText mdpEdit;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //EditText editText = (EditText) findViewById(R.id.pseudo);
        startVideo();
        Button login = (Button) findViewById(R.id.login);
        Button signup = (Button) findViewById(R.id.signUp);
        pseudoEdit = (EditText) findViewById(R.id.pseudo);
        mdpEdit = (EditText) findViewById(R.id.mdp);
        getUserData();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pseudo = pseudoEdit.getText().toString();
                mdp = mdpEdit.getText().toString();
                getUserData();
                if(refPseudo.equals("null") || refmdp.equals("null")) {
                    Toast.makeText(LoginActivity.this, "Première connexion, inscrit toi avant",
                            Toast.LENGTH_SHORT).show();
                }else if(pseudo.equals(refPseudo) && mdp.equals(refmdp)) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(LoginActivity.this, "Vous êtes connecté en tant que " + pseudo,
                            Toast.LENGTH_SHORT).show();
                    finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Mauvais pseudo ou mot de passe, " +
                            "veuillez réessayer", Toast.LENGTH_SHORT).show();
                        YoYo.with(Techniques.Shake).duration(700).playOn(pseudoEdit);
                        YoYo.with(Techniques.Shake).duration(700).playOn(mdpEdit);
                    }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((pseudoEdit.getText().toString().equals("")) || (mdpEdit.getText().toString().equals(""))) {
                    Toast.makeText(LoginActivity.this, " Veuillez à bien compléter le pseudo " +
                            "et le mot de passe", Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Shake).duration(700).playOn(pseudoEdit);
                    YoYo.with(Techniques.Shake).duration(700).playOn(mdpEdit);
                }else {
                    pseudo = pseudoEdit.getText().toString();
                    mdp = mdpEdit.getText().toString();
                    registerUserData();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    Toast.makeText(LoginActivity.this, "Vous êtes connecté en tant que " + pseudo,
                            Toast.LENGTH_SHORT).show();
                    finish();

                }
            }
        });

    }

    private void getUserData() {
        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        refPseudo = sharedPreferences.getString("pseudo", "null");
        refmdp = sharedPreferences.getString("mdp", "null");
    }
    private void registerUserData() {
        sharedPreferences = getSharedPreferences("userData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("pseudo", pseudo);
        editor.putString("mdp", mdp);
        editor.apply();
    }




   public void startVideo() {
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videobackground);
        videoView.setVideoURI(uri);
        boolean music = false;
        if(((AudioManager) getSystemService(Context.AUDIO_SERVICE)).isMusicActive()){
            music = true;
        }
        videoView.start();
        final boolean finalMusic = music;
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if(finalMusic){
                    Intent i = new Intent("com.android.music.musicservicecommand");
                    i.putExtra("command", "play");
                    sendBroadcast(i);
                }
                mp.setLooping(true);
            }
        });
    }

    public void onResume(){
        super.onResume();
        startVideo();
    }

    @Override
    public void onPause(){
        super.onPause();
    }
}
