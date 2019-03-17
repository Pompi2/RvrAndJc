package com.hashik.rvrandjc.models.JSONDataModels;

public class Subjects
{
    private String code;

    private String marks;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getMarks ()
    {
        return marks;
    }

    public void setMarks (String marks)
    {
        this.marks = marks;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", marks = "+marks+"]";
    }
}