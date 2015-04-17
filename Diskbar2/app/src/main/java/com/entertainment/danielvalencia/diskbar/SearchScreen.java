package com.entertainment.danielvalencia.diskbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by daniel.valencia on 16/04/2015.
 */
public class SearchScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_search);

        Button button_ok = (Button) findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText search_place = (EditText) findViewById(R.id.search_place);
                EditText search_description = (EditText) findViewById(R.id.search_description);
                EditText search_style = (EditText) findViewById(R.id.search_style);

                String place = search_place.getText().toString();
                String description = search_description.getText().toString();
                String style = search_style.getText().toString();

                callMainActivity(place, description, style);
            }
        });
        Button button_cancel = (Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnMainActivity();
            }
        });

    }

    private void callMainActivity(String place, String description, String style) {

        Intent i = new Intent(this, MainScreen.class );
        if (place != null && !place.isEmpty()) i.putExtra("place", place);
        if (description != null && !description.isEmpty()) i.putExtra("description", description);
        if (style != null && !style.isEmpty()) i.putExtra("style", style);
        startActivity(i);
    }

    private void returnMainActivity() {

        Intent i = new Intent(this, MainScreen.class );
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_detail_screen, menu);
        return true;
    }

}