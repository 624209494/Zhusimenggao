package testSingle;

import android.util.Log;

public class SingleTon2 {
    private static final String TAG = "SingleTon2";
    private SingleTon2() {
    }

    private static SingleTon2 singleTon2 = new SingleTon2();

    public static synchronized SingleTon2 getInstance(){
        return singleTon2;
    }


    public void  getdata (){
        Log.d(TAG, "getdata: ");
    }

}
