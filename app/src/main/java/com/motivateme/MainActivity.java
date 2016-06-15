package com.motivateme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<FeedItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PostMessageActivity.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ListView mFeed = (ListView) findViewById(R.id.lvDailyFeed);
        items = new ArrayList();
        items.add(new FeedItem("\"I'm very rich\"", R.drawable.donald_trump_workout, "Golfing with Barrack", new Goal("My first hole in one", 800, 0, 1), new Profile("TheDonald", "Donald Trump", 69, R.drawable.donald_trump)));
        items.add(new FeedItem("To succeed, you have to believe everynight in your heart, that you\'re the best", R.drawable.john_cena_workout, "Bench pressing", new Goal("Bench press 500", 800, 485, 500), new Profile("HisNameJohnCena", "John Cena", 38, R.drawable.john_cena)));
        items.add(new FeedItem("Don't think about the start of the race. Think about the ending.", R.drawable.usain_bolt_workout, "Practicing in Kingston", new Goal("100 metres in less than 9.58 seconds", 1000, 9, 10), new Profile("Bolt", "Usain Bolt", 29, R.drawable.usain_bolt)));
        items.add(new FeedItem("Failure is not an option for me. Success is all I envision", R.drawable.mcgregor_workout, "Training to knock Nate Diaz out", new Goal("Do 150 push ups in a row", 700, 102, 150), new Profile("Notorious", "Conor McGregor", 27, R.drawable.mcgregor)));

        Intent intent = getIntent();
        String postText = intent.getStringExtra("text");
        String postTitle = intent.getStringExtra("title");
        if (postTitle != null) {
            items.add(new FeedItem(postTitle, R.drawable.tupac, postText, new Goal("Showing off the app", 1337, 10, 10), new Profile("Tupac", "Tupac Shakur", 25, R.drawable.tupac)));
        }

        mFeed.setAdapter(new FeedAdapter(this, items));
        mFeed.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, ViewFeedActivity.class)
                        .putExtra("feedItem", items.get(position)));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "Implement feed searching", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        switch (id) {
            case R.id.nav_challenge_yourself:
                startActivity(new Intent(this, ChallengeYourselfActivity.class));
                break;
            case R.id.nav_goals:
                intent = new Intent(this,SetYourGoalActivity.class);
                break;
            case R.id.nav_tag_friend:
                //intent = new Intent(this, )
                break;
            case R.id.nav_friends:
                //intent = new Intent(this, )
                break;
            case R.id.nav_rewards:
                //intent = new Intent(this, )
                break;
            case R.id.nav_settings:
                //intent = new Intent(this, )
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
