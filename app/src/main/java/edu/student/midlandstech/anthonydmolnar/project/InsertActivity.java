package edu.student.midlandstech.anthonydmolnar.project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }
    public void insert(View v) {
        EditText nameET = findViewById(R.id.etName);
        EditText measureET = findViewById(R.id.etMeasure);
        String name = nameET.getText().toString();
        double measure = Double.parseDouble(measureET.getText().toString());
        MeasureDB db = new MeasureDB(this);
        db.insertMeasure(name, measure);
    }
    public void close(View v) {this.finish();}
}
