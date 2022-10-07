package DesignPattern.Project.Observer;

import DesignPattern.Project.DB;
import DesignPattern.Project.Interfaces.IObservable;
import DesignPattern.Project.Interfaces.IObserver;

import java.sql.SQLException;
import java.util.ArrayList;

public class User implements IObservable {
    private int userId;

    public User(int userId) {
        this.userId = userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public void addObserver(IObserver o) {

    }

    @Override
    public void removeObserver(IObserver o) {

    }

    @Override
    public void notifyObservers(String s, DB db) {
        try {
            ArrayList<IObserver> observers = db.getObservers(this);
//            System.out.println("User arraylist is "+observers);
            for (IObserver x:
                 observers) {
                    x.update(s,db);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
