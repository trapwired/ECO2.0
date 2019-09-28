package ch.tamidosa.eco20;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class InfoPreviewActivity extends AppCompatActivity {

    //Bool for FLAB pressed?
    private boolean isFABOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_preview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fab1 = findViewById(R.id.fab1);
        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });

        Intent intent = getIntent();
        int scanNr = intent.getIntExtra("scanNr", -1);
        //use ScanNr to change content
        Toast.makeText(this, "we got: " + scanNr, Toast.LENGTH_LONG).show();


        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab1 = (FloatingActionButton) findViewById(R.id.fab1);
    fab2 = (FloatingActionButton) findViewById(R.id.fab2);
    fab3 = (FloatingActionButton) findViewById(R.id.fab3);
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!isFABOpen){
                showFABMenu();
            }else{
                closeFABMenu();
            }
        }
    });


         */
    }
    public void onfab1Click(View view){
        Intent i = new Intent(this, ScanActivity.class);
        startActivity(i);
    }

    public void onfab2Click(View view){
        Intent i = new Intent(this, ScanCompareActivity.class);
        startActivity(i);
    }

    private void showFABMenu(){
        isFABOpen=true;
        FloatingActionButton fab1 = findViewById(R.id.fab1);
        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab1.animate().translationY(-getResources().getDimension(R.dimen.fab1_up));
        fab2.animate().translationY(-getResources().getDimension(R.dimen.fab2_up));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        FloatingActionButton fab1 = findViewById(R.id.fab1);
        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab1.animate().translationY(0);
        fab2.animate().translationY(0);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
