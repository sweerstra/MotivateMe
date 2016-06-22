package com.motivateme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GoalListAdapter extends ArrayAdapter<Goal> {
    public GoalListAdapter(Context context, Goal[] items) {
        super(context, R.layout.goals_list_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Goal goal = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.goals_list_item, parent, false);

            holder.name = (TextView) convertView.findViewById(R.id.goalName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(goal.getName());
        return convertView;
    }

    static class ViewHolder {
        private TextView name;
    }
}
