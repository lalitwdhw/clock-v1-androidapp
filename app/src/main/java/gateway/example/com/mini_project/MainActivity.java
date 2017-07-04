package gateway.example.com.mini_project;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        FragmentManager fragmentmanager=getFragmentManager();

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_clock:
                    fragmentmanager.beginTransaction()
                            .replace(R.id.content,new clock())
                            .commit();
                    return true;
                case R.id.navigation_alarm:
                    fragmentmanager.beginTransaction()
                            .replace(R.id.content,new alarm())
                            .commit();
                    return true;
                case R.id.navigation_stopwatch:
                    fragmentmanager.beginTransaction()
                            .replace(R.id.content,new stopwatch())
                            .commit();

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}

/*
  fragmentmanager.beginTransaction()
                    .replace(R.id.content_frame,new firstfragment())
                    .commit();
 */
