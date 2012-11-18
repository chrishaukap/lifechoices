package com.evilmordekai.lifechoices;

class GameDef {
    
    public enum AgeGroup{ 
       Infant(1), Toddler(2), Child(3), Teen(4), Twenties(5), Thirties(6), 
       MiddleAged(7), Retirement(8), Elderly(9), Dead(10);
       private final int mValue;
       private AgeGroup(int _value){
          mValue = _value;
       }
       public int value() {return mValue;}
    }
    public static AgeGroup getAgeGroup(int ageInMonths)
    {
    	float age = ageInMonths/12;
    	if(age >= 75) return AgeGroup.Elderly;
    	else if(age >= 65) return AgeGroup.Retirement;
    	else if(age >= 45) return AgeGroup.MiddleAged;
    	else if(age >= 30) return AgeGroup.Thirties;
    	else if(age >= 20) return AgeGroup.Twenties;
    	else if(age >= 10) return AgeGroup.Teen;
    	else if(age >= 5) return AgeGroup.Child;
    	else return AgeGroup.Infant;
    	
    }
    
    public enum Wealth{ Homeless, Poverty, Middle, Welloff, Rich, Fabulous}
    public enum OccupationalSuccess { Unsuccessful, Mediocre, Successful }
    public enum Occupation{
    	Actor, Politicitan, Scientist, Plumber, Caretaker, RefuseTechnician, Gardner, HiredMuscle,
    	DrugDealer, Porn, StandupComic, Teacher, Student, Assassin, Military, ProffesionalStarcraftPlayer
    }
    public enum Hobbies{
    	Cocaine, CrystalMeth, KillingPeople, Pot, Alcohol, PublicUrination, RagingAtNewbsInMOBAGames,
    	HelpingOldLadies, CollectingAntiqueRocks, MakingYourPetsKiss
    }
    
    public enum LifeGoals{
    	Popularity, FinancialSuccess, Education, WhilingAwayTheHours, Humanitariasm, SelfExpression		
    }
}

