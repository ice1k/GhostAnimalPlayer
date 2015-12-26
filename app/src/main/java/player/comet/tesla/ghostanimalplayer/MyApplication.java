package player.comet.tesla.ghostanimalplayer;

import android.app.Application;

import util.PlayAudios;

/**
 * Copyright 2015(c) Comet Corporation.
 * Created by asus1 on 2015/12/4.
 */
public class MyApplication extends Application {

    private PlayAudios playAudios;

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread() {
            @Override
            public void run() {
                super.run();
                playAudios = new PlayAudios(MyApplication.this);
            }
        }.start();

    }

    public PlayAudios getPlayAudios(){
        return playAudios;
    }

}
