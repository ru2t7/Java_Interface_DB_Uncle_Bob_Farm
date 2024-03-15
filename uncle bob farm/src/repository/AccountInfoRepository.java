package repository;

import model.VisitorModel;

import java.sql.*;

public class AccountInfoRepository {
    public String updateVisitor(VisitorModel visitor) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

//            String query = "INSERT INTO person values('Bogdan', 'Test')";
            String query = "UPDATE visitor SET age=?, password=?,name=?,username=? WHERE id=?;";
            preparedStatement = connection.prepareStatement(query);


            preparedStatement.setInt(1, visitor.getAge());
            preparedStatement.setString(2, visitor.getPassword());
            preparedStatement.setString(3, visitor.getName());
            preparedStatement.setString(4, visitor.getUsername());
            preparedStatement.setInt(5, visitor.getId());
            System.out.println(preparedStatement);
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
                    return "Account Updated! Connection Closed!";
                else
                    return "Account NOT Updated! Connection Closed";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error at DB level";
            }
        }
    }


}