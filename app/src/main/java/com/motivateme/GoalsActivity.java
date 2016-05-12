package com.motivateme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class GoalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView mGoals = (ListView) findViewById(R.id.lvGoals);
        final Goal[] goals = {
                new Goal("Lose 20 pounds", 300), new Goal("Get a fat % of 15", 800), new Goal("Some goal", 400)
        };
        mGoals.setAdapter(new GoalListAdapter(this, goals));
        mGoals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(GoalsActivity.this, ProgressActivity.class)
                        .putExtra("goal", goals[position]));
            }
        });
    }
}
