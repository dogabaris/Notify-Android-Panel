package com.muzkabugu.shadyfade.loginscreen;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by shadyfade on 8/7/15.
 */
public class AddNotifyActivity extends Activity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_addnotify);

        progressDialog = new ProgressDialog(this);

        Button btn_datepicker           = (Button) findViewById(R.id.btn_date);
        Button btn_timepicker           = (Button) findViewById(R.id.btn_time);
        Button btn_publish              = (Button) findViewById(R.id.btn_publish);
        final TextView tv_date          = (TextView) findViewById(R.id.tv_date);
        final TextView tv_time          = (TextView) findViewById(R.id.tv_time);
        final EditText et_title         = (EditText) findViewById(R.id.et_title);
        final EditText et_tag           = (EditText) findViewById(R.id.et_tag);
        final EditText et_announcement  = (EditText) findViewById(R.id.et_announcement);

        et_title.setMaxLines(1);
        et_tag.setMaxLines(1);
        et_announcement.setMaxLines(21);
        et_announcement.setVerticalScrollBarEnabled(true);
        et_announcement.setMovementMethod(new ScrollingMovementMethod());


        btn_datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);
                int month = mcurrentTime.get(Calendar.MONTH);
                int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker;
                datePicker = new DatePickerDialog(AddNotifyActivity.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        tv_date.setText( dayOfMonth + "/" + (monthOfYear + 1) + "/"+year);

                    }
                },year,month,day);

                datePicker.setTitle("Set Date");
                //datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Set", datePicker);
                //datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", datePicker);

                datePicker.show();

            }
        });

        btn_timepicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog timePicker;
                timePicker = new TimePickerDialog(AddNotifyActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        tv_time.setText( String.format("%02d:%02d", selectedHour, selectedMinute));
                    }
                }, hour, minute, true);

                timePicker.setTitle("Set Time");
                //timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Set", timePicker);
                //timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel", timePicker);

                timePicker.show();
            }
        });

        btn_publish.setOnClickListener(publish);

    }

    View.OnClickListener publish = new View.OnClickListener() {


        @Override
        public void onClick(View v) {

            final EditText title            = (EditText) findViewById(R.id.et_title);
            final EditText tag              = (EditText) findViewById(R.id.et_tag);
            final EditText announcement     = (EditText) findViewById(R.id.et_announcement);
            final TextView date           = (TextView) findViewById(R.id.tv_date);
            final TextView time           = (TextView) findViewById(R.id.tv_time);

            final Notify notify = new Notify();
            notify.setUsername(ActiveUser.user.getUsername());
            notify.setTitle(title.getText().toString());
            notify.setTag(tag.getText().toString());
            notify.setAnnouncement(announcement.getText().toString());
            notify.setDate(date.getText().toString() + " " + time.getText().toString());




            progressDialog.setMessage("Running...");
            progressDialog.setCancelable(false);
            progressDialog.show();


            Global.service.AddNotify(notify, new Callback<Notify>() {
                @Override
                public void success(Notify notify, Response response) {
                    progressDialog.dismiss();
                    Toast.makeText(AddNotifyActivity.this, "Success!", Toast.LENGTH_LONG).show();
                }

                @Override
                public void failure(RetrofitError error) {
                    progressDialog.dismiss();
                    Toast.makeText(AddNotifyActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    //Toast.makeText(AddNotifyActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });

        }
    };

}
