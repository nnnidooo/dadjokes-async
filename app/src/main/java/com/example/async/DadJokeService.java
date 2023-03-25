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

    WeakReference<TextView> textView; //weakReference er en type

    private String content2;


    public DadJokeService (TextView textView) {
        this.textView = new WeakReference<>(textView);

    }

    //A service that fetches a joke from an API and returns data as JSON
    //Koden forsøger at køre en opgave i en separat tråd, som ikke blokerer hovedtråden.
    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder content = null; //StringBuilder er en klasse i Java, der kan bruges til at oprette og manipulere strengdata.
        try{
            Thread.sleep(2000); //Tråden sover i 2 sekunder ved hjælp af Thread.sleep(2000).
            URL url = new URL("https://icanhazdadjoke.com/"); //En URl oprettes.

            //En HTTP-forbindelse oprettes ved hjælp af openConnection()-metoden på URL-objektet.
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //Anmodningsmetoden indstilles til "GET" ved hjælp af setRequestMethod()-metoden på forbindelsesobjektet.
            con.setRequestMethod("GET");
            //Anmodningen accepterer JSON-svar ved hjælp af setRequestProperty()-metoden på forbindelsesobjektet.
            con.setRequestProperty("Accept:", "application/json");
            //En BufferedReader oprettes ved hjælp af InputStreamReader-klassen, der bruges til at læse data fra inputstrømmen.
            BufferedReader in = new BufferedReader( //StringBuilder-objektet content oprettes for at gemme den data, der læses fra inputstrømmen.
                    new InputStreamReader(con.getInputStream())); //Et input strøm oprettes
            String inputLine;

            content = new StringBuilder();
            //En while-loop bruges til at læse og appende hver linje af data fra inputstrømmen til
            //content ved hjælp af readLine()-metoden på BufferedReader-objektet.
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
