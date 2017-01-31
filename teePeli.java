package muistipeli;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
/**
 * luokka joka toteuttaa pelin toimintalogiikan.
 * 
 */

public class teePeli extends JPanel implements ActionListener {
    private final JButton[] ruutuNappi = new JButton[64];
    private final ArrayList<String> kuvalista = new ArrayList<>();
    private ArrayList<String> lista2;
    private String testi;
    private final Kuvat pienet;
    private final Kuvat keskik;
    private final Kuvat suuri;
    private String test;
    private int mode;
    private int kaannetty = 0; 
    private final int[] id = new int[2];
    private String jono1, jono2; 
    private int size;
    private int a = 0, b = 0;
    private int loppu = 1;
    private dialogi dlogi = new dialogi();
    private int vaik;
    private int[] laskuri;
    private int klikattu;
    private int klikattu2;
    //konstruktori joka saa parametreina ruutujen määrän(koko) vaikeusasteen
    //sekä valitun pelityypin. Luo ikkunan, piirtää ruudut sekä hakee
    //ruutuihin sisällön.
    teePeli(final int koko, int vaikeus, int pelimode){
      size = koko*koko;
        
      Laskut lasku = new Laskut(vaikeus);
      this.pienet = Kuvat.Pieni;
      this.keskik = Kuvat.Keskikoko;
      this.suuri = Kuvat.Suuri;
      final JFrame peli = new JFrame("peli");
      JMenuBar menu = new JMenuBar(); //luodaan menupalkki
      JMenu valikko = new JMenu("Options"); //luodaan options-valikko
      JMenuItem helppi = new JMenuItem(new AbstractAction("?"){
       @Override
        public void actionPerformed(ActionEvent ae) {
           dlogi.apua();
        }
        });
          menu.add(valikko);
          menu.add(helppi);
    
    
       JMenuItem uusi = new JMenuItem(new AbstractAction("Uusi Peli"){
        @Override
           public void actionPerformed(ActionEvent ae) {
           size = koko;
           peli.dispose();
           teePeli Uusipeli = new teePeli(size, vaik, mode);
        }
    });
    JMenuItem lopeta = new JMenuItem(new AbstractAction("Lopeta peli"){
     @Override
     public void actionPerformed(ActionEvent ae) {
           dlogi.lopetus();
        }
    });
    valikko.add(uusi);
    valikko.add(lopeta);
    peli.setJMenuBar(menu);
      
      int j = 0, k = 0, o = 0;
      peli.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      Container cont = peli.getContentPane();
      peli.pack();
      peli.setVisible(true);
      peli.setSize(new Dimension(700, 700));
     
      
      cont.setLayout(new GridBagLayout());
      GridBagConstraints rajat = new GridBagConstraints();
         for(int i = 0; i < size; i++){
           ruutuNappi[i] = new JButton();
           ruutuNappi[i].setFont(new Font("Monospace", Font.BOLD, 28));
           ruutuNappi[i].addActionListener(this);
           rajat.gridx = k;
           rajat.gridy = j;
           rajat.weightx = 1.0;
           rajat.weighty = 1.0;
           rajat.fill = GridBagConstraints.BOTH;
           cont.add(ruutuNappi[i], rajat);
            if(pelimode == 0){
                mode = 0;
                if(i < size/2){
                this.testi = lasku.getLaskut(i);
                kuvalista.add(testi);
                }else{
                 this.testi = lasku.getRatkaisut(a);
                 kuvalista.add(testi);
                 a++;
                }
            }else if(pelimode == 2){
               mode = 2;
             if(koko == 4){
             this.testi = pienet.getKuva(i);
             kuvalista.add(testi);
             }else if(koko == 6){
             this.testi = keskik.getKuva(i);
             kuvalista.add(testi);
             }else if(koko == 8){
             this.testi = suuri.getKuva(i);
             kuvalista.add(testi);
             }
           }
           ++k;
           if (k == koko){
               j++;
               k=0;
           }
         }
     }
    //tarkkailee napin painalluksia ja reagoi niihin
    @Override
    public void actionPerformed(ActionEvent e){
        
       for(int i = 0; i < ruutuNappi.length; i++){
         if(ruutuNappi[i] == e.getSource()){
          
           test = kuvalista.get(i);
           ruutuNappi[i].setText("" + test);
           ruutuNappi[i].setEnabled(false);
           kaannetty++;
           if(mode == 0){
           if(kaannetty == 3){
              if(klikattu == klikattu2){
                ruutuNappi[id[0]].setEnabled(false);
                ruutuNappi[id[1]].setEnabled(false);
                loppu++;
                tarkistaVoitto();
               }else{
                ruutuNappi[id[0]].setEnabled(true);
                ruutuNappi[id[0]].setText("");
                ruutuNappi[id[1]].setEnabled(true);
                ruutuNappi[id[1]].setText("");
             }
              kaannetty = 1;
           }
            if(kaannetty == 1){
              klikattu = laskuri[i];
              id[0] = i;
            }
          
          if(kaannetty == 2){
              klikattu2 = laskuri[i];
              id[1] = i;
          
          }
           }
        else if(mode == 2){
             if(kaannetty == 3){
              if(jono1.equals(jono2)){
                ruutuNappi[id[0]].setEnabled(false);
                ruutuNappi[id[1]].setEnabled(false);
                loppu++;
                tarkistaVoitto();
              }else{
                ruutuNappi[id[0]].setEnabled(true);
                ruutuNappi[id[0]].setText("");
                ruutuNappi[id[1]].setEnabled(true);
                ruutuNappi[id[1]].setText("");
              }
          kaannetty = 1;
          }
          
          if(kaannetty == 1){
            jono1 = kuvalista.get(i);
            id[0] = i;
          }
          if(kaannetty == 2){
              jono2 = kuvalista.get(i);
              id[1] = i;
          
          }
       }
         }
       }
    }
    

    private void tarkistaVoitto(){
       if(loppu == size/2){
        int lopetus = dlogi.uusiPeli();
         if(lopetus == 1){
          for(int i = 0; i < size; i++){
           ruutuNappi[i].setEnabled(true);
           ruutuNappi[i].setText("");
           loppu = 0;
          }
         }
       }
    }
}