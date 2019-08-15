import java.util.Date;

public class Data{

    // private static int nextId;
    // private static int nextSeq;
    // private static int nextVal;
    private static Date nextTime = new Date();

    private int id;
    private int seq;
    private int netBeezId;
    private int val;
    private long time;
    
    public Data(int prevId, int prevSeq, int prevVal, int nbId, int num){
        this.id = prevId;
        this.seq = prevSeq;
        this.val = prevVal;
        this.netBeezId = nbId;
        this.time = Data.getNextTime(num);
    }

    public String toString() {
        return "obj,id=" + this.id + 
                ",seq=" + this.seq + 
                ",nb=" + this.netBeezId + 
                " val=" + this.val + "i" +
                " " + this.time; //generate the line-protocol formated string
    }

    // public static int getNextId(int prevId) {
    //     Data.nextId = prevId;
    //     return ++Data.nextId;
    // }

    // public static int getNextSeq(int prevSeq){
    //     Data.nextSeq = prevSeq;
    //     return ++Data.nextSeq;
    // }

    // public static int getNextVal(int prevVal){
    //     Data.nextVal = prevVal;
    //     return ++Data.nextVal;
    // }

    public static long getNextTime(int num){
        return Data.nextTime.getTime() + (num*60000);
    }
}