package com.example.farmer_facilitation;

public class MyNotifications {

    String id,date,subject,msg;
    public MyNotifications(String id, String date, String subject, String msg)
    {
        this.id=id;
       this.date=date;
       this.subject=subject;
       this.msg=msg;
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
}
