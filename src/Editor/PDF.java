/* requires itextpdf-5.1.2.jar or similar */
package Editor;

import Datatype.Employee;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import main.GUI;
import org.jdatepicker.impl.JDatePickerImpl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public class PDF {

    private JDatePickerImpl datePicker;

    public static void printAll(HashMap<Integer, Employee> employees, String exportLocation) throws DocumentException, IOException, ParseException {

        for (Employee employee : employees.values()) {

            if (employee.GenerateAgrement()) {
                create(employee, exportLocation);
            }
        }

    }
    public static void create(Employee employee, String exportLocation) throws IOException, DocumentException, ParseException {

        /* example inspired from "iText in action" (2006), chapter 2 */

        PdfReader reader = new PdfReader(exportLocation+"/template.pdf"); // input PDF
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream(exportLocation+"/AVG-"+ employee.firstNameGET() + "-" + employee.lastNameGET()+"("+GUI.dateStartPicker.getJFormattedTextField().getText()+").pdf")); // output PDF
        BaseFont bf = BaseFont.createFont( BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font

        //loop on pages (1-based)
        for (int i=1; i<=reader.getNumberOfPages(); i++){

            // get object for writing over the existing content;
            // you can also use getUnderContent for writing in the bottom layer
            PdfContentByte over = stamper.getOverContent(i);

            // write Name Title
            rubberStamp(over,employee.firstNameGET() + " " + employee.lastNameGET() + " agree", 320, 666 );

            //Date BS
                SimpleDateFormat dateText  = new SimpleDateFormat("MMMM, dd, yyyy");
                SimpleDateFormat inputDate  = new SimpleDateFormat("yyyy-MM-dd");


                Date dateStartTime = inputDate.parse(GUI.dateStartPicker.getJFormattedTextField().getText());
                Date dateEndTime = inputDate.parse(GUI.dateEndPicker.getJFormattedTextField().getText());

                String startOfSched = dateText.format(dateStartTime);
                String endOfSched = dateText.format(dateEndTime);



            // Number of Weeks for time

            rubberStamp(over, startOfSched, 410, 596 );

            // Start date of the agreement
            rubberStamp(over, startOfSched, 270, 583 );

            // End date of the agreement
            rubberStamp(over, endOfSched, 270, 571 );



            // The Not Fun table :(
            scheduleTable(employee, over);




            //Sig Feilds
            rubberStamp(over,employee.firstNameGET() + " " + employee.lastNameGET(), 95, 130);

            rubberStamp(over,employee.superVisGET(), 345, 130);


            // draw a red circle
            over.setRGBColorStroke(0xFF, 0x00, 0x00);
            over.setLineWidth(5f);
            over.ellipse(250, 450, 350, 550);
            over.stroke();
        }

        stamper.close();

    }

    public static void  rubberStamp(PdfContentByte over, String textToStamp, int xPOS, int yPOS) throws DocumentException, IOException {

        BaseFont bf = BaseFont.createFont( BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font
        over.beginText();
        over.setFontAndSize(bf, 10);    // set font and size
        over.setTextMatrix(xPOS, yPOS);   // set x,y position (0,0 is at the bottom left)
        over.showText(textToStamp);  // set text
        over.endText();
    }

    public static void scheduleTable(Employee employee, PdfContentByte over) throws DocumentException, IOException {


        //Date BS
        SimpleDateFormat hourFormat  = new SimpleDateFormat("hh:mm a");


        for(int i=0 ; i<employee.scheduleGET().getDaysWorked().length ; i++){
            for(int j=0 ; j<employee.scheduleGET().getDaysWorked()[i].length ; j++){


                //Its a bit messy calling wize
                if (employee.scheduleGET().getDaysWorked()[i][j][0] != 0) {
                    java.util.Date Start = new java.util.Date(employee.scheduleGET().getDaysWorked()[i][j][1]);
                    java.util.Date End = new java.util.Date(employee.scheduleGET().getDaysWorked()[i][j][2]);
                    double hours = employee.scheduleGET().getDaysWorked()[i][j][0]/10;


                    String startOfSched = hourFormat.format(Start);
                    //String endOfSched = hourFormat.format(End);

                    int xloc = 85 + ((j+1) * 50);
                    int yloc = 385 + ((i+1) * 10);

                    System.out.println("XGrid: "+ xloc);

                    System.out.println("YGrid: " + yloc);

                    rubberStamp(over,startOfSched, xloc, yloc );
                    //rubberStamp(over,endOfSched, xloc, yloc-20 );


                }
            }
        }

    }

    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfDictionary dict = reader.getPageN(1);
        PdfObject object = dict.getDirectObject(PdfName.CONTENTS);
        PdfArray refs = null;
        if (dict.get(PdfName.CONTENTS).isArray()) {
            refs = dict.getAsArray(PdfName.CONTENTS);
        } else if (dict.get(PdfName.CONTENTS).isIndirect()) {
            refs = new PdfArray(dict.get(PdfName.CONTENTS));
        }
        for (int i = 0; i < refs.getArrayList().size(); i++) {
            PRStream stream = (PRStream) refs.getDirectObject(i);
            byte[] data = PdfReader.getStreamBytes(stream);
            stream.setData(new String(data).replace("NULA", "Nulo").getBytes());
        }
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        stamper.close();
        reader.close();
    }



}

