package com.snakeyhips.robotpet;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    
    //UI object references
    private Toolbar toolbar;
    private TextView happyText;
    private TextView hungerText;
    private FloatingActionButton gameButton;
    private FloatingActionButton foodButton;
    
    //Robot object
    private Robot robot;
    private long currentTime;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        
        //Get screen dimensions
        Constants.SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
        Constants.SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
        
        //Set up UI objects
        getSupportActionBar().setTitle("Robot Pet");
        happyText = findViewById(R.id.happyText);
        hungerText = findViewById(R.id.hungerText);
        gameButton = findViewById(R.id.gameButton);
        foodButton = findViewById(R.id.foodButton);
        
        //Set up FAB listeners
        gameButton.setOnClickListener(
                new FloatingActionButton.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        robot.setHappy(robot.getHappy() + 25);
                        Toast toast = Toast.makeText(getApplicationContext(), "Game Pressed", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
        );
        foodButton.setOnClickListener(
                new FloatingActionButton.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        robot.setHappy(robot.getHappy() + 25);
                        Toast toast = Toast.makeText(getApplicationContext(), "Food Pressed", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
        );
        
        Retrieve previous data from SharedPreferences
        try{
            SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
            
            //robot.setId(UUID.fromString(sharedPref.getString("robotId"), ""));
            robot.setName(sharedPref.getString("robotName", "2B Saved"));
            robot.setAge(sharedPref.getInt("robotAge", 0));
            robot.setHappy(sharedPref.getInt("robotHappy", 0));
            robot.setHunger(sharedPref.getInt("robotHunger", 0));
            robot.setFatigue(sharedPref.getInt("robotFatigue", 0));
            robot.setNaughty(sharedPref.getInt("robotNaughty", 0));
            robot.setWaste(sharedPref.getInt("robotWaste", 0));
            robot.setIllness(sharedPref.getBoolean("robotIllness", false));
        } catch (Exception e){
            e.printStackTrace();
            //Initialise mRobot possibly not needed if above works
            robot = new Robot();
            //robot.setId(new UUID());
            robot.setName("2B New");
            robot.setAge(0);
            robot.setHappy(50);
            robot.setHunger(50);
            robot.setFatigue(0);
            robot.setNaughty(0);
            robot.setWaste(0);
            robot.setIllness(false);
            speechText.setText("New");
        } finally {
            getSupportActionBar().setTitle(robot.getName());
            happyText.setText(Integer.ToString(robot.getHappy()));
            hungerText.setText(Integer.ToString(robot.getHunger()));
        }
        
    }
    
    //Saves state of Robot when activity is paused
    @Override
    public void onPause(){
        super.onPause();
        
        if(robot != null){
            SharedPreferences mainPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mainPref.edit();
            
            //editor.putString("robotId", new UUID(robot.getId()));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case 0:
                //do stuff
                return true;
        }
    }*/
    
    //Back button opens dialog to ask if want to close
    @Override
    public void onBackPressed() {
        //do back button stuff here
    }
}
