package com.muzkabugu.shadyfade.loginscreen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by shadyfade on 8/3/15.
 */

public class NotifyListActivity extends ActionBarActivity {

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return true;
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Do you want to logout?");


        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                NotifyListActivity.this.finish();
            }
        });

        alert.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });

        alert.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {

            Global.getService().GetNotify(ActiveUser.user.getUsername(), new Callback<Posts>() {
                @Override
                public void success(Posts posts, Response response) {

                    //Toast.makeText(NotifyListActivity.this, posts.getPosts().get(0).getTag().getName(), Toast.LENGTH_LONG).show();
                    ListView lv_notify = (ListView) findViewById(R.id.lv_notify);
                    CustomNotifyListAdapter adapter = new CustomNotifyListAdapter(NotifyListActivity.this, posts.Posts);
                    lv_notify.setAdapter(adapter);
                }


                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(NotifyListActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            return true;
        }
        if (id == R.id.action_add) {
            //Toast.makeText(getApplicationContext(),"Add.",Toast.LENGTH_LONG).show();
            Intent i = new Intent(NotifyListActivity.this, AddNotifyActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
