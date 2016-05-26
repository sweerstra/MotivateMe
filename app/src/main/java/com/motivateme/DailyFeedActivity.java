package com.motivateme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListView mFeed = (ListView) findViewById(R.id.lvDailyFeed);
        final FeedItem[] items = {
                new FeedItem("\"I'm very rich\"", R.drawable.donald_trump_workout, "Golfing with Barrack", new Goal("My first hole in one", 800, 0, 1), new Profile("TheDonald", "Donald Trump", 69, R.drawable.donald_trump)),
                new FeedItem("To succeed, you have to believe everynight in your heart, that you\'re the best", R.drawable.john_cena_workout, "Bench pressing", new Goal("Bench press 500", 800, 485, 500), new Profile("HisNameJohnCena", "John Cena", 38, R.drawable.john_cena)),
                new FeedItem("Don't think about the start of the race. Think about the ending.", R.drawable.usain_bolt_workout, "Practicing in Kingston", new Goal("100 metres in less than 9.58 seconds", 1000, 9, 10), new Profile("Bolt", "Usain Bolt", 29, R.drawable.usain_bolt)),
                new FeedItem("Failure is not an option for me. Success is all I envision", R.drawable.mcgregor_workout, "Training to knock Nate Diaz out", new Goal("Do 150 push ups in a row", 700, 102, 150), new Profile("Notorious", "Conor McGregor", 27, R.drawable.mcgregor))
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
                Toast.makeText(DailyFeedActivity.this, "Implement feed searching", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
