package com.example.assign;

public class work_object {
    public String getWork_name() {
        return work_name;
    }

    public String getPrize() {
        return prize;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public void setWork_name(String work_name) {
        this.work_name = work_name;
    }

    String work_name,prize,time,name,status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
