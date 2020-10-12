// Thomas Lu, 10/12/2020
// Mobile, P.6 Tra
// Lab 5: Lifecycle

package com.example.lab5_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LifecycleVars vars;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView tempVars;
    TextView sharedVars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vars = new LifecycleVars();

        sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        tempVars = findViewById(R.id.tempVars);
        sharedVars = findViewById(R.id.sharedVars);

        vars.setCreate(vars.getCreate()+1);
        editor.putInt("Create",sharedPreferences.getInt("Create",0)+1);
        editor.apply();

        updateText();
    }
    private void updateText(){
        String newTempString =  "This run only:\n\n" +
                                "Create: " + vars.getCreate() + "\n" +
                                "Start: " + vars.getStart() + "\n" +
                                "Resume: " + vars.getResume() + "\n" +
                                "Pause: " + vars.getPause() + "\n" +
                                "Stop: " + vars.getStop() + "\n" +
                                "Restart: " + vars.getRestart() + "\n" +
                                "Destroy: " + vars.getDestroy();
        String newSharedString =    "All runs:\n\n" +
                                    "Create: " + sharedPreferences.getInt("Create",0) + "\n" +
                                    "Start: " + sharedPreferences.getInt("Start",0) + "\n" +
                                    "Resume: " + sharedPreferences.getInt("Resume",0) + "\n" +
                                    "Pause: " + sharedPreferences.getInt("Pause",0) + "\n" +
                                    "Stop: " + sharedPreferences.getInt("Stop",0) + "\n" +
                                    "Restart: " + sharedPreferences.getInt("Restart",0) + "\n" +
                                    "Destroy: " + sharedPreferences.getInt("Destroy",0);
        tempVars.setText(newTempString);
        sharedVars.setText(newSharedString);
    }
    @Override
    protected void onStart() {
        super.onStart();
        vars.setStart(vars.getStart()+1);
        editor.putInt("Start",sharedPreferences.getInt("Start",0)+1);
        editor.apply();
        updateText();
    }

    @Override
    protected void onStop() {
        super.onStop();
        vars.setStop(vars.getStop()+1);
        editor.putInt("Stop",sharedPreferences.getInt("Stop",0)+1);
        editor.apply();
        updateText();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vars.setDestroy(vars.getDestroy()+1);
        editor.putInt("Destroy",sharedPreferences.getInt("Destroy",0)+1);
        editor.apply();
        updateText();
    }

    @Override
    protected void onPause() {
        super.onPause();
        vars.setPause(vars.getPause()+1);
        editor.putInt("Pause",sharedPreferences.getInt("Pause",0)+1);
        editor.apply();
        updateText();
    }

    @Override
    protected void onResume() {
        super.onResume();
        vars.setResume(vars.getResume()+1);
        editor.putInt("Resume",sharedPreferences.getInt("Resume",0)+1);
        editor.apply();
        updateText();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        vars.setRestart(vars.getRestart()+1);
        editor.putInt("Restart",sharedPreferences.getInt("Restart",0)+1);
        editor.apply();
        updateText();
    }

    private class LifecycleVars {

        private int create;
        private int start;
        private int resume;
        private int pause;
        private int stop;
        private int restart;
        private int destroy;

        public LifecycleVars(){
            create = 0;
            start = 0;
            resume = 0;
            pause = 0;
            stop = 0;
            restart = 0;
            destroy = 0;
        }


        public int getCreate() {
            return create;
        }

        public void setCreate(int create) {
            this.create = create;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getResume() {
            return resume;
        }

        public void setResume(int resume) {
            this.resume = resume;
        }

        public int getPause() {
            return pause;
        }

        public void setPause(int pause) {
            this.pause = pause;
        }

        public int getStop() {
            return stop;
        }

        public void setStop(int stop) {
            this.stop = stop;
        }

        public int getRestart() {
            return restart;
        }

        public void setRestart(int restart) {
            this.restart = restart;
        }

        public int getDestroy() {
            return destroy;
        }

        public void setDestroy(int destroy) {
            this.destroy = destroy;
        }
    }

    public void resetVars(View v){
        vars.setCreate(1);
        editor.putInt("Create",1);
        vars.setStart(1);
        editor.putInt("Start",1);
        vars.setResume(1);
        editor.putInt("Resume",1);
        vars.setPause(0);
        editor.putInt("Pause",0);
        vars.setStop(0);
        editor.putInt("Stop",0);
        vars.setRestart(0);
        editor.putInt("Restart",0);
        vars.setDestroy(0);
        editor.putInt("Destroy",0);
        editor.apply();
        updateText();
    }

    public void finishButton(View v){
        finish();
    }
}