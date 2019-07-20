package com.shankaryadav.www.jsonparsingtesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<User> users;

    String URL_DATA = "http://userapi.tk/";

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        recyclerView = findViewById (R.id.recyclerview);

        LinearLayoutManager lm  = new GridLayoutManager (this,2);

        recyclerView.setLayoutManager (lm);

        recyclerView.setHasFixedSize (true);

        recyclerView.setItemAnimator (new DefaultItemAnimator ());

        recyclerView.addItemDecoration (new DividerItemDecoration (this,DividerItemDecoration.VERTICAL));

        users = new ArrayList<> ();

        loadurl();
    }

    public void loadurl(){

        JsonArrayRequest stringRequst = new JsonArrayRequest (URL_DATA,
                new Response.Listener<JSONArray> () {
                    @Override
                    public void onResponse(JSONArray response) {
                        getValue(response);
                    }
                }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue (this);

        requestQueue.add (stringRequst);

    }



    public void getValue(JSONArray response){

        for (int i =0;i<response.length ();i++){
            User userList = new User ();

            JSONObject  jsonObject = null;

            try{
                jsonObject = response.getJSONObject (i);

                String name = jsonObject.getString ("Name");
                String email = jsonObject.getString ("EmailID");
                String phone = jsonObject.getString ("Mobile");
                String imageURL = jsonObject.getString ("ImageURL");



                userList.setName (name);
                userList.setEmailID (email);
                userList.setMobile (phone);
                userList.setImageURL (imageURL);

                users.add (userList);

            }catch (JSONException e){
                    e.printStackTrace ();
            }
        }

        JsonAdapter jsonAdapter = new JsonAdapter (users,this);
        recyclerView.setAdapter (jsonAdapter);

    }



}
