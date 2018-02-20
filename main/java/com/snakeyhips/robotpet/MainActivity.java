package com.snakeyhips.robotpet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    //UI object references
    private Toolbar toolbar;
    private TextView speechText;
    private FloatingActionButton gameButton;
    private FloatingActionButton foodButton;
    
    //Robot object
    private Robot robot;
    private long currentTime;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewByID(R.id.toolbar));
        
        //Set up UI objects
        getSupportActionBar().setTitle("Title Test");      
        speechText = (TextView) findViewById(R.id.speechText);
        gameButton = (FloatingActionButton) findViewById(R.id.gameButton);
        foodButton = (FloatingActionButton) findViewById(R.id.foodButton);
        currentTime = System.currentTimeMillis();
        
        //Set up FAB listeners
        mGameButton.setOnClickListener(
                new FloatingActionButton.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        mTestText.setText("Game Pressed");
                        Toast toast = Toast.makeText(getApplicationContext(), "Game Pressed", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
        );
        mFoodButton.setOnClickListener(
                new FloatingActionButton.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        mTestText.setText("Food Pressed");
                        Toast toast = Toast.makeText(getApplicationContext(), "Food Pressed", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
        );
        
        //Retrieve previous data from SharedPreferences
        try{
            SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
            
            mRobot.setId(UUID.fromString(sharedPref.getString("robotId"), ""));
            mRobot.setName(sharedPref.getString("robotName"), ""));
            mRobot.setAge(sharedPref.getInt("robotAge"), 0));
            mRobot.setHappy(sharedPref.getInt("robotHappy"), 0));
            mRobot.setHunger(sharedPref.getInt("robotHunger"), 0));
            mRobot.setFatigue(sharedPref.getInt("robotFatigue"), 0));
            mRobot.setNaughty(sharedPref.getInt("robotNaughty"), 0));
            mRobot.setWaste(sharedPref.getInt("robotWaste"), 0));
            mRobot.setIllness(sharedPref.getBoolean("robotIllness", false));
            mSpeechText.setText("Saved");
        } catch (Exception e){
            e.printStackTrace();
            //Initialise mRobot possibly not needed if above works
            mRobot = new Robot();
            mRobot.setId(new UUID());
            mRobot.setName("Robot Name");
            mRobot.setAge(0);
            mRobot.setHappy(50);
            mRobot.setHunger(50);
            mRobot.setFatigue(0);
            mRobot.setNaughty(0);
            mRobot.setWaste(0);
            mRobot.setIllness(false);
            mSpeechText.setText("New");
        }
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settingsAbout:
                //do stuff
                return true;
        }
    }
    
    //Saves state of Robot when activity is paused
    @Override
    public void onPause(){
        super.onPause();
        
        if(mRobot != null){
            SharedPreferences mainPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mainPref.edit();
            
            editor.putString("robotId", robot.getId().toString());
            editor.putString("robotName", robot.getName());
            editor.putInt("robotAge", robot.getAge());
            editor.putInt("robotHappy", robot.getHappy());
            editor.putInt("robotHunger", robot.getHunger());
            editor.putInt("robotFatigue", robot.getFatigue());
            editor.putInt("robotNaughty", robot.getNaughty());
            editor.putInt("robotWaste", robot.getWaste());
            editor.putBoolean("robotIllness", robot.getIllness());
            editor.apply();
        }
    }
    
    
    //Back button opens dialog to ask if want to close
    @Override
    public void onBackPressed() {
        //do back button stuff here
    }
}
