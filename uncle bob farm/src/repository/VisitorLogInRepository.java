package repository;


import java.sql.*;

import model.VisitorModel;

public class VisitorLogInRepository {
    public int checkUsername(String username) {
        int visitorId = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT id FROM visitor WHERE username= ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                visitorId = resultSet.getInt("id");

            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }


        return visitorId;
    }

    public boolean checkPassword(String username, String password) {
        boolean passwordCorrect = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT id FROM visitor WHERE username= ? AND password=?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt("id") != 0)
                    passwordCorrect = true;

            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }


        return passwordCorrect;
    }

    public VisitorModel getVisitorData(String username) {
        Connection connection = null;
        VisitorModel visitor = new VisitorModel();
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT * FROM visitor WHERE username= ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                visitor.setId(resultSet.getInt("id"));
                visitor.setName(resultSet.getString("name"));
                visitor.setAge(resultSet.getInt("age"));
                visitor.setUsername(resultSet.getString("username"));
                visitor.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        return visitor;

    }

}