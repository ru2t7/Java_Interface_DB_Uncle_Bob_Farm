package repository;

import model.AnimalModel;
import model.AnimalType;
import model.FarmModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmRepository {

    public List<AnimalModel> getData(int animalTypeId) {
        List<AnimalModel> animals = new ArrayList<>();
        FarmModel farmModel = new FarmModel();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query;
            if (animalTypeId == 0) {
                query = "SELECT * FROM animal;";
                preparedStatement = connection.prepareStatement(query);

            } else {
                query = "SELECT * FROM animal WHERE animal_type_id=?;";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, animalTypeId);
            }

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);

            // Display function to show the Resultset
            while (resultSet.next()) {
                int animalId = resultSet.getInt("id");
                String animalType = farmModel.convertIdToType(resultSet.getInt("animal_type_id"));
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int pen = resultSet.getInt("pen_id");
                AnimalModel newAnimal = new AnimalModel(animalId, animalTypeId, animalType, name, age, pen);
                animals.add(newAnimal);
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
        return animals;
    }

    public String createAnimal(AnimalModel animal) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

//            String query = "INSERT INTO person values('Bogdan', 'Test')";
            String query = "INSERT INTO animal (id,animal_type_id,name,age,pen_id) VALUES (?, ?,?,?,?);";
            preparedStatement = connection.prepareStatement(query);


            preparedStatement.setInt(1, animal.getAnimalId());
            preparedStatement.setInt(2, animal.getAnimalTypeId());
            preparedStatement.setString(3, animal.getName());
            preparedStatement.setInt(4, animal.getAge());
            preparedStatement.setInt(5, animal.getPen());

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
                    return "Animal Added! Connection Closed!";
                else
                    return "Animal Not Added! Connection Closed";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error at DB level";
            }
        }
    }

    public String deleteAnimal(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

//            String query = "INSERT INTO person values('Bogdan', 'Test')";
            String query = "DELETE FROM animal WHERE name=?";
            preparedStatement = connection.prepareStatement(query);


            preparedStatement.setString(1, name);

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
                    return "Animal Deleted! Connection Closed!";
                else
                    return "Animal Not Deleted! Connection Closed";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error at DB level";
            }
        }
    }


    public int updateAnimal(AnimalModel animal) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Integer rowsAffected = 0;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

//            String query = "INSERT INTO person values('Bogdan', 'Test')";
            String query = "UPDATE animal SET name=?, age=?, pen_id=? WHERE id=?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setInt(2, animal.getAge());
            preparedStatement.setInt(3, animal.getPen());
            preparedStatement.setInt(4, animal.getAnimalId());

            rowsAffected = preparedStatement.executeUpdate();

            // Display function to show the Resultset

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

    public void showAllAnimals() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

//            String query = "INSERT INTO person values('Bogdan', 'Test')";
            String query = "SELECT * FROM animal;";
            preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\nALL THE ANIMALS: \n");
            // Display function to show the Resultset
            while (resultSet.next()) {
                String type = resultSet.getString("animal_type_id");
                String name = resultSet.getString("name");
                String age = resultSet.getString("age");
                String pen = resultSet.getString("pen_id");
                System.out.println("type:" + type + "     name:" + name + "     age:" + age + "    pen:" + pen);
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
            String query = "SELECT id FROM animal WHERE id>= ALL (SELECT id from animal);";
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

    public boolean isThereSpace(int penId) {
        int ocupation = 0;
        int capacity = 0;
        boolean thereIsSpace = true;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);


            String query1 = "SELECT ocupation FROM \"ocupation of pens\" oop JOIN animal_type ON oop.\"animal type\" =animal_type.name WHERE oop.pen=?; ";
            preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setInt(1, penId);
            ResultSet resultSet1 = preparedStatement.executeQuery();

            while (resultSet1.next()) {
                ocupation = resultSet1.getInt("ocupation");
            }
            String query2 = "SELECT capacity FROM pen WHERE id=?; ";
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setInt(1, penId);
            ResultSet resultSet2 = preparedStatement.executeQuery();

            while (resultSet2.next()) {
                capacity = resultSet2.getInt("capacity");
            }

            thereIsSpace = ocupation != capacity;

//
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
        return thereIsSpace;
    }

    public List<Integer> getPenIds(int animalTypeId) {
        List<Integer> penIds = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String url = "jdbc:postgresql://localhost:5432/unclebobfarm";
            String dbUsername = "postgres";
            String dbPassword = "root";

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);


//            String query = "INSERT INTO person values('Bogdan', 'Test')";
            String query = "SELECT id FROM pen WHERE animal_type_id=?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, animalTypeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Display function to show the Resultset
            while (resultSet.next()) {
                penIds.add(resultSet.getInt("id"));
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
        return penIds;
    }

}

