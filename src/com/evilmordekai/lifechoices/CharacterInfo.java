package com.evilmordekai.lifechoices;

import java.util.ArrayList;
import java.util.List;

import com.evilmordekai.lifechoices.Karma.Category;

class CharacterInfo {
    public CharacterInfo() {
    	mLifeGoals = GameDef.LifeGoals.Popularity;
    	mMother = new FamilyMember();
    	mFather = new FamilyMember();
    	mAgeInMonths = 12; // 1 year
    	mKarma = new ArrayList<Karma>();
    	
      mKarma.add(new Karma(0, Category.Lust));
      mKarma.add(new Karma(0, Category.Gluttony));
      mKarma.add(new Karma(0, Category.Greed));
      mKarma.add(new Karma(0, Category.Sloth));
      mKarma.add(new Karma(0, Category.Wrath));
      mKarma.add(new Karma(0, Category.Envy));
      mKarma.add(new Karma(0, Category.Pride));
      mKarma.add(new Karma(0, Category.Chastity));
      mKarma.add(new Karma(0, Category.Temperance));
      mKarma.add(new Karma(0, Category.Generosity));
      mKarma.add(new Karma(0, Category.Diligence));
      mKarma.add(new Karma(0, Category.Patience));
      mKarma.add(new Karma(0, Category.Charity));
      mKarma.add(new Karma(0, Category.Humility));
    }
    
    private List<Karma> mKarma = null;
    public final List<Karma> getKarmaStatus() {return  mKarma;}
    public int getKarma(Category cat)
    {
       return mKarma.get(cat.value()).total;
    }
    public void awardKarma(Karma karma)
    {
       mKarma.get(karma.category.value()).total += karma.total;
    }
    
    
    private int mAgeInMonths;
    public float age()
    {
    	return ((float)mAgeInMonths)/12;
    }
    public GameDef.AgeGroup ageGroup() 
    {
    	return GameDef.getAgeGroup(mAgeInMonths);    	
    }
    public GameDef.LifeGoals mLifeGoals;
    public FamilyMember mMother;
    public FamilyMember mFather;   
    
    
    private int debugUpdateCounter = 0;
    public void update()
    {
       ++debugUpdateCounter;
       if(debugUpdateCounter % 3== 0)
          ++mAgeInMonths;
    }
}

