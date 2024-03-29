
package View;

import javax.swing.*;
import java.awt.*;

public class ViewEnd extends Views {

    public ViewEnd()
    {
        super("Fin de partie");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(false);
        getContentPane().setBackground(Color.WHITE);
    }
    public void end(String nomJoueur, int score, int[] tabScore, int[] tabTenta)
    {
        JPanel mainPnl = new JPanel();
        mainPnl.setLayout(new BoxLayout(mainPnl, BoxLayout.Y_AXIS));
        mainPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPnl.setAlignmentY(Component.CENTER_ALIGNMENT);

        JLabel lblTitre = new JLabel();
        lblTitre.setForeground(Color.WHITE);
        mainPnl.setBackground(new Color(29, 53, 87));
        lblTitre.setText("Fin de partie - " + nomJoueur);
        lblTitre.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font customFont = loadCustomFont(Font.TRUETYPE_FONT, 40);
        lblTitre.setFont(customFont);

        mainPnl.add(lblTitre);


        JPanel pnlScore = new JPanel(new FlowLayout());
        pnlScore.setBackground(Color.WHITE);

        JLabel lblScoreTitre = new JLabel();
        lblScoreTitre.setText("Score total");
        lblScoreTitre.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font customFont2 = loadCustomFont(Font.TRUETYPE_FONT, 30);
        lblScoreTitre.setFont(customFont2);

        JLabel lblScore = new JLabel();
        lblScore.setText(String.valueOf(score));
        lblScore.setFont(new Font("Arial", Font.BOLD, 30));

        pnlScore.add(lblScoreTitre);
        pnlScore.add(lblScore);



        mainPnl.add(pnlScore);

        JLabel lblScoreChaqueManche = new JLabel();
        lblScoreChaqueManche.setText("Score de chaque manche");
        lblScoreChaqueManche.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblScoreChaqueManche.setFont(customFont2);
        lblScoreChaqueManche.setForeground(Color.WHITE);
        mainPnl.add(lblScoreChaqueManche);

        JPanel diagBarres = new JPanel(new FlowLayout());
        diagBarres.setBackground(Color.WHITE);

        for (int j : tabScore) {
            JPanel pnlBarre = new JPanel(new BorderLayout());
            pnlBarre.setBackground(Color.WHITE);
            JProgressBar barre = new JProgressBar(JProgressBar.VERTICAL, 0, 100); // Orientation verticale et limites min et max
            barre.setForeground(new Color(29, 53, 87));
            barre.setBackground(Color.WHITE);
            barre.setBorderPainted(false);
            barre.setPreferredSize(new Dimension(80, 150)); // Définit la largeur et la hauteur de chaque barre
            barre.setValue(0); // Valeur de départ à zéro

            JLabel lblBarre = new JLabel(String.valueOf(j));
            lblBarre.setFont(new Font("Arial", Font.BOLD, 20));
            lblBarre.setForeground(new Color(29, 53, 87));
            lblBarre.setHorizontalAlignment(SwingConstants.CENTER);

            pnlBarre.add(barre, BorderLayout.CENTER);
            pnlBarre.add(lblBarre, BorderLayout.PAGE_END);
            diagBarres.add(pnlBarre);

            animateBarVertical(barre, j); // Anime chaque barre avec la valeur correspondante dans tabScore
        }

        mainPnl.add(diagBarres);

        JLabel lblNbTenta = new JLabel();
        lblNbTenta.setText("Nombre de tentatives pour chaque manche");
        lblNbTenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNbTenta.setFont(customFont2);
        lblNbTenta.setForeground(Color.WHITE);
        mainPnl.add(lblNbTenta);


        JPanel diagBarres2 = new JPanel(new FlowLayout());
        //diagBarres2.setBackground(Color.WHITE);

        for (int i = 0; i < tabScore.length; i++) {
            JPanel pnlBarre = new JPanel(new BorderLayout());
            pnlBarre.setBackground(Color.WHITE);
            JProgressBar barre = new JProgressBar(JProgressBar.VERTICAL, 0, 10); // Orientation verticale et limites min et max
            barre.setForeground(new Color(29, 53, 87));
            barre.setBorderPainted(false);
            //barre.setBackground(Color.WHITE);
            barre.setPreferredSize(new Dimension(80, 150)); // Définit la largeur et la hauteur de chaque barre
            barre.setValue(0); // Valeur de départ à zéro

            JLabel lblBarre = new JLabel(String.valueOf(tabTenta[i]));
            lblBarre.setFont(new Font("Arial", Font.BOLD, 20));
            lblBarre.setHorizontalAlignment(SwingConstants.CENTER);
            lblBarre.setForeground(new Color(29, 53, 87));
            lblBarre.setBackground(this.getBackground());

            pnlBarre.add(barre, BorderLayout.CENTER);
            pnlBarre.add(lblBarre, BorderLayout.PAGE_END);
            diagBarres2.add(pnlBarre);

            animateBarVertical(barre, tabTenta[i]); // Anime chaque barre avec la valeur correspondante dans tabScore
        }

        mainPnl.add(diagBarres2);

        this.add(mainPnl);


        /*JButton rejouerButton = new JButton("Rejouer");
        rejouerButton.setFont(customFont2);
        rejouerButton.setBackground(Color.BLUE);
        rejouerButton.setForeground(Color.WHITE);
        rejouerButton.addActionListener( actionEvent  -> {
            try {
                //faire rejouer ici
            } catch (Exception e) {
            }

        });
        mainPnl.add(rejouerButton);*/
        setVisible(true);

    }

    private void animateBarVertical(JProgressBar barre, int valeurFinale)
    {
        int delay = 50; // Délai entre les mises à jour de la barre (en millisecondes)
        int step = 1; // Pas d'incrément pour la progression

        Timer timer = new Timer(delay, e -> {
            int currentValue = barre.getValue();
            if (currentValue < valeurFinale) {
                barre.setValue(currentValue + step);
            } else {
                ((Timer) e.getSource()).stop(); // Arrête l'animation une fois la valeur finale atteinte
            }
        });

        timer.start();
    }
}
