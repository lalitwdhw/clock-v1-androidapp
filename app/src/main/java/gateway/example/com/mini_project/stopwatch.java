package gateway.example.com.mini_project;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class stopwatch extends Fragment{

    View view;
    TextView textView;
    Button start, pause, reset;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stopwatch, container, false);

        textView = (TextView) view.findViewById(R.id.textView);
        start = (Button) view.findViewById(R.id.button);
        pause = (Button) view.findViewById(R.id.button2);
        reset = (Button) view.findViewById(R.id.button3);
        handler = new Handler();


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                reset.setEnabled(false);

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeBuff += MillisecondTime;
                handler.removeCallbacks(runnable);
                reset.setEnabled(true);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MillisecondTime = 0L;
                StartTime = 0L;
                TimeBuff = 0L;
                UpdateTime = 0L;
                Seconds = 0;
                Minutes = 0;
                MilliSeconds = 0;
                textView.setText("00:00:00");
            }
        });

        return view;
    }
        public Runnable runnable = new Runnable() {
            public void run() {
                MillisecondTime = SystemClock.uptimeMillis() - StartTime;
                UpdateTime = TimeBuff + MillisecondTime;
                Seconds = (int) (UpdateTime / 1000);
                Minutes = Seconds / 60;
                Seconds = Seconds % 60;
                MilliSeconds = (int) (UpdateTime % 1000);
                textView.setText("" + Minutes + ":"
                        + String.format("%02d", Seconds) + ":"
                        + String.format("%03d", MilliSeconds));
                handler.postDelayed(this, 0);
            }
        };

}
