package main;

import Datatype.Employee;
import com.itextpdf.text.DocumentException;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;


public class GUI extends JFrame implements ActionListener{
    protected JFrame frame;
    protected JPanel panel;
    public String dateSlected = "";


    public static JDatePickerImpl dateEndPicker;

    public static JDatePickerImpl dateStartPicker;
    protected static JLabel fileSelected = new JLabel("no file selected");
    public static JTextArea output = new JTextArea(10,60);
    public static final void initialize(){
        System.out.println("Loading...");
        // frame to contains GUI elements
        JFrame frame = new JFrame("Averaging Agreement Generator");

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


        //JLabel FooterAbout = new JLabel("\n ");



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



        JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));

            JLabel StartDate = new JLabel("Schedule Start Date");
            infoPanel.add(StartDate);


            UtilDateModel model = new UtilDateModel();

            Properties p = new Properties();
            p.put("text.today", "Today");
            p.put("text.month", "Month");
            p.put("text.year", "Year");
            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            dateStartPicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
            infoPanel.add(dateStartPicker);

            JLabel EndDate = new JLabel("Schedule End Date");
            infoPanel.add(EndDate);


            UtilDateModel modelEnd = new UtilDateModel();

            Properties pEnd = new Properties();
            p.put("text.today", "Today");
            p.put("text.month", "Month");
            p.put("text.year", "Year");
            JDatePanelImpl dateEndPanel = new JDatePanelImpl(modelEnd, pEnd);
            dateEndPicker = new JDatePickerImpl(dateEndPanel, new DateLabelFormatter());
            infoPanel.add(dateEndPicker);



        //System.out.println("DATE SELECTED:: " + dateStartPicker.getJFormattedTextField().getText());



        JPanel eastPanel = new JPanel();





        eastPanel.setLayout(new BorderLayout());
        eastPanel.add(button1, BorderLayout.SOUTH);
        eastPanel.add(infoPanel, BorderLayout.CENTER);
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
            selected.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            selected.setAcceptAllFileFilterUsed(false);


            // invoke the showsSaveDialog function to show the save dialog
            int r = selected.showSaveDialog(null);

            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION){
                String exportLocation = selected.getSelectedFile().getAbsolutePath().toString();
                System.out.println(exportLocation);

                try {
                    System.out.println("DATE Start:: " + dateStartPicker.getJFormattedTextField().getText());
                    System.out.println("DATE End:: " + dateEndPicker.getJFormattedTextField().getText());
                    Editor.PDF.printAll(Parser.employees, exportLocation);

                } catch (DocumentException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


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

    public static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }
    public static final void update(JPanel panel, Employee employee) {

    }
}
