package ru.ostapenkoyanko.myflashlight;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
    private FlashClass flashClass = new FlashClass(this);
    private ImageButton bFlash;
    private ConstraintLayout constFon;
    private TextView textViewVeryScary;
    private TextView textViewNotScary;
    private TextView textViewPushMe;
    private TextView textViewEng;
    private TextView textViewRus;
    private SeekBar backLightControl;
    private TextView backLightSetting;
    private float saveBackLightValue;
    float defaultBrightness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bFlash = findViewById(R.id.custom_button);
        constFon = findViewById(R.id.fon);
        textViewPushMe = findViewById(R.id.textView);
        textViewVeryScary = findViewById(R.id.textView4);
        textViewNotScary = findViewById(R.id.textView5);
        textViewEng = findViewById(R.id.textView1);
        textViewRus = findViewById(R.id.textView2);
        Switch switchEnableButton = findViewById(R.id.switch1);
        defaultBrightness = getWindow().getAttributes().screenBrightness;
        saveBackLightValue = defaultBrightness;
        backLightControl = findViewById(R.id.backlightcontrolid);
        backLightSetting = findViewById(R.id.backlightsettingid);
        backLightControl.setVisibility(View.INVISIBLE);

        backLightControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                // TODO Auto-generated method stub
                float BackLightValue = (float)arg1/100;
                System.out.println("arg1 = "+ arg1);
                System.out.println("BackLightValue = "+ BackLightValue);
                backLightSetting.setText(String.valueOf(BackLightValue));
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                layoutParams.screenBrightness = BackLightValue;
                getWindow().setAttributes(layoutParams);
                saveBackLightValue = BackLightValue;
            }
            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub

            }});
        setTunerPopoverSize();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        switchEnableButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textViewVeryScary.setText(R.string.strashno);
                    textViewNotScary.setText(R.string.ne_strashno);
                    textViewPushMe.setText(R.string.najmi);
                    textViewEng.setText(R.string.ang);
                    textViewRus.setText(R.string.russkii);
                } else {
                    textViewVeryScary.setText(R.string.VeryScary);
                    textViewNotScary.setText(R.string.not_scary);
                    textViewPushMe.setText(R.string.PushMe);
                    textViewEng.setText(R.string.eng);
                    textViewRus.setText(R.string.rus);
                }
            }
        });
        bFlash.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
                if(!flashClass.isFlash_on()) {
                    layoutParams.screenBrightness = saveBackLightValue;
                    getWindow().setAttributes(layoutParams);
                    flashClass.flashOn();
                    setTunerPopoverSize2();
                    constFon.setBackgroundResource(R.color.colorPrimary);
                    textViewPushMe.setTextColor(getResources().getColor(R.color.colorPrimaryDark, getTheme()));
                    textViewEng.setTextColor(getResources().getColor(R.color.colorPrimaryDark, getTheme()));
                    textViewRus.setTextColor(getResources().getColor(R.color.colorPrimaryDark, getTheme()));
                    backLightControl.setVisibility(View.VISIBLE);
                    textViewVeryScary.setVisibility(View.INVISIBLE);
                }
                else {
                    layoutParams.screenBrightness = defaultBrightness;
                    getWindow().setAttributes(layoutParams);
                    flashClass.flashOff();
                    setTunerPopoverSize();
                    constFon.setBackgroundResource(R.color.colorPrimaryDark);
                    textViewPushMe.setTextColor(getResources().getColor(R.color.colorPrimary, getTheme()));
                    textViewEng.setTextColor(getResources().getColor(R.color.colorPrimary, getTheme()));
                    textViewRus.setTextColor(getResources().getColor(R.color.colorPrimary, getTheme()));
                    backLightControl.setVisibility(View.INVISIBLE);
                    textViewVeryScary.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    private void setTunerPopoverSize() {
        int screenDensity = getScreenDensity( this );
        if (screenDensity == DisplayMetrics.DENSITY_LOW) {
            bFlash.setImageResource(R.drawable.cust_button_small);
            textViewVeryScary.setTextSize(16);
            textViewNotScary.setTextSize(16);
            textViewPushMe.setTextSize(12);
            textViewEng.setTextSize(12);
            textViewRus.setTextSize(12);
        }  else if (screenDensity == DisplayMetrics.DENSITY_MEDIUM) {
            bFlash.setImageResource(R.drawable.cust_button_default);
            textViewVeryScary.setTextSize(26);
            textViewNotScary.setTextSize(26);
            textViewPushMe.setTextSize(20);
            textViewEng.setTextSize(20);
            textViewRus.setTextSize(20);
        } else if (screenDensity == DisplayMetrics.DENSITY_HIGH) {
            bFlash.setImageResource(R.drawable.cust_button_default);
            textViewVeryScary.setTextSize(26);
            textViewNotScary.setTextSize(26);
            textViewPushMe.setTextSize(20);
            textViewEng.setTextSize(20);
            textViewRus.setTextSize(20);
        } else if (screenDensity == DisplayMetrics.DENSITY_XHIGH) {
            bFlash.setImageResource(R.drawable.cust_button_large);
            textViewVeryScary.setTextSize(36);
            textViewNotScary.setTextSize(36);
            textViewPushMe.setTextSize(26);
            textViewEng.setTextSize(26);
            textViewRus.setTextSize(26);
        } else if (screenDensity == DisplayMetrics.DENSITY_XXHIGH) {
            bFlash.setImageResource(R.drawable.cust_button_large);
            textViewVeryScary.setTextSize(36);
            textViewNotScary.setTextSize(36);
            textViewPushMe.setTextSize(26);
            textViewEng.setTextSize(26);
            textViewRus.setTextSize(26);
        } else if (screenDensity == DisplayMetrics.DENSITY_XXXHIGH) {
            bFlash.setImageResource(R.drawable.cust_button_large);
            textViewVeryScary.setTextSize(36);
            textViewNotScary.setTextSize(36);
            textViewPushMe.setTextSize(26);
            textViewEng.setTextSize(26);
            textViewRus.setTextSize(26);
        } else{
            bFlash.setImageResource(R.drawable.cust_button_default);
            textViewVeryScary.setTextSize(26);
            textViewNotScary.setTextSize(26);
            textViewPushMe.setTextSize(20);
        }
    }
    private void setTunerPopoverSize2() {
        int screenDensity = getScreenDensity( this );
        if (screenDensity == DisplayMetrics.DENSITY_LOW) {
            bFlash.setImageResource(R.drawable.cust_button2_small);
        }  else if (screenDensity == DisplayMetrics.DENSITY_MEDIUM) {
            bFlash.setImageResource(R.drawable.cust_button2_default);
        } else if (screenDensity == DisplayMetrics.DENSITY_HIGH) {
            bFlash.setImageResource(R.drawable.cust_button2_default);
        } else if (screenDensity == DisplayMetrics.DENSITY_XHIGH) {
            bFlash.setImageResource(R.drawable.cust_button2_large);
        } else if (screenDensity == DisplayMetrics.DENSITY_XXHIGH) {
            bFlash.setImageResource(R.drawable.cust_button2_large);
        } else if (screenDensity == DisplayMetrics.DENSITY_XXXHIGH) {
            bFlash.setImageResource(R.drawable.cust_button2_large);
        } else{
            bFlash.setImageResource(R.drawable.cust_button2_default);
        }
    }
    public static int getScreenDensity(Context context) {
        int density = context.getResources().getDisplayMetrics().densityDpi;
        switch(density)
        {
            case DisplayMetrics.DENSITY_LOW:
                return DisplayMetrics.DENSITY_LOW;
            case DisplayMetrics.DENSITY_MEDIUM:
                return DisplayMetrics.DENSITY_MEDIUM;
            case DisplayMetrics.DENSITY_HIGH:
                return DisplayMetrics.DENSITY_HIGH;
            case DisplayMetrics.DENSITY_XHIGH:
                return DisplayMetrics.DENSITY_XHIGH;
            case DisplayMetrics.DENSITY_XXHIGH:
                return DisplayMetrics.DENSITY_XXHIGH;
            case DisplayMetrics.DENSITY_XXXHIGH:
                return DisplayMetrics.DENSITY_XXXHIGH;
            default:
                return DisplayMetrics.DENSITY_DEFAULT;
        }
    }
    @Override
    protected void onDestroy () {
        WindowManager.LayoutParams defaultParams = getWindow().getAttributes();
        defaultParams.screenBrightness = defaultBrightness;
        getWindow().setAttributes(defaultParams);
        flashClass.flashOff();
        super.onDestroy();
    }
}