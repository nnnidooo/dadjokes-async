package com.example.async;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.nio.channels.AsynchronousByteChannel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView view = findViewById(R.id.hello);

        DadJokeService dadJokeService = new DadJokeService(view);
        dadJokeService.execute();
    }
}