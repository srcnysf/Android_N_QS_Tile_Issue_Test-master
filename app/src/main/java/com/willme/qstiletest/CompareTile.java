package com.willme.qstiletest;

import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import static android.service.quicksettings.Tile.STATE_ACTIVE;

/**
 * Created by Wen on 5/21/16.
 */
public class CompareTile extends TileService {

    @Override
    public int onTileAdded () {
        int tileMode = STATE_ACTIVE;
        String modeName = "TILE_MODE_ACTIVE";
        Log.log("CompareTile onTileAdded return "+tileMode +": " +modeName);
        return tileMode;
    }

    @Override
    public void onStartListening() {
        Log.log("CompareTile onStartListening");
        getQsTile().setState(STATE_ACTIVE);
        getQsTile().updateTile();
        super.onStartListening();
    }

    @Override
    public void onStopListening() {
        Log.log("CompareTile onStopListening");
        super.onStopListening();
    }

}
