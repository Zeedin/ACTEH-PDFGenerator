
// Java program to create open or
// save dialog using JFileChooser

import javax.swing.*;

class PDFGenerator extends GUI {

    // Jlabel to show the files user selects
    static JLabel fileSelected;
    static String dataFile;

    // a default constructor
    PDFGenerator() {

    }

    public static void main(String args[]) throws Exception {
        GUI.initialize();
        PDF.create();
         ;
/**
        ReplaceStream.main();
        ReplaceStream.manipulatePdf("F:/Other/Desktop/");

        PDDocument document = null;
        document = PDDocument.load(new File("F:/Other/Desktop/testpdf.pdf"));
        document = PDFEditor.replaceText(document, "name", "Derek");
        document.save("F:/Other/Desktop/testpdf-edit.pdf");
        document.close();
**/

    }

}
