package oem.com.passaparolav2;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {
    Button passButton;
    ImageButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20;
    ImageButton correctCounterButton, wrongCounterButton, passCounterButton;
    LinkedList<ImageButton> letters = new LinkedList<ImageButton>();
    TextView timer, questionView, correctText, passText, wrongText;
    EditText answerText;

    private static final String FORMAT = "%02d:%02d";
    String[][] questions = {
            {"Delta ovalarında biriken çok verimli toprak.", "Fizikte, elektrik akımının şiddet birimi "},
            {"Avrupa Birliğinin başkenti.", "İpeği ve şeftalisi ile meşhur ilimiz."},
            {"Ölüm cezasına çarptırılanları öldürmekle görevli kimse.", "Güç veya tehlikeli bir işe girişirken insanın kendinde bulduğu güven"},
            {"İçki ve yiyecek gibi şeylerde standart miktarın iki katı.", " Deseni karelerden oluşmuş olan kumaş."},
            {"Bireysel çıkarların, başkalarının çıkarlarının üstünde tutulması.", "Uzun süre saklanabilen yiyeceklerin genel adı."},
            {"Eski dilde anlayış, sezgi, zeka.", "50 ile 300 hektolitre alabilen büyük fıçılara verilen ad."},
            {"Albaylıktan sonra gelen yüksek rütbeli subaylara verilen genel ad.", "Çanakkale savaşları'nın yaşandığı Çanakkale ilçesi."},
            {"Ekin biçme, ürün kaldırma işi.", "Bineğe veya yük taşımaya alıştırılmamış at ya da eşek sürüsü."},
            {"Yasa ve kurallara aykırı biçimde haksız olarak kayırma.", "Toplum hayatından kaçıp tek başına yaşama"},
            {"Rus haber alma örgütünün kısa adı.", "Ocak bacalarında biriken veya çevrede savrulan kalın ise verilen ad."},
            {"Alkol su ve şeker karışımından oluşan içki", "Palamut, torik gibi balıklardan dilim dilim kesilerek yapılan salamura"},
            {"Göktürk Yazıtları bulunduğu ülke.", "Bira yapımında kullanılan çimlenmiş ve kavrulmuş arpa."},
            {"Avrupa Birliğini iki kez halk oylamasiyla reddeden ülkedir.", "Japonya'nın hangi kentine Atatürk anıtı dikilmiştir?"},
            {"Para ve emek harcamadan başkalarının sırtından geçinen kimseye ne ad verilir?", "Uyumsuz olduklarında armonik faciaya neden olan grup."},
            {"Orta oyunu'nun ana karakterlerinden Kavuklu olmayan.", " Çokeşli evliliğin sosyolojideki adı."},
            {"Kışlalarda hafif hastaların tedavi edildiği yer.", "Denizcilikte düzgün sarılmış halat yumağına verilen ad."},
            {"İran mitolojisinde, ateşten yaratılan ve ateşte yaşayan hayvan.", "7. sanat olarak da tabir edilen sanat."},
            {"Teknelerle suyun dibinde sürüklenerek çekilen balık ağı.", "Akdeniz anemisi adı da verilen bir hastalık "},
            {"Eskiden çöl arapları ve bedevilere verilen ad.", "Budizmi benimseyen ilk Türk devleti."},
            {"Ağız ve burun boşluklarıyla, gırtlak ve yemek borusu arasındaki boşluğa verilen ad.", "Türkiye'nin ilk milli parkının bulunduğu il."},
            {"Bakırcılıkta kullanılan “T” biçimindeki örse verilen ad.", "Altıkardeş takım yıldızı."}};
    String[][] answers = {{"alüvyon", "amper"}, {"brüksel", "bursa"}, {"cellat", "cesaret"}, {"duble", "dantel"}, {"egoizm", "erzak"},
            {"feraset", "fulder"}, {"general", "gelibolu"}, {"hasat", "hergele"}, {"iltimas", "inziva"},
            {"kgb", "kurum"}, {"likör", "lakerda"}, {"moğolistan", "malt"}, {"norveç", "nigata"},
            {"otlakçı", "orkestra"}, {"pişekar", "poligami"}, {"revir", "roda"}, {"semender", "sinema"},
            {"trol", "talasemi"}, {"urban", "uygurlar"}, {"yutak", "yozgat"}, {"zava", "zatülkürsi"},
    };

    int letterCounter = 0, passNumber = 0, wrongNumber = 0, correctNumber = 0;
    int[] answerList = new int[21];
    int[] answered = new int[21];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        Random rdm = new Random();
        for (int i = 0; i < letters.size(); i++) {
            answerList[i]=rdm.nextInt(2);
            answered[i] = 0;
        }

        final Animation letterAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.mainfadeinout);

        //timer set-up
        final cdTimer timer = new cdTimer(100000, 1000);
        timer.start();

        //button set-up
        setButtons();

        //button dimension set-up
        setDimensions();

        //Animation set-up
        setAnimation();

        //initiate rest of the views
        initViews();

        answerText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    correctCounterButton.clearAnimation();
                    wrongCounterButton.clearAnimation();
                    letters.get(letterCounter).clearAnimation();

                    answered[letterCounter] = 1;

                    //lowercasing the answer given by user
                    String answer = answerText.getText().toString().toLowerCase();

                    //checking if it is matched with true answer
                    if (answer.equals(answers[letterCounter][answerList[letterCounter]])) {
                        correctNumber++;
                        correctText.setText("" + correctNumber);
                        letters.get(letterCounter).setBackgroundResource(R.drawable.greenbg);
                        correctCounterButton.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.popupanim));
                        //if not, set wrong.
                    } else {
                        wrongNumber++;
                        wrongText.setText("" + wrongNumber);
                        letters.get(letterCounter).setBackgroundResource(R.drawable.redbg);
                        wrongCounterButton.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.popupanim));
                    }

                    //if all unanswered or passed, start again.
                    if (letterCounter > 20) letterCounter = 0;

                    //only starts from unanswered of passed questions, skips answered.
                    while (answered[letterCounter] != 0) {
                        letterCounter++;
                        if(letterCounter>20)letterCounter=0;
                    }

                    letters.get(letterCounter).setAnimation(letterAnim);
                    questionView.setText(questions[letterCounter][answerList[letterCounter]]);
                    answerText.setText("");
                    return true;
                }
                return false;
            }
        });
        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passCounterButton.clearAnimation();
                letters.get(letterCounter).clearAnimation();
                letters.get(letterCounter).setBackgroundResource(R.drawable.yellowbg);
                passCounterButton.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.popupanim));
                passNumber++;
                passText.setText("" + passNumber);
                answerText.setText("");

                if (letterCounter > 20) letterCounter = 0;

                do{
                    letterCounter++;
                    if(letterCounter>20)letterCounter=0;
                }while(answered[letterCounter]!=0);

                letters.get(letterCounter).setAnimation(letterAnim);
                questionView.setText(questions[letterCounter][answerList[letterCounter]]);

            }
        });

    }


    public void resetViews() {
        for (int i = 0; i < letters.size(); i++) {
            letters.get(i).clearAnimation();
            letters.get(i).setBackgroundResource(R.drawable.bluebg);
        }
        letterCounter = 0;
        wrongNumber = 0;
        correctNumber = 0;
        passNumber = 0;
        questionView.setText(questions[letterCounter][answerList[letterCounter]]);
        correctText.setText("" + correctNumber);
        wrongText.setText("" + wrongNumber);
        passText.setText("" + passNumber);
        setAnimation();
    }

    public void initViews() {
        questionView.setText(questions[letterCounter][answerList[letterCounter]]);
    }

    public void setAnimation() {
        for (int j = 0; j < letters.size(); j++) {
            Animation transIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.maintranslatein);
            transIn.setStartOffset(j * 100);
            letters.get(j).setAnimation(transIn);
        }
        letters.get(0).setAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.mainfadeinout));
    }

    public void setButtons() {
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
        passButton = (Button) findViewById(R.id.passButton);
        timer = (TextView) findViewById(R.id.timerText);
        questionView = (TextView) findViewById(R.id.questionView);
        correctText = (TextView) findViewById(R.id.correctText);
        wrongText = (TextView) findViewById(R.id.wrongText);
        passText = (TextView) findViewById(R.id.passText);
        answerText = (EditText) findViewById(R.id.answerText);
        correctCounterButton = (ImageButton) findViewById(R.id.cCounterButton);
        wrongCounterButton = (ImageButton) findViewById(R.id.wCounterButton);
        passCounterButton = (ImageButton) findViewById(R.id.pCounterButton);
        letters.add((ImageButton) b0);
        letters.add((ImageButton) b1);
        letters.add((ImageButton) b2);
        letters.add((ImageButton) b3);
        letters.add((ImageButton) b4);
        letters.add((ImageButton) b5);
        letters.add((ImageButton) b6);
        letters.add((ImageButton) b7);
        letters.add((ImageButton) b8);
        letters.add((ImageButton) b9);
        letters.add((ImageButton) b10);
        letters.add((ImageButton) b11);
        letters.add((ImageButton) b12);
        letters.add((ImageButton) b13);
        letters.add((ImageButton) b14);
        letters.add((ImageButton) b15);
        letters.add((ImageButton) b16);
        letters.add((ImageButton) b17);
        letters.add((ImageButton) b18);
        letters.add((ImageButton) b19);
        letters.add((ImageButton) b20);
    }

    public void setDimensions() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

        correctCounterButton.getLayoutParams().height = height / 17;
        correctCounterButton.getLayoutParams().width = height / 17;
        passCounterButton.getLayoutParams().height = height / 17;
        passCounterButton.getLayoutParams().width = height / 17;
        wrongCounterButton.getLayoutParams().height = height / 17;
        wrongCounterButton.getLayoutParams().width = height / 17;
        for (int i = 0; i < letters.size(); i++) {
            letters.get(i).getLayoutParams().height = height / 17;
            letters.get(i).getLayoutParams().width = height / 17;
        }

    }

    public class cdTimer extends CountDownTimer {

        int time = 0;

        public cdTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            Toast.makeText(MainActivity.this,"TAMAM SAKİN OL ŞAMPİYON, SÜRE BİTTİ",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            time++;
            if (time > 84) {
                timer.setTextColor(Color.RED);
                time = 0;
            }
            timer.setText("" + String.format(FORMAT,
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
        }
    }
}
