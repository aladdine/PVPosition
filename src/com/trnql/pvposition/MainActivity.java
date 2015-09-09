package com.trnql.pvposition;

import java.text.DecimalFormat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final TextView textViewAzimuthValue = (TextView) findViewById(R.id.textViewAzimuthValue);
        final SeekBar seekBarAzimuth = (SeekBar) findViewById(R.id.seekBarAzimuth);
        seekBarAzimuth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				textViewAzimuthValue.setText(Integer.toString(progress) + " degrees");
				
			}
		});
        
        
        final TextView textViewTiltValue = (TextView) findViewById(R.id.textViewTiltValue);
        final SeekBar seekBarTilt = (SeekBar) findViewById(R.id.seekBarTilt);
        seekBarTilt.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				textViewTiltValue.setText(Integer.toString(progress) + " degrees");
				
			}
		});
        
        final Button buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener (new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final EditText editLatitude = (EditText) findViewById(R.id.editLatitude);
				final EditText editLongitude = (EditText) findViewById(R.id.editLongitude);
				
				float latitude = 0;
				float longitude = 0;
				
				// validate latitude has a value
				if (editLatitude.getText().length() > 0) {
					latitude = Float.parseFloat(editLatitude.getText().toString());
				}
				
				// validate longitude has a value
				if (editLongitude.getText().length() > 0) {
					longitude = Float.parseFloat(editLongitude.getText().toString());
				}
				
				if (latitude < -90 || latitude > 90 ) {
					Toast.makeText(getApplicationContext(), 
							"Value of latitude is invalid", 
							Toast.LENGTH_SHORT).show();
					return;
				}
				
				if (longitude < -90 || longitude > 90 ) {
					Toast.makeText(getApplicationContext(), 
							"Value of latitude is invalid", 
							Toast.LENGTH_SHORT).show();
					return;
				}
				/*
				0° (Quito)	 0.0	6.5	72%
				 5° (Bogotá)	 4.4	6.5	72%
				10° (Caracas)	 8.7	6.5	72%
				15° (Dakar)	13.1	6.4	72%
				20° (Mérida)	17.4	6.3	72%
				25° (Key West, Taipei)	22.1	6.2	72%
				30° (Houston, Cairo)	25.9	6.1	71%
				35° (Albuquerque, Tokyo)	29.7	6.0	71%
				40° (Denver, Madrid)	33.5	5.7	71%
				45° (Minneapolis, Milano)	37.3	5.4	71%
				50° (Winnipeg, Prague)	41.1	5.1	70%
				*/
				
				final TextView textViewOptimumValue = (TextView) findViewById(R.id.textViewOptimumValue);
				final TextView textViewOptimumAzimuthValue = (TextView) findViewById(R.id.textViewOptimumAzimuthValue);
				final TextView textViewOptimumTiltValue = (TextView) findViewById(R.id.textViewOptimumTiltValue);
				int tilt = seekBarTilt.getProgress();
				int azimuth = seekBarAzimuth.getProgress();
				
				float optimum = 0;
				float optimumTilt = latitude;
				float optimumAzimuth = 0;
				
				if (latitude <=5 ) { optimum = 72; optimumTilt = (float) 0.0;}
				if (latitude > 5 && latitude <= 10  ) { optimum = 72; optimumTilt = (float) 4.4;}
				if (latitude > 10 && latitude <= 15  ) { optimum = 72; optimumTilt = (float) 8.7;}
				if (latitude > 15 && latitude <= 20  ) { optimum = 72; optimumTilt = (float) 13.1;}
				if (latitude > 20 && latitude <= 25  ) { optimum = 72; optimumTilt = (float) 17.4;}
				if (latitude > 25 && latitude <= 30  ) { optimum = 72; optimumTilt = (float) 22.1;}
				if (latitude > 30 && latitude <= 35  ) { optimum = 71; optimumTilt = (float) 25.9;}
				if (latitude > 35 && latitude <= 40  ) { optimum = 71; optimumTilt = (float) 29.7;}
				if (latitude > 40 && latitude <= 45  ) { optimum = 71; optimumTilt = (float) 33.5;}
				if (latitude > 45  ) { optimum = 70; optimumTilt = (float) 37.3;}
				
				DecimalFormat percentage = new DecimalFormat("##.#");
				
				String sOptimumTextOut = percentage.format(optimum) + "%";
				String sOptimumTiltTextOut = percentage.format(optimumTilt) + "%";
				String sOptimumAzimuthTextOut = percentage.format(optimumAzimuth) + "%";
				
				textViewOptimumValue.setText(sOptimumTextOut);		
				textViewOptimumAzimuthValue.setText(sOptimumAzimuthTextOut);		
				textViewOptimumTiltValue.setText(sOptimumTiltTextOut);		
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
