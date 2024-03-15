package repository;

import model.FarmModel;
import model.PackageModel;
import model.VisitorModel;
import model.ReservationModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisitorRepository {


    public int createReservation(ReservationModel reservation) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "INSERT INTO reservation (id,visitor_id,date,discount_id,package_id) VALUES(?,?,?,?,?);";
            preparedStatement = connection.prepareStatement(query);


            preparedStatement.setInt(1, reservation.getId());
            preparedStatement.setInt(2, reservation.getVisitorId());
            preparedStatement.setString(3, reservation.getDate());
            preparedStatement.setInt(4, reservation.getDiscountId());
            preparedStatement.setInt(5, reservation.getPackageId());

            System.out.println(preparedStatement);
            rowsAffected = preparedStatement.executeUpdate();

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
        return rowsAffected;


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
            String query = "SELECT id FROM reservation WHERE id>= ALL (SELECT id from reservation);";
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

    public int deleteVisitor(VisitorModel visitor) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

//            String query = "INSERT INTO person values('Bogdan', 'Test')";
            String query = "DELETE FROM visitor WHERE id=?";
            preparedStatement = connection.prepareStatement(query);


            preparedStatement.setInt(1, visitor.getId());

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

        } finally {
            // Close resources in a finally block to ensure they are closed even if an exception occurs
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
        return rowsAffected;
    }

    public List<PackageModel> getPackagesData(VisitorModel visitor) {
        List<PackageModel> packages = new ArrayList<>();
        FarmModel farmModel = new FarmModel();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);


            String query = "SELECT * FROM package WHERE visitor_id IN (0,?);";


            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, visitor.getId());

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);

            // Display function to show the Resultset
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                int visitorId = resultSet.getInt("visitor_id");
                PackageModel newPackage = new PackageModel(id, name, description, price, visitorId);
                packages.add(newPackage);
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
        return packages;
    }

    public List<ReservationModel> getReservationsData(VisitorModel visitor) {
        List<ReservationModel> reservations = new ArrayList<>();
        FarmModel farmModel = new FarmModel();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);


            String query = "SELECT rvd.id, rvd.date,package_id, discount_id,rvd.package,rvd.\"final price\"  FROM reservation join \"reservation visitor discount\" rvd on reservation.id=rvd.id WHERE visitor_id =?;";

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, visitor.getId());

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);

            // Display function to show the Resultset
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int visitorId = visitor.getId();
                String date = resultSet.getString("date");
                int discountId = resultSet.getInt("discount_id");
                int packageId = resultSet.getInt("package_id");
                String packageName = resultSet.getString("package");
                int finalPrice = resultSet.getInt("final price");
                ReservationModel newReservation = new ReservationModel(id, visitorId, date, discountId, packageId, packageName, finalPrice);
                reservations.add(newReservation);
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
        return reservations;
    }

    public PackageModel getPackage(String name) {
        PackageModel packageModel = new PackageModel();
        List<ReservationModel> reservations = new ArrayList<>();
        FarmModel farmModel = new FarmModel();
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query1 = "SELECT * FROM package WHERE name=?";

            preparedStatement1 = connection.prepareStatement(query1);

            preparedStatement1.setString(1, name);

            System.out.println(preparedStatement1);
            ResultSet resultSet = preparedStatement1.executeQuery();
            System.out.println(resultSet);

            // Display function to show the Resultset
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String packageName = name;
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                int visitorId = resultSet.getInt("visitor_id");
                String query2 = "SELECT discount_id FROM discounts_packages WHERE package_id=?";
                preparedStatement2 = connection.prepareStatement(query2);
                preparedStatement2.setInt(1, id);

                String[] discountOptions = new String[]{"", "", "", "", ""};

                ResultSet resultSet2 = preparedStatement2.executeQuery();
                int i = 0;
                while (resultSet2.next()) {
                    discountOptions[i] = packageModel.convertDiscountIdToOption(resultSet2.getInt("discount_id"));
                    i++;
                }

                packageModel = new PackageModel(id, name, description, price, visitorId, discountOptions);
            }


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (preparedStatement1 != null) {
                    preparedStatement1.close();
                }
                if (preparedStatement2 != null) {
                    preparedStatement2.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
        return packageModel;
    }

}
