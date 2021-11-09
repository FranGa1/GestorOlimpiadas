package frontend.panels.menu;

import javax.swing.*;

public class CreateMenuDesconectado extends CreateMenu{

    public static JPanel create(){
        CreateMenu.setURL("Files/desconectado2.png");
        return CreateMenu.create();
    }

}
