package player.comet.tesla.ghostanimalplayer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import util.DatabaseManager;
import util.OneSound;
import util.PlayAudios;

import static util.Constants.*;

/**
 ***************************************************************************************************
 * 在这里说一下，由于做不到很好的代码可扩展性，所以每次增加新功能都需要做很多改动。
 * 首先增加新武器，需要在createClickers里面更改两个ClickListener。
 * 2015年10月16日：增强了可扩展性，现在不需要修改点击监听器了,只需要在OnCreate里面加进去就可以了。
 * 2015年10月19日：修改背景，增加4门语言，图片全部改成png格式。
 * 2015年10月20日：修改了更换武器的方法，增加了联系作者这一项，目前计划：把那几个键做成菜单项。
 * 按钮监听器全部改成方法，采用xml的Onclick项反射来实现控制反转按钮监听。
 * 新计划：将所有与武器相关的方法、类全部封装到武器类里面，公用的做成静态。
 * 感谢谷歌公司，误删的一个布局文件用Ctrl+Shift+E找回来了，非常棒！差点酿成大祸。
 * 2015年10月21日凌晨：增加了很多便于调试的Logcat，深夜实在熬不住了睡觉吧。
 * 周末计划：学会Dialog，并且在两周内熟练掌握自定义。
 * 目标：将武器表弄成可扩展的，用代码生成控件。
 * 2015年10月21日下午：把构造Weapons类的代码放在初始化函数里面了，。
 * 这下解决了多次构造导致多次加入的问题，我好棒~o(≧v≦)o~~。
 * 2015年10月22日凌晨：BOSS血条不动问题十分难以解决,最终发现是setMax()的问题，涨经验了~~。
 * 回复22行：那个不科学。反正测试出来不对。也没什么，也就一点点扩展性的牺牲，码无完码嘛。。
 * 提醒：发布时注意MainActivity中shootWeapon()方法里面第一条注释！！！！务必记住！！。
 * 睡前最后一句：记得增加音效系统！。
 * 2015年10月22日：十分迫切想增强罗旭恒系统的可扩展性！！可惜能力不足啊啊啊！！。
 * 这周下雨，下周重新举行运动会！！太棒又可以带一周PC了(∩_∩)好棒~~。
 * 2015年10月22日下午：在机房查了振动的实现（注意申请权限）。
 * 和MediaPlayer的使用方法，全部在FightBOSS里面哦~乄（BGM来生愿入幻想乡。
 * 2015年10月23日：优化了打Boss失败的系统，打Boss失败会一夜回到解放前嘿嘿。
 * 2015年10月24日：程序员节~~增加了音效系统，射击罗旭恒有声音喽~。
 * 将一些常用的类、方法、常量（包括音效什么的）封装到一起了，在classes包里面。
 * 现在射击罗旭恒有了很棒的音效了（包括射击音效。BGM和爆炸）！
 * 整个项目的字体大小作了调整，适配更小的屏幕。
 * 以冰封的名义发布了第一个版本，不过还是内测级别的。
 * 2015年20月27日：今天是历史性的一天，装逼装爽了，吴沂隆带头装B，还玩了TC
 * 搭建集成装逼环境（IZE），学电子科大那个学长，双屏幕编程，引来围观
 * 把CometPrint的APP做了一个登陆界面，用模板生成了一个侧拉界面
 * 现在射击游戏增加了一定的健壮性，在音乐类的stop方法中内置了一个：
 * catch(illegalStateException | nullPointerException e)
 * 然后在振动那块用一个判断权限的方法封装起来了 嘿嘿
 * 2015年10月29日凌晨：在董海辰的影响下制作了GhostAnimalPlayer，
 * 十分好玩，练习了对于声音池的使用。
 * 然而多年前准备学的Dialog还是没学。。
 * 然后就是继续加油看网课，目前进度：动态生成控件前一章。
 * 2015年10月29日晚：现在要在C语言算法部分下点功夫了，信息学竞赛有点跟不起走了。
 * 然而成外之星的论文还在写印子，还特么是吴沂隆写的。
 * 我完全就是一个多余的存在。。。。fa♂fa♂fa♂fa♂fa♂fa。
 * 《第一行代码：Android》这本书还是很好的，虽然很傻逼的用eclipse。。
 * 不过还没看到我没学过的部分。。。
 * 现在它讲了一个隐式intent用法：用intent-filter来呼叫Activity，唯一的新知识。。。
 * 2015年11月14日：很久没写日志了，总结一下最近做的事情：
 * 一直在拿kindle学习了。
 * 首先明确了本App发展方向，有几个section，挑战模式模仿LoveLive。
 * 然后根据大师傅的建议，采用Material Design，背景图片去掉了，又特么省空间了。。
 * 接着就是把预览模式和普通的录音模式分开了，带频率的录音等以后再做。
 * 最后就是计划在官网做编译日志，关于我们里面放着鸣谢和官网链接，还有大师傅的blog。
 * 在贴吧release了1.2.1版，被傻逼喷了，不过还是有很多人支持，开森~
 * 地铁偶遇成电大神，QQ （为保护隐私已删） 成功捕获师傅一只O(∩_∩)O~~
 * 为了实现开启动画的全屏，把LoginActivity继承的AppCompatActivity改成了Activity。
 * 开始学习使用ListView。
 * 在recordsActivity中第一次使用J8的lambda表达式，感觉很漂亮，。
 * 然而AS却说不支持Java8，我问大师傅他说对我失望了。。好桑心。
 * 企图把PlayAudios类改成Service，失败了。
 * 2015年12月4日：成功加载了全局变量。
 * 2015年12月8日：为了精简把preference改成了file，然后用json来存数据，。
 * 结果各种报错。。已经折腾了6、7个小时了。
 * 2015年12月9日：重构之路很漫长。。。泪。。
 * 现在发现Json字符串萎了。。。还是老老实实用Byte吧。。。
 * 取得巨大进展，在129合唱时灵感来了！
 * 原计划：
             * // 我理一下思路：首先
             * // 开始计算FILENAME+i存不存在
             * // 先弄成1，如果1存在就继续循环看2存不存在，
             * // 如果找不到一样的话就说明找到了不存在的，那就打开该文件。
 * 目前计划：计算它的总字节数，然后除以STRING_LENGTH，是不是很6！！
 * 我简直是天才！！！
 * 2015年12月10日：现在开始正式以字符流的方式存文件，全方位重构代码~~
 * 萌萌哒~~文件操作计划再次更改！
 * 新计划：现在改成了以空格为界，读出来然后split(" ").length;
 * 就知道长度了！！！！！！
 * 又旷了晚自习来写码，今天找到手机了，发现我爸开始对我有意见了。
 * 简直好凶喔，我怕死他了哇咔咔咔咔，没人知道我早就在学校搭建好了安卓开发环境了~
 * 报了半天错结果发现我根本就没有openFileInput。。。。无语。。
 * 2015年12月14日：我发现我已经花了至少20个小时在重构数据持久化方式了。
 * 之前的计划：
     * // 我已经要被自己绕晕了，不行，必须好好想想了。
     * // 我理一下思路：首先新建一个存文件名文件输入流和一堆byte，然后从存文件名文件里面读出来所有的文件名字节流。
     * // 然后做成UTF-8编码的String，关闭输入流，然后检测它里面一共有多少个文件名，然后新建一个文件叫做"works"+数量。
     * // 然后把这个文件名以APPEND的方式输入到存文件名文件中，然后关闭存文件名文件的输入流。
     * // 写完了！！我简直是天才！！！！！TODO不删，留作纪念！！！
 * 不能再这样下去了，我决定从此开始认真学习SQLite数据库！！
 * 今天中午写了SQLite数据库的openHelper和Manager，隔几天再把数据存储彻底搞定！！
 * 弄不起不是人！！！
 * 2015年12月15日：SQL的管理方法已经封装的差不多了，现在正在把他们搞到两个Activity里面
 * SQLite爆内存。。。内心是崩溃的。。
 * 试试少产生一些对象，我估计可以、、、赌一把。。
 * 2015年12月17日：昨天把读写文件名、重命名和删除搞好了
 * 当时由于太高兴太幸福太兴奋，忘了写日志了、、、今天补上、、
 * 今天的目标是解决声音放不出来的问题（有可能是读，有可能是写）。
 * 旷英语课，开写！！！
 * 2015年12月21日：现在发现是初始化的顺序有问题，但是解决了之后还是闪退
 * 闪退的问题解决了，原因是没有close，close不能自己随随便便implement
 * 了closable就close，这是闪退的根源所在。
 * 然后我发现还是有问题，就决定直接建表、拿表名创建一组鬼畜
 * 老子就不信了！！！妈蛋啊啊啊啊啊啊啊啊啊老子的光阴啊
 *
 *
 * Ohhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh!!!!
 * 新方法是可行的！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * 我成功了！！！噢啦噢啦噢啦噢啦~~~~~~~~~~~~~~
 * 直接在机房咆哮了10秒钟、、、卧槽嗓子疼~~
 * 幸福幸福
 * 2015年12月23日：现在开始限制用户输入特殊字符、增强健壮性。
 ***************************************************************************************************
 */
