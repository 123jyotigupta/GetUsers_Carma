package com.example.android.getusers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.android.getusers.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Listener{

    private Spinner options;
    private ListView list;

    private String[] locations;
    private String[] mySelectedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locations = new String[]{
                getString(R.string.san_francisco),
                getString(R.string.cork)
        };

        options = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,locations);
        options.setAdapter(adapter);
        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CustomLocation loc = CustomLocation.getLocation(locations[position], MainActivity.this);
                mySelectedLocation = new String[]{
                        loc.getLatitude(), loc.getLongitude()
                };
                NetworkConnection connection = new NetworkConnection(MainActivity.this);
                connection.execute(mySelectedLocation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                CustomLocation loc = CustomLocation.getLocation(getString(R.string.san_francisco), MainActivity.this);
                mySelectedLocation = new String[]{
                        loc.getLatitude(), loc.getLongitude()
                };
                NetworkConnection connection = new NetworkConnection(MainActivity.this);
                connection.execute(mySelectedLocation);
            }
        });
        list = (ListView)findViewById(R.id.listView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void retrivedInformation(ArrayList<User> users) {
        ListItemAdapter mAdapter = new ListItemAdapter(MainActivity.this,users);
        list.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
