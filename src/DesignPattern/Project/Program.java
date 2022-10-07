package DesignPattern.Project;
import DesignPattern.Project.Observer.Admin;
import DesignPattern.Project.Observer.User;
import DesignPattern.Project.Strategy.Car.*;
import DesignPattern.Project.Strategy.Transport;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Program {
    Scanner text = new Scanner(System.in);
    Transport transport = new Transport();

    private double[] distance = new double[5];
    String A,B;
    private int option;
    Connection conn;
    private String username, password;
    private boolean isLogined = false,isAdmin;
    User user;
    Admin admin;
    DB db;
    Car car = new Car();
    String carDecorator=car.getDecorator();
    //the beginning of the program
    public void start(){
        db = new DB();
        conn = db.connect_to_db("Observer","postgres","nurdaulet");
        if(conn==null){
            System.out.println("Connection Failed");
            return;
        }

        login();
//        System.out.println("Choose option:");
//        System.out.println("1) Register");
//        System.out.println("2) Login");
//        int registerOrLogin= text.nextInt();
//        while (registerOrLogin!=1 || registerOrLogin!=2){
//            System.out.println("Please type 1 to register or type 2 to login");
//            registerOrLogin=text.nextInt();
//        }

    }
    private void login(){
        pageForLogin();
        while (isLogined){
            if(!isAdmin){
                if(loginedUser()){
                    isLogined=false;
                    login();
                }else isLogined=false;
            }else{
                if(loginedAdmin()){
                    isLogined=false;
                    login();
                }else isLogined=false;
            }
        }
    }
    private boolean mainPage(){
        System.out.println("Who are you?");
        System.out.println("1. Admin");
        System.out.println("2. User");
        option=text.nextInt();
        if (option==1) return true;
        else return false;
    }
    private void pageForLogin(){
        if(mainPage()) pageForLoginAdmin();
        else pageForLoginUser();
    }
    private void pageForLoginAdmin(){
        if(!loginAdmin()){
            while (wrongLogin("Admin")){
                pageForLoginAdmin();
            }
            pageForLogin();
        }else{
            isLogined=true;
            isAdmin=true;
        }
    }
    private boolean chooseUser() {
        System.out.println("1) Login");
        System.out.println("2) Create user");
        option=text.nextInt();
        if(option==1) return true;
        else return false;
    }
    private void pageForLoginUser(){
        if(chooseUser()) {
            if (!loginUser()) {
                while (wrongLogin("User")) {
                    pageForLoginUser();
                }
                pageForLogin();
            } else {
                isLogined = true;
                isAdmin = false;
            }
        }else{
            createUser();
            isLogined=true;
            isAdmin=false;
        }
    }
    private void createUser(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Write your username");
        username=scan.nextLine();
        System.out.println("Write your password");
        password=scan.nextLine();
        String user1,user_pas;
        try (
                PreparedStatement pst = conn.prepareStatement("insert into users(username,password) values('"+username+"','"+password+"');");
                ResultSet rs = pst.executeQuery()) {
            pst.setString(1,username);
            pst.setString(2, password);
            pst.executeUpdate();
            loginUser(username,password);
        } catch (SQLException ex) {
            loginUser(username,password);
            Logger lgr = Logger.getLogger(Program.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    private boolean loginAdmin(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Write your username");
        username = scan.nextLine();
        System.out.println("Write your password");
        password = scan.nextLine();
//        String sql="select * from users where username='username';";
        String admin_name,admin_pas;
        int adminId;

        try (PreparedStatement pst = conn.prepareStatement("SELECT * FROM admins where username='"+username+"' and password='"+password+"';");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                adminId = rs.getInt("admin_id");
                admin_name=rs.getString("username");
                admin_pas=rs.getString("password");
//                System.out.println(adminId + " " + admin_name+" "+admin_pas);
                if(admin_name.equals(username) && admin_pas.equals(password)) {
                    admin = new Admin(adminId);
                    return true;
                }
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(
                    Program.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }
    private boolean loginUser(String username,String password){
        String user1,user_pas;
        int userId;
        try (PreparedStatement pst = conn.prepareStatement("SELECT * FROM users where username='"+username+"' and password='"+password+"'");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                userId=rs.getInt("user_id");
                user1=rs.getString("username");
                user_pas=rs.getString("password");
                if(user1.equals(username) && user_pas.equals(password)) {
                    user = new User(userId);
                    return true;
                }

            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(
                    Program.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }
    private boolean loginUser(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Write your username");
        username=scan.nextLine();
        System.out.println("Write your password");
        password=scan.nextLine();
        if(loginUser(username,password)) return true;
        else return false;
    }
    private boolean wrongLogin(String s){
        System.out.println(s+" with this username or password doesn't exist please choose option:");
        System.out.println("1) Try again to login");
        System.out.println("2) Back to main page");
        int option = text.nextInt();
        if(option==1) return true;
        else return false;
    }
    private boolean loginedUser(){
        Scanner sc = new Scanner(System.in);
        String exit;
        System.out.println("Welcome to our program user!");
        System.out.println("Create route:");
        enterDistance();
        exit = "transport";
        while (!exit.equals("exit")){
//            System.out.println("Exit1 equal = "+exit);
            /*if (exit.equals("option")){
                boolean optionx = chooseOption();
                if (optionx) fastTrip();
                else  cheapTrip();
                //              System.out.println("Option equal " + optionx + " exit equal " + exit);
            }else */if(exit.equals("route")) {
                enterDistance();
                exit = "transport";
                continue;
            }else if(exit.equals("transport")){
//                System.out.println("Type tip of tranport(ex: Plane, Car, Train, Boat):");
//                exit = sc.nextLine();
//                print(exit);
                print("Car");
            }/*else if(exit.equals("all")){
                printAll();
            }*/else if(exit.equals("stop")){
                isLogined=false;
                break;
            }else if(exit.equals("customize")){
              chooseTypeofTransport();
                System.out.println("You choose "+car.getDescription());
            } else if(exit.equals("distance")){
                System.out.println("Distance is " + distance[1]);
            } else{
                System.out.println("text is incorrect please type again");
            }
            //        System.out.println("Exit2 equal = "+exit);
            after();
            //           System.out.print("Enter exit: ");
            exit= sc.nextLine();
        }
        if(exit.equals("exit")) return true;
        else return false;
//        System.out.println("add distance");
//        int distance = text.nextInt();
    }
    private boolean loginedAdmin(){
        Scanner sc = new Scanner(System.in);
        String exit;
        afterAdmin();
        exit=sc.nextLine();
        ArrayList<String> messages;
        while (!exit.equals("exit")){
            if(exit.equals("stop")){
                break;
            }else if(exit.equals("messages")){
                messages = showMessages();
            }else if(exit.equals("delete")){
                if(!deleteOneMessage()){
                    System.out.println("Message with this id doesn't exist");
                }else System.out.println("Message succefully deleted");
            }else if(exit.equals("all")){
                if(db.deleteAllForOneAdmin(admin.getAdminId())) System.out.println("Messages succesfully deleted");
                else System.out.println("Messages weren't deleted");
            }else{
                System.out.println("text is incorrect please type again");
            }
            afterAdmin();
            exit=sc.nextLine();
        }
        if(exit.equals("exit")){
            isLogined=false;
            isAdmin=false;
            return true;
        }else return false;
    }
    private ArrayList<String> showMessages(){
        ArrayList<String> messages = db.viewMessages(admin.getAdminId());
        System.out.println("Messages:");
        for (String s:
                messages) {
            System.out.println(s);
        }
        return messages;
    }
    private boolean deleteOneMessage(){
        Scanner sc=new Scanner(System.in);
        int option;
        beforeDeleteOneMessage();
        option= sc.nextInt();
        while (!(option==1 || option==2)){
            System.out.println("Please type 1 or 2");
            beforeDeleteOneMessage();
            option=sc.nextInt();
        }
        ArrayList<String> messages;

        if(option==1){
            messages=showMessages();
        }else messages=db.viewMessages(admin.getAdminId());
        System.out.println("Enter id of message");
        int messageId= sc.nextInt(),messId;
        for (String s:
             messages) {
            messId=getFirstNumber(s);
            System.out.println(messId+" s= "+s);
            if(messageId==messId){
                db.deleteOne(messageId);
                return true;
            }
        }
        return false;

    }
    private int getFirstNumber(String s){
        boolean flag1=false,flag2=false;
        int x=0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)>='0'&& s.charAt(i)<='9' && !flag2){
                if(!flag1) flag1=true;
                x=10*x+((int)s.charAt(i)-48);
            }else{
                if(flag1) flag2=true;
            }
            if(flag1 && flag2) break;
        }
        return x;
    }
    private void beforeDeleteOneMessage(){
        System.out.println("Choose one");
        System.out.println("1) Show messages to know id of message");
        System.out.println("2) Enter id of message");
    }
    private void afterAdmin(){
        System.out.println("1) If you want to exit from program type stop");
        System.out.println("2) If you want to exit from account type exit");
        System.out.println("3) If you want to see all messages for you type messages");
        System.out.println("4) If you want to delete one message type delete");
        System.out.println("5) If you want to delete all messages for you type all");
    }
    private void enterDistance(){
        System.out.println("All cities: Almaty, Astana, Shymkent, Aktobe, Karaganda, Taraz, Pavlodar, Ust-kamenogorsk, Semey, Atyrau, Kyzylorda, Kostanai, Uralsk, Aktau.");
        Scanner sc = new Scanner(System.in);
        System.out.println("add point - A");
        A = sc.nextLine();
        System.out.println("add point - B");
        B = sc.nextLine();
        distance[1]=routes(A,B);
        System.out.println("Distance is "+ distance[1]);
/*        System.out.println("Enter distance on air in km:");
        distance[0]= sc.nextInt();
        System.out.println("Enter distance on land(with car) in km:");
        distance[1]= sc.nextInt();
        System.out.println("Enter distance on land(with train) in km:");
        distance[2]= sc.nextInt();
        System.out.println("Enter distance on water in km:");
        distance[3]= sc.nextInt();*/
    }
    private int routes(String a2, String b2) {

        try( PreparedStatement pst = conn.prepareStatement("Select * from routes where city1='"+a2+"'and city2='"+b2+"';");
             ResultSet rs = pst.executeQuery();   ){
            while(rs.next()) {
                int distance=rs.getInt("distance");
                return distance;}
        }catch (Exception e){
            System.out.println(e);

        }
        return 0;
    }
    private boolean chooseOption(){
        System.out.println("Choose your priority!");
        System.out.println("type '1' for the most cheapest option");
        System.out.println("type '2' for the most fastest option");
        option=text.nextInt();
        if (option==1) return false;
        else return true;
    }

    private void optionDelegator(){
        if(option == 1 ){
            cheapTrip();
        }else{
            fastTrip();
        }
    }

    private void fastTrip(){
            transport.setCar(car);
            double minTime=transport.displacement(distance[1]),fuel;
            String minTransport="Car";
            transport.setPlane();
            if(minTime>transport.displacement(distance[0])){
                minTime=transport.displacement(distance[0]);
                minTransport="Plane";
            }
            transport.setTrain();
            if(minTime>transport.displacement(distance[2])){
                minTime=transport.displacement(distance[2]);
                minTransport="Train";
            }
            transport.setBoat();
            if(minTime>transport.displacement(distance[3])){
                minTime=transport.displacement(distance[3]);
                minTransport="Boat";
            }

            if(minTransport.equals("Car")){
                System.out.println(car.getDescription()+" is fastest and");
                transport.setCar(car);
                minTransport= car.getDescription();
                fuel=transport.usageFuel(distance[1]);
            } else if(minTransport.equals("Plane")){
                System.out.println("Plane is fastest and");
                transport.setPlane();
                fuel=transport.usageFuel(distance[0]);
            } else if(minTransport.equals("Train")){
                System.out.println("Train is fastest and");
                transport.setTrain();
                fuel=transport.usageFuel(distance[2]);
            } else{
                System.out.println("Boat is fastest and");
                fuel=transport.usageFuel(distance[3]);
            }

            System.out.println("fuel to distance ratio is - " + fuel);
            System.out.println("time to distance ratio is - " + minTime);
            String s="User with "+user.getUserId()+" create route from "+A+" to "+B+" and choose option fastest";
            s+=" and answer was "+minTransport+ " fuel to distance ratio is "+fuel+" time to distance is "+minTime;
            user.notifyObservers(s,db);
    }

    private void cheapTrip(){
        //
        transport.setCar(car);
        double minFuel=transport.usageFuel(distance[1]),time;
        String minTransport="Car";
        transport.setPlane();
        if(minFuel>transport.usageFuel(distance[0])){
            minFuel=transport.usageFuel(distance[0]);
            minTransport="Plane";
        }
        transport.setTrain();
        if(minFuel>transport.usageFuel(distance[2])){
            minFuel=transport.usageFuel(distance[2]);
            minTransport="Train";
        }
        transport.setBoat();
        if(minFuel>transport.usageFuel(distance[3])){
            minFuel=transport.usageFuel(distance[3]);
            minTransport="Boat";
        }

        if(minTransport.equals("Car")){
            System.out.println(car.getDescription()+" is cheapest and");
            transport.setCar(car);
            minTransport=car.getDescription();
            time=transport.displacement(distance[1]);
        } else if(minTransport.equals("Plane")){
            System.out.println("Plane is cheapest and");
            transport.setPlane();
            time=transport.displacement(distance[0]);
        } else if(minTransport.equals("Train")){
            System.out.println("Train is cheapest and");
            transport.setTrain();
            time=transport.displacement(distance[2]);
        } else{
            System.out.println("Boat is cheapest and");
            time=transport.displacement(distance[3]);
        }

        System.out.println("fuel to distance ratio is - " + minFuel);
        System.out.println("time to distance ratio is - " + time);
        String s="User with "+user.getUserId()+" create route from "+A+" to "+B+" and choose option cheapest";
        s+=" and answer was "+minTransport+ " fuel to distance ratio is "+minFuel+" time to distance is "+time;
        user.notifyObservers(s,db);
    }
    private void print(String type){
        if(type.equals("Plane")){
            transport.setPlane();
            System.out.println("Plane:");
            System.out.println("fuel to distance ratio is - " + transport.usageFuel(distance[0]));
            System.out.println("time to distance ratio is - " + transport.displacement(distance[0]));
            String s="User with "+user.getUserId()+" create route from "+A+" to "+B+" and choose Plane";
            s+=" and answer was fuel to distance ratio is "+transport.usageFuel(distance[0])+" time to distance is "+transport.displacement(distance[0]);
            user.notifyObservers(s,db);
        } else if(type.equals("Car")){
            transport.setCar(car);
            System.out.println(car.getDescription()+":");
            System.out.println("Distance is "+distance[1]+" km");
            System.out.println("money is - " + transport.usageFuel(distance[1])+" tenge");
            System.out.println("time is - " + transport.displacement(distance[1])+ " hours");
            String s="User with "+user.getUserId()+" create route from "+A+" to "+B+" and choose "+car.getDescription();
            s+=" and answer was money to distance ratio is "+transport.usageFuel(distance[1])+" time to distance is "+transport.displacement(distance[1]);
            user.notifyObservers(s,db);
        } else if(type.equals("Train")){
            transport.setTrain();
            System.out.println("Train:");
            System.out.println("fuel to distance ratio is - " + transport.usageFuel(distance[2]));
            System.out.println("time to distance ratio is - " + transport.displacement(distance[2]));
            String s="User with "+user.getUserId()+" create route from "+A+" to "+B+" and choose Train";
            s+=" and answer was fuel to distance ratio is "+transport.usageFuel(distance[2])+" time to distance is "+transport.displacement(distance[2]);
            user.notifyObservers(s,db);
        } else if(type.equals("Boat")){
            transport.setBoat();
            System.out.println("Boat:");
            System.out.println("fuel to distance ratio is - " + transport.usageFuel(distance[3]));
            System.out.println("time to distance ratio is - " + transport.displacement(distance[3]));
            String s="User with "+user.getUserId()+" create route from "+A+" to "+B+" and choose Boat";
            s+=" and answer was fuel to distance ratio is "+transport.usageFuel(distance[3])+" time to distance is "+transport.displacement(distance[3]);
            user.notifyObservers(s,db);
        }else{
            System.out.println("text is incorrect please type again");
        }
    }
    private void printAll(){
        print("Plane");
        print("Car");
        print("Train");
        print("Boat");
    }
    private void after(){
        System.out.println("1) If you want exit from program type stop");
        System.out.println("2) If you want exit from account type exit");
//        System.out.println("3) If you want to change the option type option");
        System.out.println("4) If you want to change destination points type route");
        System.out.println("5) If you want to see one type of transport type transport");
//        System.out.println("6) If you want to see all types of transport type all");
        System.out.println("7) If you want to customize transport type customize");
        System.out.println("8) If you want to see distance type distance");
    }

    private void changer(String trans){
        if(trans.equals("1")) return;
        if(trans.equals("Car")){
            transport.setCar(car);
        }else if(trans.equals("Boat")){
            transport.setBoat();
        }else if(trans.equals("Plane")){
            transport.setPlane();
        }else if(trans.equals("Train")){
            transport.setTrain();
        }else{
            System.out.println("text it correctly! pls:)");
        }
    }

    private void chooseTypeofTransport(){
        System.out.println("Type of transport(Example: Plane):");
        Scanner sc = new Scanner(System.in);
        String transport = sc.nextLine();
        if(transport.equals("Car")){
            decorateCar();
        }else{
            System.out.println("Coming soon");
        }
    }
    private void decorateCar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose body type of car");
        carBodyTypes();
        System.out.println("Choose fuel type of car");
        carFuelTypes();
        System.out.println("Choose free or toll road");
        carTollRoad();
        System.out.println("Choose with driver or without");
        carDriver();
    }
    private void carBodyTypes(){
        System.out.println("Enter number:");
        System.out.println("1) Default");
        System.out.println("2) Hatchback");
        System.out.println("3) SUV");
        System.out.println("4) SportCar");
        Scanner sc = new Scanner(System.in);
        int type = sc.nextInt();
        while (type<1 && type>4){
            System.out.println("Please type number between 1 and 4");
            System.out.println("1) Default");
            System.out.println("2) Hatchback");
            System.out.println("3) SUV");
            System.out.println("4) SportCar");
            type=sc.nextInt();
        }
        carBodyType(type);
    }
    private void carBodyType(int type){
        if(type==1) car=new Car();
        else if(type==2) car=new Hatchback(new Car());
        else if(type==3) car = new SUV(new Car());
        else if(type==4) car = new SportCar(new Car());
    }
    private void carFuelTypes(){
        System.out.println("1) Default");
        System.out.println("2) Diesel");
        System.out.println("3) Electricity");
        Scanner sc = new Scanner(System.in);
        int type = sc.nextInt();
        while (type<1 && type>3){
            System.out.println("Please type number between 1 and 3");
            System.out.println("1) Default");
            System.out.println("2) Diesel");
            System.out.println("3) Electricity");
            type=sc.nextInt();
        }
        carFuelType(type);
    }
    private void carFuelType(int type){
        if(type==1){
            carDecorator=car.getDecorator();
            carBodyType(carDecorator.charAt(0));
        }
        else if(type==2) car=new Diesel(car);
        else if(type==3) car = new Electricity(car);
    }
    private void carTollRoad(){
        System.out.println("1) Free");
        System.out.println("2) Toll");
        Scanner sc= new Scanner(System.in);
        int type = sc.nextInt();
        while(type<1 && type>2){
            System.out.println("Please type number between 1 and 2");
            System.out.println("1) Free");
            System.out.println("2) Toll");
            type=sc.nextInt();
        }
        if(type==1){
            car = new FreeRoad(car);
        }else{
            car=new TollRoad(car);
        }
    }
    private void carDriver(){
        System.out.println("1) Car with driver");
        System.out.println("2) Car without driver");
        Scanner sc= new Scanner(System.in);
        int type = sc.nextInt();
        while(type<1 && type>2){
            System.out.println("Please type number between 1 and 2");
            System.out.println("1) Car with driver");
            System.out.println("2) Car without driver");
            type=sc.nextInt();
        }
        if(type==1){
            car = new WithDriver(car);
        }else{
            car=new WIthoutDriver(car);
        }
    }

}
