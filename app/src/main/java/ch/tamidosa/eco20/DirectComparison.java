package ch.tamidosa.eco20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class DirectComparison extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_comparison);

        Intent intent = getIntent();
        int scanNr = intent.getIntExtra("ScanNr", 0);
    }
}
