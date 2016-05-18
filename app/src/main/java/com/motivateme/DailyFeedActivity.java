package com.motivateme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class DailyFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView mFeed = (ListView) findViewById(R.id.lvDailyFeed);
        final FeedItem[] items = {
                new FeedItem("Biggest chicken evar", 0, "Look at dis mafacking chicken dog", new Goal("Get a fat % of 15", 800), new Profile("Dony", "Donald Trump", 69, R.drawable.donald_trump)),
                new FeedItem("Wtf je zegt bij in de gym bruh", 0, "Popped a molly i'm sweating real hard", new Goal("Bench 200", 800), new Profile("Johnny", "John Cena", 38, R.drawable.john_cena))
        };

        mFeed.setAdapter(new FeedAdapter(this, items));
        mFeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(DailyFeedActivity.this, ViewFeedActivity.class)
                        .putExtra("feedItem", items[position]));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(getApplicationContext(), "Implement feed searching", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
