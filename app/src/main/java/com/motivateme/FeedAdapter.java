package com.motivateme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FeedAdapter extends ArrayAdapter<FeedItem> {
    public FeedAdapter(Context context, List<FeedItem> items) {
        super(context, R.layout.feed_list_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FeedItem item = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.feed_list_item, parent, false);

            holder.profilePic = (ImageView) convertView.findViewById(R.id.profilePic);
            holder.profileName = (TextView) convertView.findViewById(R.id.profileName);
            holder.description = (TextView) convertView.findViewById(R.id.postDescription);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Profile p = item.getProfile();
        holder.profilePic.setImageResource(p.getProfilePicture());
        holder.profileName.setText(p.getName());
        holder.description.setText(item.getDescription());
        return convertView;
    }

    static class ViewHolder {
        private ImageView profilePic;
        private TextView profileName;
        private TextView description;
    }
}
