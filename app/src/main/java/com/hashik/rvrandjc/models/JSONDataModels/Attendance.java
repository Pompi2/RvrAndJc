package com.hashik.rvrandjc.models.JSONDataModels;

public class Attendance
{
    private String period;

    private String time;

    public String getPeriod ()
    {
        return period;
    }

    public void setPeriod (String period)
    {
        this.period = period;
    }

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [period = "+period+", time = "+time+"]";
    }
}
