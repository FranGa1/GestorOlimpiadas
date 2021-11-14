package Tests;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Background {
    public static void main(String[] args) {
        JFrame f = new JFrame();

        Image img = null;
        try {
            img = ImageIO.read(new File("Files/fondoepico.jpg"));
        } catch (IOException e) {
            System.out.println("asdf");
        }

        JPanel panel = new BackgroundPanel(img);
        TransparentPanel panel1 = new TransparentPanel();
        panel1.setBackground(new Color(0,0,0,55));
        panel1.add(new JButton("HOlA"));
//        panel1.setBackground(null);
        panel.add(panel1);

        f.add(panel);



//        Icon img = null;
////        try {
//        img = new ImageIcon(new ImageIcon("Files/fondoepico.jpg").getImage().getScaledInstance(1920,1080,Image.SCALE_DEFAULT));
////            img = ImageIO.read(new File("Files/fondoepico.jpg"));
////        }
//
//
//        JLabel contentPane = new JLabel();
//        contentPane.setIcon( img );
//        contentPane.setLayout( new BorderLayout() );
//        f.setContentPane( contentPane );

        f.setVisible(true);
    }
}
