package player.comet.tesla.ghostanimalplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import util.DatabaseManager;
import util.OneSound;
import util.PlayAudios;

import static util.Constants.DELETE;
import static util.Constants.MY_TAG;
import static util.Constants.NEW_NAME;
import static util.Constants.ORIGINAL_NAME;
import static util.Constants.ORIGINAL_NAME_ID;

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

        MyApplication app = (MyApplication) getApplication();
        player = app.getPlayAudios();
//        filePath = Environment.getDataDirectory().getPath() + PATH;
//        filePath = PATH;
//        fatherFile = new File(filePath);
//        readingFile = fatherFile.listFiles();
        listView = (ListView) findViewById(R.id.listView);
        empty = (TextView) findViewById(R.id.empty);

        initListView();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RecordsActivity.this, RenameOrDeleteActivity.class);
                intent.putExtra(ORIGINAL_NAME, names[position]);
                intent.putExtra(ORIGINAL_NAME_ID, position);
                RecordsActivity.this.startActivityForResult(intent, 1);
                Log.d(MY_TAG, "OnItemClickListener set.");
//                Toast.makeText(RecordsActivity.this, "重命名暂未开放", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
                new Thread() {
                    @Override
                    public void run() {
                        int id;
                        long time;
                        manager = new DatabaseManager(RecordsActivity.this);
                        try {
                            Log.d(MY_TAG, "reading file name is : " + names[arg2]);
                            ArrayList<OneSound> sounds = manager.querySounds(names[arg2]);
                            Log.d(MY_TAG, "reading size is : " + sounds.size());
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
                        manager.close();
                        Log.d(MY_TAG, "读取完毕");
                    }
                }.start();
            }
        });

        Log.d(MY_TAG, "OnItemLongClickListener set.");
    }


    private void initListView(){
        if(findArrays()){
            listView.setAdapter(new ArrayAdapter<>(this,
                    R.layout.support_simple_spinner_dropdown_item, names));
        }
        else {
            listView.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }
    }

    private boolean findArrays(){

        manager = new DatabaseManager(this);
        names = manager.queryGroups();
        manager.close();

        Log.d(MY_TAG, "names.length = " + names.length);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                switch (resultCode){
                    case RESULT_OK:
                        manager = new DatabaseManager(RecordsActivity.this);
                        if(data.getBooleanExtra(DELETE, false))
                            manager.deleteSoundGroup(data.getStringExtra(ORIGINAL_NAME));
                        else
                            manager.renameSoundGroup(data.getStringExtra(ORIGINAL_NAME), data.getStringExtra(NEW_NAME));
                        manager.close();

                        break;
                    case RESULT_CANCELED:
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        initListView();
    }
}
