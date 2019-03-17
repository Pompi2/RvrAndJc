package com.hashik.rvrandjc.models.JSONDataModels;

public class Data
{
    private String grade;

    private String title;

    public String getGrade ()
    {
        return grade;
    }

    public void setGrade (String grade)
    {
        this.grade = grade;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [grade = "+grade+", title = "+title+"]";
    }
}