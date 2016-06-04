package com.willme.qstiletest;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

/**
 * Created by Wen on 5/21/16.
 */
public class CustomQSTile extends TileService {

    @Override
    public int onTileAdded() {

        int tileMode = getTileModePreference(this);
        String modeName = null;
        if(tileMode == TILE_MODE_PASSIVE){
            modeName = "TILE_MODE_PASSIVE";
        } else if (tileMode == TILE_MODE_ACTIVE){
            modeName = "TILE_MODE_ACTIVE";
        }
        Log.log("TestTile onTileAdded return "+tileMode +": " +modeName);
        Log.log("press the \"requestListeningState\" button or expand the quick setting panel to test");
        return tileMode;
    }

    @Override
    public void onStartListening() {
        Log.log("TestTile onStartListening");
        getQsTile().setState(Tile.STATE_ACTIVE);
        getQsTile().updateTile();
        super.onStartListening();
    }

    @Override
    public void onStopListening() {
        Log.log("TestTile onStopListening");
        super.onStopListening();
    }

    public static int getTileModePreference(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getInt("tile_mode", TILE_MODE_PASSIVE);
    }

    public static void setTileModePreference(Context context, int mode){
        if(mode != TILE_MODE_ACTIVE && mode != TILE_MODE_PASSIVE){
            throw new IllegalArgumentException("tile mode: "+mode+" is illegal!");
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putInt("tile_mode", mode).apply();
    }


}
