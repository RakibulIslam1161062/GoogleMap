package com.example.googlemap;



import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private static  final String url = "http://52.29.113.22/rakib/getnotification";

    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        this.getSupportActionBar().hide();


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Notification.this));

        listItems = new ArrayList<>();


        loadRecyclerData();





    }


    private void loadRecyclerData(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();

                        try {
                            JSONArray array = new JSONArray(s);
                            for (int i = array.length()-1 ; i>=0; i--){
                                JSONObject o = array.getJSONObject(i);

//                                Toast.makeText(MainActivity.this,"here: "+o.getString("_id"),Toast.LENGTH_SHORT).show();
//
//                                if(o.getString("name").equals(null) || o.getString("dept").equals(null) ||o.getString("userName").equals(null) )
//                                    continue;



                                ListItem item = new ListItem( o.getString("busName"),
                                        o.getString("adminName"),
                                        o.getString("adminDesig"),o.getString("message") );
                                listItems.add(item);
                            }
                            adapter = new MyAdapter(listItems,getApplicationContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}


//
//        for (int i = 0 ;i <10 ; i++){
//            ListItem listItem = new ListItem(
//                    "heading " + (i+1),
//                    "Dummy Text"
//            );
//
//            listItems.add(listItem);
//        }
//
//        adapter = new MyAdapter(listItems, this);
//
//        recyclerView.setAdapter(adapter);




