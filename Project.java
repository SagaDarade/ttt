package com.example.projectsdetails;

import com.google.gson.annotations.SerializedName;

public class Project {


    @SerializedName("pid")
    private int pid;

    @SerializedName("pname")
    private String pName;

    @SerializedName("duration")
    private String pDurat;

    @SerializedName("submit")
    private String pDate;

    @SerializedName("response")
    private String resp;

    public Project(int pid, String pName, String pDurat, String pDate) {
        this.pid = pid;
        this.pName = pName;
        this.pDurat = pDurat;
        this.pDate = pDate;
    }

    public int getPid() {
        return pid;
    }

    public String getpName() {
        return pName;
    }

    public String getpDurat() {
        return pDurat;
    }

    public String getpDate() {
        return pDate;
    }

    public String getResp() {
        return resp;
    }
}