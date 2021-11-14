package backend.export;


import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportCSV {

    private static JFrame PARENT_FRAME;

    public ExportCSV(JFrame parentFrame) {
        PARENT_FRAME = parentFrame;
    }

    public static void Export(List<List<String>> data){
        FileWriter csvWriter = null;
        try {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(PARENT_FRAME) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                // save to file
            }

            csvWriter = new FileWriter("prueba.csv");
            for (List<String> datos_fila : data) {
                csvWriter.append(String.join(",", datos_fila));
                csvWriter.append('\n');
            }

            csvWriter.close();

            JOptionPane.showMessageDialog(null,
                    "El archivo se exporto correctamente", "CSV Exportado",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo exportar");
        }

    }
}
