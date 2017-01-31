package muistipeli;

import java.util.*;

public class Laskut{
  
  private final List<String> Laskut = new ArrayList<>();
  private final List<String> Ratk = new ArrayList<>();
  private String lasku;
  private String ratk;
  private int ratkaisu;
  private int i;

  
  public Laskut(int vaikeus){
     switch(vaikeus){
         case 0:   
             sijoitaHelpot();
             break;
             
         case 1:
             sijoitaKeskivaikea();
             break;
         
         case 2:
             sijoitaVaikea();
             break;
     }
  }
  
  
  private void sijoitaHelpot(){
   for(i = 1; i < 9; i++){
    for(int j = 1; j < 9; j++){

     ratkaisu = i + j;
     ratk = String.valueOf(ratkaisu);
     lasku = String.valueOf(i) + "+" + String.valueOf(j);
     Laskut.add(lasku);
     Ratk.add(ratk);
    }
  }
}
    private void sijoitaKeskivaikea(){
   for(i = 1; i < 9; i++){
    for(int j = 1; j < 9; j++){

     ratkaisu = i * j;
     ratk = String.valueOf(ratkaisu);
     lasku = String.valueOf(i) + "*" + String.valueOf(j);
     Laskut.add(lasku);
     Ratk.add(ratk);
    }
  }
}
      private void sijoitaVaikea(){
   for(i = 30; i < 40; i++){
    for(int j = 60; j < 70; j++){
     ratkaisu = i * j;
     ratk = String.valueOf(ratkaisu);
     lasku = String.valueOf(i) + "*" + String.valueOf(j);
     Laskut.add(lasku);
     Ratk.add(ratk);
    }
  }
}
  public String getLaskut(int index){
   if(index < Laskut.size()){
     return Laskut.get(index);
   }else
       return "\uFFFD";
  }
  public String getRatkaisut(int index){
   if(index < Ratk.size()){
     return Ratk.get(index);
   }else
       return "\uFFFD";
  }
}