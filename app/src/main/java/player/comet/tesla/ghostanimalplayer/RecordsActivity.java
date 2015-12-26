package player.comet.tesla.ghostanimalplayer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import util.DatabaseManager;
import util.OneSound;
import util.PlayAudios;

import static util.Constants.*;

public class RecordsActivity extends AppCompatActivity {

    private ListView listView;
    private TextView empty;

    private PlayAudios player;

    private DatabaseManager manager;
//    private ArrayList<OneSound> sounds;
    private String[] names;
//    private BufferedReader reader;
//    private FileInputStream fileInputStream;
//    private String[] files;
//    private BufferedReader fileNamesReader;
//    private FileInputStream fileNamesIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

//        filePath = Environment.getDataDirectory().getPath() + PATH;
//        filePath = PATH;
//        fatherFile = new File(filePath);
//        readingFile = fatherFile.listFiles();
        listView = (ListView) findViewById(R.id.listView);
        empty = (TextView) findViewById(R.id.empty);
        manager = new DatabaseManager(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(findArrays()){
            MyApplication app = (MyApplication) getApplication();
            player = app.getPlayAudios();
            listView.setAdapter(new ArrayAdapter<>(this,
                    R.layout.support_simple_spinner_dropdown_item, names));
        }
        else {
            listView.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RecordsActivity.this, "重命名暂未开放", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
//                try {
//                    target.beginArray();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                new Thread() {
                    @Override
                    public void run() {
                        int id;
                        long time;
                        try {
                            Log.d(MY_TAG, "reading file name is :" + names[arg2] + JSON + ":");
                            ArrayList<OneSound> sounds = manager.querySounds(names[arg2]);
//                        String string = "";
//                        fileInputStream = openFileInput(files[arg2] + JSON);
//                        reader = new BufferedReader(new InputStreamReader(fileInputStream));
//                        reader.read(CharBuffer.wrap(string));
//                        String[] strings = string.split(SPLITTER);
//                        int size;
//                        Log.d(MY_TAG, "size = " + size);
                            for (int i = 0; i < sounds.size(); i++) {
                                id = sounds.get(i).id;
                                time = sounds.get(i).time;
                                Thread.sleep(time);
                                Log.d(MY_TAG, "start reading: arg2 = " + arg2);
                                if (id != 0)
                                    player.chooseOneToPlay(id);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Log.d(MY_TAG, "读取完毕");
                    }
                }.start();
//                try {
//                    target.endArray();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    private boolean findArrays(){
        names = manager.queryGroups();
        Log.d(MY_TAG, "names[0] = " + names[0]);
        return names.length >= 0;
//    private File fatherFile;
//    private File[] readingFile;
//        if(fatherFile.list() != null) {
//            numOfRecords = fatherFile.list().length;
//            strings = fatherFile.list();
//            Log.d(MY_TAG,"read " + numOfRecords);
//            return true;
//        }

//        try {
//            fileNamesIn = openFileInput(FILENAMES);
//            fileNamesReader = new BufferedReader(new InputStreamReader(fileNamesIn));
//            String allFileNames = "";
//            if(fileNamesIn.available() > 1){
//                fileNamesReader.read(CharBuffer.wrap(allFileNames));
//                fileNamesReader.close();
//                Log.v(MY_TAG, "allFileNames = " +
//                        allFileNames
//                );
//                files = allFileNames.split(SPLITTER);
//                return true;
//            }
//            else {
//                numOfRecords = 0;
//                files = new String[1];
//                files[0] = "空";
//                return false;
//            }
//        } catch (IOException|NullPointerException e) {
//            e.printStackTrace();
//            return false;
//        }
    }

}
