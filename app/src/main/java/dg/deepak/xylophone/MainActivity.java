package dg.deepak.xylophone;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity  {
    public static final int Max_NUMBER_STREAMS = 7;
    public  static  final int SOURCE_QUALITY = 0;
    MediaPlayer mPlayer;
    SoundPool mySoundPool;
    boolean loaded = false;
    float actVolume, maxVolume, volume;
    AudioManager audioManager;

    int[] soundID =new int[7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mySoundPool = new SoundPool(Max_NUMBER_STREAMS, AudioManager.STREAM_MUSIC, SOURCE_QUALITY);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;
        mySoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        for(int i=1;i<=7;i++){
            String s = "note"+i;
            int resID=getResources().getIdentifier(s, "raw", getPackageName());
            soundID[i-1] = mySoundPool.load(this,resID,1);
        }
    }
    public void onButtonPressed(View v){
        String s = v.getTag().toString();
        int num = Integer.parseInt(s);
        s="note"+s;
        mySoundPool.play(soundID[num-1],volume, volume,1,0,1f);
        //Toast.makeText(this, "button pressed for"+s, Toast.LENGTH_SHORT).show();

    }

}
