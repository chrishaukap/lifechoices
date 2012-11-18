package com.evilmordekai.lifechoices;

import java.util.ArrayList;
import java.util.List;

import com.evilmordekai.lifechoices.GameDef.AgeGroup;
import com.evilmordekai.lifechoices.Karma.Category;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

public class GameDriver extends Application {
    private final String TAG = this.getClass().getSimpleName();

    private static String EXTERNAL_STORAGE_DIRECTORY = Environment.getExternalStorageDirectory().toString();
    final static String TARGET_BASE_PATH = EXTERNAL_STORAGE_DIRECTORY + "/GeoViewer";

    private MyGLSurfaceView mGLView = null;
    private CharacterInfo mCharacter = null;
    public CharacterInfo getCharacter() {return mCharacter;}
    
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate");

        super.onCreate();
        mCharacter = new CharacterInfo();
        initEvents();
    }
    
    private List<Event> mEvents = null;
    private void initEvents()
    {
       mEvents = new ArrayList<Event>();
       
       // for now, set up a bunch of debug Events
       Event event = new Event();
       event.mEventString = "You Pooed your diapers";
       event.mMinAgeGroup = AgeGroup.Infant;
       event.mMaxAgeGroup = AgeGroup.Toddler;
       mEvents.add(event);
       
       event = new Event();
       event.mEventString = "Your mother neglects you a nother night when she's high meth";
       event.mMinAgeGroup = AgeGroup.Infant;
       event.mMaxAgeGroup = AgeGroup.Child;
       mEvents.add(event);

       event = new Event();
       event.mEventString = "Because you scream so much your parents locked you in your room without dinner";
       event.mMinAgeGroup = AgeGroup.Infant;
       event.mMaxAgeGroup = AgeGroup.Child;
       event.addKarmaAward(new Karma(50, Category.Wrath));
       event.addKarmaAward(new Karma(-50, Category.Temperance));
       mEvents.add(event);
       
       event = new Event();
       event.mEventString = "You Pooed your diapers";
       event.mMinAgeGroup = AgeGroup.Retirement;
       event.mMaxAgeGroup = AgeGroup.Elderly;
       mEvents.add(event);
       
       event = new Event();
       event.mEventString = "You father slapped you around again and you don't quite know why";
       event.mMinAgeGroup = AgeGroup.Child;
       event.mMaxAgeGroup = AgeGroup.Teen;
       event.addKarmaAward(new Karma(200, Category.Wrath));
       event.addKarmaAward(new Karma(50, Category.Envy));
       event.addKarmaAward(new Karma(30, Category.Lust));
       event.addKarmaAward(new Karma(100, Category.Humility));
       event.addKarmaAward(new Karma(-100, Category.Temperance));
       mEvents.add(event);
       
       event = new Event();
       event.mEventString = "You fought with your sister and came out of it with a black eye";
       event.mMinAgeGroup = AgeGroup.Child;
       event.mMaxAgeGroup = AgeGroup.Teen;
       event.addKarmaAward(new Karma(50, Category.Wrath));
       event.addKarmaAward(new Karma(-50, Category.Patience));
       mEvents.add(event);
       
       event = new Event();
       event.mEventString = "This kids at school are teasing you and you decide to fight back (<-- LifeChoice)";
       event.mMinAgeGroup = AgeGroup.Child;
       event.mMaxAgeGroup = AgeGroup.Twenties;
       event.addKarmaAward(new Karma(400, Category.Wrath));
       event.addKarmaAward(new Karma(-100, Category.Patience));
       event.addKarmaAward(new Karma(-300, Category.Temperance));
       event.addKarmaRequirement(new Karma(200, Category.Wrath));
       event.addKarmaRequirement(new Karma(-200, Category.Temperance));
       mEvents.add(event);
              
       event = new Event();
       event.mEventString = "An argument with your father came to blows";
       event.mMinAgeGroup = AgeGroup.Child;
       event.mMaxAgeGroup = AgeGroup.Twenties;
       event.addKarmaAward(new Karma(200, Category.Wrath));
       event.addKarmaAward(new Karma(-50, Category.Patience));
       event.addKarmaAward(new Karma(-300, Category.Temperance));
       event.addKarmaRequirement(new Karma(1000, Category.Wrath));
       mEvents.add(event);
       
       event = new Event();
       event.mEventString = "You got into a minor bar fight, but were able to shake hands before the evening was out";
       event.mMinAgeGroup = AgeGroup.Twenties;
       event.mMaxAgeGroup = AgeGroup.MiddleAged;
       event.addKarmaAward(new Karma(300, Category.Wrath));
       event.addKarmaAward(new Karma(-200, Category.Patience));
       event.addKarmaAward(new Karma(-200, Category.Temperance));
       event.addKarmaRequirement(new Karma(400, Category.Wrath));
       event.addKarmaRequirement(new Karma(-400, Category.Temperance));
       mEvents.add(event);

       event = new Event();
       event.mEventString = "You were mugged in an alley, but he brought a knife to a gun fight.  You kill the guy (LIFE CHOICE)";
       event.mMinAgeGroup = AgeGroup.Twenties;
       event.mMaxAgeGroup = AgeGroup.MiddleAged;
       event.addKarmaAward(new Karma(1000, Category.Wrath));
       event.addKarmaAward(new Karma(-400, Category.Temperance));
       event.addKarmaRequirement(new Karma(1000, Category.Wrath));
       event.addKarmaRequirement(new Karma(-500, Category.Temperance));
       mEvents.add(event);
       
       event = new Event();
       event.mEventString = "You Pooed your diapers";
       event.mMinAgeGroup = AgeGroup.Retirement;
       event.mMaxAgeGroup = AgeGroup.Elderly;
       
       mEvents.add(event);
       event = new Event();
       event.mEventString = "You Pooed your diapers";
       event.mMinAgeGroup = AgeGroup.Retirement;
       event.mMaxAgeGroup = AgeGroup.Elderly;
       mEvents.add(event);
       
    }
    
    private boolean paused = false;
    
    public boolean ispaused() {return paused;}
    
    public void setPause(boolean _isPaused) {paused = _isPaused;}
    
    public Event update()
    {
       mCharacter.update();
       List<Event> eventPool = getEventPool();
       Event event = selectEvent(eventPool);
       
       for(Karma award : event.mKarmaAward)
          mCharacter.awardKarma(award);
       return event;
    }
    private Event selectEvent(List<Event> eventPool)
    {
       // random for now
       int min = 0, max = eventPool.size()-1;
       int idx = min + (int)(Math.random() * ((max - min) + 1));
       
       return eventPool.get(idx);
    }
    private List<Event> getEventPool()
    {
       List<Event> ret = new ArrayList<Event>();
       for(Event event : mEvents)  
          if(event.isPossible(mCharacter.ageGroup(), mCharacter.getKarmaStatus()))
             ret.add(event);
       return ret;
    }

    public void reinit(MyGLSurfaceView view) {
        Log.d(TAG, "reinit");
        mGLView = view;
    }

    public MyGLSurfaceView getGLView() {
        return mGLView;
    }

}