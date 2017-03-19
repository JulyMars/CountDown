package wx.learn.countdown;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText timeInput;
    private Button btnGetTimer;
    private Button btnStartTimer;
    private Button btnStopTimer;
    private TextView timerShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        timeInput = (EditText) findViewById(R.id.timeInput);
        btnGetTimer = (Button) findViewById(R.id.btnGetTimer);
        btnStartTimer = (Button) findViewById(R.id.btnStartTimer);
        btnStopTimer = (Button) findViewById(R.id.btnStopTimer);
        timerShow = (TextView) findViewById(R.id.timerShow);
        btnGetTimer.setOnClickListener(this);
        btnStartTimer.setOnClickListener(this);
        btnStopTimer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGetTimer:
                timeCount = Integer.parseInt(String.valueOf(timeInput.getText()));
                timerShow.setText(timeCount + "");
                System.out.println("timeCount:" + timeCount);
                break;
            case R.id.btnStartTimer:
                startTime();
                break;
            case R.id.btnStopTimer:
                stopTime();
                break;
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            timerShow.setText(timeCount + "");
            startTime();
        }
    };

    private Timer timer;
    private TimerTask timerTask;
    private int timeCount = 0;

    private void startTime() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                timeCount--;
                Message msg = handler.obtainMessage();
                msg.arg1 = timeCount;
                handler.sendMessage(msg);
            }
        };
        timer.schedule(timerTask, 1000);
    }

    private void stopTime() {
        timer.cancel();
    }
}
