
// Java program to create open or
// save dialog using JFileChooser

import javax.swing.*;

class PDFGenerator extends GUI {

    // Jlabel to show the files user selects
    static JLabel fileSelected;
    static String dataFile;

    // a default constructor
    PDFGenerator()
    {
    }

    public static void main(String args[])
    {
        GUI.initialize();
        /**
        System.out.println("Loading...");
        // frame to contains GUI elements
        JFrame frame = new JFrame("file chooser");

        // set the size of the frame
        frame.setSize(1024, 720);

        // set the frame's visibility
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // button to open save dialog
        JButton button1 = new JButton("save");

        // button to open open dialog
        JButton button2 = new JButton("open");

        // make an object of the class filechooser
        PDFGenerator f1 = new PDFGenerator();

        // add action listener to the button to capture user
        // response on buttons
        button1.addActionListener(f1);
        button2.addActionListener(f1);

        // make a panel to add the buttons and labels
        JPanel panel = new JPanel();

        // add buttons to the frame
        panel.add(button1);
        panel.add(button2);

        // set the label to its initial value
        fileSelected = new JLabel("no file selected");

        // add panel to the frame
        panel.add(fileSelected);
        frame.add(panel);

        frame.show();
         **/
    }

    //Event Hander
    /**
    public void actionPerformed(ActionEvent evt)
    {
        // if the user presses the save button show the save dialog
        String com = evt.getActionCommand();

        if (com.equals("save")) {
            // create an object of JFileChooser class
            JFileChooser selected = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // invoke the showsSaveDialog function to show the save dialog
            int r = selected.showSaveDialog(null);

            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION)

            {
                // set the label to the path of the selected file
                fileSelected.setText(selected.getSelectedFile().getAbsolutePath());




            }
            // if the user cancelled the operation
            else
                fileSelected.setText("the user cancelled the operation");
        }

        // if the user presses the open dialog show the open dialog
        else {
            // create an object of JFileChooser class
            JFileChooser selected = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // invoke the showsOpenDialog function to show the save dialog
            int r = selected.showOpenDialog(null);

            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION)

            {
                // set the label to the path of the selected file
                fileSelected.setText(selected.getSelectedFile().getAbsolutePath());

                System.out.println("data Loaded!");
                String dataFile = selected.getSelectedFile().getAbsolutePath().toString();

                try{
                    loadFile(dataFile);
                }
                catch(Exception e) {


                }
            }
            // if the user cancelled the operation
            else
                fileSelected.setText("the user cancelled the operation");
        }


    }**/




}
