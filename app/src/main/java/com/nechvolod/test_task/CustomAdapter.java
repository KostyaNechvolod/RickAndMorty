package com.nechvolod.test_task;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nechvolod.test_task.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater inflater;
    List<Result> resultList;

    public CustomAdapter(Context ctx, List<Result> resultList) {
        this.ctx = ctx;
        this.resultList = resultList;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public Object getItem(int position) {
        return resultList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return resultList.get(position).getId();
    }

    Result getResult(int position) {
        return ((Result) getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item, parent, false);
        }

        final Result r = getResult(position);

        ImageView imageView = (ImageView) view.findViewById(R.id.image_view_list_activity);
        TextView textView = (TextView) view.findViewById(R.id.name_list_activity);
        Picasso.get().load(r.getImage()).into(imageView);
        textView.setText(r.getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, CharacterInfoActivity.class);
                intent.putExtra(Result.class.getSimpleName(), r);
                ctx.startActivity(intent);
            }
        });
        return view;
    }

}
