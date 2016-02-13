package me.kglawrence.keri.ibrowse;

/**
 * Created by kglawrence on 2/13/16.
 */

import android.app.Application;

import com.orm.SugarContext;

public class ClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}