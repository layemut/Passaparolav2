package oem.com.passaparolav2;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class word_finding extends Activity {
TextView deneme;
    ArrayList<Integer> intdeneme = new ArrayList<>();
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_finding);
        deneme=(TextView)findViewById(R.id.deneme);
        for(int i=0;i<15;i++){
            intdeneme.add(i);
        }
        intdeneme.remove(5);
        for(int i=0;i<14;i++){
            deneme.setText(deneme.getText()+intdeneme.get(i).toString());
        }

    }


}
