package ca.marshall.xlightstools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class WebsiteAdapter extends ArrayAdapter<Website> {
    public WebsiteAdapter(@NonNull Context context,  @NonNull List<Website> websites) {
        super(context, 0,websites );

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Website site = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.api_listitem,parent,false);
        }

        TextView textName = (TextView) convertView.findViewById(R.id.apiItemName);
        TextView textUrl = (TextView) convertView.findViewById(R.id.apiItemUrl);
        textName.setText(site.getTitle());
        textUrl.setText(site.getBaseUrl());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openWebSite = new Intent(Intent.ACTION_VIEW, Uri.parse(site.getUrl()));
                getContext().startActivity(openWebSite);
            }
        });
        return convertView;
    }
}