public class MainActivity extends AppCompatActivity {

    private boolean alreadyPlaying = false;
//    private boolean alreadyStarted = false;
    private boolean isWriting;
    private int idCounter;
    private long timeCounter;
    private PlayAudios playAudios;
    private LinearLayout linearLayout;
//    private SharedPreferences preferences;

    private Animation slideIn;
    private Animation slideOut;

    private String fileName;
    private DatabaseManager manager;
    private ArrayList<OneSound> sounds;
//    private LayoutAnimationController slideInController;
//    private LayoutAnimationController slideOutController;
//    private FileInputStream fileInputStream;
//    private BufferedReader fileNamesReader;
//    private BufferedWriter fileNamesWriter;
//    private BufferedWriter writer;
//
//    private FileOutputStream fileOutputStream;
//    private FileOutputStream fileNamesOut;
//    private FileInputStream fileNamesIn;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApplication app = (MyApplication) getApplication();
        playAudios = app.getPlayAudios();

//
//try {
//fileNamesReader = new JsonReader(new InputStreamReader(fileNamesIn));
//fileNamesWriter = new JsonWriter(new OutputStreamWriter(fileNamesOut));
//} catch (FileNotFoundException e) {
//e.printStackTrace();
//}
//
//filePath =  PATH + getApplication().getPackageName() + "ice1000/records";
//filePath = Environment.getExternalStorageDirectory().getPath() + PATH;
//fatherFile = new File(filePath);
//fatherFile.mkdirs();
//slideInController  = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_slide_in);
//slideOutController = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_slide_out);

