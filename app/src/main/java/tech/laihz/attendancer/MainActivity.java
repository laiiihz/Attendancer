package tech.laihz.attendancer;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    Handler handler=new UIHnadler();
    int positionMain=0;
    int count=0;

//    class Worked extends Thread{
//        void getMsg(String url) throws IOException {
//            HttpGets httpGets=new HttpGets();
//            String result=httpGets.run(url);
//            Message msg=new Message();
//            Bundle bundle=new Bundle();
//            bundle.putString("message",result);
//            msg.setData(bundle);
//            handler.sendMessage(msg);
//        }
//    }

    class UIHnadler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle=msg.getData();
            String result=bundle.getString("message");
            System.out.print("#################"+result);
            ProgressBar progressBar=findViewById(R.id.progressBar1);
            Button button=findViewById(R.id.button_login);
            progressBar.setVisibility(View.INVISIBLE);
            button.setClickable(true);
            if(result.equals("0")){
                AlertDialog.Builder ab=new AlertDialog.Builder(MainActivity.this);
                ab.setTitle("账户或密码错误");
                ab.setMessage("请重新输入账户或密码");
                AlertDialog ad=ab.create();
                ad.show();
            }else if(result.equals("1")){
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,RealMainActivity.class);
                finish();
                startActivity(intent);
            }else{
                AlertDialog.Builder ab=new AlertDialog.Builder(MainActivity.this);
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
        setContentView(R.layout.activity_main);
        ProgressBar progressBar=findViewById(R.id.progressBar1);
        Button button=findViewById(R.id.button_login);
        Button button1=findViewById(R.id.test_button);
        Spinner spinner=findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener(){
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        positionMain=position;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                }
        );

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                button.setClickable(false);
                TextView textViewName=findViewById(R.id.editTextName);
                TextView textViewPsw=findViewById(R.id.editTextPsw);


                String name=textViewName.getText().toString();
                String psw=textViewPsw.getText().toString();
                new Thread(){
                    public void run(){
                        HttpGets httpGets=new HttpGets();
                        try {
                            String result=httpGets.run("http://10.27.17.60:4567/type/"+positionMain+"/name/"+name+"/psw/"+psw);
                            Message msg = new Message();
                            Bundle data = new Bundle();
                            System.out.print("@@@@@@@@@@@@@@@@"+result);
                            data.putString("message",result);
                            msg.setData(data);
                            handler.sendMessage(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        Button textView=findViewById(R.id.textDev);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(count==10){
                    Toast.makeText(MainActivity.this,"继续点击进入开发者模式",Toast.LENGTH_SHORT).show();
                }
                if(count>10){
                    Toast.makeText(MainActivity.this,"再点击"+(15-count)+"次进入开发模式",Toast.LENGTH_SHORT).show();
                }
                if(count==15){
                    count=0;
                    Intent intent=new Intent();
                    intent.setClass(MainActivity.this,DevActivity.class);
                    startActivity(intent);
                }
            }
        });

        /*------------------------FOR TEST ONLY-----------------------------*/
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,RealMainActivity.class);
                finish();
                startActivity(intent);
            }
        });
        /*------------------------FOR TEST ONLY-----------------------------*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.main_menu_about){
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
