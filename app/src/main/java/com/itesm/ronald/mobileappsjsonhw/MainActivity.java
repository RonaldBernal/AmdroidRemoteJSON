package com.itesm.ronald.mobileappsjsonhw;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{
    private boolean expanded;
    private String jsonURL = "https://raw.githubusercontent.com/RonaldBernal/AmdroidRemoteJSON/master/Contacts.json";
    private ListView list;
    private ArrayList<Friend> friends = new ArrayList<Friend>();
    private FriendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.expanded = false;
        this.list = (ListView)findViewById(R.id.listView);
        this.adapter = new FriendAdapter(this, this.friends);
        this.list.setAdapter(this.adapter);
        this.list.setOnItemClickListener(this);
    }

    public void retrieveJSON(View v){
        JsonRequest jreq = new JsonRequest(this, this.adapter, this.friends, list);
        jreq.execute(jsonURL);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.adapter.tooglevisibility(this.list, position, this.expanded);
        this.expanded = !this.expanded;
    }
}
