package com.entertainment.danielvalencia.diskbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.entertainment.danielvalencia.diskbar.com.entertainment.tools.DownloadImage;

/**
 * Created by daniel.valencia on 16/04/2015.
 */
public class ItemDetail extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        Bundle bundle = getIntent().getExtras();
        String mainImage = bundle.getString("mainImage");
        String textTitle = bundle.getString("textTitle");
        String textStyle = bundle.getString("textStyle");
        String context_large = bundle.getString("text");
        String textAddress = bundle.getString("textAddress");
        String textWebPage = bundle.getString("textWebPage");

        new DownloadImage((ImageView) findViewById(R.id.mainImage))
                .execute(mainImage);

        TextView textView_textTitle = (TextView) findViewById(R.id.textTitle);
        TextView textView_textStyle = (TextView) findViewById(R.id.textStyle);
        TextView textView_context_large = (TextView) findViewById(R.id.context_large);
        TextView textView_textAddress = (TextView) findViewById(R.id.textAddress);
        TextView textView_textWebPage = (TextView) findViewById(R.id.textWebPage);

        textView_textTitle.setText(textTitle);
        textView_textStyle.setText(textStyle);
        textView_context_large.setText(context_large);
        textView_textAddress.setText(textAddress);
        textView_textWebPage.setText(textWebPage);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.back) {

            Intent i = new Intent(this, MainScreen.class );
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}