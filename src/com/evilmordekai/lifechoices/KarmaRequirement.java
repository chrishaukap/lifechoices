package com.evilmordekai.lifechoices;

public class KarmaRequirement
{
   public enum Comparison{ Equal, Lessthan, Greaterthan };
   public KarmaRequirement( Karma _karma, Comparison _compare)  
   {
      karma = _karma;
      compare = _compare;
   }
   public Karma karma;
   public Comparison compare;
}