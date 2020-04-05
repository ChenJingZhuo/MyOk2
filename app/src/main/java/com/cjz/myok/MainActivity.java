package com.cjz.myok;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAll();
        getInfo();
    }

    private void getAll(){
        new MyOk("dataInterface/UserWorkEnvironmental/getAll", "") {
            @Override
            public void ok(JSONObject jsonObject) {
                if (jsonObject!=null){
                    Log.d("5555", jsonObject.toString());
                    //切换UI线程执行UI操作
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "访问成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        };
    }

    private void getInfo(){
        //多线程并发访问
        for (int i = 5; i <= 24; i++) {
            new MyOk("dataInterface/Stage/getInfo", "id=" + i) {
                @Override
                public void ok(JSONObject jsonObject) {
                    if (jsonObject!=null){
                        Log.d("6666", jsonObject.toString());
                    }
                }
            };
        }
    }

}
