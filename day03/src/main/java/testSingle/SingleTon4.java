package testSingle;

import android.util.Log;

public class SingleTon4 {
    private SingleTon4() { }

    private static class Holder {
        public static final SingleTon4 INSTANCE = new SingleTon4();
    }

    public static SingleTon4 getInstance(){
        return Holder.INSTANCE;
    }

    private static final String TAG = "SingleTon4";
    public void intesd(){
        Log.d(TAG, "intesd: ");
    }







}
