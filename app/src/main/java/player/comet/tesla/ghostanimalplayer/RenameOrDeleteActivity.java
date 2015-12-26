package player.comet.tesla.ghostanimalplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static util.Constants.*;

public class RenameOrDeleteActivity extends AppCompatActivity {

    private String originalName;
    private EditText rename;
    private TextView SureText;
    private TextView CancelButton;
    private TextView SureButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename_or_delete);

        Intent data = getIntent();
        originalName = data.getStringExtra(ORIGINAL_NAME);
        rename = (EditText) findViewById(R.id.rename);

        SureText = (TextView) findViewById(R.id.SureText);
        CancelButton = (TextView) findViewById(R.id.CancelButton);
        SureButton = (TextView) findViewById(R.id.SureButton);

        rename.setText(originalName);
        rename.setSelected(true);

//        rename.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if(b){
//                    resultName = ((EditText)view).getText().toString();
//                }
//            }
//        });


    }

    public void delete(View view){
        Intent intent = new Intent();
        intent.putExtra(DELETE, true);
        intent.putExtra(ORIGINAL_NAME, originalName);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void save(View view){
        Intent intent = new Intent();
        String resultName = rename.getText().toString();
        if(resultName.contains(" ") || resultName.contains(".")){
            Toast.makeText(RenameOrDeleteActivity.this, "名称不能包含特殊字符！", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d(MY_TAG, "resultName = " + resultName);
        Log.d(MY_TAG, "originalName = " + originalName);
        intent.putExtra(ORIGINAL_NAME, originalName);
        intent.putExtra(NEW_NAME, resultName);
        intent.putExtra(DELETE, false);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancel(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

    public void deleteButNotSure(View view){
        SureText.setVisibility(View.VISIBLE);
        CancelButton.setVisibility(View.VISIBLE);
        SureButton.setVisibility(View.VISIBLE);
    }
}
