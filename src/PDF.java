/* requires itextpdf-5.1.2.jar or similar */

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;


public class PDF {

    public static void create() throws IOException, DocumentException {

        /* example inspired from "iText in action" (2006), chapter 2 */

        PdfReader reader = new PdfReader("F:/Other/Desktop/testpdf.pdf"); // input PDF
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream("F:/Other/Desktop/AVG-DerekWarne.pdf")); // output PDF
        BaseFont bf = BaseFont.createFont( BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font

        //loop on pages (1-based)
        for (int i=1; i<=reader.getNumberOfPages(); i++){

            // get object for writing over the existing content;
            // you can also use getUnderContent for writing in the bottom layer
            PdfContentByte over = stamper.getOverContent(i);

            // write Name Title
            rubberStamp(over,"Derek Warne agree", 320, 666 );

            // Number of Weeks for time
            rubberStamp(over,"20/04/2023", 408, 596 );

            // Start date of the agreement
            rubberStamp(over,"20/04/2023", 360, 583 );

            // End date of the agreement
            rubberStamp(over,"1/05/2023", 360, 571 );












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

