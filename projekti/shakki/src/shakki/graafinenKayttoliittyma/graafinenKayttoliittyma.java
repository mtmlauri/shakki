package shakki.graafinenKayttoliittyma;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

import shakki.Lauta;

public class graafinenKayttoliittyma implements Runnable {
    JFrame ikkuna;
    Container ruudukko;
    int lahtoX=-1;
    int lahtoY=-1;
    int kohdeX=-1;
    int kohdeY=-1;
    ArrayList<JButton> kaikkiNappulat = new ArrayList<JButton>();
    
    Lauta pelilauta = new Lauta();
    String nappulat = "kdtlrs SRLTDK";
    
    @Override
    public void run() {
        pelilauta.alustaLauta();
        
        ikkuna = new JFrame("Shakki");
        ikkuna.setPreferredSize(new Dimension(600, 600));
        
        luoValikko();
        luoAsettelu();
        
        ikkuna.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ikkuna.pack();
        ikkuna.setVisible(true);
    }
    
    private void luoValikko() {
        JMenuBar valikko = new JMenuBar();
        
        JMenu peli = new JMenu("Peli");
        valikko.add(peli);
        JMenu siirra = new JMenu("Siirrä");
        valikko.add(siirra);
        JMenu asema = new JMenu("Asema");
        valikko.add(asema);
        JMenu nayta = new JMenu("Näytä");
        valikko.add(nayta);
        
        JMenuItem uusi = new JMenuItem("Uusi peli");
        uusi.addActionListener(new valikonKuuntelija(this));
        peli.add(uusi);
        
        JMenuItem avaa = new JMenuItem("Avaa");
        avaa.addActionListener(new valikonKuuntelija(this));
        peli.add(avaa);
        
        JMenuItem tallenna = new JMenuItem("Tallenna");
        tallenna.addActionListener(new valikonKuuntelija(this));
        peli.add(tallenna);
        
        JMenuItem sulje = new JMenuItem("Sulje");
        sulje.addActionListener(new valikonKuuntelija(this));
        peli.add(sulje);
        
        JMenuItem kumoa = new JMenuItem("Kumoa");
        kumoa.addActionListener(new valikonKuuntelija(this));
        siirra.add(kumoa);
        
        JMenuItem asetaAsema = new JMenuItem("Aseta asema");
        asetaAsema.addActionListener(new valikonKuuntelija(this));
        asema.add(asetaAsema);
        
        JMenuItem vaihdaKatselusuunta = new JMenuItem("Vaihda katselusuunta");
        vaihdaKatselusuunta.addActionListener(new valikonKuuntelija(this));
        nayta.add(vaihdaKatselusuunta);
        
        ikkuna.setJMenuBar(valikko);
    }
    
    public void uusi() {
        String nimi = JOptionPane.showInputDialog("Mikä on nimesi? ");
        JOptionPane.showMessageDialog(ikkuna, "Tervetuloa pelaamaan, " + nimi);
        pelilauta.alustaLauta();
        tulostaLauta();
    }
    
    public void avaa() {
        JOptionPane.showMessageDialog(ikkuna, "Avaa peli -toimintoa ei tueta vielä.");
    }
    
    public void tallenna() {
        JOptionPane.showMessageDialog(ikkuna, "Tallenna peli -toimintoa ei tueta vielä.");
    }
    
    public void sulje() {
        ikkuna.dispose();
    }
    
    public void kumoa() {
        JOptionPane.showMessageDialog(ikkuna, "Toimintoa ei tueta vielä.");
    }
    
    public void asetaAsema() {
        JOptionPane.showMessageDialog(ikkuna, "Toimintoa ei tueta vielä.");
    }
    
    public void asetaKatselusuunta () {
        JOptionPane.showMessageDialog(ikkuna, "Toimintoa ei tueta vielä.");
    }
    
    private Container luoRuudukko() {
        String rivit = "012345678";
        String sarakkeet = " ABCDEFGH";
        
        ruudukko = new Container();
        ruudukko.setLayout(new GridLayout(9,9));
         for (int j = 7; j >= 0; j--) {
            JLabel rivi = new JLabel(""+rivit.charAt(j+1));
            rivi.setAlignmentX(Component.CENTER_ALIGNMENT);
            rivi.setAlignmentY(Component.CENTER_ALIGNMENT);
            ruudukko.add(rivi);
            for (int i = 0; i < 8; i++) {
                JButton nappula = new JButton("" + nappulat.charAt(pelilauta.getLauta()[i][j] + 6));
                kaikkiNappulat.add(nappula);
                nappula.addActionListener(new nappulanKuuntelija(this));
                nappula.setAlignmentX(Component.CENTER_ALIGNMENT);
                if ((i + j) % 2 == 0) {
                    nappula.setBackground(Color.GRAY);
                }
                else {
                    nappula.setBackground(Color.WHITE);
                }
                nappula.setOpaque(true);
                nappula.setBorderPainted(true);
                ruudukko.add(nappula);
            }
        }
        for (int i = 0; i < 9; i++) {
            JLabel sarake = new JLabel(""+ sarakkeet.charAt(i));
            sarake.setAlignmentX(Component.CENTER_ALIGNMENT);
            sarake.setAlignmentY(Component.CENTER_ALIGNMENT);
            ruudukko.add(sarake);
        }
        return ruudukko;
    }
    
    
    private Container luoShakkilauta() {
        Container shakkilauta = new Container();
        shakkilauta.setLayout(new BoxLayout(shakkilauta, BoxLayout.Y_AXIS));
        
        JLabel ylateksti = new JLabel("Musta");
        ylateksti.setAlignmentX(Component.CENTER_ALIGNMENT);
        shakkilauta.add(ylateksti);
        
        shakkilauta.add(luoRuudukko());
        
        JLabel alateksti = new JLabel("Valkea");
        shakkilauta.add(alateksti);
        
        return shakkilauta;
    }
    
    private void luoAsettelu() {
        Container asettelu = ikkuna.getContentPane();
        asettelu.setLayout(new BoxLayout(asettelu, BoxLayout.Y_AXIS));
        
        Container vasenJaOikea = new Container();
        vasenJaOikea.setLayout(new BoxLayout(vasenJaOikea, BoxLayout.X_AXIS));
        

        vasenJaOikea.add(luoShakkilauta());
        
        JLabel siirrot = new JLabel("Siirtotilasto");
        siirrot.setAlignmentX(Component.CENTER_ALIGNMENT);
        siirrot.setAlignmentY(Component.TOP_ALIGNMENT);
        vasenJaOikea.add(siirrot);
        
        asettelu.add(vasenJaOikea);
        
        JLabel virheet = new JLabel("VIRHETIEDOT: ");
        siirrot.setAlignmentX(Component.LEFT_ALIGNMENT);
        asettelu.add(virheet);
        
        
        // Nappulan korotuksen yhdeydessä pitää kysyä vielä, miten nappula korotetaan.
        // Ehkäpä se toimisi jossain ponnahdusikkunassa
    }
    
    public void tulostaLauta() {
        for (JButton nappula : kaikkiNappulat) {
            nappula.setText(nappulat);
        }
        int k = 0;
        for (int j = 7; j >= 0; j--) {
            for (int i = 0; i < 8; i++) {
                kaikkiNappulat.get(k).setText("" + nappulat.charAt(pelilauta.getLauta()[i][j] + 6));
                k++;
            }
        }
    }
    
    public void siirra() {
        pelilauta.siirra(lahtoX, lahtoY, kohdeX, kohdeY);
        tulostaLauta();
    }
}
