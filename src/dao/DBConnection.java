package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

                                                                                                                                                                                                                                                                                                                                                                                            
                                                                                                                             private static Properties props = new Properties();
	  



    // ENSURE YOU DON'T CHANGE THE BELOW CODE WHEN YOU SUBMIT
    
    

    public static Connection getConnection() {
        Connection con = null;
        try {
            FileInputStream fis = new FileInputStream("database.properties");
            Properties props = new Properties();
            props.load(fis);

            Class.forName(props.getProperty("db.driver"));
            con = DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.username"),
                    props.getProperty("db.password")
            );
            System.out.println("Database connected successfully!");
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return con;
    }
}



