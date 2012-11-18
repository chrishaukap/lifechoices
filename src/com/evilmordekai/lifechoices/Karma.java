
package com.evilmordekai.lifechoices;

public class Karma
{
   public enum Category{ 
      Lust(0),       //(excessive sexual appetites) 
      Gluttony(1),   //(over-indulgence)     
      Greed(2),      //(avarice) 
      Sloth(3),      //(laziness/idleness)
      Wrath(4),      //(anger) 
      Envy(5),       //(jealousy) 
      Pride(6),      //(vanity)     
      
      Chastity(7),   //(purity)
      Temperance(8), //(self-restraint) 
      Generosity(9), //Kindness (admiration) 
      Diligence(10), //(zeal/integrity/Labor) 
      Patience(11),  //(composure)
      Charity(12),   //(giving) 
      Humility(13);  //(humbleness)                

      private final int mValue;
      private Category(int _value){
         mValue = _value;
      }
      public int value() {return mValue;}
   };
   public int total;
   public Category category;
   
   public Karma( int value, Category cat) { total = value; category = cat;}
}
