package com.example.stardustcrusaders;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.content.res.AssetFileDescriptor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Inf133_Project extends ActionBarActivity {

	private TextView mOrientationLabel;
    private SensorManager mSensorManager;
	private SensorEventListener mEventListenerOrientation;
	private float lastValue1, lastValue2, lastValue3;
	private MediaPlayer mp;
	private AssetFileDescriptor afd;
	private AssetFileDescriptor ohno;
	private AssetFileDescriptor ohmygod;
	private AssetFileDescriptor sonuvabeetch;
	private AssetFileDescriptor zawarudo;
	private float[] x = new float[4];;
	private boolean virginity = true;
	
	
	private void updateUI()
	{
		runOnUiThread(new Runnable(){
			@Override
			public void run() {
				//mOrientationLabel.setText(" " + lastValue1 + "\n" + lastValue2 + "\n " + lastValue3);	
			}});
	}
	
	synchronized void playAudio(AssetFileDescriptor afd)
	{
		if(mp.isPlaying())
		{
			return;
		}
		else
		{
			try {
				mp.reset();
				mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
				mp.prepare();
				mp.start();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf133__project);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mEventListenerOrientation = new SensorEventListener(){

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSensorChanged(SensorEvent event) {
				// TODO Auto-generated method stub
				float[] values = event.values;
				if(virginity)
				{
					x[1] = values[0];
					x[2] = values[1];
					x[3] = values[2];
					virginity = false;
				}
				lastValue1 = values[0];
				lastValue2 = values[1];
				lastValue3 = values[2];
				updateUI();
				if(lastValue1 > (x[1]+45) || lastValue1 < (x[1]-45) && 
						(lastValue2 > (x[2]-45) && lastValue2 < (x[2]+45)) &&
						(lastValue3 > (x[3]-45) && lastValue3 < (x[3]+45)))
				{
					playAudio(afd);
				}
				
				if((lastValue1 < (x[1]+45) && lastValue1 > (x[1]-45)) && 
						(lastValue2 < (x[2]-45)) &&
						(lastValue3 > (x[3]-45) && lastValue3 < (x[3]+45)))
				{
					playAudio(ohno);
				}
				
				if(lastValue1 < (x[1]+45) && lastValue1 > (x[1]-45) && 
						(lastValue2 > (x[2]+45)) &&
						(lastValue3 > (x[3]-45) && lastValue3 < (x[3]+45)))
				{
					playAudio(ohmygod);
				}
				
				if(lastValue1 < (x[1]+45) && lastValue1 > (x[1]-45) && 
						(lastValue2 > (x[2]-45) && lastValue2 < (x[2]+45)) &&
						(lastValue3 < (x[3]-45) ))
				{
					playAudio(sonuvabeetch);
				}
				
				if(lastValue1 < (x[1]+45) && lastValue1 > (x[1]-45) && 
						(lastValue2 > (x[2]-45) && lastValue2 < (x[2]+45)) &&
						( lastValue3 > (x[3]+45)))
				{
					playAudio(zawarudo);
				}
				/*All in regards to 1st value (flat)
				 * Neutral:180 
				 * Flat clockwise left: goes to 90
				 * Flat clockwise right: goes to 270
				 * 2nd value (forwards/backwards)
				 * Neutral: 0
				 * Away: 90
				 * Towards: -90
				 * 3rd Value (left and righT)
				 * Neutral: 0 
				 * Left: 90
				 * Right: -90
				 */
			}
        };
        mp = new MediaPlayer();
        afd = getApplicationContext().getResources().openRawResourceFd(R.raw.oraoraora);
        ohno = getApplicationContext().getResources().openRawResourceFd(R.raw.ohno);
        ohmygod = getApplicationContext().getResources().openRawResourceFd(R.raw.ohmygod);
        sonuvabeetch = getApplicationContext().getResources().openRawResourceFd(R.raw.sonuvabeetch);
        zawarudo = getApplicationContext().getResources().openRawResourceFd(R.raw.zawarudo);
    }
	
	@SuppressWarnings("deprecation")
	@Override
	public void onResume()
	{
		super.onResume();
		mSensorManager.registerListener(mEventListenerOrientation, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), mSensorManager.SENSOR_DELAY_FASTEST);
	}
	
	@Override
	public void onStop()
	{
		mSensorManager.unregisterListener(mEventListenerOrientation);
		super.onStop();
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inf133__project, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
