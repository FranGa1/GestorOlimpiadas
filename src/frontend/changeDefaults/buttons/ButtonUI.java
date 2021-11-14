package frontend.changeDefaults.buttons;

import javax.swing.*;
import java.awt.*;

public class ButtonUI extends JButton {

    public ButtonUI(String text){
        super(text);
        this.setUI(new StyledButtonUI());
        this.setFont(new Font("Calibri", Font.BOLD, 22));
        this.setBackground(new Color(0xE30D28));
        this.setForeground(Color.white);
        //0x4C9ED9
    }

    public ButtonUI(String text, int plusWidth, int plusHeight){
        super(text);
        this.setUI(new StyledButtonUI(plusWidth, plusHeight));
        this.setFont(new Font("Calibri", Font.BOLD, 22));
        this.setBackground(new Color(0xE30D28));
        this.setForeground(Color.white);
        //0x4C9ED9
    }

    public ButtonUI(String text, ImageIcon img){
        super(text, img);
        this.setUI(new StyledButtonUI());
        this.setFont(new Font("Calibri", Font.BOLD, 22));
        this.setBackground(new Color(0xE30D28));
        this.setForeground(Color.white);
        //0x4C9ED9
    }

    public ButtonUI(String text, ImageIcon img, int plusWidth, int plusHeight){
        super(text, img);
        this.setUI(new StyledButtonUI(plusWidth, plusHeight));
        this.setFont(new Font("Calibri", Font.BOLD, 22));
        this.setBackground(new Color(0xE30D28));
        this.setForeground(Color.white);
        //0x4C9ED9
    }

//    public ButtonUI(ImageIcon img){
//        super(img);
//        this.setUI(new StyledButtonUI());
//        this.setFont(new Font("Calibri", Font.BOLD, 22));
//        this.setBackground(new Color(0xE30D28));
//        this.setForeground(Color.white);
        //0x4C9ED9
//    }
}

