package player.comet.tesla.ghostanimalplayer;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class AboutUsActivity extends AppCompatActivity {

    private ImageView NC;
    private AnimationDrawable NCFloating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        NC = (ImageView) findViewById(R.id.ncFloater);
        NC.setBackgroundResource(R.drawable.nc_floating);

        NCFloating = (AnimationDrawable) NC.getBackground();
        NCFloating.start();
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
            case R.id.ProgramLeague:
                if(!joinQQGroup("1xAz-QGQL0FrWLWvBz_a5yE6aIv_64et")){
                    Toast.makeText(this,"抱歉，您未安装手机QQ，或安装的版本不支持，请升级。",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Github:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ice1000/GhostAnimalPlayer")));
                break;
            default:
                break;
        }

    }

    /**
     *
     * 发起添加群流程。群号：ProgramLeague(319293196) 的 key 为： 1xAz-QGQL0FrWLWvBz_a5yE6aIv_64et
     * 调用 joinQQGroup(1xAz-QGQL0FrWLWvBz_a5yE6aIv_64et) 即可发起手Q客户端申请加群 ProgramLeague(319293196)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回false表示呼起失败
     *
     */
    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }


}
