package com.muzkabugu.shadyfade.loginscreen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {

    private ProgressDialog progressDialog;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        String username = preferences.getString("username", "");
        String password = preferences.getString("password", "");

        EditText loginUsername = (EditText) findViewById(R.id.Et_Username);
        EditText loginPassword = (EditText) findViewById(R.id.Et_Password);
        Button loginButton = (Button) findViewById(R.id.BLogin);
        ImageView logo = (ImageView) findViewById(R.id.logo);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        String previoususername = preferences.getString("username", "");
        String previouspassword = preferences.getString("password", "");

        loginUsername.setText(previoususername);
        loginPassword.setText(previouspassword);

        loginButton.setOnClickListener(login);
    }

    private boolean InputControl(String str){
        Pattern inputType = Pattern.compile("^[a-zA-Z0-9_]{3,}$");
        return inputType.matcher(str).matches();
    }

    View.OnClickListener login = new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            final EditText username = (EditText) findViewById(R.id.Et_Username);
            final EditText password = (EditText) findViewById(R.id.Et_Password);

            if(!InputControl(username.getText().toString()) && !InputControl(password.getText().toString())){
                Toast.makeText(MainActivity.this,"Username and password cannot be less than 6 characters and contain special characters!",Toast.LENGTH_LONG).show();
                return;
            }

            final User user = new User();
            user.setUsername(username.getText().toString());
            user.setPassword(password.getText().toString());


            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();


                Global.service.Login(user, new Callback<UserResponse>() {

                    @Override
                    public void success(UserResponse userResponse, Response response) {
                        progressDialog.dismiss();

                        ActiveUser.user = user;
                        editor.putString("username", user.getUsername());
                        editor.putString("password", user.getPassword());
                        editor.apply();

                        ActiveUser.user = user;
                        editor.putString("username", user.getUsername());
                        editor.putString("password", user.getPassword());
                        editor.commit();

                        Toast.makeText(MainActivity.this, "Login Successful." + userResponse.getProfile().getRoles().toString(), Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(MainActivity.this, NotifyListActivity.class);
                        i.putExtra("username", user.getUsername());
                        startActivity(i);
                        MainActivity.this.finish();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        progressDialog.dismiss();

                        if (error.isNetworkError()) {
                            Toast.makeText(MainActivity.this, "Check your internet connection!", Toast.LENGTH_LONG).show();
                        } else {
                            if (error.getResponse() == null) {
                                Toast.makeText(MainActivity.this, "Timed out!", Toast.LENGTH_LONG).show();
                            } else {
                                // Error message handling
                                try {
                                    Toast.makeText(MainActivity.this, "Wrong Username or Password!", Toast.LENGTH_LONG).show();
                                } catch (Exception ex) {
                                    Toast.makeText(MainActivity.this, "Unknown error!", Toast.LENGTH_LONG).show();
                            }
                        }
                        }
                }
            });
        }

    };
}