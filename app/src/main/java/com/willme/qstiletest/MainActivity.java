package com.willme.qstiletest;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.service.quicksettings.TileService;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ScrollView mScrollView;
    TextView mLogText;
    LogReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        mLogText = (TextView) findViewById(R.id.log);
        final RadioButton passive = (RadioButton) findViewById(R.id.modePassive);
        final RadioButton active = (RadioButton) findViewById(R.id.modeActive);
        int mode = CustomQSTile.getTileModePreference(this);
        if(mode == TileService.TILE_MODE_PASSIVE){
            passive.setChecked(true);
        } else if (mode == TileService.TILE_MODE_ACTIVE){
            active.setChecked(true);
        }
        mReceiver = new LogReceiver();
        mReceiver.register(this);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.modePassive == checkedId){
                    CustomQSTile.setTileModePreference(MainActivity.this, TileService.TILE_MODE_PASSIVE);
                    Log.log("set TestTile mode to TILE_MODE_PASSIVE");
                    Log.log("re-add the tile to test");
                } else if (R.id.modeActive == checkedId){
                    CustomQSTile.setTileModePreference(MainActivity.this, TileService.TILE_MODE_ACTIVE);
                    Log.log("set TestTile mode to TILE_MODE_ACTIVE");
                    Log.log("re-add the tile to test");
                }
            }
        });
    }

    public void requestListeningState(View view) {
        Log.log("requestListeningState");
        TileService.requestListeningState(getApplicationContext(), new ComponentName(this, CustomQSTile.class));
        TileService.requestListeningState(getApplicationContext(), new ComponentName(this, CompareTile.class));
    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    class LogReceiver extends BroadcastReceiver{

        void register(Context context){
            context.registerReceiver(this, new IntentFilter(Log.ACTION_LOG));
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra(Log.EXTRA_MSG);
            mLogText.append("\n"+msg);
            mScrollView.fullScroll(View.FOCUS_DOWN);
        }
    }
}
