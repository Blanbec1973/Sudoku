package view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileDialogUtils {

    private FileDialogUtils() throws InstantiationException {
        throw new InstantiationException("Utility class : "+this.getClass().getName());
    }

    public static String afficheSaveFileDialog(MyView view, String typeDialog) {
        String fileName="";
        int returnVal = 0;
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "*.sud", "sud");
        chooser.setFileFilter(filter);

        File defaultDir = new File(view.pathName);
        chooser.setCurrentDirectory(defaultDir);

        if (typeDialog.equals("Save")) {
            returnVal = chooser.showSaveDialog(view.getFenetre());}
        if (typeDialog.equals("Open")) {
            returnVal = chooser.showOpenDialog(view.getFenetre());}

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            if (chooser.getSelectedFile().getName().contains("."))
                fileName = chooser.getSelectedFile().getAbsolutePath();
            else
                fileName = chooser.getSelectedFile().getAbsolutePath()+".sud";
        }
        return fileName;
    }
}
