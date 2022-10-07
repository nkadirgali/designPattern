package DesignPattern.Project.Interfaces;

import DesignPattern.Project.DB;

import java.sql.Connection;

public interface IObservable {
    public void addObserver(IObserver o);
    public void removeObserver(IObserver o);
    public void notifyObservers(String s, DB db);
}