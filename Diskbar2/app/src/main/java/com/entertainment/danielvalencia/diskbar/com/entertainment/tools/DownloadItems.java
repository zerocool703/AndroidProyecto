package com.entertainment.danielvalencia.diskbar.com.entertainment.tools;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.entertainment.danielvalencia.diskbar.ItemDetail;
import com.entertainment.danielvalencia.diskbar.R;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel.valencia on 15/04/2015.
 */
public class DownloadItems extends AsyncTask<String, String, Void> {

    private ProgressDialog progressDialog = null;

    private Activity activity = null;
    
    public DownloadItems(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog = ProgressDialog.show(activity, "", "Please wait...", true);

                // start time consuming background process here
            }
        }, 1000); // starting it in 1 second


        /*progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface arg0) {
                DownloadItems.this.cancel(true);
            }
        });
        */
    }

    private JSONParser parser = new JSONParser();

    @Override
    protected Void doInBackground(String... params) {

        String url = "http://demosys.tsb.com.bo:8524/DiscoRepository/discoInfo";
        //String url = "http://192.168.1.63:8524/DiscoRepository/discoInfo";
        String method = "POST";
        ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
        loadParameters(parameters);

        try {
            JSONObject result = parser.makeHttpRequest(url, method, parameters);
            final List<Item> data = parser.makeItem(result);

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    ListView itemList = (ListView) activity.findViewById(R.id.ListView_itemList);
                    //itemList.removeAllViews();

                    itemList.setAdapter(new AdapterList(activity, R.layout.item, data) {
                        @Override
                        public void onAttach(Object input, View view) {

                            TextView textView_top = (TextView) view.findViewById(R.id.textView_top);
                            textView_top.setText(((Item) input).get_topText());

                            TextView textView_bottom = (TextView) view.findViewById(R.id.textView_bottom);
                            textView_bottom.setText(((Item) input).get_bottomText());

                            new DownloadImage((ImageView) view.findViewById(R.id.disco_icon))
                                    .execute(((Item) input).get_url_image());

                        }
                    });

                    itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> pariente, View view, int position, long id) {

                            Item selected = (Item) pariente.getItemAtPosition(position);

                            Intent i = new Intent(activity, ItemDetail.class);
                            i.putExtra("mainImage", selected.get_url_image());
                            i.putExtra("textTitle", selected.get_topText());
                            i.putExtra("textStyle", selected.getStyle());
                            i.putExtra("text", selected.get_bottomText());
                            i.putExtra("textAddress", selected.getAddress());
                            i.putExtra("textWebPage", selected.getWebpage());

                            activity.startActivity(i);
                        }
                    });
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void loadParameters(ArrayList<NameValuePair> parameters) {

        Intent i = activity.getIntent();
        Bundle bundle = i.getExtras();
        if (bundle != null) {
            if (bundle.containsKey("place")) {

                String place = bundle.getString("place");
                parameters.add(new BasicNameValuePair("top_text", place));
            }
            if (bundle.containsKey("description")) {

                String description = bundle.getString("description");
                parameters.add(new BasicNameValuePair("bottom_text", description));
            }
            if (bundle.containsKey("style")) {

                String style = bundle.getString("style");
                parameters.add(new BasicNameValuePair("style", style));
            }
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();

                // start time consuming background process here
            }
        }, 5000); // starting it in 5 second
        */
    }
}
