package player.comet.tesla.ghostanimalplayer;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import classes.PlayAudios;

import static classes.Constants.MY_TAG;
import static classes.Constants.PATH;

public class RecordsActivity extends AppCompatActivity {

    private int numOfRecords = 0;
    private ListView listView;
    private MyApplication app;

    private PlayAudios player;
    private String[] strings;

    private FileInputStream fileInputStream;
    private String filePath;
    private File fatherFile;
    private File[] readingFile;
    private JsonReader target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        filePath = Environment.getExternalStorageDirectory().getPath() + PATH;
        fatherFile = new File(filePath);
        readingFile = fatherFile.listFiles();

        findArrays();

        numOfRecords = 0;
        app = (MyApplication) getApplication();
        player = app.getPlayAudios();

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item, strings));

    }

    @Override
    protected void onResume() {
        super.onResume();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RecordsActivity.this,"重命名暂未开放",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, final long arg3) {
                Log.d(MY_TAG, "start to read");
                try {
                    fileInputStream = new FileInputStream(readingFile[arg2]);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                target = new JsonReader(new InputStreamReader(fileInputStream));
                try {
                    target.beginArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new Thread() {
                    @Override
                    public void run() {
                        int j;
                        long k;
                        try {
                            while (target.hasNext()) {
                                    j = target.nextInt();
                                    k = target.nextLong();
                                    Thread.sleep(k);
                                Log.d(MY_TAG, "start reading: arg2 = " + arg2);
                                if (j != 0)
                                    player.chooseOneToPlay(j);
                            }
                        } catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                        }
                        Log.d(MY_TAG, "读取完毕");
                    }
                }.start();
                try {
                    target.endArray();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private void findArrays(){
        numOfRecords = fatherFile.list().length;
        strings = fatherFile.list();
        Log.d(MY_TAG,"read " + numOfRecords);

    }

}
