package com.example.googlemap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.googlemap.Login.loginCheck;

public class ResetPass extends AppCompatActivity {

    private EditText oldPass1;
    private  EditText newPass1;
    private EditText rNewPass1;
    private Button confirmBtn;
    private   String userNameCheck;
    private  String passwordCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        this.getSupportActionBar().hide();

        SharedPreferences prefs = getSharedPreferences(loginCheck, MODE_PRIVATE);
        userNameCheck = prefs.getString("userName", "");
        passwordCheck = prefs.getString("password", "");

        //Toast.makeText(ResetPass.this,passwordCheck+" ",Toast.LENGTH_LONG).show();

        oldPass1 = (EditText) findViewById(R.id.oldPass);
        newPass1 = (EditText) findViewById(R.id.newPass);
        rNewPass1 = (EditText) findViewById(R.id.rNewPass);
        confirmBtn = (Button) findViewById(R.id.confirm);




        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Old,New,rNew;
                Old = oldPass1.getText().toString();
                New = newPass1.getText().toString();
                rNew = rNewPass1.getText().toString();

                //Toast.makeText(ResetPass.this,Old+" "+New+" "+rNew,Toast.LENGTH_LONG).show();


                if(passwordCheck.equals(Old) && New.equals(rNew)){

                    ResetPassCred resetPassCred = new ResetPassCred(userNameCheck,Old,rNew);
                    sendNetworkRequest(resetPassCred);
                }
                else {
                    Toast.makeText(ResetPass.this,"Incorrect Credentials",Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    public void sendNetworkRequest(ResetPassCred resetPassCred){

//192.168.1.121:4000/resetPass
        //"http://52.29.113.22/rakib/"
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://52.29.113.22/rakib/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        ApiForReset  api = retrofit.create(ApiForReset.class);
        Call<ResetPassCred> call = api.getResetPost(resetPassCred);

        call.enqueue(new Callback<ResetPassCred>() {
            @Override
            public void onResponse(Call<ResetPassCred> call, Response<ResetPassCred> response) {

                SharedPreferences.Editor editor = getSharedPreferences(loginCheck, MODE_PRIVATE).edit();
                editor.putBoolean("serverCheck", true);
                editor.putString("userName", response.body().getUserName());
                editor.putString("password",response.body().getPassword());
                editor.commit();


                Toast.makeText(ResetPass.this,"Password Reset Successfull",Toast.LENGTH_LONG).show();

                Intent intent2 = new Intent(ResetPass.this,Home.class);
                startActivity(intent2);

            }
            @Override
            public void onFailure(Call<ResetPassCred> call, Throwable t) {
                Toast.makeText(ResetPass.this,"trying",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
