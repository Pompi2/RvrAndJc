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

    private Overview[] overview;

    public String getNumber ()
    {
        return number;
    }

    public void setNumber (String number)
    {
        this.number = number;
    }

    public Overview[] getOverview()
    {
        return overview;
    }

    public void setOverview(Overview[] overview)
    {
        this.overview = overview;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [number = "+number+", overview = "+overview+"]";
    }
}
