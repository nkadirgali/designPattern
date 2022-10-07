package DesignPattern.Project.Observer;

import DesignPattern.Project.DB;
import DesignPattern.Project.Interfaces.IObserver;

public class Admin implements IObserver {
    private int adminId;
    private User user;


    public  Admin(int adminId){
        this.adminId = adminId;
    }
    public Admin(int adminId,User user) {
        this.adminId = adminId;
        this.user = user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAdminId(int adminId){this.adminId=adminId;}
    public int getAdminId() {
        return adminId;
    }

    @Override
    public void update(String s, DB db) {
        db.insertMessages(s,adminId,user.getUserId());
    }
}
