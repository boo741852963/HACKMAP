package com.example.android.miwok;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapterr extends ArrayAdapter <words> {
    private int colourId;
    public WordAdapterr(Activity context, ArrayList<words> wordsss,int colour) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, wordsss);
        colourId=colour;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        words currentword = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView banglaview = (TextView) listItemView.findViewById(R.id.bangla);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        banglaview.setText(currentword.getTran());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView englishview = (TextView) listItemView.findViewById(R.id.original);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        englishview.setText(currentword.getOri());
        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        ImageView img = (ImageView) listItemView.findViewById(R.id.pic);
        if(currentword.hasImg()==false)
            img.setVisibility(View.GONE);
        else
            img.setImageResource(currentword.getImgresid());
        View textcontainer=listItemView.findViewById(R.id.text_container);
        int color=ContextCompat.getColor(getContext(),colourId);
        textcontainer.setBackgroundColor(color);
        return listItemView;
    }
}
