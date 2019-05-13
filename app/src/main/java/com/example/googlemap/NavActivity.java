package com.example.googlemap;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class NavActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public static final int PICK_IMAGE=100;
    Uri imageUri;
    ImageView imageView;

    private  ImageButton ib ;


    protected void onCreateDrawer() {


        setContentView(R.layout.activity_nav);

        drawerLayout = findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerLayout.setBackgroundColor(8423833);






        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        imageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.profile) ;
        //ib = (ImageButton) navigationView.getHeaderView(0).findViewById(R.id.ib) ;

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

       /* ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"loool",Toast.LENGTH_SHORT).show();

                openGallery();
            }
        });
*/

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        // drawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here


                        switch (menuItem.getItemId()) {

                            case R.id.trans: {
                                //do somthing
                                Toast.makeText(NavActivity.this,"lol",Toast.LENGTH_SHORT).show();
                                break;
                            }
                            case R.id.food: {

                                Toast.makeText(NavActivity.this,"lolo",Toast.LENGTH_SHORT).show();
                            }

                            case R.id.emerg: {


                                Toast.makeText(NavActivity.this,"lolo",Toast.LENGTH_SHORT).show();
                            }
                            case R.id.settings: {
                                Toast.makeText(NavActivity.this,"lolo",Toast.LENGTH_SHORT).show();
                            }
                            case R.id.logout: {
                                Toast.makeText(NavActivity.this,"lolo",Toast.LENGTH_SHORT).show();
                            }
                        }
                        //close navigation drawer
                        // mDrawerLayout.closeDrawer(GravityCompat.START);

                        drawerLayout.closeDrawers();
                        return true;
                    }
                });


    }


    public void openGallery(){

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);


        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
