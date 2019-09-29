package ch.tamidosa.eco20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class DirectComparison extends AppCompatActivity {
    public int SN = 2314;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_comparison);

        Intent intent = getIntent();
        int scanNr1 = intent.getIntExtra("ScanNr1", -1);
        SN = scanNr1;
        int scanNr2 = intent.getIntExtra("ScanNr2", -1);
        // Toast.makeText(getBaseContext(),"we got 1and2:" + scanNr1 + " " + scanNr2, Toast.LENGTH_LONG).show();
            switch (scanNr1){
                case 2314:
                    ImageView iv = (ImageView) findViewById(R.id.image_1c);
                    iv.setImageResource(R.drawable.prodcompban);
                    iv.setVisibility(View.VISIBLE);
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onClickI(2314);
                        }
                    });
                    break;
                case 2315:
                    ImageView iv2 = (ImageView) findViewById(R.id.image_1c);
                    iv2.setImageResource(R.drawable.prodcompappnz);
                    iv2.setVisibility(View.VISIBLE);
                    iv2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onClickI(2315);
                        }
                    });
                    break;
                case 2316:
                    ImageView iv3 = (ImageView) findViewById(R.id.image_1c);
                    iv3.setImageResource(R.drawable.prodcompappch);
                    iv3.setVisibility(View.VISIBLE);
                    iv3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onClickI(2316);
                        }
                    });
                    break;
            }
            if(scanNr1 != scanNr2){
                switch (scanNr2){
                    case 2314:
                        ImageView iv = (ImageView) findViewById(R.id.image_2c);
                        iv.setImageResource(R.drawable.prodcompban);
                        iv.setVisibility(View.VISIBLE);
                        iv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onClickI(2314);
                            }
                        });
                        break;
                    case 2315:
                        ImageView iv2 = (ImageView) findViewById(R.id.image_2c);
                        iv2.setImageResource(R.drawable.prodcompappnz);
                        iv2.setVisibility(View.VISIBLE);
                        iv2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onClickI(2315);
                            }
                        });
                        break;
                    case 2316:
                        ImageView iv3 = (ImageView) findViewById(R.id.image_2c);
                        iv3.setImageResource(R.drawable.prodcompappch);
                        iv3.setVisibility(View.VISIBLE);
                        iv3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onClickI(2316);
                            }
                        });
                        break;
                }
            }



    }

    private void onClickI(int i){
        Intent inte = new Intent(this, InfoPreviewActivity.class);
        inte.putExtra("ScanNr1", i);
        startActivity(inte);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, InfoPreviewActivity.class);
        i.putExtra("ScanNr1", SN);
        startActivity(i);
    }
}
