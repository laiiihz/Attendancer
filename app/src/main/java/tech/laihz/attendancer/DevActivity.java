package tech.laihz.attendancer;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

public class DevActivity extends AppCompatActivity {

    private Handler handler=new MeHandle();

    class MeHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle=msg.getData();
            String result=bundle.getString("message");
            String result2=bundle.getString("msgall");
            System.out.print("#################"+result);
            ProgressBar progressBar=findViewById(R.id.progressBar);
            Button button=findViewById(R.id.buttonTest);
            progressBar.setVisibility(View.GONE);
            button.setClickable(true);
            TextView textViewAll=findViewById(R.id.textViewAll);
            textViewAll.setText(result2);
            if(result.equals("200")){
               TextView textView=findViewById(R.id.textView3);
               textView.setText("pass");
            }else{
                AlertDialog.Builder ab=new AlertDialog.Builder(DevActivity.this);
                ab.setTitle("fail");
                ab.setMessage("fail");
                AlertDialog ad=ab.create();
                ad.show();
            }


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);
        setTitle("开发者模式");
        Button button=findViewById(R.id.buttonTest);
        ProgressBar progressBar=findViewById(R.id.progressBar);
        TextView textView=findViewById(R.id.textViewAll);
        TextView textView1=findViewById(R.id.textView3);


        button.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            button.setClickable(false);
            textView.setText("testing...");
            textView1.setText("testing...");

            new Thread(){
                public void run(){
                    HttpGets httpGets=new HttpGets();
                    try {
                        String result=httpGets.runBaidu();
                        String result2=httpGets.runBaidud("https://baidu.com");
                        Message msg = new Message();
                        Bundle data = new Bundle();
                        System.out.print("@@@@@@@@@@@@@@@@"+result);
                        data.putString("message",result);
                        data.putString("msgall",result2);
                        msg.setData(data);
                        handler.sendMessage(msg);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        });

    }


}
