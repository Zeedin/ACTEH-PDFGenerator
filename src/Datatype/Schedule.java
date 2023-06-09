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
        int day = dayOfTheWeek(dateStartTime, globledateStart);

        //int baseWeek = weekOfTheMonth(globledateStart);

        //int currentWeek = weekOfTheMonth(dateStartTime);


        //int baseWeek = getWeekOfSchedule(globledateStart);
        //int currentWeek = getWeekOfSchedule(dateStartTime);

        //System.out.println(baseWeek);
        //System.out.println(currentWeek);
        int relWeek = getWeekOfSchedule(dateStartTime,  globledateStart);
        //int relWeek = currentWeek - baseWeek;

        //System.out.println("Week ID: "+relWeek );

        if (relWeek != 69 && day != 69) {
            month[relWeek][day][0] = hoursWorked;
            month[relWeek][day][1] = dateStartTimeUNIX;
            month[relWeek][day][2] = dateEndTimeUNIX;
        }
    }

    private static int getWeekOfSchedule(Date date, Date globledateStart) {
        Calendar schedDay = Calendar.getInstance();
        schedDay.setTime(date);
        //schedDay.setFirstDayOfWeek(Calendar.SATURDAY);
        int day = schedDay.get(Calendar.DAY_OF_YEAR);


        Calendar StartDay = Calendar.getInstance();
        StartDay.setTime(globledateStart);
        //StartDay.setFirstDayOfWeek(Calendar.SATURDAY);
        int startDay =  StartDay.get(Calendar.DAY_OF_YEAR);

        int weekNumber = day - startDay;
        int weekid = 69;

        if (weekNumber <= 6){weekid = 0;}
        else if (weekNumber <= 13 ){weekid = 1;}
        else if (weekNumber <= 20 ){weekid = 2;}
        else if (weekNumber <= 27 ){weekid = 3;}
        else if (weekNumber < 0  ){weekid = 69;} // junk value to throw out
        else {weekid = 69;} // junk value to throw out

        //System.out.println("YearStartNumb: "+startDay +" CompareDay: "+day+" Week ID: "+weekid + " Math: " + weekNumber);
        return weekid;
    }


    /**
     *
     * GETTER
     *
     */


//Yes I know I can cast error insted of 69 but trying to throw together quickly
    public int dayOfTheWeek(Date date, Date globledateStart) {
            Calendar schedDay = Calendar.getInstance();
            schedDay.setTime(date);
            //schedDay.setFirstDayOfWeek(Calendar.SATURDAY);
            int day = schedDay.get(Calendar.DAY_OF_YEAR);


            Calendar StartDay = Calendar.getInstance();
            StartDay.setTime(globledateStart);
            //StartDay.setFirstDayOfWeek(Calendar.SATURDAY);
            int startDay =  StartDay.get(Calendar.DAY_OF_YEAR);

            int dayNumber = day - startDay;
            int dayid = 69;

    if (dayNumber >=0 && dayNumber < 28){
            while (dayNumber >6 ){
                dayNumber = dayNumber - 7;
            }

            if (dayNumber == 0 ){dayid = 0;} // Sat
            else if (dayNumber == 1 ){dayid = 1;} //Sun
            else if (dayNumber == 2 ){dayid = 2;} //Mon
            else if (dayNumber == 3 ){dayid = 3;} //Tues
            else if (dayNumber == 4 ){dayid = 4;} //Wed
            else if (dayNumber == 5 ){dayid = 5;} //Thurs
            else if (dayNumber == 6 ){dayid = 6;} //Friday

            else {dayid = 69;} // junk value to throw out
        }
    else {dayid = 69;}// junk value to throw out
            System.out.println("WeekStart: "+startDay +" CompareDay: "+day+" Day ID: "+dayid + " Math: " + dayNumber);
            return dayid;
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
