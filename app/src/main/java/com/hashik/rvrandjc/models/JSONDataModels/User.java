package com.hashik.rvrandjc.models.JSONDataModels;

public class User
{
    private String number;

    private String rank;

    public String getRank() {
        return rank;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    private String cgpa;

    private Attendance[] attendance;

    public String getNumber ()
    {
        return number;
    }

    public void setNumber (String number)
    {
        this.number = number;
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
        return "ClassPojo [number = "+number+", attendance = "+attendance+"]";
    }
}
