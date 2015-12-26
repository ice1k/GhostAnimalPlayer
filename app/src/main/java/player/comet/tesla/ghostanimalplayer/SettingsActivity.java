package player.comet.tesla.ghostanimalplayer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import classes.Constants;

public class SettingsActivity extends AppCompatActivity {

    Switch aSwitch;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences preferences = getSharedPreferences(Constants.DEFAULT,MODE_ENABLE_WRITE_AHEAD_LOGGING);
        final SharedPreferences.Editor editor = preferences.edit();

        aSwitch = (Switch)findViewById(R.id.UISwitcher);

        boolean bool = preferences.getBoolean(Constants.UISTYLE,false);
        if(bool) {
            aSwitch.setChecked(true);
            aSwitch.setText(R.string.classic);
        }
        else {
            aSwitch.setChecked(false);
            aSwitch.setText(R.string.windows);
        }

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( !isChecked ){
                    editor.putBoolean(Constants.UISTYLE,false);
                    aSwitch.setText(R.string.windows);
                }
                else {
                    editor.putBoolean(Constants.UISTYLE,true);
                    aSwitch.setText(R.string.classic);
                }
                editor.apply();
                Log.d(Constants.MY_TAG,"onCheckChanged.");
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,OptionActivity.class));
        finish();
        super.onBackPressed();
    }
}
