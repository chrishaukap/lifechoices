/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.evilmordekai.lifechoices;

import com.evilmordekai.lifechoices.Karma.Category;

import android.app.Application;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.os.Handler;


@TargetApi(11)
public class MainActivity extends Activity {

    private MyGLSurfaceView mView;
    private GameDriver mGame;
    private Handler myHandler = null;
    private LinearLayout eventLayout = null;
    private LinearLayout debugLayout = null;
    private MainActivity thisActivity = null;

    private static int maxVisibleMinorEvents = 6;
    private Runnable update=new Runnable() {
    	private void cullLifeEvents()
    	{
    		int numChildren = eventLayout.getChildCount();
    		if(numChildren > maxVisibleMinorEvents)
    			eventLayout.removeViewAt(0);
    	}
    	private void alphaFade()
    	{
    		int numChildren = eventLayout.getChildCount();
    		float intensity = 0.3f;
    		for(int i=0; i<numChildren; ++i)
    		{
    		   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) 
    		      ((TextView)eventLayout.getChildAt(i)).setAlpha(intensity);
    			intensity += 0.1f;    			
    		}
    	}
    	
    	public void run() {
            // do real work here

            if (!mGame.ispaused()) {
               cullLifeEvents();
               alphaFade();
               
               
               Event event = mGame.update(); // constructs event pool, selects next event
                              
               TextView t = new TextView(thisActivity);  
               t.setText(event.mEventString);  
               t.setTextSize(40);  
               t.setTextColor(Color.BLACK);
                // debug
               eventLayout.addView(t);
               thisActivity.updateDebugText();
               
               mView.requestRender();  // render OpenGL
               
               myHandler.postDelayed(update, 1000);
            }
        }
    };
    private void updateDebugText()
    {
       debugLayout.removeAllViews();
       TextView view = new TextView(this);
       view.setTextColor(Color.rgb(255,  255, 255));
       view.setTextSize(20);
       CharacterInfo c = mGame.getCharacter();
       String debugText = "Age: " + c.age() + "\n" + 
                          "AgeGroup: " + c.ageGroup() + "\n" +
                          "LifeGoal: " + c.mLifeGoals + "\n" +
                          "Karma:\n" +
                          "  Lust " + c.getKarma(Category.Lust) + "\n" +
                          "  Gluttony " + c.getKarma(Category.Gluttony) + "\n" +
                          "  Greed " + c.getKarma(Category.Greed) + "\n" +
                          "  Sloth " + c.getKarma(Category.Sloth) + "\n" +
                          "  Wrath " + c.getKarma(Category.Wrath) + "\n" +
                          "  Envy " + c.getKarma(Category.Envy) + "\n" +
                          "  Pride " + c.getKarma(Category.Pride) + "\n" +
                          "  Chastity " + c.getKarma(Category.Chastity) + "\n" +
                          "  Temperance " + c.getKarma(Category.Temperance) + "\n" +
                          "  Generosity " + c.getKarma(Category.Generosity) + "\n" +
                          "  Diligence " + c.getKarma(Category.Diligence) + "\n" +
                          "  Patience " + c.getKarma(Category.Patience) + "\n" +
                          "  Charity " + c.getKarma(Category.Charity) + "\n" +
                          "  Humility " + c.getKarma(Category.Humility) + "\n";
      		
       view.setText(debugText);
       view.setGravity(Gravity.RIGHT);
       debugLayout.addView(view);
    }
    
    private void setUpDebugText()
    {
       debugLayout = new LinearLayout(this);
       debugLayout.setLayoutParams( 
          new android.view.ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,
                                                  LayoutParams.MATCH_PARENT));
       debugLayout.setGravity(Gravity.RIGHT);
       debugLayout.setOrientation(LinearLayout.VERTICAL);
       
    }
    
    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        thisActivity = this;

        
        Application app = getApplication();
        mGame = (GameDriver) getApplication();
        mView = new MyGLSurfaceView(mGame, mGame);
        myHandler = new Handler();          
        
        eventLayout = new LinearLayout(this);
        eventLayout.setLayoutParams( 
              new android.view.ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT,
                                                      LayoutParams.MATCH_PARENT));  
        eventLayout.setGravity(Gravity.LEFT);
        eventLayout.setOrientation(LinearLayout.VERTICAL);
        
        mView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));  
                
        FrameLayout fl = new FrameLayout(this);  
        fl.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));  
        fl.addView(mView);  
        fl.addView(eventLayout);
        
        if(debugTextEnabled()) {
           setUpDebugText();
           fl.addView(debugLayout);
        }
        
        setContentView(fl);          
        myHandler.post(update);
    }
    private boolean debugTextEnabled()
    {
       return true;
    }

     @Override
    protected void onStart() {
        super.onStart();
        mGame.reinit(mView);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        mView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mView.onResume();
    }
    
}
