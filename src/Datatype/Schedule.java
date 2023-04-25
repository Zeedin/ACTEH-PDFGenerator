package Datatype;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.Format.*;

public class Schedule {




    public Schedule(int employeeID) {

        this.employeeID = employeeID;

    }


    protected int employeeID;

    protected long[][][] month = new long[4][7][3];
    protected boolean AvgNeeded;


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

        Date dateStartTime = format.parse(startTime);
        Date dateEndTime = format.parse(endTime);


        long dateStartTimeUNIX = dateStartTime.getTime();
        long dateEndTimeUNIX = dateEndTime.getTime();
        /// 3600 to convert seconds to hours then /100 to remove the extra long value and keep once dec place for half hours so can keep as long and not double
        long hoursWorked = ((dateEndTimeUNIX - dateStartTimeUNIX) / 3600)/100;


        //index values
        int day = dayOfTheWeek(dateStartTime);

        int baseWeek = weekOfTheMonth(dateStartTime);
        int currentWeek = weekOfTheMonth(dateStartTime);

        int relWeek = currentWeek - baseWeek;

        month[relWeek][day][0] = hoursWorked;
        month[relWeek][day][1] = dateStartTimeUNIX;
        month[relWeek][day][2] = dateEndTimeUNIX;
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
}
