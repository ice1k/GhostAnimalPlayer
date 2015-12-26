package player.comet.tesla.ghostanimalplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import util.PlayAudios;

public class Main2Activity extends AppCompatActivity {

    private PlayAudios playAudios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        MyApplication app = (MyApplication) getApplication();
        playAudios = app.getPlayAudios();
    }

    public void playSound(View v){
        playAudios.chooseOneToPlay(v.getId());
    }

}
