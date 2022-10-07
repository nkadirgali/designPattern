package DesignPattern.Project;

import DesignPattern.Project.Interfaces.IObserver;
import DesignPattern.Project.Observer.Admin;
import DesignPattern.Project.Observer.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
    Connection conn;
    public Connection connect_to_db(String dbname, String user, String pass){
//        Connection conn=null;
        conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
            if(conn!=null){
                return conn;
//                System.out.println("Connection Established and conn = "+conn);
            }else{
                System.out.println("Connection Failed");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }
    public ArrayList<IObserver> getObservers(User user) throws SQLException {
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM admins");
        ResultSet rs = pst.executeQuery();
        ArrayList<IObserver> arrayList = new ArrayList<>();
        int x;
        Admin observer = new Admin(0,user);
//        if(rs.next()){
//            x=rs.getInt("admin_id");
//            observer=new Admin(x,user);
//            System.out.println("admin_id of 1 admin is " + x + " and observer = "+ observer);
//            arrayList.add(observer);
//        }
        while (rs.next()) {
            x=rs.getInt("admin_id");
            observer.setAdminId(x);
  //          System.out.println("admin_id of 1 admin is " + x + " and observer = "+ observer);
            arrayList.add(observer);
        }
//        System.out.println("ArrayList is "+arrayList);
        return arrayList;
    }
    public void insertMessages(String s,int adminId,int userId){
        try (
                PreparedStatement pst = conn.prepareStatement("insert into messages(admin_id,user_id,messages) values("+adminId+","+userId+",'"+s+"');");
                ResultSet rs = pst.executeQuery()) {
//            pst.setInt(1,adminId);
//            pst.setInt(2, userId);
//            pst.setString(3,s);
//            pst.executeUpdate();
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(Program.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public ArrayList<String> viewMessages(int adminId){
        ArrayList<String> arrayList = new ArrayList<>();
        String s;
        try(
                PreparedStatement pst = conn.prepareStatement("Select * from messages where admin_id="+adminId);
                ResultSet rs = pst.executeQuery();
                ) {
//            System.out.println("view Messages");
            while (rs.next()) {
                s = "Message with " + rs.getInt("message_id") + " messageId and with message ";
                s += rs.getString("messages");
//                System.out.println(s);
                arrayList.add(s);
            }
//            System.out.println("--------------------------");
            return arrayList;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public boolean deleteOne(int messageId){
        try(
                PreparedStatement pst = conn.prepareStatement("delete from messages where message_id="+messageId);
                ResultSet rs = pst.executeQuery();
        ) {
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    public boolean deleteAllForOneAdmin(int adminId){
 //       System.out.println("adminId= "+adminId);
        try(
                PreparedStatement pst = conn.prepareStatement("delete from messages where admin_id="+adminId);
                ResultSet rs = pst.executeQuery();
        ) {
//            System.out.println("adminId= "+adminId);
            return true;
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(Program.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
