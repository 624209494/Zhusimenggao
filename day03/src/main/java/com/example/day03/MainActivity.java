package com.example.day03;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import testSingle.SingleTon1;
import testSingle.SingleTon2;
import testSingle.SingleTon3;
import testSingle.SingleTon4;
import testSingle.SingleTon5;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 单例模式
     */
    private Button mDanli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mDanli = (Button) findViewById(R.id.danli);
        mDanli.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.danli:
                testSingle();
                break;
        }
    }

    private void testSingle() {
        SingleTon1.getInstance().getsda();
        SingleTon2.getInstance().getdata();
        SingleTon3.getInstance().getdats();
        SingleTon4.getInstance().intesd();
        SingleTon5.INSTANCE.getintesds();

    }
}
