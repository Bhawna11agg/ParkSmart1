package com.example.parksmart1.ui.share;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.parksmart1.R;

public class ShareFragment extends Fragment {
    private ShareViewModel shareViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        Button shareBtn = view.findViewById(R.id.button_share_now);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);

                //Get the app link in the Play Store
                final String appPackageName = getContext().getPackageName();
                String strAppLink;
                try {
                    strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
                } catch (android.content.ActivityNotFoundException activityNotFound) {
                    strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
                }
                // This is the sharing part
                shareIntent.setType("text/link");
                String shareBody = "Hey! Download ParkSmart application for ease of finding parking" +
                        "\n" + "" + strAppLink;
                String shareSub = "APP NAME/TITLE";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share ParkSmart Using"));
            }

        });
        return view;
    }}