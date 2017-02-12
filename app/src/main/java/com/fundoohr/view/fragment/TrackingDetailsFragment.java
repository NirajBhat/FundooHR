package com.fundoohr.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.fundoohr.bridgeit.fundoohr.R;
import com.fundoohr.callback.TrackingDetailArrayInterface;
import com.fundoohr.model.TrackingDetailsModel;
import com.fundoohr.utility.ProgressUtil;
import com.fundoohr.viewmodel.TrackingDetailViewModel;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
/**
 * * Purpose:
 * It Is The View Of MVVM Design Pattern.
 * It Is The UI Class Which Hold The UI Elements.
 * It Listens To Action Performed In UI class.
 * It Implements And The Observer Pattern To Listen Changes In The View model.
 * It Holds The View model To Update Its State Of The UI.
 * It is The Activity Which Need To Be Included In Manifest.xml File.
 **/
public class TrackingDetailsFragment extends Fragment implements TrackingDetailArrayInterface {
    ProgressUtil prog;
    Button mSave,mCancel;
    String mTrack_url;
    ImageButton mEditbutton;
    EditText mEditText, mEditText1, mEditText2, mEditText3, mEditText4, mEditText5;
    SharedPreferences mSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracking_details, container, false);
        mSave= (Button) view.findViewById(R.id.save);
        mCancel = (Button) view.findViewById(R.id.cancel);
        mEditbutton = (ImageButton) view.findViewById(R.id.track_edit);
        mEditText = (EditText) view.findViewById(R.id.track_stack);
        mEditText1 = (EditText) view.findViewById(R.id.track_startdate);
        mEditText2 = (EditText) view.findViewById(R.id.track_enddate);
        mEditText3 = (EditText) view.findViewById(R.id.track_currweek);
        mEditText4 = (EditText) view.findViewById(R.id.track_weekleft);
        mEditText5 = (EditText) view.findViewById(R.id.track_week1);


        mEditText.setFocusable(false); //to disable it
        mEditText1.setFocusable(false);
        mEditText2.setFocusable(false);
        mEditText3.setFocusable(false);
        mEditText4.setFocusable(false);
        mEditText5.setFocusable(false);
        mSave.setVisibility(View.INVISIBLE);
        mCancel.setVisibility(view.INVISIBLE);
        mEditbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // mEditText.setEnabled(true); // to enable it
                mEditText.setFocusableInTouchMode(true); //to enable it
                mEditText1.setFocusableInTouchMode(true);
                mEditText2.setFocusableInTouchMode(true);
                mEditText3.setFocusableInTouchMode(true);
                mEditText4.setFocusableInTouchMode(true);
                mEditText5.setFocusableInTouchMode(true);
                //Button to save and Cancel to Update the Data to the Server
                mSave.setVisibility(View.VISIBLE);
                mCancel.setVisibility(View.VISIBLE);
            }
        });
        mSharedPreferences = getActivity().getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String token = mSharedPreferences.getString("token", null);
        String engineerId = getArguments().getString("id");
        RequestParams requestParams = new RequestParams();
        requestParams.put("engineerId", engineerId);
        Log.i("HR", "onCreateView: token" + token);
        Log.i("HR", "onCreateView: token" + engineerId);
        mTrack_url=getResources().getString(R.string.Tracking_url);
        TrackingDetailViewModel trackViewModel = new TrackingDetailViewModel();
        trackViewModel.trackingData(token,mTrack_url,requestParams, this);
        prog = new ProgressUtil(getActivity());
        prog.showProgress("Loading...");
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestParams paramData =new RequestParams();
                paramData.put("",mEditText.getText().toString());
                paramData.put("",mEditText1.getText().toString());
                paramData.put("",mEditText2.getText().toString());
                paramData.put("",mEditText3.getText().toString());
                paramData.put("",mEditText4.getText().toString());
                paramData.put("",mEditText5.getText().toString());
               

            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCancel.setVisibility(View.INVISIBLE);
                mSave.setVisibility(View.INVISIBLE);
            }
        });


        return view;
    }

    @Override
    public void trackingData(ArrayList<TrackingDetailsModel> trackingDetailsModels) {
        prog.dismissProgress();
        TrackingDetailsModel trackingModel = trackingDetailsModels.get(0);
        mEditText.setText(trackingModel.getTechStack());
        mEditText1.setText(trackingModel.getBridgelabzStartDate());
        mEditText2.setText(trackingModel.getBridgelabzEndDate());
        mEditText3.setText(trackingModel.getCurrentWeek());
        mEditText4.setText(trackingModel.getNumberOfWeeksLeft());
        mEditText5.setText(trackingModel.getWeek1());
        Log.i("TRac", "trackingData: "+trackingModel.getWeek1());
    }
}
