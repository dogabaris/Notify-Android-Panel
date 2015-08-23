package com.muzkabugu.shadyfade.loginscreen;

import android.app.AlertDialog;
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
                    lv_notify.setEnabled(true);
                    lv_notify.setAlpha(1);

                    final LayoutInflater inflater = (LayoutInflater)NotifyListActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
                    final AlertDialog.Builder changenotify = new AlertDialog.Builder(NotifyListActivity.this);
                    final View arrangementDialogView = inflater.inflate(R.layout.dialogbox_arrangement, null);
                    changenotify.setView(arrangementDialogView);
                    changenotify.setCancelable(false);

                    lv_notify.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id) {

                            changenotify.setTitle("Arrangement for " + posts.Posts.get(position).getTitle());

                            final EditText et_title = (EditText) arrangementDialogView.findViewById(R.id.change_title);
                            final TextView et_tag = (TextView) arrangementDialogView.findViewById(R.id.change_tag);
                            final EditText et_content = (EditText) arrangementDialogView.findViewById(R.id.change_content);
                            final EditText et_date = (EditText) arrangementDialogView.findViewById(R.id.change_date);

                            et_title.setText(posts.Posts.get(position).getTitle());
                            et_tag.setText(posts.Posts.get(position).getTag().getName());
                            et_content.setText(posts.Posts.get(position).getContent());
                            et_date.setText(posts.Posts.get(position).getPublished_at());

                            //Editable title = et_title.getText();


                            changenotify.setPositiveButton("Update", new DialogInterface.OnClickListener() {


                                //Notify arrange_notify = new Notify(posts.Posts.get(position).getUser().getUsername(),et_tag.getText().toString(),et_title.getText().toString()
                                 //                                   ,et_content.getText().toString(),et_date.getText().toString());


                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String title=et_title.getText().toString();
                                    String tag=et_tag.getText().toString();
                                    String content=et_content.getText().toString();
                                    String date=et_date.getText().toString();

                                    Notify arrange_notify = new Notify(posts.Posts.get(position).getUser().getUsername(),tag,title
                                            ,content,date);

                                    Global.getService().UpdateNotify(posts.Posts.get(position).getId(),arrange_notify, new Callback<ArrangementResponse>() {
                                        @Override
                                        public void success(ArrangementResponse arrangementResponse, Response response) {
                                            Toast.makeText(NotifyListActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void failure(RetrofitError error) {
                                            Toast.makeText(NotifyListActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                                        }
                                    });



                                    dialog.dismiss();

                                    lv_notify.setAlpha(0);
                                    lv_notify.setEnabled(false);
                                    findViewById(R.id.action_refresh).performClick();
                                }
                            });

                            changenotify.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Global.getService().DeleteNotify(posts.Posts.get(position).getId(), new Callback<ArrangementResponse>() {
                                        @Override
                                        public void success(ArrangementResponse arrangementResponse, Response response) {
                                            Toast.makeText(NotifyListActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void failure(RetrofitError error) {
                                            Toast.makeText(NotifyListActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    dialog.dismiss();

                                    lv_notify.setAlpha(0);
                                    lv_notify.setEnabled(false);
                                    findViewById(R.id.action_refresh).performClick();
                                }
                            });

                            changenotify.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    lv_notify.setAlpha(0);
                                    lv_notify.setEnabled(false);
                                    findViewById(R.id.action_refresh).performClick();
                                }
                            });

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
