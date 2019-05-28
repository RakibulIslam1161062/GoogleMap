package com.example.googlemap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.googlemap.Login.loginCheck;

public class Home extends NavActivity {


    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public static final int PICK_IMAGE=100;
    Uri imageUri;
    ImageView imageView;
    ImageView imageView2;


    private ImageButton ib ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_home);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreateDrawer();


        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.red3);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2756a3")));




        drawerLayout = findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
        drawerLayout.setBackgroundColor(8423833);
        //this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


      // getSupportActionBar().setHomeAsUpIndicator(R.drawable.lo);


        imageView2 = (ImageView) findViewById(R.id.bus2);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Home.this,BusList.class);
                startActivity(intent2);
            }
        });


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
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.du.ac.bd"));
                    startActivity(intent);
                    break;
                }


                case R.id.emerg: {

                    Intent i = new Intent(Intent.ACTION_DIAL);
                    String p = "tel:999"  ;
                    i.setData(Uri.parse(p));
                    startActivity(i);
                    break;
                   // Toast.makeText(Home.this,"lolo",Toast.LENGTH_SHORT).show();
                }
                case R.id.settings: {
                   // Toast.makeText(Home.this,"lolo",Toast.LENGTH_SHORT).show();

                    Intent intent2 = new Intent(Home.this,ResetPass.class);
                    startActivity(intent2);

                    break;
                }
                case R.id.logout: {

                    SharedPreferences.Editor editor = getSharedPreferences(loginCheck, MODE_PRIVATE).edit();
                    editor.putBoolean("serverCheck", false);
                    editor.commit();

                    Intent intent2 = new Intent(Home.this,Login.class);
                    startActivity(intent2);
                    break;
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
