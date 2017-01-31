/* Käyttöliittymäohjelmointi 2014
 * Tuukka Rantahalvari, Juha Repo
 *
 */
package muistipeli;
import java.awt.*;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;



public class Muistipeli extends JPanel implements ActionListener {
    private int vaikeus = 10;
    private int pelimode = 10;
    private int koko = 10;
    final dialogi dialogi = new dialogi();
   
    
   
    //tekee pääikkunan valikot ja napit
    private Container teelaatikko(String nimi, float paikka, String napit[]){
      JPanel container = new JPanel();
      container.setBorder(BorderFactory.createTitledBorder(nimi));
      BoxLayout layout = new BoxLayout(container, BoxLayout.Y_AXIS);
      container.setLayout(layout);
    
      //tee napit && kuuntelijat
      for (int i = 0, n = 3; i < n; i++){
        JButton nappi1 = new JButton(napit[i]);
        nappi1.setVerticalTextPosition(AbstractButton.CENTER);
        nappi1.setHorizontalTextPosition(AbstractButton.LEADING);
        nappi1.setAlignmentX(Component.CENTER_ALIGNMENT);
        nappi1.addActionListener(this);
        container.add(nappi1);
      }
     
     return container;
    }
   
    //kuunnellaan ja reagoidaan nappien painalluksiin
     @Override
     public void actionPerformed(ActionEvent e){
     String action = e.getActionCommand();
        switch (action) {
            case "helppo":
                vaikeus = 0;
                break;
            case "keskivaikea":
                vaikeus = 1;
                break;
            case "vaikea":
                vaikeus = 2;
                break;
            case "matikka":
                pelimode = 0;
                break;
            case "koodi":
                pelimode = 1;
                break;
            case "kuva":
                pelimode = 2;
                break;
            case "pieni":
                koko = 4;
                break;
            case "keskikoko":
                koko = 6;
                break;
            case "suuri":
                koko = 8;
                break;
            case "Uusi peli":
                if(koko < 9 && pelimode < 3 && vaikeus < 3){
                teePeli uusiPeli =  new teePeli(koko, vaikeus, pelimode);
               }
                break;
            case "Lopeta peli":
                dialogi.lopetus();
                break;
               }
        }
    
     //luodaan käyttöliittymä (aloitusikkuna)
    private void createGUI(){
    
    JMenuBar palkki = new JMenuBar(); //luodaan menupalkki
    JMenu valikko = new JMenu("Options"); //luodaan options-valikko
    JMenuItem helppi = new JMenuItem(new AbstractAction("?"){
     @Override
        public void actionPerformed(ActionEvent ae) {
           dialogi.apua();
        }
    });
    palkki.add(valikko);
    palkki.add(helppi);
    
    
    JMenuItem uusi = new JMenuItem("Uusi peli");
    JMenuItem lopeta = new JMenuItem("Lopeta peli");
    valikko.add(uusi);
    valikko.add(lopeta);
    uusi.addActionListener(this);
    lopeta.addActionListener(this);
    
    
    JFrame frame = new JFrame("Muistipeli");
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setJMenuBar(palkki);
     
       String napit1[] = {"matikka", "koodi", "kuva"};
       String napit2[] = {"helppo", "keskivaikea", "vaikea"};
       String napit3[] = {"pieni", "keskikoko", "suuri"};

     Container contentPane = frame.getContentPane();
     Container paneeli1 = teelaatikko("tyyppi", Component.LEFT_ALIGNMENT, napit1);
     Container paneeli2 = teelaatikko("vaikeustaso", Component.RIGHT_ALIGNMENT, napit2);
     Container paneeli3 = teelaatikko("koko", Component.RIGHT_ALIGNMENT, napit3);
     
     JButton aloita;
        aloita = new JButton(new AbstractAction("Aloita"){
            @Override
            public void actionPerformed(ActionEvent e){
                if(koko < 9 && pelimode < 3 && vaikeus < 3){
                teePeli peli = new teePeli(koko, vaikeus, pelimode);
                }
            }
        });
    
     contentPane.setLayout(new GridBagLayout());
     GridBagConstraints rajoite = new GridBagConstraints();
     rajoite.weightx = 0.0;
     rajoite.gridx = 0; //vasen reuna
     JTextArea alue = new JTextArea();
     alue.setText("Tämä on \n muistipeli");
     alue.setEditable(false);
     alue.setSize(paneeli1.getSize());
     contentPane.add(alue, rajoite);
     
     rajoite.gridx = 1;
     rajoite.gridy = 0; //ylin rivi
     rajoite.fill = GridBagConstraints.HORIZONTAL;
     contentPane.add(paneeli1, rajoite);
     
     rajoite.gridx = 0;
     rajoite.gridy = 1;
     rajoite.fill = GridBagConstraints.HORIZONTAL;
     contentPane.add(paneeli2, rajoite);
     
     rajoite.gridx = 1;
     rajoite.gridy = 1;
     rajoite.fill = GridBagConstraints.HORIZONTAL;
     contentPane.add(paneeli3, rajoite);
     
     rajoite.weighty = 1.0;
     rajoite.gridy = 2;
     rajoite.gridx = 0;
     rajoite.fill = GridBagConstraints.BOTH;
     rajoite.gridwidth = 2;
     contentPane.add(aloita, rajoite);
     aloita.addActionListener(this);
     
     frame.pack();
     frame.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         EventQueue.invokeLater(new Runnable() {
             @Override 
            public void run(){
            new Muistipeli().createGUI();
            
            }
            
        });
    }
    
}
