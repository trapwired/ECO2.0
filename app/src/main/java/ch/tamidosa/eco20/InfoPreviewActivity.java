package ch.tamidosa.eco20;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class InfoPreviewActivity extends AppCompatActivity {

    //Bool for FLAB pressed?
    private boolean isFABOpen = false;
    private LinkedHashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();

    private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;

    private int SN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_preview);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FLAB Stuff
        FloatingActionButton fab = findViewById(R.id.fab);
        FloatingActionButton fab1 = findViewById(R.id.fab1);
        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });

        Intent intent = getIntent();
        SN = intent.getIntExtra("ScanNr1", 0);
        //adapt all info depending on scanNr


        //expandable stuff
        // add data for displaying in expandable list view
        loadData(SN);

        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(InfoPreviewActivity.this, deptList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        //expand all the Groups
        expandAll();

        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //get the child info
                ChildInfo detailInfo = headerInfo.getProductList().get(childPosition);

                //display it or do something with it
                //Toast.makeText(getBaseContext(), " Clicked on :: " + headerInfo.getName()
                 //       + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //display it or do something with it
                //Toast.makeText(getBaseContext(), " Header is :: " + headerInfo.getName(),
                //        Toast.LENGTH_LONG).show();

                return false;
            }
        });
    }

    public void onfab1Click(View view){
        Intent i = new Intent(this, ScanActivity.class);
        i.putExtra("scanNr", SN);
        i.putExtra("compar", true);
        startActivity(i);
    }

    public void onfab2Click(View view){
        Intent i = new Intent(this, ScanActivity.class);

        startActivity(i);
    }

    private void showFABMenu(){
        isFABOpen=true;
        LinearLayout fabLayout1 = (LinearLayout) findViewById(R.id.fabLayout1);
        LinearLayout fabLayout2 = (LinearLayout) findViewById(R.id.fabLayout2);
        fabLayout1.setVisibility(View.VISIBLE);
        fabLayout2.setVisibility(View.VISIBLE);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.animate().rotationBy(180);
        fabLayout1.animate().translationY(-getResources().getDimension(R.dimen.fab1_up));
        fabLayout2.animate().translationY(-getResources().getDimension(R.dimen.fab2_up));

        //fabLayout1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        //fabLayout2.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
        //fabLayout3.animate().translationY(-getResources().getDimension(R.dimen.standard_145));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        LinearLayout fabLayout1 = (LinearLayout) findViewById(R.id.fabLayout1);
        LinearLayout fabLayout2 = (LinearLayout) findViewById(R.id.fabLayout2);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.animate().rotationBy(180);
        fabLayout1.animate().translationY(0);
        fabLayout2.animate().translationY(0);
        fabLayout1.setVisibility(View.GONE);
        fabLayout2.setVisibility(View.GONE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.collapseGroup(i);
        }
    }

    //load some initial data into out list
    private void loadData(int scanNr){
        //2314
        //2315
        //2316
        switch(scanNr){ //EcoScore rechnungen stimmen!!!
            case 2314:
                setHeader("Chiquita Banana","2.90CHF/kg","18.7");
                addProduct("Score", "18.7 fully grown beeches need one day to compensate the CO2 emissions of 1 kg Chiquita Banana's from Colombia");
                addProduct("Trace","TransportBanana");
                addProduct("Components","Banana in Store:\t0.1 E/kg");
                addProduct("Components","Transport:\t2.8 E/kg");
                addProduct("Components","Ripe Chamber:\t3.2 E/kg");
                addProduct("Components","Shipping:\t8.9 E/kg");
                addProduct("Components","Storage:\t1.1 E/kg");
                addProduct("Components","Transport:\t1.9 E/kg");
                addProduct("Components","Storage:\t0.3 E/kg");
                addProduct("Components","Packaging:\t0.2 E/kg");
                addProduct("Components","Harvest:\t0.1 E/kg");
                addProduct("Components","Cultivation:\t0.1 E/kg");
                ImageView prImage = (ImageView)findViewById(R.id.prodfarm);
                prImage.setVisibility(View.INVISIBLE);
                break;
            case 2315:
                setHeader("Gala Apple CH","4.90CHF/kg","4.9");
                addProduct("Score", "4.9 fully grown beeches need one day to compensate the CO2 emissions of 1 kg local grown Gala Apple's.\nThis number can deviate strongly depending on the season");
                addProduct("Trace","TransportAppleCH");
                addProduct("Components","Apple in Store:\t 0.1 E/kg");
                addProduct("Components","Transport:\t 1.2 E/kg");
                addProduct("Components","Storage:\t 0.2 E/kg");
                addProduct("Components","Transport:\t 1.0 E/kg");
                addProduct("Components","Packaging:\t 0.2 E/kg");
                addProduct("Components","Storehouse:\t 0.3 E/kg");
                addProduct("Components","Transport:\t 1.6 E/kg");
                addProduct("Components","Harvest:\t 0.2 E/kg");
                addProduct("Components","Cultivation:\t 0.1 E/kg");
                break;
            case 2316:
                default:
                    setHeader("Dazzle Apple NZ","4.10CHF/kg","29.2");
                    addProduct("Score", "29.2 fully grown beeches need one day to compensate the CO2 emissions of 1kg imported Dazzle Apples from New Zealand");
                    addProduct("Trace","TransportAppleNZ");
                    addProduct("Components","Apple in Store:\t 0.1 E/kg");
                    addProduct("Components","Transport:\t 2.9 E/kg");
                    addProduct("Components","Storage:\t 1.4 E/kg");
                    addProduct("Components","Shipping:\t 18.9 E/kg");
                    addProduct("Components","Storehouse:\t 1.9 E/kg");
                    addProduct("Components","Transport:\t 3.6 E/kg");
                    addProduct("Components","Packaging:\t 0.2 E/kg");
                    addProduct("Components","Harvest:\t 0.2 E/kg");
                    addProduct("Components","Cultivation:\t 0.1 E/kg");
        }
    }

    private void setHeader(String name, String price, String score){

        TextView prPrice = (TextView)findViewById(R.id.product_price);
        TextView prName = (TextView)findViewById(R.id.product_name);
        TextView prScore = (TextView)findViewById(R.id.product_score);
        ImageView prImage = (ImageView)findViewById(R.id.product_preview_img);
        prPrice.setText(price);
        prName.setText(name);
        prScore.setText(score);
        if(name.equals("Chiquita Banana")){
            prImage.setImageResource(R.drawable.bananen);
        }else if(name.equals("Gala Apfel CH")){
            prImage.setImageResource(R.drawable.aepfel_gold_kiss);
        }else{
            prImage.setImageResource(R.drawable.aepfel_sweetango);
        }

    }

    //here we maintain our products in various departments
    private int addProduct(String department, String product){

        int groupPosition = 0;

        //check the hash map if the group already exists
        GroupInfo headerInfo = subjects.get(department);
        //add the group if doesn't exists
        if(headerInfo == null){
            headerInfo = new GroupInfo();
            headerInfo.setName(department);
            subjects.put(department, headerInfo);
            deptList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<ChildInfo> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        ChildInfo detailInfo = new ChildInfo();
        //detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setName(product);
        if(department == "Trace"){ detailInfo.setSource(product); }
        else{detailInfo.setSource((""));}
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
    }

    public void onClickFam(View view){
        Intent i = new Intent(this, FamilyComparison.class);
        startActivity(i);
    }

}
