package com.exp.bypass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);
        /*boolean a = isDebugged(getApplicationContext());
        if(a == true){
            Log.i("state","debugged");
        }
        else{
            Log.i("state","no debuggerd");
        }*/
        boolean b = debuggerTespiti();
        if(b==true){
            Log.i("state","Detect");
        }
        else{
            Log.i("state","no Detect");
        }
        controlTime();
    }

    public static boolean isDebugged(Context context){
        return ((context.getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0);
    }

    public static boolean debuggerTespiti(){
        return Debug.isDebuggerConnected();
    }

    static boolean controlTime(){
        long start = Debug.threadCpuTimeNanos();

        for(int i=0; i<1000000; ++i)
            continue;

        long stop = Debug.threadCpuTimeNanos();

        if(stop - start < 10000000) {
            return false;
        }
        else {
            return true;
        }
    }
}
