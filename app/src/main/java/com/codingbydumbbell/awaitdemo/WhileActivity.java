package com.codingbydumbbell.awaitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class WhileActivity extends AppCompatActivity {

    private TextView textView;
    private ProgressBar bar;
    private Button btn;

    int i = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        findViews();
    }

    private void findViews() {
        textView = findViewById(R.id.textView);
        bar = findViewById(R.id.progressBar);
        btn = findViewById(R.id.button);
    }

    public void onClick(View view) {
        // 設定 UI
        textView.setText("演算中⋯⋯");
        btn.setEnabled(false);
        bar.setVisibility(View.VISIBLE);
        doThings(); // 執行某些耗時工作

        while (true) {
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i != -1) {
                textView.setText("演算結果：" + i);
                btn.setEnabled(true);
                bar.setVisibility(View.GONE);
                i = -1;
                break;
            }
        }
    }

    private void doThings() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000); // 模擬演算
                    i = new Random().nextInt(10);
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
