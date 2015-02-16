package com.itesm.ronald.mobileappsjsonhw;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Ronald on 2/16/2015.
 */
public class JsonRequest extends AsyncTask<String, Void, ArrayList<Friend>> {
    private Activity activity;
    private ProgressDialog dialog;
    private ArrayList<Friend> friends = new ArrayList<Friend>();
    private FriendAdapter adapter;
    private ListView list;

    public JsonRequest(Activity activity, FriendAdapter adapter, ArrayList<Friend> friends, ListView list){
        this.activity = activity;
        this.friends = friends;
        this.adapter = adapter;
        this.list = list;
        //this.dialog = new ProgressDialog(activity.getApplicationContext());
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        //this.dialog.setTitle("Loading Videos");
        //this.dialog.show();

    }

    @Override
    protected ArrayList<Friend> doInBackground(String... params) {

        HttpGet get = new HttpGet(params[0]);
        StringBuilder sb = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        JSONObject result = null;

        try{
            HttpResponse response = client.execute(get);
            StatusLine sl = response.getStatusLine();
            int code = sl.getStatusCode();

            if(code == 200) {

                HttpEntity entity = response.getEntity();
                InputStream is = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String currentLine;
                while ((currentLine = br.readLine()) != null) {
                    sb.append(currentLine);
                }

                result = new JSONObject(sb.toString());

                JSONArray contacts = result.getJSONArray("Contacts");
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject contact = contacts.getJSONObject(i);
                    this.friends.add(
                        new Friend(
                            contact.getString("Name"),
                            contact.getString("Hobby"),
                            contact.getString("Age"),
                            contact.getString("Phone"),
                            contact.getString("Address")
                        )
                    );
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this.friends;
    }


    @Override
    protected void onPostExecute(ArrayList<Friend> result){
        this.adapter = new FriendAdapter(activity, result, 0);
        this.list.setAdapter(this.adapter);
 //       this.adapter.notifyDataSetChanged();
        //this.dialog.dismiss();
    }

}