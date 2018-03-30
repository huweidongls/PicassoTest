package com.example.a99zan.picassotest;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.textView)
    TextView textView;
    OkHttpClient okHttpClient;
    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    textView.setText("123123");
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @OnClick({R.id.button})
    public void onClick (View view){
        switch (view.getId()){
            case R.id.button:
//                load();
//                startActivity(new Intent(MainActivity.this, Main3Activity.class));
                playMusic();
                break;
        }
    }

    private void playMusic() {
        MediaPlayer mediaPlayer = new MediaPlayer();
        //重置mediaplayer
        mediaPlayer.reset();
        //将需要播放的资源与之绑定
        mediaPlayer = MediaPlayer.create(this, Uri.parse("http://abv.cn/music/红豆.mp3"));
        //开始播放
        mediaPlayer.start();
        //是否循环播放
        mediaPlayer.setLooping(false);
    }

    private void load() {
        okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    private Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e("111", "请求失败");
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Log.e("111", response.body().string());
            Message message = new Message();
            message.obj = response.body().string();
            message.what = 1;
            handler.sendMessage(message);
        }
    };

}
