
package com.evilmordekai.lifechoices;

import java.util.ArrayList;
import java.util.List;

public class Event
{
   public Event() {
      mKarmaRequirement = new ArrayList<Karma>();     
      mEventString = "";
      mLifeEvent = false;
      mMinAgeGroup = GameDef.AgeGroup.Infant;
      mMaxAgeGroup = GameDef.AgeGroup.Elderly;
      mKarmaAward = new ArrayList<Karma>();
   }
   public List<Karma> mKarmaAward;
   public void addKarmaAward(Karma karma){
      mKarmaAward.add(karma);
   }
   
   
   
   private List<Karma> mKarmaRequirement;
   public void addKarmaRequirement(Karma karma){
      mKarmaRequirement.add(karma);
   }
   public boolean isPossible(GameDef.AgeGroup age, List<Karma> karmaStatus)
   {     
      if( age.value() < mMinAgeGroup.value() || age.value() > mMaxAgeGroup.value())
         return false;
      
      for( Karma req : mKarmaRequirement){
         int value = karmaStatus.get( req.category.value() ).total;
         if( value < req.total)
            return false;
      }      
      
      return true;
   }
   
   public String mEventString;
 
   // determines whether this is a lifeEvent granting the player a choice
   private boolean mLifeEvent;
   
   // the age group range in which this event may occur
   public GameDef.AgeGroup mMinAgeGroup;
   public GameDef.AgeGroup mMaxAgeGroup;

   // function will be called by GameDriver when this event occurs.
   // yesNo has already been asked at this point 
   public void occurred()
   {
      doOccurred();
   }
   
   // override this function to do any event-specific processing 
   public void doOccurred()
   {
      return;
   }
    
    
    
    
}
