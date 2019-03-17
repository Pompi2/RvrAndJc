package com.hashik.rvrandjc.models.JSONDataModels;

public class Attendancereport
{
    private String date;

    private Attendance[] attendance;

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public Attendance[] getAttendance ()
    {
        return attendance;
    }

    public void setAttendance (Attendance[] attendance)
    {
        this.attendance = attendance;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [date = "+date+", attendance = "+attendance+"]";
    }
}
