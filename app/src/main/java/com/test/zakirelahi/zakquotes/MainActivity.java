package com.test.zakirelahi.zakquotes;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    private TextView mStatus;
    private int counter = 0;
    private TextSwitcher mSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        java.util.Random r = new java.util.Random();
        this.counter=r.nextInt(this.getQuotes().length - 1);





        final Button NextQuoteButton = (Button) findViewById(R.id.nextquotebutton);
        NextQuoteButton.setOnClickListener( new View.OnClickListener () {

            public void onClick(View p1) {
                showNextQuote();
            }
        });


        final MainActivity activity = this;
        mSwitcher = (TextSwitcher) findViewById(R.id.quotetext);
        mSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {

                TextView myText = new TextView(MainActivity.this);
                myText.setGravity(Gravity.CENTER);
                myText.setTextAppearance(activity, android.R.style.TextAppearance_Large);
                myText.setTextColor(Color.parseColor("#000000"));
                myText.setBackgroundColor(Color.parseColor("#00000000"));
                myText.setPadding(10, 10, 10, 10);
                myText.setTypeface();
                return myText;
            }
        });

        final int time = 200;
        Animation in = new AlphaAnimation(0,1);
        in.setDuration(time);
        Animation out = new AlphaAnimation(1,0);
        out.setDuration(time);
        in.setStartOffset(time/2);

        // set the animation type of textSwitcher
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);

        this.showQuote();


        final Button exitButton = (Button) findViewById(R.id.exitbutton);
        exitButton.setOnClickListener( new View.OnClickListener () {
            public void onClick(View p1) {
                finish();
            }
        });
    }


    private void showNextQuote()
    {
        this.counter++;
        this.counter = this.counter % this.getQuotes().length;
        showQuote();
    }

    private void showQuote()
    {
        // no matter what, counter shall be within array index
        this.counter = this.counter % this.getQuotes().length;

        this.mSwitcher.setText(this.getQuotes()[this.counter]);

    }
    private String[] getQuotes() {
        return getResources().getStringArray(R.array.quoteslist);
    }
}
