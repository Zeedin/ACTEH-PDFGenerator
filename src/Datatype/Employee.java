package Datatype;

public class Employee {

    public Employee(int employeeID) {

        this.employeeID = employeeID;

    }


    //bPerms Stats
    protected int employeeID;
    protected String firstName;
    protected String lastName;
    protected String superVis;
    protected String startTime;
    protected String endTime;
    protected String total;



    protected Schedule schedule;


    /**
     *
     * SETTERS
     *
     */

    public final void employeeIDSET(int employeeID) {
        this.employeeID = employeeID;
    }
    public final void firstNameSET(String firstName) {
        this.firstName = firstName;
    }
    public final void lastNameSET(String lastName) {
        this.lastName = lastName;
    }
    public final void startTimeSET(String startTime) {
        this.startTime = startTime;
    }
    public final void endTimeSET(String EndTime) {
        this.endTime = endTime;
    }
    public final void totalSET(String total) {
        this.total = total;
    }

    public final void superVisSET(String superVis) {

        superVis = superVis.replaceFirst("[(].*?[)]", "");

        this.superVis = superVis;
    }

    public final void scheduleSET(Schedule schedule) {
        this.schedule = schedule;
    }
    /**
     *
     * GETTER
     *
     */

    public int employeeIDGET() {
        return employeeID;
    }
    public String firstNameGET() {
        return firstName;
    }
    public String lastNameGET() {
        return lastName;
    }
    public String superVisGET() {
        return superVis;
    }
    public String startTimeGET() {
        return startTime;
    }
    public String endTimeGET() {
        return endTime;
    }
    public String totalGET() {
        return total;
    }

    public Boolean GenerateAgrement() {
        return schedule.GenerateAgrement;
    }

    public Schedule scheduleGET() {
        return schedule;
    }




    @Override
    public String toString() {

        String printOut = "\n" + firstName + " " + lastName + "\n \t \t \t \t Schedule " + schedule.toString();

        return printOut;
    }
public boolean equals(int employeeID) {

        return employeeID == this.employeeID;
        }
/**
public String compareTo(Datatype.Employee e) {

        int value;
        value = employeeID.compareTo(e.employeeIDGET());
        return value;
        }**/
}
