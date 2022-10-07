package DesignPattern.Project.Interfaces;

import DesignPattern.Project.DB;

public interface IObserver {
    public void update(String s, DB db);
}
