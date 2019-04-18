package tech.laihz.attendancer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class RealMainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private Boolean[] studentChecked=new Boolean[100];
    private Boolean isStartNaming=false;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    //solveHome();
                    setTitle("Home");
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    setTitle("Dashboard");
                    LinearLayout linearLayout=findViewById(R.id.lineLayoutHome);

                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    setTitle(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle("Home");
        setContentView(R.layout.activity_real_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Button button=findViewById(R.id.button_name);
        button.setOnClickListener(v -> {
            if(!isStartNaming){
                isStartNaming=true;
                button.setText("点到");
            }else {

            }
        });
        ConstraintLayout constraintLayout=findViewById(R.id.start_naming);
        Switch switcher=findViewById(R.id.switchNaming);
        switcher.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                constraintLayout.setVisibility(View.GONE);
            }else {
                constraintLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.right_menu,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.menu_settings:
                openActivity(0);
                return true;
            case R.id.menu_about:
                openActivity(1);
            default:
                return false;
        }
    }

    void openActivity(int activity){
        Intent intent=new Intent();
        switch (activity){
            case 0:
                intent.setClass(RealMainActivity.this,SettingsActivity.class);
                break;
            case 1:
                intent.setClass(RealMainActivity.this,AboutActivity.class);
                break;
            default:
                Toast.makeText(RealMainActivity.this,"fail to start activity",Toast.LENGTH_SHORT).show();
                return;
        }
        startActivity(intent);
    }

    void solveHome(){
        LinearLayout linearLayout=findViewById(R.id.lineLayoutHome);
        for (int i=0;i<30;i++){
            LinearLayout linearLayoutHorizon=new LinearLayout(this);
            linearLayoutHorizon.setOrientation(LinearLayout.HORIZONTAL);
            TextView textView=new TextView(this);
            textView.setText("test"+i);
            textView.setTextSize(24);
            CheckBox checkBox=new CheckBox(this);
            checkBox.setTextSize(24);

            linearLayoutHorizon.addView(textView);
            linearLayoutHorizon.addView(checkBox);
            linearLayout.addView(linearLayoutHorizon);
        }


    }
}
