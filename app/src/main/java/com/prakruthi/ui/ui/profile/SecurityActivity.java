package com.prakruthi.ui.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.prakruthi.R;
import com.prakruthi.ui.utils.Constants;

public class SecurityActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        init();

        String image=getIntent().getStringExtra("Attachment");
        webView.loadUrl(image);
    }

    private void init() {


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setTitle("Security");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(Constants.TERMS_COND_URL));
                i.setData(Uri.parse(Constants.SECURITY));

                startActivity(i);
                finish();
            }
        });
        webView = findViewById(R.id.iv_security);
        webView.getSettings().setBuiltInZoomControls(true);
    }

}