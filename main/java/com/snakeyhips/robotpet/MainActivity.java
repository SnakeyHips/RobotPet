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
    private Toolbar mToolbar;
    private TextView mTestText;
    private FloatingActionButton mGameButton;
    private FloatingActionButton mFoodButton;
    
    //Robot object
    private Robot mRobot;
    
    private GameView mGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
        
        setSupportActionBar(findViewByID(R.id.mToolbar));
        
        //Set up UI objects
        getSupportActionBar().setTitle("Title Test");
        mTestText = (TextView) findViewById(R.id.mTestText);
        mGameButton = (FloatingActionButton) findViewById(R.id.mGameButton);
        mFoodButton = (FloatingActionButton) findViewById(R.id.mFoodButton);
        
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
            
            mRobot.setId(UUID.fromString(sharedPref.getString("mRobotId"), ""));
            mRobot.setName(sharedPref.getString("mRobotName"), ""));
            mRobot.setAge(sharedPref.getInt("mRobotAge"), 0));
            mRobot.setHappy(sharedPref.getInt("mRobotHappy"), 0));
            mRobot.setHunger(sharedPref.getInt("mRobotHunger"), 0));
            mRobot.setFatigue(sharedPref.getInt("mRobotFatigue"), 0));
            mRobot.setNaughty(sharedPref.getInt("mRobotNaughty"), 0));
            mRobot.setWaste(sharedPref.getInt("mRobotWaste"), 0));
            mRobot.setAge(sharedPref.getInt("mRobotAge"), 0));
            mRobot.setIllness(sharedPref.getBoolean("mRobotIllness", false));
        } catch (Exception e){
            e.printStackTrace();
            //Initialise mRobot
            mRobot = new Robot();
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
            case R.id.mSettingsAbout:
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
            
            editor.putString("mRobotId", mRobot.getId().toString());
            editor.putString("mRobotName", mRobot.getName());
            editor.putInt("mRobotAge", mRobot.getAge());
            editor.putInt("mRobotHappy", mRobot.getHappy());
            editor.putInt("mRobotHunger", mRobot.getHunger());
            editor.putInt("mRobotFatigue", mRobot.getFatigue());
            editor.putInt("mRobotNaughty", mRobot.getNaughty());
            editor.putInt("mRobotWaste", mRobot.getWaste());
            editor.putBoolean("mRobotIllness", mRobot.getIllness());
            editor.apply();
        }
    }
    
    
    //Back button opens dialog to ask if want to close
    @Override
    public void onBackPressed() {
        //do back button stuff here
    }
}
