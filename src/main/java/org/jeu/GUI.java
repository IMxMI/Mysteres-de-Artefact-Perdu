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

/**
 * Classe responsable de l'interface graphique utilisateur (GUI) du jeu.
 * <p>
 * Affiche les messages du jeu, permet la saisie de commandes et affiche les images associées aux zones ou objets.
 * </p>
 */
public class GUI implements ActionListener {

    /** Référence vers la logique principale du jeu */
    private Jeu jeu;

    /** Fenêtre principale de l'interface */
    private JFrame fenetre;

    /** Champ de saisie pour les commandes utilisateur */
    private JTextField entree;

    /** Zone de texte pour l'affichage des messages du jeu */
    private JTextArea texte;

    /** Label utilisé pour afficher les images liées au jeu */
    private JLabel image;

    /**
     * Construit la GUI et l'initialise pour le jeu donné.
     * @param j Instance du jeu à associer à l'interface graphique
     */
    public GUI(Jeu j) {
        jeu = j;
        creerGUI();
    }

    /**
     * Affiche une chaîne de caractères dans la zone de texte.
     * Place également le curseur à la fin.
     * @param s Le texte à afficher
     */
    public void afficher(String s) {
        texte.append(s);
        texte.setCaretPosition(texte.getDocument().getLength());
    }

    /**
     * Affiche un saut de ligne dans la zone de texte.
     */
    public void afficher() {
        afficher("\n");
    }

    /**
     * Affiche une image à partir du nom de fichier spécifié (dans le dossier "images").
     * Redimensionne l'image pour l'adapter à l'interface.
     * @param nomImage Le nom du fichier image à afficher
     */
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

    /**
     * Active ou désactive la possibilité de saisir du texte dans le champ de commande.
     * @param ok {@code true} pour activer la saisie, {@code false} pour la désactiver
     */
    public void enable(boolean ok) {
        entree.setEditable(ok);
        if (!ok)
            entree.getCaret().setBlinkRate(0);
    }

    /**
     * Crée et organise tous les composants graphiques de la fenêtre.
     */
    private void creerGUI() {
        fenetre = new JFrame("Jeu");

        entree = new JTextField(34);

        texte = new JTextArea();
        texte.setEditable(false);
        JScrollPane listScroller = new JScrollPane(texte);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100, 100));

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

    /**
     * Gère les actions déclenchées par l'utilisateur (ex : appui sur Entrée dans le champ de saisie).
     * @param e L'événement d'action reçu
     */
    public void actionPerformed(ActionEvent e) {
        executerCommande();
    }

    /**
     * Lit la commande saisie par l'utilisateur, l'efface du champ de saisie,
     * et transmet la commande à la logique du jeu.
     */
    private void executerCommande() {
        String commandeLue = entree.getText();
        entree.setText("");
        jeu.traiterCommande(commandeLue);
    }
}
