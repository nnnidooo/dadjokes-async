package com.example.async;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class DadJokeService extends AsyncTask <Void,Void,String> {

    WeakReference<TextView> textView;

    private String content2;


    public DadJokeService (TextView textView) {
        this.textView = new WeakReference<>(textView);

    }

    //A service that fetches a joke from an API and returns data as JSON
    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder content = null;
        try{
            Thread.sleep(2000);
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
        return content2 = content.toString();
    }

    //execute dad joke
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
            textView.get().setText(content2);

    }
}
