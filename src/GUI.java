import Datatype.Employee;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JFrame implements ActionListener{
    protected JFrame frame;
    protected JPanel panel;
    protected static JLabel fileSelected = new JLabel("no file selected");
    public static JTextArea output = new JTextArea(10,80);
    public static final void initialize(){
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
        GUI f1 = new GUI();

        // add action listener to the button to capture user
        // response on buttons
        button1.addActionListener(f1);
        button2.addActionListener(f1);

        // make a panel to add the buttons and labels
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // add buttons to the frame


        // set the label to its initial value


        JLabel FooterAbout = new JLabel("\n ");



        JLabel Header= new JLabel("Footer");
        Header.setFont(new Font("Arial", Font.PLAIN, 30));
        Border Headerborder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);

        Header.setBorder(Headerborder);
        Header.setPreferredSize(new Dimension(1024, 60));

        Header.setText("Averaging Agreement PDF Generator");
        Header.setHorizontalAlignment(JLabel.CENTER);
        Header.setVerticalAlignment(JLabel.CENTER);



        JLabel Footer= new JLabel("Footer");
        Border Footerborder = BorderFactory.createLineBorder(Color.BLACK);

        Footer.setBorder(Footerborder);
        Footer.setPreferredSize(new Dimension(1024, 40));

        Footer.setText("Developed By Derek Warne :: https://github.com/Zeedin/ACTEH-PDFGenerator");
        Footer.setHorizontalAlignment(JLabel.CENTER);
        Footer.setVerticalAlignment(JLabel.CENTER);


        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BorderLayout());
        eastPanel.add(button1, BorderLayout.SOUTH);
        eastPanel.add(button2, BorderLayout.NORTH);
        //eastPanel.add(fileSelected, BorderLayout.CENTER);

        // add panel to the frame

        panel.add(Header, BorderLayout.NORTH);




           // panel.add(output, BorderLayout.CENTER);
            JScrollPane scroll = new JScrollPane (output, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            panel.add(scroll , BorderLayout.CENTER);
            panel.add(eastPanel, BorderLayout.EAST);


        panel.add(Footer, BorderLayout.SOUTH);



        frame.add(panel);
        frame.show();
    }




    //Event Hander
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
                    Parser.loadFile(dataFile);
                }
                catch(Exception e) {


                }
            }
            // if the user cancelled the operation
            else
                fileSelected.setText("the user cancelled the operation");
        }


    }

    public static final void update(JPanel panel, Employee employee) {

    }
}
