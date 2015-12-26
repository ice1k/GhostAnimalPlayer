package classes;

import java.io.File;

/**
 * Copyright (c) 2015.
 * Coded by Tesla.
 * All rights reserved.
 */
public class  Constants {

    public static final String MY_TAG = "Tesla`s message";
    public static final String EXAMPLE_SOUND_ID = "This is the id which belongs to sounds.";
    public static final String EXAMPLE_BGM_ID = "This is the id which belongs to bgms.";
    public static final String EXAMPLE_TIME_ID = "This is the id which belongs to times.";
    public static final String DEFAULT = "DEFAULT";
    public static final String ID = " ID ";
    public static final String TIME = " TIME ";
    public static final String PLAY_ACTION = "PLAY_ACTION";
    public static final String PLAY_ACTION2 = "PLAY_ACTION2";
    public static final String STOP = "STOP";
    public static final String UISTYLE = "UISTYLE";
    public static final String PATH = "/ice1000";
    public static final String FILENAME = "鬼♂畜作品";
    public static final String JSON = ".json";
    //判断文件是否存在
    public boolean fileIsExists(String strFile) {
        try {
            File f=new File(strFile);
            if(!f.exists())
                return false;
        }
        catch (Exception e) {
            return false;
        }

        return true;
    }

}