package com.muzkabugu.shadyfade.loginscreen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by shadyfade on 8/3/15.
 */

public class NotifyListActivity extends ActionBarActivity {
    public ListView lv_notify;

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
            lv_notify = (ListView) findViewById(R.id.lv_notify);


            Global.getService().GetNotify(ActiveUser.user.getUsername(), new Callback<Posts>() {
                @Override
                public void success(final Posts posts, Response response) {

                    final CustomNotifyListAdapter adapter = new CustomNotifyListAdapter(NotifyListActivity.this, posts.Posts);
                    lv_notify.setAdapter(adapter);


                    final LayoutInflater inflater = (LayoutInflater)NotifyListActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    final AlertDialog.Builder changenotify = new AlertDialog.Builder(NotifyListActivity.this);
                    final View arrangementDialogView = inflater.inflate(R.layout.dialogbox_arrangement, null);
                    changenotify.setView(arrangementDialogView);

                    lv_notify.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(final AdapterView<?> parent, final View view, int position, long id) {

                            changenotify.setTitle("Arrangement for " + posts.Posts.get(position).getTitle());

                            TextView et_title = (TextView) arrangementDialogView.findViewById(R.id.change_title);
                            TextView et_tag = (TextView) arrangementDialogView.findViewById(R.id.change_tag);
                            TextView et_content = (TextView) arrangementDialogView.findViewById(R.id.change_content);
                            TextView et_date = (TextView) arrangementDialogView.findViewById(R.id.change_date);

                            et_title.setText(posts.Posts.get(position).getTitle());
                            et_tag.setText(posts.Posts.get(position).getTag().getName());
                            et_content.setText(posts.Posts.get(position).getContent());
                            et_date.setText(posts.Posts.get(position).getPublished_at());

                            /*changenotify.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            changenotify.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            changenotify.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });*/

                            changenotify.show();
                        }
                    });
                    Toast.makeText(NotifyListActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
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
