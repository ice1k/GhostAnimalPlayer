package player.comet.tesla.ghostanimalplayer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import classes.PlayAudios;

import static classes.Constants.FILENAME;
import static classes.Constants.MY_TAG;
import static classes.Constants.PATH;

/**
 ***************************************************************************************************
 * 在这里说一下，由于做不到很好的代码可扩展性，所以每次增加新功能都需要做很多改动。
 * 首先增加新武器，需要在createClickers里面更改两个ClickListener。
 * 2015年10月16日：增强了可扩展性，现在不需要修改点击监听器了,只需要在OnCreate里面加进去就可以了。
 * 2015年10月19日：修改背景，增加4门语言，图片全部改成png格式。
 * 2015年10月20日：修改了更换武器的方法，增加了联系作者这一项，目前计划：把那几个键做成菜单项。
 * 按钮监听器全部改成方法，采用xml的Onclick项来实现按钮监听。
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
 * 把Cometprint的APP做了一个登陆界面，用模板生成了一个侧拉界面
 * 现在射击游戏增加了一定的健壮性，在音乐类的stop方法中内置了一个：
 * catch(illegalStateException | nullPointerException e)
 * 然后在振动那块用一个判断权限的方法封装起来了 嘿嘿
 * 2015年10月29日凌晨：在董海辰的影响下制作了GhostAnimalPlayer，
 * 十分好玩，练习了对于声音池的使用。
 * 然而多年前准备学的Dialog还是没学。。
 * 然后就是继续加油看网课，目前进度：动态生成控件前一章。
 * 2015年10月29日晚：现在要在C语言算法部分下点功夫了，信息学竞赛有点跟不起走了
 * 然而成外之星的论文还在写印子，还特么是吴沂隆写的
 * 我完全就是一个多余的存在。。。。fa♂fa♂fa♂fa♂fa♂fa
 * 《第一行代码：Android》这本书还是很好的，不过还没看到我没学过的部分
 * 现在它讲了一个隐式intent用法：用intent-filter来呼叫Activity，唯一的新知识。。。
 * 2015年11月14日：很久没写日志了，总结一下最近做的事情：
 * 一直在拿kindle学习了
 * 首先明确了本App发展方向，有几个section，挑战模式模仿LoveLive。
 * 然后根据大师傅的建议，采用Material Design，背景图片去掉了，又特么省空间了。。
 * 接着就是把预览模式和普通的录音模式分开了，带频率的录音等以后再做。
 * 最后就是计划在官网做编译日志，关于我们里面放着鸣谢和官网链接，还有大师傅的blog。
 * 在贴吧release了1.2.1版，被傻逼喷了，不过还是有很多人支持，开森~
 * 地铁偶遇成电大神，QQ （为保护隐私已删） 成功捕获师傅一只O(∩_∩)O~~
 * 为了实现开启动画的全屏，把LoginActivity继承的AppCompatActivity改成了Activity
 * 开始学习使用ListView
 * 在recordsActivity中第一次使用J8的lambda表达式，感觉很漂亮，
 * 然而AS却说不支持Java8，我问大师傅他说对我失望了。。好桑心
 * 企图把PlayAudios类改成Service，失败了
 * 2015年12月4日：成功加载了全局变量
 ***************************************************************************************************
 */
public class MainActivity extends AppCompatActivity {

    private boolean alreadyPlaying = false;
//    private boolean alreadyStarted = false;
    private boolean isWriting;
    private long idCounter;
    private long timeCounter;
    private long recordCounter;
    private PlayAudios playAudios;
    private LinearLayout linearLayout;
//    private SharedPreferences preferences;

    private MyApplication app;
    private Animation slideIn;
    private Animation slideOut;
//    private LayoutAnimationController slideInController;
//    private LayoutAnimationController slideOutController;
//    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private String filePath;
    private File fatherFile;
    private File writingFile;
    private JsonWriter target;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        deleteAll(null);
        app = (MyApplication) getApplication();
        playAudios = app.getPlayAudios();

        filePath = Environment.getExternalStorageDirectory().getPath() + PATH;
        fatherFile = new File(filePath);

        slideIn  = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out);
//        slideInController  = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_slide_in);
//        slideOutController = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_slide_out);

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

    public void deleteAll(View v){
        isWriting = false;
        writingFile.delete();

        timeCounter = 0;
        idCounter = 0;
        Toast.makeText(this,"已删除",Toast.LENGTH_SHORT).show();
    }

    public void writeGhostAnimal(View v){
        isWriting = true;
        writingFile = getNewFile();
        try {
            fileOutputStream = new FileOutputStream(writingFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        target = new JsonWriter(new OutputStreamWriter(fileOutputStream));
        try {
            target.beginArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        初始化recordCounter
//        if(alreadyStarted)
//            recordCounter++;
//        else
//            alreadyStarted = true;
//        TextView textView = (TextView)v;
        if( v.getId() == R.id.start ){
            ((TextView)v).setText(R.string.stop);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stopPlaying(v);
                }
            });
        }
//        else {
////            ((TextView)v).setText(R.string.start);
//        }

        showOptionsMenu(null);
        Toast.makeText(this,"已开始，请尽情调教！",Toast.LENGTH_SHORT).show();
        Log.d(MY_TAG, "start writing " + recordCounter);
    }

    public void stopPlaying(View v){
//        recordCounter++;
//        isWriting = false;
        Toast.makeText(this,"已停止",Toast.LENGTH_SHORT).show();
        try {
            target.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(MY_TAG, "stop writing");
        startActivity(new Intent(this, RecordsActivity.class));
        finish();
    }

    public void showOptionsMenu(View v){
        if(linearLayout.getVisibility() == View.INVISIBLE) {
            linearLayout.setVisibility(View.VISIBLE);
            linearLayout.startAnimation(slideIn);
        }
        else {
            linearLayout.startAnimation(slideOut);
//            linearLayout.setVisibility(View.INVISIBLE);
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

    private File getNewFile(){
        String fileName ;
        for(int i = 1; ; i++){
            fileName = FILENAME + i;
            boolean bool = true;
            for (File file: fatherFile.listFiles()) {
                if(fileName.equals(file.getName())){
                    bool = false;
                    break;
                }
            }
            if(bool){
                break;
            }
        }
        return new File(filePath + "/" + fileName);
    }

    private void saveData(final int id){
//        try{sleep(125);} catch (InterruptedException e) {e.printStackTrace();}
//        先把上次的time保存起来
        long lastTime = timeCounter;
        long fuck = 0;
//        然后再更新time的值
        timeCounter = System.currentTimeMillis();
//        如果不是第一次就把时间记录下来
        if(idCounter > 0)
            fuck = timeCounter - lastTime;
        try {
            target.value(id);
            target.value(fuck);
        } catch (IOException e) {
            e.printStackTrace();
        }
        idCounter++;
    }

}
