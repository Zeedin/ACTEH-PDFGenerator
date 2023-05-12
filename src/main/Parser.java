package main;

import Datatype.Employee;
import Datatype.Schedule;

import java.io.*;
import java.util.HashMap;

public class Parser {

    public static HashMap<Integer, Employee> employees = new HashMap<Integer, Employee>();
    public static void loadFile(String File) throws IOException {

        InputStream document = new FileInputStream(new File(File));

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        for (int length; (length = document.read(buffer)) != -1; ) {
            result.write(buffer, 0, length);
        }
        // StandardCharsets.UTF_8.name() > JDK 7
        PDFGenerator.dataFile = result.toString("UTF-8");

        //System.out.print(dataFile);

        csvParser(PDFGenerator.dataFile);
        document.close();
    }

    public static void csvParser(String rawfile){


        //System.out.println("csvParser");


        String scheduledEmployees[] = rawfile.split("\\r?\\n");
        //Datatype.Employee employees[] = null;


        for (int i=0; i < scheduledEmployees.length; i++){
            ////System.out.println("Array :: " +  scheduledEmployees[i]);

            String employeeRAW[] = scheduledEmployees[i].split(",");

            try {

                if (isNumeric(employeeRAW[0].toString())){

                    int eID = Integer.parseInt(employeeRAW[0]);


                    if (employees.get(eID) != null) {
                        Employee employeeUpdate = employees.get(eID);
                        Schedule scheduleUpdate = employeeUpdate.scheduleGET();

                        scheduleUpdate.addShift(employeeRAW[7], employeeRAW[8], 9);
                    }

                    else {

                        Employee Person = new Employee(eID);
                        Schedule empSchedule = new Schedule(eID);

                        Person.firstNameSET(employeeRAW[1]);
                        Person.lastNameSET(employeeRAW[2]);
                        Person.startTimeSET(employeeRAW[7]);
                        Person.endTimeSET(employeeRAW[8]);
                        Person.totalSET(employeeRAW[9]);
                        Person.superVisSET(employeeRAW[13]);

                        empSchedule.addShift(employeeRAW[7], employeeRAW[8], 10);


                        Person.scheduleSET(empSchedule);

                        employees.put(eID, Person);
                    }
                    //System.out.println("Array :: " + "[" + eID + "]" + scheduledEmployees[i]);

                }
            }
            catch (Exception e){
                //System.out.println("[ERROR]" + e);
            }
        }
        GUI.output.setText(null);

        for (Object value : employees.values()) {
            GUI.output.append(value.toString());
        }


    }


    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
