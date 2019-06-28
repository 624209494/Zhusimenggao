package testSingle;

import android.util.Log;

public class SingleTon3 {
    private static final String TAG = "SingleTon3";
    private SingleTon3 (){};
    private static  SingleTon3 singleTon3;
    public static SingleTon3 getInstance(){
        if (singleTon3==null){
            synchronized (SingleTon3.class){
                if (singleTon3 == null){
                    singleTon3 = new SingleTon3();
                }
            }
        }
        return singleTon3;
    }
    public void  getdats (){
        Log.d(TAG, "getdats: ");
    }

}
