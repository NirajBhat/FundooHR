package com.fundoohr.bridgeit.fundoohr.view.activity;

import android.app.DatePickerDialog;
//import android.support.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.bridgeit.fundoohr.adapter.GridCalenderAdapter;
import com.fundoohr.bridgeit.fundoohr.callback.AttendenceArrayInterface;
import com.fundoohr.bridgeit.fundoohr.model.AttendenceModel;
import com.fundoohr.bridgeit.fundoohr.utility.MonthYearPickerDialog;
import com.fundoohr.bridgeit.fundoohr.viewmodel.AttendenceViewModel;

//import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AttendanceDetailsActivity extends AppCompatActivity implements AttendenceArrayInterface {
    DatePickerDialog.OnDateSetListener pick;
    Button save, cancel, down_arrow;
    TextView textView;
    GridView calendarView;
    static String mEngeerId;
    int getmonth, getyear;
    Context mContext;
    ArrayList<AttendenceModel> attendenceModels;
    EngineerProfileActivity engineerCollapse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance__details);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        textView = (TextView) findViewById(R.id.datedisplay);
        down_arrow = (Button) findViewById(R.id.date);
        save = (Button) findViewById(R.id.btn_save);
        cancel = (Button) findViewById(R.id.btn_cancel);
        save.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);
        SharedPreferences preferences = this.getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String tokenValue = preferences.getString("token", null);
        Log.i("engg collap", "callPersonal: " + tokenValue);
        mEngeerId = getIntent().getExtras().getString("engineerId");
        Log.i("oncreate", "onCreate: " + mEngeerId);
        String[] monthNames = getResources().getStringArray(R.array.Month);
        engineerCollapse = new EngineerProfileActivity();

        String attendenceURL= getResources().getString(R.string.attendence_url);

        down_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MonthYearPickerDialog pd = new MonthYearPickerDialog();
                pd.setListener(AttendanceDetailsActivity.this, pick, calendarView);
                pd.show(getFragmentManager(), "MonthYearPickerDialog");
            }
        });
        getmonth = getIntent().getIntExtra("month", 0);
        getyear = getIntent().getIntExtra("year", 0);
        long timestamp = getIntent().getLongExtra("timeStamp", 0);
        // set data to textview
        textView.setText(monthNames[getmonth] + "," + getyear);
        //get current month and year
        Calendar _calendar = Calendar.getInstance(Locale.getDefault());
        int mMonth = _calendar.get(Calendar.MONTH) + 1;
        int mYear = _calendar.get(Calendar.YEAR);
        int mDate = _calendar.get(Calendar.DATE);

        //Sending the required parameters to ViewModel
        AttendenceViewModel attendenceViewModel = new AttendenceViewModel();
        attendenceViewModel.attendViewModelData(attendenceURL,timestamp, mEngeerId, tokenValue, this);
        Log.i("sending to view model", "engiId: " + mEngeerId);
        Log.i("sending to view model", "timeStamp: " + timestamp);
        Log.i("sending to view model", "token: " + tokenValue);

        //Sending the EngineerId to month Picker
        MonthYearPickerDialog month = new MonthYearPickerDialog();
        getFragmentManager().beginTransaction().replace(R.id.attendance_frame, month.newInstance(mEngeerId));
        Log.i("Attend", "onCreate: " + mEngeerId);





    }

    //Getting back the data from View Model were the data is obtained from Controller
    @Override
    public void getAttendArrayData(ArrayList<AttendenceModel> attendenceModels) {
        this.attendenceModels = attendenceModels;
        Log.i("attendenceModels", "getAttendArrayData: " + attendenceModels.size());
        for (int i = 0; i <attendenceModels.size() -1; i++) {
            Log.i("attendenceModels", "getAttendArrayData:1 " + attendenceModels.get(i).getMarkedStatus());
            Log.i("attendenceModels", "getAttendArrayData:1 " + attendenceModels.get(i).getAttendenceStatus());
            Log.i("attendenceModels", "getAttendArrayData:1 " + attendenceModels.get(i).getPunchIn());
            Log.i("attendenceModels", "getAttendArrayData:1 " + attendenceModels.get(i).getPunchOut());
            Log.i("attendenceModels", "getAttendArrayData:1 " + attendenceModels.get(i).getReason());


        }

        //Adapter Class to set
      /*  GridCalenderAdapter adapter = new GridCalenderAdapter(AttendanceDetailsActivity.this,attendenceModels,
                                                              getmonth + 1, getyear, save, cancel);
       // adapter.notifyDataSetChanged();
        calendarView = (GridView) findViewById(R.id.calendar);
        calendarView.setAdapter(adapter);*/




    }


}


