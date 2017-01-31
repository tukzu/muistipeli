package muistipeli;

import java.awt.*;
import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;

/*
* Luokka jossa toteutaan tarvittavat dialogit
*
*/
public class dialogi extends JDialog{
    JFrame frame;
   
  public void dialog(){
  
  }
  //toteutaa aloitusikkunan menuvalikossa olevan lopeta peli napin varmistus
  //dialogin
  public void lopetus(){
    
      Object[] options = {"Kyllä", "ei"};
      int lopeta = JOptionPane.showOptionDialog(frame, 
              "Oletko varma", "Lopeta",
              JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE,
              null,
              options,
              options[0]);
     if (lopeta == JOptionPane.YES_OPTION){
       System.exit(0);
       }
  }
  //metodi joka tuottaa help dialogin
  public void apua(){
     JOptionPane.showMessageDialog(frame, "Valittuasi koon, vaikeuden, sekä pelityypin"
             + " paina aloita niin pääset pelaamaan muistipeliä"
             + "\nPelissä etsit kuvapareja, tai lasku ja ratkaisupareja"
             + " klikkaamalla ruutuja");
}
  public int uusiPeli(){
      Object[] options = {"Uusi peli", "Lopeta"};
      int lopetus = JOptionPane.showOptionDialog(frame, 
              "Onneksi olkoon voitit pelin", "Olet voittaja",
              JOptionPane.YES_NO_OPTION,
              JOptionPane.QUESTION_MESSAGE,
              null,
              options,
              options[0]);
     if (lopetus == JOptionPane.YES_OPTION){
       return 1;
     }else if(lopetus == JOptionPane.NO_OPTION){
      System.exit(0);
     }
    
    return 0;
  }
}