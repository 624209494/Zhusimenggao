package testSingle;

import android.util.Log;

public class SingleTon1 {
    private SingleTon1() {
    }

    private static SingleTon1 singleTon1;

    public static synchronized SingleTon1 getInstance(){
        if (singleTon1 == null){
            singleTon1 =new SingleTon1();
        }
        return singleTon1;
    }

    private static final String TAG = "SingleTon1";
    public void getdara (){
        Log.d(TAG, "getdara: ");
    }


}
