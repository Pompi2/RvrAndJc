package com.hashik.rvrandjc.models.JSONDataModels;

public class Internalmarks
{
    private Subjects[] subjects;

    private String title;

    public Subjects[] getSubjects ()
    {
        return subjects;
    }

    public void setSubjects (Subjects[] subjects)
    {
        this.subjects = subjects;
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
        return "ClassPojo [subjects = "+subjects+", title = "+title+"]";
    }
}

