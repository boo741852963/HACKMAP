package com.example.android.miwok;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TranslateFragment extends Fragment {
    ProgressBar bar;
    public TranslateFragment() {
        // Required empty public constructor
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_translate, container, false);
        WebView translater=(WebView)rootView.findViewById(R.id.wb);
         bar=(ProgressBar)rootView.findViewById(R.id.barr) ;
        translater.setWebViewClient(new mywebclient());
        translater.loadUrl("https://translate.google.com");
        WebSettings webSettings =translater.getSettings();
        webSettings.setJavaScriptEnabled(true);
        return rootView;
    }
    public class mywebclient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            bar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
