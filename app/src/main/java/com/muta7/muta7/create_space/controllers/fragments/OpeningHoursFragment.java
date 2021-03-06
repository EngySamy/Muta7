package com.muta7.muta7.create_space.controllers.fragments;

import android.app.DialogFragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.muta7.muta7.R;
import com.muta7.muta7.create_space.helpers.SubmitListener;
import com.muta7.muta7.general_resources.TimePickerFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

/**
 * Created by DeLL on 22/07/2017.
 */

public class OpeningHoursFragment extends CreateSpaceFragmentBase {
    SubmitListener mCallback;
    Vector<Vector<TextView> > workingHours;
    LinearLayout daysLayout;
    boolean customize=false;
    int diffHours,daysCount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView= inflater.inflate(R.layout.opening_hours, container, false);
        final Button start=(Button) rootView.findViewById(R.id.startTime);
        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment(start);
                newFragment.show(getActivity().getFragmentManager(), "timePicker");
            }
        });

        final Button end=(Button) rootView.findViewById(R.id.endTime);
        end.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment(end);
                newFragment.show(getActivity().getFragmentManager(), "timePicker");
            }
        });

        daysLayout=(LinearLayout) rootView.findViewById(R.id.daysLayout);

        final CheckBox[] weekDays;
        weekDays=new CheckBox[7];
        weekDays[0]=(CheckBox)rootView.findViewById(R.id.day0);
        weekDays[1]=(CheckBox)rootView.findViewById(R.id.day1);
        weekDays[2]=(CheckBox)rootView.findViewById(R.id.day2);
        weekDays[3]=(CheckBox)rootView.findViewById(R.id.day3);
        weekDays[4]=(CheckBox)rootView.findViewById(R.id.day4);
        weekDays[5]=(CheckBox)rootView.findViewById(R.id.day5);
        weekDays[6]=(CheckBox)rootView.findViewById(R.id.day6);
        final String[] weekDaysStrings=getResources().getStringArray(R.array.week);


        final Button custom =(Button) rootView.findViewById(R.id.customButton);
        final Button undo =(Button) rootView.findViewById(R.id.undoCustomButton);
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                custom.setVisibility(View.INVISIBLE);
                undo.setVisibility(View.VISIBLE);
                try {
                    diffHours =timeDifference(start.getText().toString(),end.getText().toString());
                    Vector<String> workDays=new Vector<>();
                    for(int i=0;i<7;i++){
                        if(!weekDays[i].isChecked())
                            workDays.add(weekDaysStrings[i]);
                    }

                    customization(start.getText().toString(),daysLayout,workDays);
                    customize=true;
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undo.setVisibility(View.INVISIBLE);
                custom.setVisibility(View.VISIBLE);
                daysLayout.removeAllViews();
                customize=false;
            }
        });

        Button submit=(Button) rootView.findViewById(R.id.Submit);
        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mCallback.Submit(1);
                    }
                }
        );


        return rootView;

    }

    private int timeDifference(String d1,String d2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date1 = simpleDateFormat.parse(d1);
        Date date2 = simpleDateFormat.parse(d2);

        long difference = date2.getTime() - date1.getTime();
        double days =  (difference / (1000*60*60*24));
        double hours =  ((difference - (1000*60*60*24*days)) / (1000*60*60));
        //int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
        hours = (hours < 0 ? -hours : hours);
        if(date1.compareTo(date2)>0)
            hours=24-hours;
        Toast.makeText(getContext(),"======= Hours"+" :: "+(int) Math.ceil(hours),Toast.LENGTH_LONG).show();
        return (int) Math.ceil(hours);
    }

    private void customization( String firstHour, LinearLayout parentLayout, Vector<String> workingDays) throws ParseException {
        daysCount=workingDays.size();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = simpleDateFormat.parse(firstHour);
        Calendar cal =Calendar.getInstance();
        cal.setTime(date);

        workingHours=new Vector<>(daysCount);

        for(int i=0;i<diffHours+1;i++){
            Vector<TextView> day=new Vector<>(diffHours);
            LinearLayout oneDayLayout=new LinearLayout(getContext());
            oneDayLayout.setOrientation(LinearLayout.HORIZONTAL);
            oneDayLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            oneDayLayout.setWeightSum(diffHours);
            parentLayout.addView(oneDayLayout);
            //add new button
            for(int j=0;j<daysCount+1;j++){
                final TextView hour=new TextView(getContext());
                final float dimInPixels= getResources().getDimension(R.dimen.small_entry_in_dp);
                final float mrgnInPixels= getResources().getDimension(R.dimen.margin);
                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams((int)dimInPixels,(int)dimInPixels,1);
                params.setMargins((int)mrgnInPixels,(int)mrgnInPixels,(int)mrgnInPixels,(int)mrgnInPixels);
                hour.setLayoutParams(params);

                if(i==0){
                    if(j!=0){
                        hour.setText(workingDays.elementAt(j-1));
                    }
                } else if(j==0){

                    Integer temp=cal.get(Calendar.HOUR_OF_DAY);
                    hour.setText(temp.toString());
                    cal.add(Calendar.HOUR_OF_DAY,1);  //add one hour

                } else {
                    hour.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.alphaSelect));
                    //click listener for each cell
                    hour.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ColorDrawable buttonColor = (ColorDrawable) hour.getBackground();
                            int colorId = buttonColor.getColor();
                            if (colorId == ContextCompat.getColor(getContext(), R.color.alphaSelect))
                            {
                                hour.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.alphaUnselect));
                                Toast.makeText(getContext(),"Asassy",Toast.LENGTH_LONG).show();
                            }

                            else{
                                hour.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.alphaSelect));
                                Toast.makeText(getContext(),"msh Asassy.. colorId "+colorId,Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }

                day.add(hour);
                oneDayLayout.addView(hour);
            }
            workingHours.add(day);
        }
    }

    private void getCustomizedHours(){
        if(workingHours!=null &&customize){

            for(int i=0;i<diffHours+1;i++) {
                for (int j = 0; j < daysCount + 1; j++) {
                    ColorDrawable buttonColor = (ColorDrawable) workingHours.elementAt(i).elementAt(j).getBackground();
                    int colorId = buttonColor.getColor();
                    if (colorId == ContextCompat.getColor(getContext(), R.color.alphaSelect))
                    {

                    }

                }
            }


        }
    }


    @Override
    public boolean validate() {
        return false;
    }

    @Override
    public Object getData() {
        return null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SubmitListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement SubmitListener");
        }
    }
}
