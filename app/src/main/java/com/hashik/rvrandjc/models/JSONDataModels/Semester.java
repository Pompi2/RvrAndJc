package com.hashik.rvrandjc.models.JSONDataModels;

public class Semester
{
    private Data[] data;

    private String title;

    public Data[] getData ()
    {
        return data;
    }

    public void setData (Data[] data)
    {
        this.data = data;
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
        return "ClassPojo [data = "+data+", title = "+title+"]";
    }
}
