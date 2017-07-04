package gateway.example.com.mini_project;


import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class alarm extends Fragment{

    View view;

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static alarm inst;
    private TextView alarmTextView;
    Button alarmToggle;
    public static alarm instance() {
        return inst;
    }

    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.alarm, container, false);
        alarmTimePicker = (TimePicker) view.findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) view.findViewById(R.id.alarmText);
        alarmToggle = (Button) view.findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);


        alarmToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    Log.d("MyActivity", "Alarm On");
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
                    Intent myIntent = new Intent(view.getContext(), AlarmReceiver.class);
                    pendingIntent = PendingIntent.getBroadcast(view.getContext(), 0, myIntent, 0);
                    alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
                }
            }
        });
    return view;
    }
    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }

}

