package repository;

import model.VisitorModel;

import java.sql.*;

public class CreateAccountRepository {
    public String createAccount(VisitorModel visitor) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

//            String query = "INSERT INTO person values('Bogdan', 'Test')";
            String query = "INSERT INTO visitor (id,age,password,name,username) VALUES (?, ?,?,?,?);";
            preparedStatement = connection.prepareStatement(query);


            preparedStatement.setInt(1, visitor.getId());
            preparedStatement.setInt(2, visitor.getAge());
            preparedStatement.setString(3, visitor.getPassword());
            preparedStatement.setString(4, visitor.getName());
            preparedStatement.setString(5, visitor.getUsername());

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return "Error at DB level";
        } finally {
            // Close resources in a finally block to ensure they are closed even if an exception occurs
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (rowsAffected == 1)
                    return "Account Created! Connection Closed!";
                else
                    return "Account NOT Created! Connection Closed";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error at DB level";
            }
        }
    }

    public int getNextIndex() {
        int index = 50;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

//            String query = "INSERT INTO person values('Bogdan', 'Test')";
            String query = "SELECT id FROM visitor WHERE id>= ALL (SELECT id from visitor);";
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Display function to show the Resultset
            while (resultSet.next()) {
                index = resultSet.getInt("id");
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
        return (index + 1);
    }
}
