package com.muzkabugu.shadyfade.loginscreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shadyfade on 8/17/15.
 */
public class CustomNotifyListAdapter extends ArrayAdapter {
    private List<Posts> PostsList;

    public CustomNotifyListAdapter(Context context, Posts posts){
        super(context,R.layout.notifylist_row, (List) posts);
        PostsList = (List<Posts>) posts;
    }

    public View getView(int position,View convertView,ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View item = inflater.inflate(R.layout.notifylist_row, null);

        TextView title = (TextView) item.findViewById(R.id.tv_title);
        TextView tag = (TextView) item.findViewById(R.id.tv_tag);
        TextView content = (TextView) item.findViewById(R.id.tv_content);
        TextView user = (TextView) item.findViewById(R.id.tv_user);
        TextView notifydate = (TextView) item.findViewById(R.id.tv_notifydate);

        title.setText(PostsList.get(position).getTitle());
        tag.setText(PostsList.get(position).getRoles().get(0).getName());
        content.setText(PostsList.get(position).getContent());
        user.setText(PostsList.get(position).getPostuser().get(0).getUsername());
        notifydate.setText(PostsList.get(position).getPublished_at());

        return item;
    }
}
