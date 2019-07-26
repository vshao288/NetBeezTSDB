public class Data{

    private static int nextId = 0;
    private static int nextSeq = 0;
    private static int nextVal = 0;
    private static int nextTime = 0;

    private int id;
    private int seq;
    private int netBeezId = 13;
    private int val;
    private int time;
    
    public Data(){
        this.id = Data.getNextId();
        this.seq = Data.getNextSeq();
        this.val = Data.getNextVal();
        this.time = Data.getNextTime();
    }

    public String toString() {
        return "obj,id=" + this.id + 
                ",seq=" + this.seq + 
                ",nbid=" + this.netBeezId + 
                " val=" + this.val +
                " " + this.time; //generate the line-protocol formated string
    }

    public static int getNextId() {
        return ++Data.nextId;
        //return UUID.generate();
    }

    public static int getNextSeq(){
        return ++Data.nextSeq;
    }

    public static int getNextVal(){
        return ++Data.nextVal;
    }

    public static int getNextTime(){
        return ++Data.nextTime;
    }
}