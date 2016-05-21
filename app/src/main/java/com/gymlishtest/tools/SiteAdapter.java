package com.gymlishtest.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gymlishtest.ClassItems.Site;
import com.gymlishtest.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Kévin on 20/05/2016.
 */
public class SiteAdapter extends ArrayAdapter<Site> {

    public SiteAdapter(Context context, ArrayList<Site> objects) {
        super(context,0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_site,parent,
                    false);
        }
        SiteViewHolder viewHolder = (SiteViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new SiteViewHolder();
            viewHolder.nameSite = (TextView) convertView.findViewById(R.id.nameSite);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.overview);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.imageSite);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SiteViewHolder) convertView.getTag();
        }

        Site item = getItem(position);
        if (item!= null) {
            viewHolder.nameSite.setText(String.format("%s", item.getNameSite()));
            viewHolder.overview.setText(String.format("%s", item.getOverviewSite()));
            Picasso.with(getContext()).load(item.getImage()).fit().into(viewHolder.image);
        }

        return convertView;

    }
    private class SiteViewHolder{
        public TextView nameSite;
        public TextView overview;
        public ImageView image;

    }
}
