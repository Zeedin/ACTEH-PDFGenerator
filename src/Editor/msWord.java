package Editor;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class msWord{

    public static void replace() throws IOException, InvalidFormatException {
        // TODO Auto-generated method stub

        XWPFDocument doc = new XWPFDocument(OPCPackage.open("F:/Other/Desktop/test/testdoc.docx"));
        for (XWPFParagraph p : doc.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains("name")) {
                        text = text.replace("name", "DEREK");
                        r.setText(text, 0);
                    }
                }
            }
        }
        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);
                            if (text != null && text.contains("name")) {
                                text = text.replace("name", "DEREK");
                                r.setText(text,0);
                            }
                        }
                    }
                }
            }
        }
        doc.write(new FileOutputStream("F:/Other/Desktop/test/testdoc-new.docx"));

    }

}