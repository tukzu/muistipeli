package muistipeli;


import java.awt.Font;
import java.util.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Tehdään oikean kokoiset listat merkeistä joita käytetään pelissäkuvina 
 *  
 */

enum Kuvat{

    Pieni(0x21, 0x28),
    Keskikoko(0x21, 0x32),
    Suuri(0x21, 0x40);
    public static final String FAMILY = Font.SERIF;
    public static final Font fontti = new Font(FAMILY, Font.PLAIN, 12);
    private static final Random rng = new Random();
    private final int eka, viim;
    private final List<String> lista = new ArrayList<String>();
    
    private Kuvat(int eka, int viim){
        this.eka = eka;
        this.viim = viim;
    }
    //muuttaa tekee merkkijono unicóden code pointeista ja tallentaa ne listaan
    private void init(){
     for(int i = eka; i <= viim; i++){
       if(fontti.canDisplay(i)){
         StringBuilder strBuild = new StringBuilder();
         strBuild.appendCodePoint(i);
         lista.add(strBuild.toString());
         lista.add(strBuild.toString());
       }
     }
     sekoita();
 }
    //palauttaa merkkijonon, joka sisältää indeksin kohdassa i olevan merkin
    //tai korvausmerkin
    public String getKuva(int index){
        if (lista.isEmpty()){
           init();
        }
        if(index < lista.size()){
            return lista.get(index);
        
        }else 
          return "\uFFFD";
    }
    //järjestaa listan sisällön sattumanvaraiseen järjestykseen
    public void sekoita(){
    Collections.shuffle(lista, rng);
    }
}