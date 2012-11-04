package com.example.android.lifechoices;

class CharacterInfo {
    public CharacterInfo() {
    	lifeGoals = GameDef.LifeGoals.Popularity;
    	mother = new FamilyMember();
    	father = new FamilyMember();
    	ageInMonths = 12; // 1 year
    }
    
    private int ageInMonths;
    public float age()
    {
    	return ((float)ageInMonths)/12;
    }
    public GameDef.AgeGroup ageGroup() 
    {
    	return GameDef.getAgeGroup(ageInMonths);    	
    }
    public GameDef.LifeGoals lifeGoals;
    public FamilyMember mother;
    public FamilyMember father;   
    
    
    private int debugUpdateCounter = 0;
    public void update()
    {
       ++debugUpdateCounter;
       if(debugUpdateCounter % 3== 0)
          ++ageInMonths;
    }
}

