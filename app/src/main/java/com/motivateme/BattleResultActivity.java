package com.motivateme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BattleResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView mFeed = (ListView) findViewById(R.id.lvBattleResult);

        final List<FeedItem> items = new ArrayList();

        Intent intent = getIntent();

        items.add(new FeedItem("Gymming", R.drawable.paul, "Paul", new Goal("Run 3000 metres", 800, 0, 1), new Profile("Paul", "12.22", 25, R.drawable.paul)));
        items.add(new FeedItem("Gymming", R.drawable.paul, "(Your time)", new Goal("Run 3000 metres", 800, 0, 1), new Profile("Paul", intent.getStringExtra("time"), 25, R.drawable.souf)));
        items.add(new FeedItem("Failure is not an option for me. Success is all I envision", R.drawable.matt, "Matt", new Goal("Do 150 push ups in a row", 700, 102, 150), new Profile("Notorious", "14.65", 27, R.drawable.matt)));
        items.add(new FeedItem("Failure is not an option for me. Success is all I envision", R.drawable.matt, "Jim", new Goal("Do 150 push ups in a row", 700, 102, 150), new Profile("Notorious", "16.33", 27, R.drawable.jim)));

        mFeed.setAdapter(new FeedAdapter(this, items));
        mFeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

}
