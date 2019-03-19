package com.hashik.rvrandjc.models.JSONDataModels;

public class Overview {
    private String att;

    private String code;

    public String getAtt() {
        return att;
    }

    public void setAtt(String att) {
        this.att = att;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ClassPojo [att = " + att + ", code = " + code + "]";
    }
}