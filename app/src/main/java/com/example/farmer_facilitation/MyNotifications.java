package com.example.farmer_facilitation;

public class MyNotifications {

    String id, date, subject, msg, status;

    public MyNotifications(String id, String date, String subject, String msg, String status) {
        this.id = id;
        this.date = date;
        this.subject = subject;
        this.msg = msg;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getMsg() {
        return msg;
    }

    public String getSubject() {
        return subject;
    }

    public String getStatus() {
        return status;
    }
}
