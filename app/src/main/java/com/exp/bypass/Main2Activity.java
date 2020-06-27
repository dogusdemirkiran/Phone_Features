package com.exp.bypass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private TextView textView;
    String phoneTur;
    static final int PERMISSION_READ_STATE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.text2);

    }

    public void aaa(View view){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            MyTelephonyManager();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_PHONE_STATE},PERMISSION_READ_STATE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_READ_STATE:
            {
                if(grantResults.length>=0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    MyTelephonyManager();
                }
                else{
                    Toast.makeText(this,".....",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void MyTelephonyManager(){
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        int phoneType = manager.getPhoneType();
        switch (phoneType){
            case(TelephonyManager.PHONE_TYPE_CDMA):
                phoneTur = "CDMA";
                break;

            case(TelephonyManager.PHONE_TYPE_GSM):
                 phoneTur = "GSM";
                break;

            case(TelephonyManager.PHONE_TYPE_NONE):
                phoneTur = "NONE";
                break;
        }

        boolean isRoaming = manager.isNetworkRoaming();
        String PhoneType = phoneTur;
        String IMEINumber = manager.getDeviceId();
        String subscriberID = manager.getDeviceId();
        String SIMSerialNumber = manager.getSimSerialNumber();
        String networkCountryISO = manager.getNetworkCountryIso();
        String SIMCountryISO = manager.getSimCountryIso();
        String softwareVersion = manager.getDeviceSoftwareVersion();
        String voiceMailNumber = manager.getVoiceMailNumber();

        String info="Phone Details:\n";
        info+="\n IMEI Number:"+IMEINumber;
        info+="\n SubscriberID:"+subscriberID;
        info+="\n Sim Serial Number:"+SIMSerialNumber;
        info+="\n Network Country ISO:"+networkCountryISO;
        info+="\n SIM Country ISO:"+SIMCountryISO;
        info+="\n Software Version:"+softwareVersion;
        info+="\n Voice Mail Number:"+voiceMailNumber;
        info+="\n Phone Network Type:"+PhoneType;
        info+="\n In Roaming? :"+isRoaming;
        textView.setText(info);
    }
}
