package com.example.googlemap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class BusActivity extends AppCompatActivity {

    GridLayout mainGrid;


    LinearLayout layoutForTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);




        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        openEvent(mainGrid);

    }

    private void openEvent(GridLayout mainGrid) {

        for (int i=0;i<mainGrid.getChildCount();i++){
            CardView cardView = (CardView)mainGrid.getChildAt(i);

            final int finalI =i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(BusActivity.this,"click on grid"+ finalI,Toast.LENGTH_SHORT).show();
                    if(finalI==1)  openMap();
                    else if (finalI==0) openTriplist();
                    else if (finalI==2) openCommitteelist();
                    else if (finalI==3) openMapForDestinationAlarm();
                    else  if(finalI==4) openMapForBusArrivalAlarm();
                    else openNotification();

                }
            });
        }
    }

    private void openNotification(){

    }

    private void openMapForBusArrivalAlarm() {

        String key = "arrival";
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("key",key);
        startActivity(intent);
    }
    private void openMapForDestinationAlarm(){

        String key = "destination";
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("key",key);
        startActivity(intent);


    }
    private void openCommitteelist() {

        Intent intent = new Intent(this, CommitteeList.class);
        startActivity(intent);

    }
    private void openTriplist() {
//        layoutForTrips = findViewById(R.id.layout);
//        ImageView imageView = new ImageView(BusActivity.this);
//        imageView.setImageResource(R.drawable.bg1);

       // addView(imageView);

        Intent intent = new Intent(this, TripList.class);
        startActivity(intent);


    }

    public void addView(ImageView imageView){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500,500);
        layoutParams.setMargins(0,10,0,10);

        imageView.setLayoutParams(layoutParams);
        layoutForTrips.addView(imageView);
    }

    public void openMap(){

        String key = "location";
        Intent intent = new Intent(this,MapsActivity.class);
        intent.putExtra("key",key);
        startActivity(intent);
    }


}
