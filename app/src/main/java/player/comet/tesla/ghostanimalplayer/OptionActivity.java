package player.comet.tesla.ghostanimalplayer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import util.Constants;

public class OptionActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences(Constants.DEFAULT,MODE_ENABLE_WRITE_AHEAD_LOGGING);

//        缺省选择windows，可以调成经典
        if(preferences.getBoolean(Constants.UISTYLE,false)){
            setContentView(R.layout.activity_option);
        }
        else
            setContentView(R.layout.activity_option_windows);

    }

    public void onChooseOptions(View v){
        switch (v.getId()) {
            case R.id.recordMode:
            case R.id.mRecordMode:
                Toast.makeText(this, R.string.loading,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OptionActivity.this, MainActivity.class));
                break;
            case R.id.testMode:
            case R.id.mTestMode:
                Toast.makeText(this, R.string.loading,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OptionActivity.this, Main2Activity.class));
                break;
            case R.id.aboutMe:
            case R.id.mAboutMe:
                startActivity(new Intent(OptionActivity.this, AboutUsActivity.class));
                break;
            case R.id.myRecords:
            case R.id.mMyRecords:
                Toast.makeText(this, R.string.loading,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OptionActivity.this, RecordsActivity.class));
                break;
            case R.id.settings:
            case R.id.mSettings:
                startActivity(new Intent(OptionActivity.this, SettingsActivity.class));
//                只有在这个模式进去之后要停止，方便刷新
                finish();
                break;
            default:
                Toast.makeText(this,getString(R.string.not_available),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
