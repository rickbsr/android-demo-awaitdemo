package com.codingbydumbbell.awaitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class TagActivity extends AppCompatActivity {

    private TextView textView;
    private ProgressBar bar;
    private Button btn;

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        findViews();

        this.view = new View(this) {
            @Override
            public void setTag(final Object tag) {
                System.out.println(tag);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("演算結果：" + tag.toString());
                        btn.setEnabled(true);
                        bar.setVisibility(View.GONE);
                    }
                });
            }
        };
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
    }

    private void doThings() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000); // 模擬演算
                    view.setTag(new Random().nextInt(10)); // 將結果放入 Tag 中
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
