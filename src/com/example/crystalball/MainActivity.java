package com.example.crystalball;

import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crystalball.ShakeDetector.OnShakeListener;

public class MainActivity extends ActionBarActivity 
{
	private CrystalBall mCrystalBall;
	private Button getAnswerButton;
	private TextView answerLabel;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    
    

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Declare Variable and link to view 
        answerLabel = (TextView) findViewById(R.id.textView1);
        getAnswerButton = (Button) findViewById(R.id.button1);
        mCrystalBall=new CrystalBall();
        
        mSensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector=new ShakeDetector(new OnShakeListener() {
			
			@Override
			public void onShake() {
				handleNewAnswer();
				
			}
		});
        
        getAnswerButton.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{
				handleNewAnswer();
			}
		});
    }
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	mSensorManager.registerListener(mShakeDetector, mAccelerometer, 
      		  SensorManager.SENSOR_DELAY_UI);
    	
    }
    
    @Override
    public void onPause()
    {
    	super.onPause();
    	mSensorManager.unregisterListener(mShakeDetector);
    	
    }
   
    private void animateCrystalBall()
    {
    	ImageView crystalBallImage=(ImageView) findViewById(R.id.imageView1);
    	crystalBallImage.setImageResource(R.drawable.ball_animation);
    	AnimationDrawable ballAnimation=(AnimationDrawable) crystalBallImage.getDrawable();
    	if(ballAnimation.isRunning())
    	{
    		ballAnimation.stop();
    	}
    	
    	ballAnimation.start();
    }
    
    private void playSound()
    {
    	MediaPlayer player=MediaPlayer.create(this, R.raw.crystal_ball);
    	player.start();
    	player.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
				
				
			}
		});
    	
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	private void handleNewAnswer() {
		String answer=mCrystalBall.getAnAnswer();
		
		//update label with random answer
		answerLabel.setText(answer);
		animateCrystalBall();
		playSound();
	}

  
    

}
