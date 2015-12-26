package player.comet.tesla.ghostanimalplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

//为了兼容全屏效果
public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
//                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(LoginActivity.this,OptionActivity.class));
                finish();
            }
        }.start();
    }
}
