package player.comet.tesla.ghostanimalplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import classes.PlayAudios;

public class Main2Activity extends AppCompatActivity {

    private PlayAudios playAudios;
    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        app = (MyApplication) getApplication();
        playAudios = app.getPlayAudios();
    }

    public void playSound(View v){
        playAudios.chooseOneToPlay(v.getId());
    }

}
