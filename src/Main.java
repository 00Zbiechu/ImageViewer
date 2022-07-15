import javax.swing.*;
import java.awt.*;


public class Main {

    public static void main(String args[]){

        Window window = new Window();
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

}

class Window extends JFrame{

    //Label do prezentowania wybranych plików
    private JLabel label;

    //Chooser do otwierania plików
    JFileChooser chooser;

    Window(){

        setTitle("ImageViewer");
        setResizable(false);
        intiGUI();

    }

    private void intiGUI(){

        setLayout(new BorderLayout());
        setSizeAndPosition();
        createMenu();
        createSelector();
        createLabel();

    }

    private void setSizeAndPosition(){

        setSize(new Dimension(300,400));
        setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getSize().getHeight()/4));

    }


    private void createMenu(){

        JMenuBar menuBar = new JMenuBar();
        add(menuBar,BorderLayout.NORTH);

        JMenu file = new JMenu("Plik");
        menuBar.add(file);

        JMenuItem menuItem = new JMenuItem("Wybierz");
        file.add(menuItem);

        //Zdarzenie dla menuItem'a
        menuItem.addActionListener(e -> {

            int options = chooser.showOpenDialog(null);

            if(options==JFileChooser.APPROVE_OPTION){

                String path = chooser.getSelectedFile().getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(path);

                //Transformacja rozmiarów zdjęcia
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(200, 300,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);  // transform it back

                //Ustawianie obrazu jako label
                label.setText("");
                label.setIcon(imageIcon);
            }
        });

        JMenuItem exit = new JMenuItem("Zakończ");
        file.add(exit);

        exit.addActionListener(e -> {
            System.exit(0);
        });

    }

    private void createLabel(){

        label = new JLabel("Wybierz plik...");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setMaximumSize(new Dimension(200,300));
        add(label,BorderLayout.CENTER);

    }


    private void createSelector(){

        chooser = new JFileChooser();

    }



}
