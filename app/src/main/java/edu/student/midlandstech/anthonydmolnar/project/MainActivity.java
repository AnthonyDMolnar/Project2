package edu.student.midlandstech.anthonydmolnar.project;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MeasureDB db;
    protected static Measure fromMeasure;
    protected static Measure toMeasure;
    public final static int SPACING_VERTICAL = 75;
    public final static int SPACING_HORIZONTAL = 40;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Configuration config = getResources().getConfiguration();
        modifyLayout(config);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new MeasureDB(this);
        ArrayList<Measure> list = db.selectAll();
        fromMeasure = list.get(0);
        toMeasure = list.get(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id==R.id.from){
            Intent fromActivity = new Intent(this, FromActivity.class);
            this.startActivity(fromActivity);
            return true;
        }
        if (id==R.id.to){
            Intent toActivity = new Intent(this, ToActivity.class);
            this.startActivity(toActivity);
            return true;
        }
        if (id ==R.id.insert){
            Intent addActivity = new Intent(this, InsertActivity.class);
            this.startActivity(addActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public void convert(View v) {
        DecimalFormat df = new DecimalFormat("###.0");
        TextView answer = findViewById(R.id.lblAnswer);
        EditText amountStr = findViewById(R.id.etAmount);
        double amount = Double.parseDouble(amountStr.getText().toString());
        double result = toMeasure.fromTeaspoons(fromMeasure.toTeaspoons(amount));
        answer.setText(df.format(amount) + " " + fromMeasure.getName() + " is "
            + df.format(result) + " " + toMeasure.getName()
        );
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        modifyLayout(newConfig);
    }

    public void modifyLayout(Configuration newConfig) {
        TextView text = findViewById(R.id.lblAmount);
        ViewGroup.MarginLayoutParams params2
                = (ViewGroup.MarginLayoutParams) text.getLayoutParams();
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            params2.setMargins(0, SPACING_HORIZONTAL, 0, 0);
        } else if (newConfig.ORIENTATION_PORTRAIT
            == Configuration.ORIENTATION_PORTRAIT) {
            params2.setMargins(0,SPACING_VERTICAL,0,0);
        }
    }
}
