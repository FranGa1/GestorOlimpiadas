import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    JPanel panel1;
    JPanel panel2;
    JButton b1;
    JButton b2;

    Main(){
        super("Panels");
        setSize(800,500);
//        panel1 = new JPanel();
//        panel1.setBounds(0,0,200,200);
//        panel1.setBounds(0,0,200,200);
//        panel1.setBackground(Color.red);
        b1 = new JButton("GO TO PANEL 2");
//        b1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                panel1.setVisible(false);
//                panel2.setVisible(true);
//            }
//        });
//        panel1.add(b1);
//
//        panel2 = new JPanel();
//        panel2.setVisible(false);
//        panel2.setBackground(Color.green);
        b2 = new JButton("GO TO PANEL 1");
//        b2.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                panel2.setVisible(false);
//                panel1.setVisible(true);
//            }
//        });
//        panel2.add(b2);
//
//
//        add(panel1);
//        add(panel2);
//

        JButton b3  = new JButton("b3");
        JButton b4 = new JButton("b4");
        JButton b5 = new JButton("B5");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,3,100,100));
        panel.setBackground(Color.red);
        panel.setBounds(0,0,800,500);
        JButton button = new JButton("button");
        button.setLayout(null);
        panel.add(button);
        panel.add(b1);
        panel.add(b2);
        panel.add(b4);
        panel.add(b5);
        panel.add(b3);

//        setLayout(null);
        add(panel);


        setVisible(true);
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}
