//package backend;
//
//import javax.swing.*;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//
//public class ExportCSV {
//
//    public ExportCSV(List<List<String>> data){
//        FileWriter csvWriter = null;
//        try {
////            JFileChooser fileChooser = new JFileChooser();
////            if (fileChooser.showSaveDialog(modalToComponent) == JFileChooser.APPROVE_OPTION) {
////                File file = fileChooser.getSelectedFile();
////                // save to file
////            }
//            csvWriter = new FileWriter("/home/claudia/Documentos/salida.csv");
//            csvWriter.append("Nombre");
//            csvWriter.append(",");
//            csvWriter.append("Edad");
//            csvWriter.append(",");
//            csvWriter.append("Materia");
//            csvWriter.append('\n');
//            for (List<String> datos_fila : filas) {
//                csvWriter.append(String.join(",", datos_fila));
//                csvWriter.append('\n');
//            }
//            csvWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("No se pudo exportar");
//        }
//
//    }
//}
