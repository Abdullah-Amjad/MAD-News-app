package com.example.news;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private TextView heading;
    // list view
   private ListView list;

    ArrayList<news> N=new ArrayList<news>();
    private static CustomAdapter adapter;
   String json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextViewResult = findViewById(R.id.test);
       heading = findViewById(R.id.news);


       list=findViewById(R.id.List);
    // ArrayAdapter<news> arrayAdapter=new ArrayAdapter<news>(this, android.R.layout.simple_list_item_1,N);
        adapter= new CustomAdapter(N,getApplicationContext());







        heading.setText("");
        mTextViewResult.setText("Wait..........");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://alasartothepoint.alasartechnologies.com/listItem.php?id=1 ")
                .build();


        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {

                    // Json File in to string
                    String myResponse = response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                          //  mTextViewResult.setText(myResponse);
                            json=myResponse;
                            try {
                                JSONObject jsonObject=new JSONObject(json);
                                JSONArray data=jsonObject.getJSONArray("data");
                                for(int i=0;i<data.length();i++){
                                    JSONObject jsonObject1=data.getJSONObject(i);
                                    String id=jsonObject1.getString("id");
                                    int idd=Integer.parseInt(id);
                                   String url=jsonObject1.getString("url");
                                   String des=jsonObject1.getString("description");
                                   String head=jsonObject1.getString("heading");
                                    String reference=jsonObject1.getString("reference");
                                    news n=new news(idd,url,head,des,reference);
                                    N.add(n);


                                }


                            }catch(Exception e){

                                mTextViewResult.setText("Data Not find");


                            }

                            // adapter
                            heading.setText("News");
                            mTextViewResult.setText("");
                            list.setAdapter(adapter);
                       //   list.setAdapter(arrayAdapter);
                        }
                    });

                }


            }


        });



    }
}
