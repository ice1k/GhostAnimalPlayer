package classes;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

import player.comet.tesla.ghostanimalplayer.R;

/**
 * Copyright (c) 2015.
 * Coded by Tesla.
 * All rights reserved.
 */
//public class this extends Service {
public class PlayAudios {

    private Context context;
    private SoundPool soundPool;
    private MediaPlayer mediaPlayer;
    private int[] sound;

    public PlayAudios(Context c) {
        this.context = c;
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,1);
        sound = new int[50];
        sound[ 1] = soundPool.load(context, R.raw.sound_a, 1);
        sound[ 2] = soundPool.load(context, R.raw.sound_billy, 1);
        sound[ 3] = soundPool.load(context, R.raw.sound_f_ck_you, 1);
        sound[ 4] = soundPool.load(context, R.raw.sound_fa, 1);
        sound[ 5] = soundPool.load(context, R.raw.sound_iron_door, 1);
        sound[ 6] = soundPool.load(context, R.raw.sound_nico, 1);
        sound[ 7] = soundPool.load(context, R.raw.sound_wa, 1);
        sound[ 8] = soundPool.load(context, R.raw.sound_wolai, 1);
        sound[ 9] = soundPool.load(context, R.raw.sound_bilibili, 1);
        sound[10] = soundPool.load(context, R.raw.sound_come_on, 1);
        sound[11] = soundPool.load(context, R.raw.sound_lets_go, 1);
        sound[12] = soundPool.load(context, R.raw.sound_ah_fuck_you, 1);
        sound[13] = soundPool.load(context, R.raw.sound_your_ass, 1);
        sound[14] = soundPool.load(context, R.raw.sound_yeah_ah, 1);
        sound[15] = soundPool.load(context, R.raw.sound_ya_shi_la, 1);
        sound[16] = soundPool.load(context, R.raw.sound_chao_ni_a, 1);
        sound[17] = soundPool.load(context, R.raw.sound_she_jing_early, 1);
        sound[18] = soundPool.load(context, R.raw.sound_lan_lan_lu, 1);
        sound[19] = soundPool.load(context, R.raw.sound_ya, 1);
        sound[20] = soundPool.load(context, R.raw.sound_big_hip_dividing, 1);
        sound[21] = soundPool.load(context, R.raw.sound_ni_mas, 1);
        sound[22] = soundPool.load(context, R.raw.sound_this_week, 1);
        sound[23] = soundPool.load(context, R.raw.sound_pieces, 1);
        sound[24] = soundPool.load(context, R.raw.sound_angry_me_die, 1);
        sound[25] = soundPool.load(context, R.raw.sound_fuck_billy, 1);
        sound[26] = soundPool.load(context, R.raw.sound_mrs_sao, 1);
        sound[27] = soundPool.load(context, R.raw.sound_i_went_yo_he_bei, 1);
        sound[28] = soundPool.load(context, R.raw.sound_agriculture_not_good, 1);
        sound[29] = soundPool.load(context, R.raw.sound_what_do_you_want_to_do, 1);
        sound[30] = soundPool.load(context, R.raw.sound_dont_fight, 1);
        sound[31] = soundPool.load(context, R.raw.sound_short_of_resource, 1);
        sound[32] = soundPool.load(context, R.raw.sound_ha_ha_ha, 1);
        sound[33] = soundPool.load(context, R.raw.sound_jin_ke_la, 1);
        sound[34] = soundPool.load(context, R.raw.sound_gay, 1);
        sound[35] = soundPool.load(context, R.raw.sound_south_fly, 1);
        sound[36] = soundPool.load(context, R.raw.sound_destroy_hate, 1);
        sound[37] = soundPool.load(context, R.raw.sound_turned_on_last_year, 1);
        sound[38] = soundPool.load(context, R.raw.sound_nong_si, 1);
        sound[39] = soundPool.load(context, R.raw.sound_3_years_kid, 1);
        sound[40] = soundPool.load(context, R.raw.sound_what_happens, 1);
        sound[41] = soundPool.load(context, R.raw.sound_i_do_work_from_my_heart, 1);
        sound[42] = soundPool.load(context, R.raw.sound_have_jie_cao, 1);
        sound[43] = soundPool.load(context, R.raw.sound_i_feel_so_good, 1);
    }

