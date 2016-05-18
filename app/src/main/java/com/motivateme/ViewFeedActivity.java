package com.motivateme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        FeedItem feedItem = (FeedItem) getIntent().getSerializableExtra("feedItem");
        Profile p = feedItem.getProfile();
        getSupportActionBar().setTitle(p.getName());
        ImageView mPicture = (ImageView) findViewById(R.id.viewPicture);
        mPicture.setImageResource(feedItem.getPicture());
        ListView mCurrentGoal = (ListView) findViewById(R.id.lvCurrentGoal);
        TextView mTitle = (TextView) findViewById(R.id.viewTitle);
        mTitle.setText(feedItem.getTitle());

        final Goal[] goals = {
                feedItem.getWorkingGoal()
        };
        mCurrentGoal.setAdapter(new GoalListAdapter(this, goals));
        mCurrentGoal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ViewFeedActivity.this, ProgressActivity.class)
                        .putExtra("goal", goals[position]));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_like:
                Toast.makeText(getApplicationContext(), "You liked this post", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
