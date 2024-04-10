import java.sql.*;
import java.util.Scanner;

public class Main {
    //connecting to database
    private static final String url = "jdbc:postgresql://localhost:5432/login";
    private static final String username = "postgres";
    private static final String password = "0000";

    private static boolean addData(String UserName, String UserPW) throws SQLException {
        Connection connect = DriverManager.getConnection(url, username, password);
        String InsertData = "INSERT INTO users (username,password) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connect.prepareStatement(InsertData)) {
            preparedStatement.setString(1, UserName);
            preparedStatement.setString(2, UserPW);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    private static void ListData() {
        try {
            Connection connect = DriverManager.getConnection(url, username, password);
            Statement st = connect.createStatement();
            //adding value to the database

            ResultSet rs = st.executeQuery("Select * from users");
            //listing all data
            while (rs.next()) {
                String UN = rs.getString("username");
                String PW = rs.getString("password");
                System.out.println("Username:" + UN + " Password:" + PW);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String getData(String UserName,String UserPW) {
        try
        {
            Connection connect = DriverManager.getConnection(url, username, password);
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery("Select * from users");
            //iterating over the database
            while (rs.next())
            {
                String UN = rs.getString("username");
                String PW = rs.getString("password");
                if (UN.equals(UserName) && PW.equals(UserPW))
                {
                    return "You are now logged in!";
                }
            }
            return "Wrong Username or Password!";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "ERROR!";
        }
    }


    public static void main(String[] args) throws SQLException {
        Scanner input=new Scanner(System.in);
        String answer;
        String UserName;
        String UserPW;
        System.out.print("Do you have an account(Y,N):");
        answer=input.next();
        if(answer.equalsIgnoreCase("N")){
            System.out.println("\t\tREGISTER");
            System.out.print("Username:");
            UserName=input.next();
            System.out.print("Password:");
            UserPW=input.next();
            addData(UserName,UserPW);
        }
        else{
            System.out.println("\t\tLOGIN");
            System.out.print("Username:");
            UserName=input.next();
            System.out.print("Password:");
            UserPW=input.next();
            System.out.println(getData(UserName,UserPW));
        }



    }
}




