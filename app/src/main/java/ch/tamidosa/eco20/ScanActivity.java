package ch.tamidosa.eco20;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class ScanActivity extends AppCompatActivity {

    private final int CAMERA_RESULT = 101;

    public final int CUSTOMIZED_REQUEST_CODE = 0x0000ffff;

    ArrayList<Integer> comp = new ArrayList<Integer>();

    private boolean compar = false;

    private int SN;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        Intent intent = getIntent();
        compar = intent.getBooleanExtra("compar", false);
        int scanNr = intent.getIntExtra("scanNr", -1);
        // Toast.makeText(getBaseContext(), "Got:" + scanNr, Toast.LENGTH_LONG).show();
        SN = scanNr;

        //Get permission for Camera
        if(ContextCompat.checkSelfPermission(ScanActivity.this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            StartScanning("already Granted");
        }
        else{
            if(shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)){
                //Toast.makeText(getApplicationContext(), "Permission Needed.", Toast.LENGTH_LONG).show();
            }
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, CAMERA_RESULT);
            StartScanning("just Granted");
        }




    }

    private void StartScanning(String text) {
        //Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
        new IntentIntegrator(this).initiateScan(); // `this` is the current Activity
    }


    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != CUSTOMIZED_REQUEST_CODE && requestCode != IntentIntegrator.REQUEST_CODE) {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        switch (requestCode) {
            case CUSTOMIZED_REQUEST_CODE: {
                Toast.makeText(this, "REQUEST_CODE = " + requestCode, Toast.LENGTH_LONG).show();
                break;
            }
            default:
                break;
        }

        IntentResult result = IntentIntegrator.parseActivityResult(resultCode, data);

        if(result.getContents() == null) {
            Log.d("MainActivity", "Cancelled scan");
            Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
        } else {
            Log.d("MainActivity", "Scanned");

            Intent intent;
            if(compar){
                intent = new Intent(this, DirectComparison.class);
            } else {
                intent = new Intent(this, InfoPreviewActivity.class);
            }

            String scan = result.getContents();
            Double d = Double.parseDouble(scan);
            if(d < Integer.MAX_VALUE)
            intent.putExtra("ScanNr1", d.intValue());
            intent.putExtra("ScanNr2", SN);
            startActivity(intent);
            }

        }
    }


