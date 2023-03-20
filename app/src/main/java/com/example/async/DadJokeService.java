package com.example.async;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DadJokeService extends AsyncTask<Void,Void,String> {
    //A service that fetches a joke from an API and returns data as JSON
    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder content = null;
        try{
            Thread.sleep(4000);
            URL url = new URL("https://icanhazdadjoke.com/");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept:", "application/json");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return content.toString();
    }
}
