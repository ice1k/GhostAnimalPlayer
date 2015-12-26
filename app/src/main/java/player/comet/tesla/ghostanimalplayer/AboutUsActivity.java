package player.comet.tesla.ghostanimalplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    public void viewWeb(View v){
        switch (v.getId()) {
            case R.id.studioWebsite:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.comet-studio.cn")));
                break;
            case R.id.teacherBlog:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://18312847646.github.io")));
                break;
            case R.id.bStation:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://space.bilibili.com/8309713#!/index")));
                break;
            default:
                break;
        }

    }
}
