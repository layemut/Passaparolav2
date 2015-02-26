package oem.com.passaparolav2;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
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
            {"Delta ovalarında biriken çok verimli toprak.", "Fizikte, elektrik akımının şiddet birimi."
                    , "Hristanlıkta kilise tarafından verilen \"cemaatten kovma\" cezası"},
            {"Avrupa Birliğinin başkenti.", "İpeği ve şeftalisi ile meşhur ilimiz.", "Kırgızıstan’ın başkenti."},
            {"Ölüm cezasına çarptırılanları öldürmekle görevli kimse.", "Güç veya tehlikeli bir işe girişirken insanın kendinde bulduğu güven",
                    "Eski dilde davranışları çabuk ve kesin olan."},
            {"İçki ve yiyecek gibi şeylerde standart miktarın iki katı.", " Deseni karelerden oluşmuş olan kumaş."
                    , "Kuruluşlarda belli bir işi yapmak üzere ayrılmış bölümlerden her biri"},
            {"Bireysel çıkarların, başkalarının çıkarlarının üstünde tutulması.", "Uzun süre saklanabilen yiyeceklerin genel adı."
                    , "Sanat ya da yazın yapıtını ana çizgileriyle gösteren ön çalışma"},
            {"Eski dilde anlayış, sezgi, zeka.", "50 ile 300 hektolitre alabilen büyük fıçılara verilen ad."
                    , "avuğun istenilen yere yumurtlaması için o yere konulan, yumurta benzeri nesne"},
            {"Albaylıktan sonra gelen yüksek rütbeli subaylara verilen genel ad.", "Çanakkale savaşları'nın yaşandığı Çanakkale ilçesi."
                    , "Tarihte Türk adıyla kurulan ilk Türk devleti"},
            {"Ekin biçme, ürün kaldırma işi.", "Bineğe veya yük taşımaya alıştırılmamış at ya da eşek sürüsü.", "Yüksek sesle bağırmak"},
            {"Yasa ve kurallara aykırı biçimde haksız olarak kayırma.", "Toplum hayatından kaçıp tek başına yaşama"
                    , "Gözün renkli kısmı."},
            {"Rus haber alma örgütünün kısa adı.", "Ocak bacalarında biriken veya çevrede savrulan kalın ise verilen ad."
                    , "Saatlerin üzerinde rakamlar bulunan iç kısmı."},
            {"Alkol su ve şeker karışımından oluşan içki", "Palamut, torik gibi balıklardan dilim dilim kesilerek yapılan salamura"
                    , "Osmanlıda bir devrede ismini vermiş çiçeğin adı."},
            {"Göktürk Yazıtları bulunduğu ülke.", "Bira yapımında kullanılan çimlenmiş ve kavrulmuş arpa."
                    , "Türkiye'de sadece iki kişiye verilmiş, bir tanesi Fevzi Çakmak'ta bulunan rütbe."},
            {"Avrupa Birliğini iki kez halk oylamasiyla reddeden ülkedir.", "Japonya'nın hangi kentine Atatürk anıtı dikilmiştir?"
                    , "Güneşe yakınlığı bakımından 8. olan gezegen."},
            {"Para ve emek harcamadan başkalarının sırtından geçinen kimseye ne ad verilir?", "Uyumsuz olduklarında armonik faciaya neden olan grup."
                    , "Afrika’da yaşayan, gövdesi kızıl kestane, bacakları beyaz çizgili memeli hayvan"},
            {"Orta oyunu'nun ana karakterlerinden Kavuklu olmayan.", " Çokeşli evliliğin sosyolojideki adı.", "Denizaltılarda su yüzeyini görmeye yarayan metal boru."},
            {"Kışlalarda hafif hastaların tedavi edildiği yer.", "Denizcilikte düzgün sarılmış halat yumağına verilen ad.", "Mağazanın yalnızca bir tür eşya satılan bölümü"},
            {"İran mitolojisinde, ateşten yaratılan ve ateşte yaşayan hayvan.", "7. sanat olarak da tabir edilen sanat.",
                    "Ramazanda oruç tutanların gün doğmadan önce belirli saatte yedikleri yemek."},
            {"Teknelerle suyun dibinde sürüklenerek çekilen balık ağı.", "Akdeniz anemisi adı da verilen bir hastalık ", "Aşkabat hangi ülkenin başkentidir?"},
            {"Eskiden çöl arapları ve bedevilere verilen ad.", "Budizmi benimseyen ilk Türk devleti."
                    , "Afrika’nın en yüksek dağı kilimanjaro’nun, yerli dillerde “özgürlük” anlamına gelen yeni adı."},
            {"Ağız ve burun boşluklarıyla, gırtlak ve yemek borusu arasındaki boşluğa verilen ad.", "Türkiye'nin ilk milli parkının bulunduğu il."
                    , "İpsal ve dedeağaç sınır kapıları bizi hangi ülkeye bağlar?"},
            {"Bakırcılıkta kullanılan “T” biçimindeki örse verilen ad.", "Altıkardeş takım yıldızı."
                    , "Cam parlaklığında güzel yeşil renkte saydam ve değerli süs taşına verilen ad."}};
    String[][] answers = {{"alüvyon", "amper", "afaroz"}, {"brüksel", "bursa", "bişkek"}, {"cellat", "cesaret", "cevval"},
            {"duble", "dantel", "departman"}, {"egoizm", "erzak", "eskiz"},
            {"feraset", "fulder", "fol"}, {"general", "gelibolu", "göktürkler"}, {"hasat", "hergele", "haykırmak"}, {"iltimas", "inziva", "iris"},
            {"kgb", "kurum", "kadran"}, {"likör", "lakerda", "lale"}, {"moğolistan", "malt", "mareşal"}, {"norveç", "nigata", "neptün"},
            {"otlakçı", "orkestra", "okapi"}, {"pişekar", "poligami", "periskop"}, {"revir", "roda", "reyon"}, {"semender", "sinema", "sahur"},
            {"trol", "talasemi", "türkmenistan"}, {"urban", "uygurlar", "uhuru"}, {"yutak", "yozgat", "yunanistan"}, {"zava", "zatülkürsi", "zümrüt"},
    };

    int letterCounter = 0, passNumber = 0, wrongNumber = 0, correctNumber = 0;
    int[] answerList = new int[21];
    int[] answered = new int[21];
    boolean finishedOnce=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);


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

        //random answers
        randomAnswers();

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


                    //only starts from unanswered or passed questions, skips answered.
                    while (answered[letterCounter] != 0) {
                        letterCounter++;
                        if (letterCounter > 20){
                            letterCounter = 0;
                            finishedOnce=true;
                        }
                    }

                    if(finishedOnce){
                        passNumber--;
                        passText.setText("" + passNumber);
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
                if(!finishedOnce) {
                    passNumber++;
                }
                passText.setText("" + passNumber);
                answerText.setText("");

                do {
                    letterCounter++;
                    if (letterCounter > 20) {
                        letterCounter = 0;
                        finishedOnce =true;
                    }
                } while (answered[letterCounter] != 0);

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

    public void randomAnswers(){
        for (int i = 0; i < 21; i++) {
            Random rdm = new Random();
            answerList[i] = rdm.nextInt(3);
            answered[i] = 0;
        }
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
            Toast.makeText(MainActivity.this, "TAMAM SAKİN OL ŞAMPİYON, SÜRE BİTTİ", Toast.LENGTH_LONG).show();
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
