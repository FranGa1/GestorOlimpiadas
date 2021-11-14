package backend.export;


import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class ExportCSV {

    private static JFrame PARENT_FRAME;
    private static final Pattern PATTERN = Pattern.compile("(.csv$)");

    public ExportCSV(JFrame parentFrame) {
        PARENT_FRAME = parentFrame;
    }

    public static void Export(List<List<String>> data){
        try {
            //Abrimos un JFileChooser para que el usuario elija donde guardar el archivo
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new CSVFilter());
            if (fileChooser.showSaveDialog(PARENT_FRAME) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String path = file.getPath();

                //Si el path no termina en .csv agregamos la extension
                if (!PATTERN.matcher(path).find()) path += ".csv";

                //Escribimos el archivo
                FileWriter csvWriter = new FileWriter(path);
                for (List<String> datos_fila : data) {
                    csvWriter.append(String.join(",", datos_fila));
                    csvWriter.append('\n');
                }
                csvWriter.close();
                JOptionPane.showMessageDialog(null,
                        "El archivo se exporto correctamente", "CSV Exportado",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo exportar");
        }

    }
}
