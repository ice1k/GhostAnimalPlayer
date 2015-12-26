package util;

/**
 * Created by Administrator on 2015/12/14 0014.
 */
public class OneSound{
    public String name;
    public int id;
    public int cnt;
    public long time;

    public OneSound(String name, int id, long time, int cnt) {
        this.name = name;
        this.id = id;
        this.time = time;
        this.cnt = cnt;
    }

    public OneSound(String name, int id, long time) {
        this.name = name;
        this.id = id;
        this.time = time;
        cnt = 0;
    }

    public OneSound() {
    }
}
