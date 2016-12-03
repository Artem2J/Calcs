import javax.swing.*;
import java.io.File;

import static com.sun.glass.ui.Cursor.setVisible;

/**
 * Created by 2jart on 01.12.2016.
 */
public class MyFrame extends JFrame{
    String fileName = "";
    public MyFrame(){
        setBounds(0,0,500,500);
        JFileChooser dialog = new JFileChooser();
        dialog.showOpenDialog(this);
        File file = dialog.getSelectedFile();
        fileName = file.getAbsolutePath();
        setVisible(false);
        this.dispose();
    }

}