//    private void constructSounds(int soundEncoding){
//        switch (soundEncoding){
//            case  1:
//                sound[ 1] = soundPool.load(context, R.raw.sound_a, 1);
//                break;
//            case  2:
//                sound[ 2] = soundPool.load(context, R.raw.sound_billy, 1);
//                break;
//            case  3:
//                sound[ 3] = soundPool.load(context, R.raw.sound_f_ck_you, 1);
//                break;
//            case  4:
//                sound[ 4] = soundPool.load(context, R.raw.sound_fa, 1);
//                break;
//            case  5:
//                sound[ 5] = soundPool.load(context, R.raw.sound_iron_door, 1);
//                break;
//            case  6:
//                sound[ 6] = soundPool.load(context, R.raw.sound_nico, 1);
//                break;
//            case  7:
//                sound[ 7] = soundPool.load(context, R.raw.sound_wa, 1);
//                break;
//            case  8:
//                sound[ 8] = soundPool.load(context, R.raw.sound_wolai, 1);
//                break;
//            case  9:
//                sound[ 9] = soundPool.load(context, R.raw.sound_bilibili, 1);
//                break;
//            case 10:
//                sound[10] = soundPool.load(context, R.raw.sound_come_on, 1);
//                break;
//            case 11:
//                sound[11] = soundPool.load(context, R.raw.sound_lets_go, 1);
//                break;
//            case 12:
//                sound[12] = soundPool.load(context, R.raw.sound_ah_fuck_you, 1);
//                break;
//            case 13:
//                sound[13] = soundPool.load(context, R.raw.sound_your_ass, 1);
//                break;
//            case 14:
//                sound[14] = soundPool.load(context, R.raw.sound_yeah_ah, 1);
//                break;
//            case 15:
//                sound[15] = soundPool.load(context, R.raw.sound_ya_shi_la, 1);
//                break;
//            case 16:
//                sound[16] = soundPool.load(context, R.raw.sound_chao_ni_a, 1);
//                break;
//            case 17:
//                sound[17] = soundPool.load(context, R.raw.sound_she_jing_early, 1);
//                break;
//            case 18:
//                sound[18] = soundPool.load(context, R.raw.sound_lan_lan_lu, 1);
//                break;
//            case 19:
//                sound[19] = soundPool.load(context, R.raw.sound_ya, 1);
//                break;
//            case 20:
//                sound[20] = soundPool.load(context, R.raw.sound_big_hip_dividing, 1);
//                break;
//            case 21:
//                sound[21] = soundPool.load(context, R.raw.sound_ni_mas, 1);
//                break;
//            case 22:
//                sound[22] = soundPool.load(context, R.raw.sound_this_week, 1);
//                break;
//            case 23:
//                sound[23] = soundPool.load(context, R.raw.sound_pieces, 1);
//                break;
//            case 24:
//                sound[24] = soundPool.load(context, R.raw.sound_angry_me_die, 1);
//                break;
//            case 25:
//                sound[25] = soundPool.load(context, R.raw.sound_fuck_billy, 1);
//                break;
//            case 26:
//                sound[26] = soundPool.load(context, R.raw.sound_mrs_sao, 1);
//                break;
//            case 27:
//                sound[27] = soundPool.load(context, R.raw.sound_i_went_yo_he_bei, 1);
//                break;
//            case 28:
//                sound[28] = soundPool.load(context, R.raw.sound_agriculture_not_good, 1);
//                break;
//            case 29:
//                sound[29] = soundPool.load(context, R.raw.sound_what_do_you_want_to_do, 1);
//                break;
//            case 30:
//                sound[30] = soundPool.load(context, R.raw.sound_dont_fight, 1);
//                break;
//            case 31:
//                sound[31] = soundPool.load(context, R.raw.sound_short_of_resource, 1);
//                break;
//            case 32:
//                sound[32] = soundPool.load(context, R.raw.sound_ha_ha_ha, 1);
//                break;
//            case 33:
//                sound[33] = soundPool.load(context, R.raw.sound_jin_ke_la, 1);
//                break;
//            case 34:
//                sound[34] = soundPool.load(context, R.raw.sound_gay, 1);
//                break;
//            case 35:
//                sound[35] = soundPool.load(context, R.raw.sound_south_fly, 1);
//                break;
//            case 36:
//                sound[36] = soundPool.load(context, R.raw.sound_destroy_hate, 1);
//                break;
//            case 37:
//                sound[37] = soundPool.load(context, R.raw.sound_turned_on_last_year, 1);
//                break;
//            case 38:
//                sound[38] = soundPool.load(context, R.raw.sound_nong_si, 1);
//                break;
//            case 39:
//                sound[39] = soundPool.load(context, R.raw.sound_3_years_kid, 1);
//                break;
//            case 40:
//                sound[40] = soundPool.load(context, R.raw.sound_what_happens, 1);
//                break;
//            case 41:
//                sound[41] = soundPool.load(context, R.raw.sound_i_do_work_from_my_heart, 1);
//                break;
//            case 42:
//                sound[42] = soundPool.load(context, R.raw.sound_have_jie_cao, 1);
//                break;
//            case 43:
//                sound[43] = soundPool.load(context, R.raw.sound_i_feel_so_good, 1);
//                break;
//            default:
//                break;
//        }
//
//    }

    private void setMediaPlayer(final boolean loopOrNot){
        mediaPlayer.setVolume(10,10);
        mediaPlayer.start();
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mp.stop();
                mp.reset();
//                mediaPlayer.release();
                return false;
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
//                如果重复就设置重复。
                if (loopOrNot) {
                    mediaPlayer.reset();
                    mediaPlayer.start();
                } else {
                    mediaPlayer.release();
                }
            }
        });
    }

