package oem.com.passaparolav2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


public class splashscreen extends Activity {
    ImageButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20;
    ImageButton passaView;
    Button about,start,donate;
    View circle;
    LinkedList<ImageButton> splashLetters = new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //removing actionbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splashscreen);


        final Animation passaViewAnim = AnimationUtils.loadAnimation(splashscreen.this,R.anim.splashtexttranslate);

        setUpSplashScreenLetter();
        //Getting device's resolution to set button pixels.
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        //setting buttons' pixels
        for(int i=0; i<splashLetters.size();i++){
            splashLetters.get(i).getLayoutParams().height=height/25;
            splashLetters.get(i).getLayoutParams().width=height/25;
        }
        passaView.getLayoutParams().width=width/2;
        passaView.getLayoutParams().height=height/10;

        //shuffling for random animation
        int[] shuffle = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        shuffleArray(shuffle);
        //adding shuffled animation
        for(int i =0;i<splashLetters.size();i++){
            Animation translateDown = AnimationUtils.loadAnimation(splashscreen.this,R.anim.splashtranslatedown);
            translateDown.setStartOffset(i * 75);
            splashLetters.get(shuffle[i]).setAnimation(translateDown);
        }

        passaView.setAnimation(passaViewAnim);
        TranslateAnimation translateAnimation = new TranslateAnimation(-500,0,0,0);
        translateAnimation.setDuration(1000);
        start.setAnimation(translateAnimation);
        donate.setAnimation(translateAnimation);
        translateAnimation = new TranslateAnimation(500,0,0,0);
        translateAnimation.setDuration(1000);
        about.setAnimation(translateAnimation);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(splashscreen.this,MainActivity.class);
                startActivity(i);
            }
        });
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(splashscreen.this,word_finding.class);
                startActivity(i);
            }
        });
    }

    static void shuffleArray(int[] ar)
    {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
    public void setUpSplashScreenLetter() {
        b0 = (ImageButton) findViewById(R.id.b0);
        b1 = (ImageButton) findViewById(R.id.b1);
        b2 = (ImageButton) findViewById(R.id.b2);
        b3 = (ImageButton) findViewById(R.id.b3);
        b4 = (ImageButton) findViewById(R.id.b4);
        b5 = (ImageButton) findViewById(R.id.b5);
        b6 = (ImageButton) findViewById(R.id.b6);
        b7 = (ImageButton) findViewById(R.id.b7);
        b8 = (ImageButton) findViewById(R.id.b8);
        b9 = (ImageButton) findViewById(R.id.b9);
        b10 = (ImageButton) findViewById(R.id.b10);
        b11 = (ImageButton) findViewById(R.id.b11);
        b12 = (ImageButton) findViewById(R.id.b12);
        b13 = (ImageButton) findViewById(R.id.b13);
        b14 = (ImageButton) findViewById(R.id.b14);
        b15 = (ImageButton) findViewById(R.id.b15);
        b16 = (ImageButton) findViewById(R.id.b16);
        b17 = (ImageButton) findViewById(R.id.b17);
        b18 = (ImageButton) findViewById(R.id.b18);
        b19 = (ImageButton) findViewById(R.id.b19);
        b20 = (ImageButton) findViewById(R.id.b20);
        passaView=(ImageButton)findViewById(R.id.passaView);
        about=(Button)findViewById(R.id.button2);
        start=(Button)findViewById(R.id.button);
        donate=(Button)findViewById(R.id.button3);
        circle=(View)findViewById(R.id.circle);
        splashLetters.add(b0);
        splashLetters.add(b1);
        splashLetters.add(b2);
        splashLetters.add(b3);
        splashLetters.add(b4);
        splashLetters.add(b5);
        splashLetters.add(b6);
        splashLetters.add(b7);
        splashLetters.add(b8);
        splashLetters.add(b9);
        splashLetters.add(b10);
        splashLetters.add(b11);
        splashLetters.add(b12);
        splashLetters.add(b13);
        splashLetters.add(b14);
        splashLetters.add(b15);
        splashLetters.add(b16);
        splashLetters.add(b17);
        splashLetters.add(b18);
        splashLetters.add(b19);
        splashLetters.add(b20);
    }

}
