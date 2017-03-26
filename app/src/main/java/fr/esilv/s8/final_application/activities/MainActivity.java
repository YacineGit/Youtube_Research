package fr.esilv.s8.final_application.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;


import fr.esilv.s8.final_application.R;
import fr.esilv.s8.final_application.adapters.VideosAdapter;
import fr.esilv.s8.final_application.models.Constants;
import fr.esilv.s8.final_application.models.VideoResponse;
import fr.esilv.s8.final_application.interfaces.onVideoSelectedListener;

public class MainActivity extends AppCompatActivity implements onVideoSelectedListener {

    private RecyclerView recyclerView;
    private Button buttonSearch;
    private TextView search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        search = (TextView) findViewById(R.id.Search);

        initializeRecycler();

        getVideos("");

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getVideos(search.getText().toString());
            }
        });

    }

    private void initializeRecycler(){

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setAdapter(List<VideoResponse.ItemsBean> videos) {

        VideosAdapter adapter = new VideosAdapter(videos);
        recyclerView.setAdapter(adapter);
    }

    private void getVideos(String queryString) {
        String request = "";
        String stringQueryTransformed = transformString(queryString);

        if (queryString == "") {
            request = Constants.VIDEO_URL + "?part=snippet&relevanceLanguage=fr&key=" + Constants.API_KEY;
        } else {
            request = Constants.VIDEO_URL + "?part=snippet&q=" + transformString(stringQueryTransformed) + "&maxResults="+ 20 +"&key=" + Constants.API_KEY;
        }
        StringRequest videoRequest = new StringRequest(request, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                VideoResponse videos = new Gson().fromJson(response, VideoResponse.class);
                setAdapter(videos.getItems());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Contracts", "Error");
            }
        });
        Volley.newRequestQueue(this).add(videoRequest);
    }



    private String transformString(String queryString) {
        queryString.replace(" ", "|");
        return queryString;
    }


    @Override
    public void onVideoSelected(VideoResponse videos) {
    }
}