        slideIn  = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out);

        linearLayout = (LinearLayout)findViewById(R.id.optionsMenu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playAudios.chooseOneToPlay(11);
    }

    @Override
    protected void onPause() {
        super.onPause();
        playAudios.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            manager.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void readGhostAnimal(View v) {
//        try {
//            fileInputStream = openFileInput("f♂uk.fuck");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            Log.d(MY_TAG,"找不到文件");
//        }
        isWriting = false;
        startActivity(new Intent(MainActivity.this, RecordsActivity.class));
        finish();
    }
//    public void deleteAll(View v){
//        isWriting = false;
//        if(.delete()){
//            Toast.makeText(this,"已删除",Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(this,"删除失败",Toast.LENGTH_SHORT).show();
//        }
//        timeCounter = 0;
//        idCounter = 0;
//    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void writeGhostAnimal(View v){
//
        //target = new JsonWriter(new OutputStreamWriter(fileOutputStream));
        //try {
        //target.beginArray();
        //}
        //catch (IOException e) {
        //e.printStackTrace();
        //}
        //初始化recordCounter
        //if(alreadyStarted)
        //recordCounter++;
        //else
        //alreadyStarted = true;
        //TextView textView = (TextView)v;
        if (isWriting) {
            stopPlaying();
            isWriting = false;
            ((TextView)v).setText(R.string.start);
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startPlaying();
//                }
//            });
        } else {
            startPlaying();
            isWriting = true;
            idCounter = 0;
            ((TextView)v).setText(R.string.stop);
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    stopPlaying();
//                }
//            });
        }
//
//else {
//((TextView)v).setText(R.string.start);
//}
//        showOptionsMenu(null);
//        Toast.makeText(this,"已开始，请尽情调教！",Toast.LENGTH_SHORT).show();
//        Log.d(MY_TAG, "start writing " );
    }

    public void showOptionsMenu(View v){
        if(linearLayout.getVisibility() == View.INVISIBLE) {
            linearLayout.setVisibility(View.VISIBLE);
            linearLayout.startAnimation(slideIn);
        }
        else {
            linearLayout.startAnimation(slideOut);
            linearLayout.setVisibility(View.INVISIBLE);
        }
    }

    public void playSound(View v){
        if(isWriting){
            saveData(v.getId());
        }
       playAudios.chooseOneToPlay(v.getId());
    }

    public void playBGM(View v){
        if(alreadyPlaying){
            playAudios.stop();
        }
        else {
            alreadyPlaying = true;
        }
        switch (v.getId()){
            case R.id.startBGMHuiYeCity:
                playAudios.playBackGroundMusic(false, 1);
                break;
            case R.id.startBGMFinallyAnimalSister:
                playAudios.playBackGroundMusic(false, 2);
                break;
            case R.id.stopMusic:
                playAudios.stop();
                break;
            default:
                break;
        }
    }

	/** 保存数据方法 */
    private void saveData(final int id){
//        先把上次的time保存起来
        long lastTime = timeCounter;
        long fuck = 0;
//        然后再更新time的值
        timeCounter = System.currentTimeMillis();
//        如果不是第一次就把时间记录下来
        if(idCounter > 0)
            fuck = timeCounter - lastTime;
//        输出调试信息
        Log.v(MY_TAG,"id = " + id + " ; fuck = " + fuck);
        sounds.add(new OneSound(id, fuck, idCounter));
//        manager.addSound(FILENAME, id, fuck, idCounter);
        idCounter++;
    }

    /** 开始播放方法 */
    private void startPlaying()  {
//        isWriting = true;
//try {
        //writingFile = getNewFile();
        //} catch (IOException e) {
        //e.printStackTrace();
        //}
        manager = new DatabaseManager(this);
        sounds = new ArrayList<>();

        fileName = manager.findSoundGroupName();
//            fileNamesIn = openFileInput(FILENAMES);
//            fileNamesReader = new BufferedReader(new InputStreamReader(fileNamesIn));
//            String allFileNames = "";
//            fileNamesReader.read(CharBuffer.wrap(allFileNames));
//            fileNamesReader.close();
//            Log.d(MY_TAG,"fileName = " + fileName);
//            int i = (allFileNames.split(SPLITTER)).length ;
////            i++;
//            fileName = FILENAME + i + JSON;
//            fileNamesOut = openFileOutput(FILENAMES,MODE_APPEND);
//            fileNamesWriter = new BufferedWriter(new OutputStreamWriter(fileNamesOut));
//            fileNamesWriter.write(fileName + SPLITTER);
//            fileNamesWriter.flush();
//            fileNamesOut.close();
//
        //if (fileNamesReader != null) {
        //fileNamesReader.beginArray();
        //do if (fileName.equals(fileNamesReader.nextString())) {
        //bool = false;
        //break;
        //}
        //while (fileNamesReader.hasNext());
        //fileNamesReader.endArray();
        //}
        Log.d(MY_TAG,"writing file name is :" + fileName + ":");
//            fileOutputStream = openFileOutput(FILENAME + i + JSON,MODE_APPEND);
//            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
//        catch (IOException e) {
//            try {
//                String fileName = FILENAME + "0" + JSON;
//                fileNamesOut = openFileOutput(FILENAMES,MODE_APPEND);
//                fileNamesWriter = new BufferedWriter(new OutputStreamWriter(fileNamesOut));
//                fileNamesWriter.write(fileName + SPLITER);
//                fileNamesWriter.flush();
//                fileNamesWriter.close();
//                fileNamesOut.close();
//                fileOutputStream = openFileOutput(fileName,MODE_APPEND);
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//            Log.v(MY_TAG,"本次直接打开。。。");
//            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
//            e.printStackTrace();
//        }
    }

    /** 结束播放方法 */
    private void stopPlaying(){
//        recordCounter++;
//        isWriting = false;
        Toast.makeText(this,"已停止",Toast.LENGTH_SHORT).show();
//        try {
//            writer.flush();
//            writer.close();
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        manager.addSounds(fileName, sounds);

        Log.d(MY_TAG, "stop writing");
        manager.close();
//        startActivity(new Intent(this, RecordsActivity.class));
//        finish();
    }
//

//private File getNewFile() throws IOException {

//String fileName;

//fileName = getFileName(fatherFile);

//File file = new File(filePath + "/" + fileName + JSON);

//file.mkdirs();

//Log.d(MY_TAG,"====文件读取====" + file.exists() + "====文件路径====" + file.getAbsolutePath());

//file.createNewFile();

//return file;

//}

//

//private String getFileName(File fatherFile) throws IOException {

//String fileName = FILENAME + "1";

//boolean bool = true;

////        if (fatherFile.exists()) {

//Log.d(MY_TAG,"====文件读取==== getNewFile" );

//if(fatherFile.listFiles() != null) {

//for (int i = 2; bool && i < 100; i++) {

//for (File file : fatherFile.listFiles()) {

//fileName = FILENAME + i;

//if (fileName.equals(file.getName())) {

//bool = false;

//break;

//}

//}

//}

//}

//else {

//// 直接使用初始文件名

//fatherFile.createNewFile();

//}

//return fileName;

//}

}
