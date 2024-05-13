package database;

import java.sql.*;

public class DatabaseConnection
{
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static void connect(String path)
    {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + path + ".db");
            System.out.println("Połącznie się powiodło");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void disconnect()
    {
        try {
            if (connection != null && !connection.isClosed())
            {
                connection.close();
                System.out.println("Rozłączenie się powiodło");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertTrack(int ID, String artist, String name, int duration)
    {
        try {
            Statement insertStatement = connection.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO song ");
            sb.append("VALUES ");
            sb.append(String.format("(%d, '%s', '%s', %d)",
                    ID, artist, name, duration));

            insertStatement.executeUpdate(sb.toString());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readTrack()
    {
        try {
            Statement selectionStatement = connection.createStatement();
            String query = "SELECT artist FROM song WHERE id = 20;";
            ResultSet rs = selectionStatement.executeQuery(query);
            while (rs.next())
            {
                System.out.println("artist of song nr 20 is " + rs.getString("artist"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
