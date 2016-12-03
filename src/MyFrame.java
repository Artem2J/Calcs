import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 2jart on 01.12.2016.
 */
public class MyFrame extends JFrame{
    List<String> fileNames = new ArrayList<String>();
    public MyFrame(){
        setBounds(0,0,500,500);
        JFileChooser dialog = new JFileChooser();
        dialog.showOpenDialog(this);
        File[] files = dialog.getSelectedFiles();
        for (File file : files) fileNames.add(file.getAbsolutePath());
        setVisible(false);
        this.dispose();
    }

}