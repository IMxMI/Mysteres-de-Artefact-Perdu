package org.jeu;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI implements ActionListener
{
    private Jeu jeu;
    private JFrame fenetre;
    private JTextField entree;
    private JTextArea texte;
    private JLabel image;

    public GUI(Jeu j) {
        jeu = j;
        creerGUI();
    }

    public void afficher(String s) {
        texte.append(s);
        texte.setCaretPosition(texte.getDocument().getLength());
    }
    
    public void afficher() {
        afficher("\n");
    }

    public void afficheImage(String nomImage) {
        URL imageURL = this.getClass().getClassLoader().getResource("images/" + nomImage);

        if (imageURL != null) {
            ImageIcon icon = new ImageIcon(imageURL);
            Image imageRedimensionnee = icon.getImage().getScaledInstance(750, 750, java.awt.Image.SCALE_SMOOTH);
            image.setIcon(new ImageIcon(imageRedimensionnee));
            fenetre.pack();
        } else {
            System.err.println("Image non trouvée " + nomImage);
        }
    }

    public void enable(boolean ok) {
        entree.setEditable(ok);
        if ( ! ok )
            entree.getCaret().setBlinkRate(0);
    }

    private void creerGUI() {
        fenetre = new JFrame("Jeu");
        
        entree = new JTextField(34);

        texte = new JTextArea();
        texte.setEditable(false);
        JScrollPane listScroller = new JScrollPane(texte);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100,100));

        JPanel panel = new JPanel();
        image = new JLabel();

        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(entree, BorderLayout.SOUTH);

        fenetre.getContentPane().add(panel, BorderLayout.CENTER);
        
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        entree.addActionListener(this);

        fenetre.pack();
        fenetre.setVisible(true);
        entree.requestFocus();
    }

    public void actionPerformed(ActionEvent e) {
        executerCommande();
    }

    private void executerCommande() {
        String commandeLue = entree.getText();
        entree.setText("");
        jeu.traiterCommande( commandeLue);
    }
}