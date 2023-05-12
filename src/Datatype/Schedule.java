package Datatype;

import main.GUI;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Schedule {




    public Schedule(int employeeID) {

        this.employeeID = employeeID;

    }


    protected int employeeID;

    protected long[][][] month = new long[4][7][3];
    public boolean GenerateAgrement = false;


    /**
     *
     * SETTERS
     *
     */
    public final void employeeIDSET(int employeeID) {
        this.employeeID = employeeID;
    }
    public final void addShift(String startTime, String endTime, int totalHours) throws ParseException {
        //Format 4/16/2023 9:00 - MM/dd/yyyy h:mm
        SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd h:mm");

        SimpleDateFormat inputDate  = new SimpleDateFormat("yyyy-MM-dd");
        Date globledateStart = inputDate.parse(GUI.dateStartPicker.getJFormattedTextField().getText());
        long globledateStartTimeUNIX = globledateStart.getTime();

        Date dateStartTime = format.parse(startTime);
        Date dateEndTime = format.parse(endTime);


        long dateStartTimeUNIX = dateStartTime.getTime();
        long dateEndTimeUNIX = dateEndTime.getTime();
        /// 3600 to convert seconds to hours then /100 to remove the extra long value and keep once dec place for half hours so can keep as long and not double
        long hoursWorked = ((dateEndTimeUNIX - dateStartTimeUNIX) / 3600)/100;
        if (hoursWorked > 80){

            GenerateAgrement = true;
        }

        //index values
        int day = dayOfTheWeek(dateStartTime);

        int baseWeek = weekOfTheMonth(globledateStart);
        int currentWeek = weekOfTheMonth(dateStartTime);

        int relWeek = currentWeek - baseWeek;

        month[relWeek][day][0] = hoursWorked;
        month[relWeek][day][1] = dateStartTimeUNIX;
        month[relWeek][day][2] = dateEndTimeUNIX;
    }

    private static int getWeekOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.SATURDAY);
        return cal.get(Calendar.WEEK_OF_MONTH);
    }


    /**
     *
     * GETTER
     *
     */



    public int dayOfTheWeek(Date check){

        Calendar c = Calendar.getInstance();
        c.setTime(check);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);


        return dayOfWeek;
    }

    public int weekOfTheMonth(Date date){

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekOfYear = c.get(Calendar.WEEK_OF_YEAR);


        return weekOfYear;
    }

    public long[][][] getDaysWorked(){
        return month;
    }

    @Override
    public String toString() {

        String printOut = "\n";


        for(int i=0 ; i<month.length ; i++){
            printOut = printOut + "    WEEK " + (i +1) + "\n";
            for(int j=0 ; j<month[i].length ; j++){

                if (month[i][j][0] != 0) {
                    java.util.Date Start = new java.util.Date(month[i][j][1]);
                    java.util.Date End = new java.util.Date( month[i][j][2]);
                    double hours = month[i][j][0]/10;

                    printOut = printOut + " \t Total Hours : " + hours;
                    printOut = printOut + "    |    Start Time : " + Start;
                    printOut = printOut + "    |    End Time : " + End + "\n";
                }
            }
        }



        return printOut;
    }
}
