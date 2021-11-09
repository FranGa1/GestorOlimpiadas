package Tests;


import frontend.changeDefaults.TextFieldUI;
import frontend.changeDefaults.WPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TestDesplegables extends JFrame {

    public TestDesplegables(){
        JPanel panel = new WPanel();
        JTextField textField = new TextFieldUI(30);

        String[] optionsToChoose = {"", "Argentina", "alemania", "rusia", "viernam", "republica democratica del congo"};
        String[] opt2 = {"", "Red", "Blue", "Yellow", "Purple", "Pink"};
        JComboBox<String> comboBox = new JComboBox<>(optionsToChoose);
        //comboBox.SelectedIndex=-1;
        comboBox.setPreferredSize(new Dimension(300,20));
        comboBox.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        comboBox.setBackground(new Color(0xBAE6F3));

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                System.out.println("Se presiono");
                comboBox.setModel(new DefaultComboBoxModel<>(opt2));
            }
        });
//        for (int i = 0; i < comboBox.getComponentCount(); i++)
//        {
//            if (comboBox.getComponent(i) instanceof JComponent) {
//                ((JComponent) comboBox.getComponent(i)).setBorder(new EmptyBorder(0,0,0,0));
//                //((JComponent) comboBox.getComponent(i)).setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
//            }
//
//
//            if (comboBox.getComponent(i) instanceof AbstractButton) {
//                ((AbstractButton) comboBox.getComponent(i)).setBorderPainted(false);
//                ((AbstractButton) comboBox.getComponent(i)).setBorder(new EmptyBorder(1,1,1,1));
//            }
//        }

        panel.add(textField);
        panel.add(comboBox);
        panel.setBackground(Color.RED);
        this.add(panel);
        this.setSize(400,400);
    }
    public static void main(String[] args)
    {

        // Creating Object of CardLayoutDemo class.
        TestDesplegables cl = new TestDesplegables();

        // Function to set default operation of JFrame.
        cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Function to set visibility of JFrame.
        cl.setVisible(true);
    }
}