//    音乐，必须设置循环与否。
    public void playBackGroundMusic(boolean loopOrNot, int Id) {
        switch (Id){
            case 1:
                mediaPlayer = MediaPlayer.create(context, R.raw.bgm_hui_ye_cheng);
                break;
            case 2:
                mediaPlayer = MediaPlayer.create(context, R.raw.bgm_finally_animal_sister);
                break;
            default:
                break;
        }
        setMediaPlayer(loopOrNot);
    }

//    音效
    private void playSound(int soundEnCoding){
        if(soundEnCoding < sound.length){
            soundPool.play(sound[soundEnCoding],1,1,0,0,1);
        }
        Log.d(Constants.MY_TAG, "Voice" + soundEnCoding + " is played!");
    }

    public void stop() {
        try {
            mediaPlayer.stop();
            mediaPlayer.release();
        }catch (IllegalStateException | NullPointerException e){
            e.printStackTrace();
        }
    }

    public void chooseOneToPlay(int key){
        switch (key){
            case 0:
                break;
            case R.id.umi:
                this.playSound(1);
                break;
            case R.id.billy:
                this.playSound(2);
                break;
            case R.id.vanYoung:
                this.playSound(3);
                break;
            case R.id.fa:
                this.playSound(4);
                break;
            case R.id.door:
                this.playSound(5);
                break;
            case R.id.nico:
                this.playSound(6);
                break;
            case R.id.uncleGeWa:
                this.playSound(7);
                break;
            case R.id.uncleGe:
                this.playSound(8);
                break;
            case R.id.misakaMikoto:
                this.playSound(9);
                break;
            case R.id.comeOn:
                this.playSound(10);
                break;
            case R.id.letsGo:
                this.playSound(11);
                break;
            case R.id.ahFuckYou:
                this.playSound(12);
                break;
            case R.id.yourAss:
                this.playSound(13);
                break;
            case R.id.yeahAh:
                this.playSound(14);
                break;
            case R.id.yaShiLa:
                this.playSound(15);
                break;
            case R.id.chaoNiA:
                this.playSound(16);
                break;
            case R.id.sheJingEarly:
                this.playSound(17);
                break;
            case R.id.lanLanLu:
                this.playSound(18);
                break;
            case R.id.ya:
                this.playSound(19);
                break;
            case R.id.bigHipDividing:
                this.playSound(20);
                break;
            case R.id.niMas:
                this.playSound(21);
                break;
            case R.id.thisWeek:
                this.playSound(22);
                break;
            case R.id.pieces:
                this.playSound(23);
                break;
            case R.id.angryMeDie:
                this.playSound(24);
                break;
            case R.id.fuckBilly:
                this.playSound(25);
                break;
            case R.id.mrsSao:
                this.playSound(26);
                break;
            case R.id.iWentToHeBei:
                this.playSound(27);
                break;
            case R.id.agricultureNotGood:
                this.playSound(28);
                break;
            case R.id.whatDoYouWantToDo:
                this.playSound(29);
                break;
            case R.id.dontFight:
                this.playSound(30);
                break;
            case R.id.shortInResource:
                this.playSound(31);
                break;
            case R.id.haHaHa:
                this.playSound(32);
                break;
            case R.id.jinKeLa:
                this.playSound(33);
                break;
            case R.id.gay:
                this.playSound(34);
                break;
            case R.id.southFly:
                this.playSound(35);
                break;
            case R.id.destroyHate:
                this.playSound(36);
                break;
            case R.id.turnOnLastYear:
                this.playSound(37);
                break;
            case R.id.nongSi:
                this.playSound(38);
                break;
            case R.id.threeYearsKid:
                this.playSound(39);
                break;
            case R.id.whatHappens:
                this.playSound(40);
                break;
            case R.id.iDoWorkFromMyHeart:
                this.playSound(41);
                break;
            case R.id.haveJieCao:
                this.playSound(42);
                break;
            case R.id.iFeelSoGood:
                this.playSound(43);
                break;
            default:
                Log.d(Constants.MY_TAG,"被水淹没，不知所措");
                break;
        }
    }

//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
}
