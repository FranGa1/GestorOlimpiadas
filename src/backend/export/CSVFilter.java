package backend.export;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class CSVFilter extends FileFilter {
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        return f.isFile() && f.getName().toLowerCase().endsWith(".csv");
    }

    public String getDescription() {
        return "*.csv";
    }
}
