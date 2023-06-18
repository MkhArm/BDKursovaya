package org.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseController {
    private Connection connection;

    public DatabaseController() {
        try {
            String url = "jdbc:postgresql://localhost/airport_db";
            String username = "postgres";
            String password = "221199";

            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField sql_field;
    @FXML
    private TableView tSql;

    @FXML
    public void handleQuerySQL(ActionEvent event) throws IOException {

        Statement statement = null;     // оператор запроса
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql_field.getText());

            ArrayList<Map<String, Object>> listObject = new ArrayList<>();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    row.put(columnName, value);
                }

                listObject.add(row);
            }

            System.out.println(listObject);

            tSql.getColumns().clear(); // удаление всех столбцов из таблицы
            tSql.getItems().clear(); // удаление всех элементов из ObservableList

            if (!listObject.isEmpty()) {
                Map<String, Object> firstRow = listObject.get(0);

                for (String columnName : firstRow.keySet()) {
                    TableColumn<Map<String, Object>, String> column = new TableColumn<>(columnName);
                    column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(columnName).toString()));
                    tSql.getColumns().add(column);
                }

                ObservableList<Map<String, Object>> data = FXCollections.observableArrayList(listObject);

                tSql.setItems(data);
                tSql.refresh();
            }

            resultSet.close();
            statement.close();


        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());     // SQL Standard
            System.out.println("ErrorCode: " + e.getErrorCode());   // Vendor-specific exception code for the Database
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private TextField sql_Hard11;
    @FXML
    private TextField sql_Hard12;

    public void handleHardQuerySQL1(ActionEvent actionEvent) {

        Statement statement = null;     // оператор запроса

        try {

            statement = connection.prepareStatement(" UPDATE Flight SET departure_date = ? WHERE flight_number = ?; ");
            String flight_number = sql_Hard11.getText();
            String departure_date = sql_Hard12.getText();
            ((PreparedStatement) statement).setDate(1, java.sql.Date.valueOf(departure_date));
            ((PreparedStatement) statement).setString(2, flight_number);
            ((PreparedStatement) statement).executeQuery(); // результат запроса на поиск

            statement.close();


        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());     // SQL Standard
            System.out.println("ErrorCode: " + e.getErrorCode());   // Vendor-specific exception code for the Database
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @FXML
    private TextField sql_Hard21;
    @FXML
    private TextField sql_Hard22;
    @FXML
    private TextField sql_Hard23;
    @FXML
    private TextField sql_Hard24;
    @FXML
    private TextField sql_Hard25;
    @FXML
    private TextField sql_Hard26;
    @FXML
    private TextField sql_Hard27;

    public void handleHardQuerySQL2(ActionEvent actionEvent) {

        Statement statement = null;     // оператор запроса

        try {

            statement = connection.prepareStatement("INSERT INTO Aircraft (tail_number, aircraft_type, runway_type, load_capacity, readiness) VALUES (?, ?, ?, ?, ?); DELETE FROM AssignedAircraft WHERE flight_number = ?; INSERT INTO AssignedAircraft (race_id, flight_number, tail_number) VALUES (?, ?, ?); ");
            String tail_number = sql_Hard21.getText();
            String aircraft_type = sql_Hard22.getText();
            String runway_type = sql_Hard23.getText();
            int load_capacity = Integer.parseInt(sql_Hard24.getText());
            Boolean readiness = Boolean.valueOf(sql_Hard25.getText());
            String flight_number = sql_Hard26.getText();
            int race_id = Integer.parseInt(sql_Hard27.getText());
            ((PreparedStatement) statement).setString(1, tail_number);
            ((PreparedStatement) statement).setString(2, aircraft_type);
            ((PreparedStatement) statement).setString(3, runway_type);
            ((PreparedStatement) statement).setInt(4, load_capacity);
            ((PreparedStatement) statement).setBoolean(5, readiness);
            ((PreparedStatement) statement).setString(6, flight_number);
            ((PreparedStatement) statement).setInt(7, race_id);
            ((PreparedStatement) statement).setString(8, flight_number);
            ((PreparedStatement) statement).setString(9, tail_number);
            ((PreparedStatement) statement).executeQuery(); // результат запроса на поиск

            statement.close();


        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());     // SQL Standard
            System.out.println("ErrorCode: " + e.getErrorCode());   // Vendor-specific exception code for the Database
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @FXML
    private TextField sql_Hard31;

    public void handleHardQuerySQL3(ActionEvent actionEvent) {

        Statement statement = null;     // оператор запроса

        try {

            statement = connection.prepareStatement("UPDATE Employee SET position = 'fired' WHERE personnel_number IN (SELECT personnel_number FROM Crew WHERE crew_number = ?); DELETE FROM CrewComposition WHERE crew_number = ?; DELETE FROM Crew WHERE crew_number = ?; DELETE FROM Employee WHERE position =  'fired'; ");
            int crew_number = Integer.parseInt(sql_Hard31.getText());
            ((PreparedStatement) statement).setInt(1, crew_number);
            ((PreparedStatement) statement).setInt(2, crew_number);
            ((PreparedStatement) statement).setInt(3, crew_number);
            ((PreparedStatement) statement).executeQuery(); // результат запроса на поиск

            statement.close();


        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());     // SQL Standard
            System.out.println("ErrorCode: " + e.getErrorCode());   // Vendor-specific exception code for the Database
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @FXML
    private TextField sql_Hard41;

    @FXML
    private TableView tHard;

    public void handleHardQuerySQL4(ActionEvent actionEvent) {
        Statement statement = null;     // оператор запроса

        try {

            statement = connection.prepareStatement("SELECT Aircraft.tail_number FROM Flight JOIN AssignedAircraft ON Flight.flight_number = AssignedAircraft.flight_number JOIN Aircraft ON AssignedAircraft.tail_number = Aircraft.tail_number WHERE Flight.departure_date = ?;");
            String departureDate = sql_Hard41.getText();
            ((PreparedStatement) statement).setDate(1, java.sql.Date.valueOf(departureDate));

            ResultSet resultSet = ((PreparedStatement) statement).executeQuery(); // результат запроса на поиск

            ArrayList<Map<String, Object>> listObject = new ArrayList<>();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    row.put(columnName, value);
                }

                listObject.add(row);
            }

            System.out.println(listObject);

            tHard.getColumns().clear(); // удаление всех столбцов из таблицы
            tHard.getItems().clear(); // удаление всех элементов из ObservableList

            if (!listObject.isEmpty()) {
                Map<String, Object> firstRow = listObject.get(0);

                for (String columnName : firstRow.keySet()) {
                    TableColumn<Map<String, Object>, String> column = new TableColumn<>(columnName);
                    column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(columnName).toString()));
                    tHard.getColumns().add(column);
                }

                ObservableList<Map<String, Object>> data = FXCollections.observableArrayList(listObject);

                tHard.setItems(data);
                tHard.refresh();
            }

            statement.close();


        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());     // SQL Standard
            System.out.println("ErrorCode: " + e.getErrorCode());   // Vendor-specific exception code for the Database
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private TextField sql_Hard51;
    public void handleHardQuerySQL5(ActionEvent actionEvent) {

        Statement statement = null;     // оператор запроса

        try {

            statement = connection.prepareStatement("SELECT DISTINCT PreparingRunway.shift_id, PreparingRunway.shift_number, PreparingRunway.runway_number FROM PreparingRunway CROSS JOIN Shift JOIN AircraftBeing ON AircraftBeing.shift_number = Shift.shift_number JOIN Aircraft ON AircraftBeing.tail_number = Aircraft.tail_number JOIN Runway ON PreparingRunway.runway_number = Runway.runway_number WHERE Aircraft.aircraft_type = ? AND Aircraft.runway_type = Runway.runway_type;");
            String aircraftType = sql_Hard51.getText();
            ((PreparedStatement) statement).setString(1, aircraftType);

            ResultSet resultSet = ((PreparedStatement) statement).executeQuery(); // результат запроса на поиск

            ArrayList<Map<String, Object>> listObject = new ArrayList<>();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    row.put(columnName, value);
                }

                listObject.add(row);
            }

            System.out.println(listObject);

            tHard.getColumns().clear(); // удаление всех столбцов из таблицы
            tHard.getItems().clear(); // удаление всех элементов из ObservableList

            if (!listObject.isEmpty()) {
                Map<String, Object> firstRow = listObject.get(0);

                for (String columnName : firstRow.keySet()) {
                    TableColumn<Map<String, Object>, String> column = new TableColumn<>(columnName);
                    column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(columnName).toString()));
                    tHard.getColumns().add(column);
                }

                ObservableList<Map<String, Object>> data = FXCollections.observableArrayList(listObject);

                tHard.setItems(data);
                tHard.refresh();
            }

            statement.close();


        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());     // SQL Standard
            System.out.println("ErrorCode: " + e.getErrorCode());   // Vendor-specific exception code for the Database
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }

    }
    @FXML
    private TextField sql_Hard61;
    @FXML
    private TextField sql_Hard62;
    public void handleHardQuerySQL6(ActionEvent actionEvent) {

        Statement statement = null;     // оператор запроса

        try {

            statement = connection.prepareStatement("SELECT Employee.full_name, Employee.education, Employee.start_date FROM Employee WHERE Employee.education = ? AND extract(year from current_date) - extract(year from Employee.start_date) > ? ORDER BY full_name DESC;");
            String education = sql_Hard61.getText();
            String experience = sql_Hard62.getText();
            ((PreparedStatement) statement).setString(1, education);
            ((PreparedStatement) statement).setInt(2, Integer.parseInt(experience));

            ResultSet resultSet = ((PreparedStatement) statement).executeQuery(); // результат запроса на поиск

            ArrayList<Map<String, Object>> listObject = new ArrayList<>();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    row.put(columnName, value);
                }

                listObject.add(row);
            }

            System.out.println(listObject);

            tHard.getColumns().clear(); // удаление всех столбцов из таблицы
            tHard.getItems().clear(); // удаление всех элементов из ObservableList

            if (!listObject.isEmpty()) {
                Map<String, Object> firstRow = listObject.get(0);

                for (String columnName : firstRow.keySet()) {
                    TableColumn<Map<String, Object>, String> column = new TableColumn<>(columnName);
                    column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(columnName).toString()));
                    tHard.getColumns().add(column);
                }

                ObservableList<Map<String, Object>> data = FXCollections.observableArrayList(listObject);

                tHard.setItems(data);
                tHard.refresh();
            }

            statement.close();


        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());     // SQL Standard
            System.out.println("ErrorCode: " + e.getErrorCode());   // Vendor-specific exception code for the Database
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @FXML
    private TextField sql_Hard71;
    public void handleHardQuerySQL7(ActionEvent actionEvent) {

        Statement statement = null;     // оператор запроса

        try {

            statement = connection.prepareStatement("SELECT Aircraft.tail_number, Aircraft.aircraft_type, Aircraft.runway_type, Aircraft.load_capacity, Aircraft.readiness FROM AircraftBeing JOIN Shift ON AircraftBeing.shift_number = Shift.shift_number JOIN Aircraft ON AircraftBeing.tail_number = Aircraft.tail_number WHERE Shift.shift_number = ? ORDER BY load_capacity DESC;");
            String shiftNumber = sql_Hard71.getText();
            ((PreparedStatement) statement).setInt(1, Integer.parseInt(shiftNumber));

            ResultSet resultSet = ((PreparedStatement) statement).executeQuery(); // результат запроса на поиск

            ArrayList<Map<String, Object>> listObject = new ArrayList<>();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    row.put(columnName, value);
                }

                listObject.add(row);
            }

            System.out.println(listObject);

            tHard.getColumns().clear(); // удаление всех столбцов из таблицы
            tHard.getItems().clear(); // удаление всех элементов из ObservableList

            if (!listObject.isEmpty()) {
                Map<String, Object> firstRow = listObject.get(0);

                for (String columnName : firstRow.keySet()) {
                    TableColumn<Map<String, Object>, String> column = new TableColumn<>(columnName);
                    column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(columnName).toString()));
                    tHard.getColumns().add(column);
                }

                ObservableList<Map<String, Object>> data = FXCollections.observableArrayList(listObject);

                tHard.setItems(data);
                tHard.refresh();
            }

            statement.close();


        } catch (SQLException e) {
            System.out.println("SQLState: " + e.getSQLState());     // SQL Standard
            System.out.println("ErrorCode: " + e.getErrorCode());   // Vendor-specific exception code for the Database
            System.out.println("Message: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public List<Runway> getAllRunways() {
        List<Runway> runways = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Runway");

            while (resultSet.next()) {
                String runwayNumber = resultSet.getString("runway_number");
                int length = resultSet.getInt("length");
                String runwayClass = resultSet.getString("class");
                String runwayType = resultSet.getString("runway_type");

                Runway runway = new Runway(runwayNumber, length, runwayClass, runwayType);
                System.out.println(runwayNumber + "  " + length + "  " + runwayClass + "  " + runwayType);
                runways.add(runway);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return runways;
    }

    public void insertRunway(ActionEvent actionEvent) {
        try {

            String runwayNumber = sql_field11.getText();
            String length = sql_field12.getText();
            String runwayClass = sql_field13.getText();
            String runwayType = sql_field14.getText();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO Runway (runway_number, length, class, runway_type) VALUES (?, ?, ?, ?)");
            statement.setString(1, runwayNumber);
            statement.setInt(2, Integer.parseInt(length));
            statement.setString(3, runwayClass);
            statement.setString(4, runwayType);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Runway> deleteRunway() {
        List<Runway> runways = new ArrayList<>();

        try {
            String runwayNumber = sql_field11.getText();
            String length = sql_field12.getText();
            String runwayClass = sql_field13.getText();
            String runwayType = sql_field14.getText();

            StringBuilder queryBuilder = new StringBuilder("DELETE FROM Runway WHERE 1=1");

            if (runwayNumber != null && !runwayNumber.isEmpty()) {
                queryBuilder.append(" AND runway_number = ?");
            }

            if (length != null && !length.isEmpty()) {
                queryBuilder.append(" AND length = ?");
            }

            if (runwayClass != null && !runwayClass.isEmpty()) {
                queryBuilder.append(" AND class = ?");
            }

            if (runwayType != null && !runwayType.isEmpty()) {
                queryBuilder.append(" AND runway_type = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (runwayNumber != null && !runwayNumber.isEmpty()) {
                statement.setString(paramIndex++, runwayNumber);
            }

            if (length != null && !length.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(length));
            }

            if (runwayClass != null && !runwayClass.isEmpty()) {
                statement.setString(paramIndex++, runwayClass);
            }

            if (runwayType != null && !runwayType.isEmpty()) {
                statement.setString(paramIndex, runwayType);
            }

            ResultSet resultSet = statement.executeQuery();

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return runways;
    }

    @FXML
    private TableView<Runway> tRunway;

    @FXML
    public void refreshRunway(ActionEvent actionEvent) {

        TableColumn<Runway, ?> colum = tRunway.getColumns().get(0); // Получение 1 столбца
        colum.setCellValueFactory(new PropertyValueFactory<>("runwayNumber"));
        colum = tRunway.getColumns().get(1); // Получение 2 столбца
        colum.setCellValueFactory(new PropertyValueFactory<>("length"));
        colum = tRunway.getColumns().get(2); // Получение 3 столбца
        colum.setCellValueFactory(new PropertyValueFactory<>("runwayClass"));
        colum = tRunway.getColumns().get(3); // Получение 4 столбца
        colum.setCellValueFactory(new PropertyValueFactory<>("runwayType"));

        List<Runway> runwayData = getAllRunways();

        // Очистка TableView перед заполнением новыми данными
        tRunway.getItems().clear();

        // Добавление данных в таблицу
        for (Runway runway : runwayData) {
            tRunway.getItems().add(runway);
        }
        System.out.println();
    }

    @FXML
    private TextField sql_field11;
    @FXML
    private TextField sql_field12;
    @FXML
    private TextField sql_field13;
    @FXML
    private TextField sql_field14;

    public List<Runway> getRunways() {
        List<Runway> runways = new ArrayList<>();

        try {
            String runwayNumber = sql_field11.getText();
            String length = sql_field12.getText();
            String runwayClass = sql_field13.getText();
            String runwayType = sql_field14.getText();

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Runway WHERE 1=1");

            if (runwayNumber != null && !runwayNumber.isEmpty()) {
                queryBuilder.append(" AND runway_number = ?");
            }

            if (length != null && !length.isEmpty()) {
                queryBuilder.append(" AND length = ?");
            }

            if (runwayClass != null && !runwayClass.isEmpty()) {
                queryBuilder.append(" AND class = ?");
            }

            if (runwayType != null && !runwayType.isEmpty()) {
                queryBuilder.append(" AND runway_type = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (runwayNumber != null && !runwayNumber.isEmpty()) {
                statement.setString(paramIndex++, runwayNumber);
            }

            if (length != null && !length.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(length));
            }

            if (runwayClass != null && !runwayClass.isEmpty()) {
                statement.setString(paramIndex++, runwayClass);
            }

            if (runwayType != null && !runwayType.isEmpty()) {
                statement.setString(paramIndex, runwayType);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                runwayNumber = resultSet.getString("runway_number");
                int runwayLength = resultSet.getInt("length");
                runwayClass = resultSet.getString("class");
                runwayType = resultSet.getString("runway_type");

                Runway runway = new Runway(runwayNumber, runwayLength, runwayClass, runwayType);
                System.out.println(runwayNumber + "  " + runwayLength + "  " + runwayClass + "  " + runwayType);
                runways.add(runway);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return runways;
    }


    @FXML
    private TextField sql_field21;
    @FXML
    private TextField sql_field22;
    @FXML
    public void searchRunway(ActionEvent actionEvent) {

        TableColumn<Runway, ?> colum = tRunway.getColumns().get(0); // Получение 1 столбца
        colum.setCellValueFactory(new PropertyValueFactory<>("runwayNumber"));
        colum = tRunway.getColumns().get(1); // Получение 2 столбца
        colum.setCellValueFactory(new PropertyValueFactory<>("length"));
        colum = tRunway.getColumns().get(2); // Получение 3 столбца
        colum.setCellValueFactory(new PropertyValueFactory<>("runwayClass"));
        colum = tRunway.getColumns().get(3); // Получение 4 столбца
        colum.setCellValueFactory(new PropertyValueFactory<>("runwayType"));

        List<Runway> runwayData = getRunways();

        // Очистка TableView перед заполнением новыми данными
        tRunway.getItems().clear();

        // Добавление данных в таблицу
        for (Runway runway : runwayData) {
            tRunway.getItems().add(runway);
        }
        System.out.println();

    }

    public List<Shift> getAllShifts() {
        List<Shift> shifts = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Shift");

            while (resultSet.next()) {
                int shiftNumber = resultSet.getInt("shift_number");
                String periodDay = resultSet.getString("period_day");

                Shift shift = new Shift(shiftNumber, periodDay);
                System.out.println(shiftNumber + "  " + periodDay);
                shifts.add(shift);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shifts;
    }

    @FXML
    private TableView<Shift> tShift;

    @FXML
    public void refreshShift(ActionEvent actionEvent) {

        TableColumn<Shift, ?> colum = tShift.getColumns().get(0); // Получение 1 столбца
        colum.setCellValueFactory(new PropertyValueFactory<>("shiftNumber"));
        colum = tShift.getColumns().get(1); // Получение 2 столбца
        colum.setCellValueFactory(new PropertyValueFactory<>("periodDay"));

        List<Shift> shiftData = getAllShifts();

        // Очистка TableView перед заполнением новыми данными
        tShift.getItems().clear();

        // Добавление данных в таблицу
        for (Shift shift : shiftData) {
            tShift.getItems().add(shift);
        }
        System.out.println();
    }

    @FXML
    public void searchShift(ActionEvent actionEvent) {

        TableColumn<Shift, ?> colum = tShift.getColumns().get(0); // Получение 1 столбца
        colum.setCellValueFactory(new PropertyValueFactory<>("shiftNumber"));
        colum = tShift.getColumns().get(1); // Получение 2 столбца
        colum.setCellValueFactory(new PropertyValueFactory<>("periodDay"));

        List<Shift> runwayData = getShifts();

        // Очистка TableView перед заполнением новыми данными
        tShift.getItems().clear();

        // Добавление данных в таблицу
        for (Shift shift : runwayData) {
            tShift.getItems().add(shift);
        }
        System.out.println();

    }

    public void insertShift(ActionEvent actionEvent) {
        try {

            String shiftNumber = sql_field21.getText();
            String periodDay = sql_field22.getText();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO Shift (shift_number, period_day) VALUES (?, ?)");
            statement.setInt(1, Integer.parseInt(shiftNumber));
            statement.setString(2, periodDay);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Shift> getShifts() {
        List<Shift> shifts = new ArrayList<>();

        try {
            String shiftNumber = sql_field21.getText();
            String periodDay = sql_field22.getText();

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Shift WHERE 1=1");

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                queryBuilder.append(" AND shift_number = ?");
            }

            if (periodDay != null && !periodDay.isEmpty()) {
                queryBuilder.append(" AND period_day = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(shiftNumber));
            }

            if (periodDay != null && !periodDay.isEmpty()) {
                statement.setString(paramIndex, periodDay);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int sshiftNumber = resultSet.getInt("shift_number");
                String speriodDay = resultSet.getString("period_day");

                Shift shift = new Shift(sshiftNumber, speriodDay);
                System.out.println(shiftNumber + "  " + periodDay);
                shifts.add(shift);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shifts;
    }

    public List<Shift> deleteShift() {
        List<Shift> shifts = new ArrayList<>();

        try {

            String shiftNumber = sql_field21.getText();
            String periodDay = sql_field22.getText();

            StringBuilder queryBuilder = new StringBuilder("DELETE FROM Shift WHERE 1=1");

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                queryBuilder.append(" AND shift_number = ?");
            }

            if (periodDay != null && !periodDay.isEmpty()) {
                queryBuilder.append(" AND period_day = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(shiftNumber));
            }

            if (periodDay != null && !periodDay.isEmpty()) {
                statement.setString(paramIndex, periodDay);
            }

            ResultSet resultSet = statement.executeQuery();

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shifts;
    }

    @FXML
    private TextField sql_field31;
    @FXML
    private TextField sql_field32;
    @FXML
    private TextField sql_field33;
    @FXML
    private TextField sql_field34;
    @FXML
    private TextField sql_field35;

    public List<Aircraft> getAllAircrafts() {
        List<Aircraft> aircrafts = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Aircraft");

            while (resultSet.next()) {
                String tailNumber = resultSet.getString("tail_number");
                String aircraftType = resultSet.getString("aircraft_type");
                String runwayType = resultSet.getString("runway_type");
                int loadCapacity = resultSet.getInt("load_capacity");
                boolean readiness = resultSet.getBoolean("readiness");

                Aircraft aircraft = new Aircraft(tailNumber, aircraftType, runwayType, loadCapacity, readiness);
                aircrafts.add(aircraft);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aircrafts;
    }

    @FXML
    private TableView<Aircraft> tAircraft;

    @FXML
    public void refreshAircraft(ActionEvent actionEvent) {

        TableColumn<Aircraft, ?> colum = tAircraft.getColumns().get(0);
        colum.setCellValueFactory(new PropertyValueFactory<>("tailNumber"));
        colum = tAircraft.getColumns().get(1);
        colum.setCellValueFactory(new PropertyValueFactory<>("aircraftType"));
        colum = tAircraft.getColumns().get(2);
        colum.setCellValueFactory(new PropertyValueFactory<>("runwayType"));
        colum = tAircraft.getColumns().get(3);
        colum.setCellValueFactory(new PropertyValueFactory<>("loadCapacity"));
        colum = tAircraft.getColumns().get(4);
        colum.setCellValueFactory(new PropertyValueFactory<>("readiness"));

        List<Aircraft> aircraftData = getAllAircrafts();

        tAircraft.getItems().clear();

        for (Aircraft aircraft : aircraftData) {
            tAircraft.getItems().add(aircraft);
        }
    }


    public void insertAircraft(ActionEvent actionEvent) {
        try {
            String tailNumber = sql_field31.getText();
            String aircraftType = sql_field32.getText();
            String runwayType = sql_field33.getText();
            int loadCapacity = Integer.parseInt(sql_field34.getText());
            boolean readiness = Boolean.parseBoolean(sql_field35.getText());

            PreparedStatement statement = connection.prepareStatement("INSERT INTO Aircraft (tail_number, aircraft_type, runway_type, load_capacity, readiness) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, tailNumber);
            statement.setString(2, aircraftType);
            statement.setString(3, runwayType);
            statement.setInt(4, loadCapacity);
            statement.setBoolean(5, readiness);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void searchAircraft(ActionEvent actionEvent) {
        TableColumn<Aircraft, ?> colum = tAircraft.getColumns().get(0);
        colum.setCellValueFactory(new PropertyValueFactory<>("tailNumber"));
        colum = tAircraft.getColumns().get(1);
        colum.setCellValueFactory(new PropertyValueFactory<>("aircraftType"));
        colum = tAircraft.getColumns().get(2);
        colum.setCellValueFactory(new PropertyValueFactory<>("runwayType"));
        colum = tAircraft.getColumns().get(3);
        colum.setCellValueFactory(new PropertyValueFactory<>("loadCapacity"));
        colum = tAircraft.getColumns().get(4);
        colum.setCellValueFactory(new PropertyValueFactory<>("readiness"));

        List<Aircraft> aircraftData = getAircrafts();

        tAircraft.getItems().clear();

        for (Aircraft aircraft : aircraftData) {
            tAircraft.getItems().add(aircraft);
        }
    }

    public List<Aircraft> getAircrafts() {
        List<Aircraft> aircrafts = new ArrayList<>();

        try {
            String tailNumber = sql_field31.getText();
            String aircraftType = sql_field32.getText();
            String runwayType = sql_field33.getText();
            String loadCapacity = sql_field34.getText();
            String readiness =sql_field35.getText();

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Aircraft WHERE 1=1");

            if (tailNumber != null && !tailNumber.isEmpty()) {
                queryBuilder.append(" AND tail_number = ?");
            }

            if (aircraftType != null && !aircraftType.isEmpty()) {
                queryBuilder.append(" AND aircraft_type = ?");
            }

            if (runwayType != null && !runwayType.isEmpty()) {
                queryBuilder.append(" AND runway_type = ?");
            }

            if (loadCapacity != null && !loadCapacity.isEmpty()) {
                queryBuilder.append(" AND load_capacity = ?");
            }

            if (readiness != null && !readiness.isEmpty()) {
                queryBuilder.append(" AND readiness = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (tailNumber != null && !tailNumber.isEmpty()) {
                statement.setString(paramIndex++, tailNumber);
            }

            if (aircraftType != null && !aircraftType.isEmpty()) {
                statement.setString(paramIndex++, aircraftType);
            }

            if (runwayType != null && !runwayType.isEmpty()) {
                statement.setString(paramIndex++, runwayType);
            }

            if (loadCapacity != null && !loadCapacity.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(loadCapacity));
            }

            if (readiness != null && !readiness.isEmpty()) {
                statement.setBoolean(paramIndex, Boolean.parseBoolean(readiness));
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String stailNumber = resultSet.getString("tail_number");
                String saircraftType = resultSet.getString("aircraft_type");
                String srunwayType = resultSet.getString("runway_type");
                int sloadCapacity = resultSet.getInt("load_capacity");
                boolean sreadiness = resultSet.getBoolean("readiness");

                Aircraft aircraft = new Aircraft(stailNumber, saircraftType, srunwayType, sloadCapacity, sreadiness);
                aircrafts.add(aircraft);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aircrafts;
    }



    public void deleteAircraft(ActionEvent actionEvent) {
        try {
            String tailNumber = sql_field31.getText();
            String aircraftType = sql_field32.getText();
            String runwayType = sql_field33.getText();
            String loadCapacity = sql_field34.getText();
            String readiness = sql_field35.getText();

            StringBuilder queryBuilder = new StringBuilder("DELETE FROM Aircraft WHERE 1=1");

            if (tailNumber != null && !tailNumber.isEmpty()) {
                queryBuilder.append(" AND tail_number = ?");
            }

            if (aircraftType != null && !aircraftType.isEmpty()) {
                queryBuilder.append(" AND aircraft_type = ?");
            }

            if (runwayType != null && !runwayType.isEmpty()) {
                queryBuilder.append(" AND runway_type = ?");
            }

            if (loadCapacity != null && !loadCapacity.isEmpty()) {
                queryBuilder.append(" AND load_capacity = ?");
            }

            if (readiness != null && !readiness.isEmpty()) {
                queryBuilder.append(" AND readiness = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (tailNumber != null && !tailNumber.isEmpty()) {
                statement.setString(paramIndex++, tailNumber);
            }

            if (aircraftType != null && !aircraftType.isEmpty()) {
                statement.setString(paramIndex++, aircraftType);
            }

            if (runwayType != null && !runwayType.isEmpty()) {
                statement.setString(paramIndex++, runwayType);
            }

            if (loadCapacity != null && !loadCapacity.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(loadCapacity));
            }

            if (readiness != null && !readiness.isEmpty()) {
                statement.setBoolean(paramIndex, Boolean.parseBoolean(readiness));
            }

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField sql_field41;
    @FXML
    private TextField sql_field42;
    @FXML
    private TextField sql_field43;
    @FXML
    private TextField sql_field44;

    @FXML
    private TableView<Flight> tFlight;

    @FXML
    public void refreshFlight(ActionEvent actionEvent) {

        TableColumn<Flight, ?> column = tFlight.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        column = tFlight.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("runwayNumber"));
        column = tFlight.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("range"));
        column = tFlight.getColumns().get(3);
        column.setCellValueFactory(new PropertyValueFactory<>("departureDate"));

        List<Flight> flightData = getAllFlights();

        tFlight.getItems().clear();

        for (Flight flight : flightData) {
            tFlight.getItems().add(flight);
        }
    }


    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Flight");

            while (resultSet.next()) {
                String flightNumber = resultSet.getString("flight_number");
                String runwayNumber = resultSet.getString("runway_number");
                int range = resultSet.getInt("range");
                Date departureDate = resultSet.getDate("departure_date");

                Flight flight = new Flight(flightNumber, runwayNumber, range, departureDate);
                flights.add(flight);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public void insertFlight(ActionEvent actionEvent) {
        try {
            String flightNumber = sql_field41.getText();
            String runwayNumber = sql_field42.getText();
            String range = sql_field43.getText();
            String departureDate = sql_field44.getText();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO Flight (flight_number, runway_number, range, departure_date) VALUES (?, ?, ?, ?)");
            statement.setString(1, flightNumber);
            statement.setString(2, runwayNumber);
            statement.setInt(3, Integer.parseInt(range));
            statement.setDate(4, java.sql.Date.valueOf(departureDate));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchFlight(ActionEvent actionEvent) {
        TableColumn<Flight, ?> column = tFlight.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        column = tFlight.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("runwayNumber"));
        column = tFlight.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("range"));
        column = tFlight.getColumns().get(3);
        column.setCellValueFactory(new PropertyValueFactory<>("departureDate"));

        List<Flight> flightData = getFlights();

        tFlight.getItems().clear();

        for (Flight flight : flightData) {
            tFlight.getItems().add(flight);
        }
    }

    public List<Flight> getFlights() {
        List<Flight> flights = new ArrayList<>();

        try {
            String flightNumber = sql_field41.getText();
            String runwayNumber = sql_field42.getText();
            String range = sql_field43.getText();
            String departureDate = sql_field44.getText();

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Flight WHERE 1=1");

            if (flightNumber != null && !flightNumber.isEmpty()) {
                queryBuilder.append(" AND flight_number = ?");
            }

            if (runwayNumber != null && !runwayNumber.isEmpty()) {
                queryBuilder.append(" AND runway_number = ?");
            }

            if (range != null && !range.isEmpty()) {
                queryBuilder.append(" AND range = ?");
            }

            if (departureDate != null && !departureDate.isEmpty()) {
                queryBuilder.append(" AND departure_date = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (flightNumber != null && !flightNumber.isEmpty()) {
                statement.setString(paramIndex++, flightNumber);
            }

            if (runwayNumber != null && !runwayNumber.isEmpty()) {
                statement.setString(paramIndex++, runwayNumber);
            }

            if (range != null && !range.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(range));
            }

            if (departureDate != null && !departureDate.isEmpty()) {
                statement.setDate(paramIndex, java.sql.Date.valueOf(departureDate));
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String fFlightNumber = resultSet.getString("flight_number");
                String fRunwayNumber = resultSet.getString("runway_number");
                int fRange = resultSet.getInt("range");
                Date fDepartureDate = resultSet.getDate("departure_date");

                Flight flight = new Flight(fFlightNumber, fRunwayNumber, fRange, fDepartureDate);
                flights.add(flight);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    public void deleteFlight(ActionEvent actionEvent) {
        try {
            String flightNumber = sql_field41.getText();
            String runwayNumber = sql_field42.getText();
            String range = sql_field43.getText();
            String departureDate = sql_field44.getText();

            StringBuilder queryBuilder = new StringBuilder("DELETE FROM Flight WHERE 1=1");

            if (flightNumber != null && !flightNumber.isEmpty()) {
                queryBuilder.append(" AND flight_number = ?");
            }

            if (runwayNumber != null && !runwayNumber.isEmpty()) {
                queryBuilder.append(" AND runway_number = ?");
            }

            if (range != null && !range.isEmpty()) {
                queryBuilder.append(" AND range = ?");
            }

            if (departureDate != null && !departureDate.isEmpty()) {
                queryBuilder.append(" AND departure_date = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (flightNumber != null && !flightNumber.isEmpty()) {
                statement.setString(paramIndex++, flightNumber);
            }

            if (runwayNumber != null && !runwayNumber.isEmpty()) {
                statement.setString(paramIndex++, runwayNumber);
            }

            if (range != null && !range.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(range));
            }

            if (departureDate != null && !departureDate.isEmpty()) {
                statement.setDate(paramIndex, java.sql.Date.valueOf(departureDate));
            }

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField sql_field51;
    @FXML
    private TextField sql_field52;
    @FXML
    private TextField sql_field53;

    @FXML
    private TableView<AssignedAircraft> tAssignedAircraft;

    @FXML
    public void refreshAssignedAircraft(ActionEvent actionEvent) {
        TableColumn<AssignedAircraft, ?> column = tAssignedAircraft.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("raceId"));
        column = tAssignedAircraft.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        column = tAssignedAircraft.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("tailNumber"));

        List<AssignedAircraft> assignedAircraftData = getAllAssignedAircraft();

        tAssignedAircraft.getItems().clear();

        for (AssignedAircraft assignedAircraft : assignedAircraftData) {
            tAssignedAircraft.getItems().add(assignedAircraft);
        }
    }

    public List<AssignedAircraft> getAllAssignedAircraft() {
        List<AssignedAircraft> assignedAircraftList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM AssignedAircraft");

            while (resultSet.next()) {
                int raceId = resultSet.getInt("race_id");
                String flightNumber = resultSet.getString("flight_number");
                String tailNumber = resultSet.getString("tail_number");

                AssignedAircraft assignedAircraft = new AssignedAircraft(raceId, flightNumber, tailNumber);
                assignedAircraftList.add(assignedAircraft);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assignedAircraftList;
    }

    public void insertAssignedAircraft(ActionEvent actionEvent) {
        try {
            String raceId = sql_field51.getText();
            String flightNumber = sql_field52.getText();
            String tailNumber = sql_field53.getText();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO AssignedAircraft (race_id, flight_number, tail_number) VALUES (?, ?, ?)");
            statement.setInt(1, Integer.parseInt(raceId));
            statement.setString(2, flightNumber);
            statement.setString(3, tailNumber);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchAssignedAircraft(ActionEvent actionEvent) {
        TableColumn<AssignedAircraft, ?> column = tAssignedAircraft.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("raceId"));
        column = tAssignedAircraft.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("flightNumber"));
        column = tAssignedAircraft.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("tailNumber"));

        List<AssignedAircraft> assignedAircraftData = getAssignedAircraft();

        tAssignedAircraft.getItems().clear();

        for (AssignedAircraft assignedAircraft : assignedAircraftData) {
            tAssignedAircraft.getItems().add(assignedAircraft);
        }
    }

    public List<AssignedAircraft> getAssignedAircraft() {
        List<AssignedAircraft> assignedAircraftList = new ArrayList<>();

        try {
            String raceId = sql_field51.getText();
            String flightNumber = sql_field52.getText();
            String tailNumber = sql_field53.getText();

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM AssignedAircraft WHERE 1=1");

            if (raceId != null && !raceId.isEmpty()) {
                queryBuilder.append(" AND race_id = ?");
            }

            if (flightNumber != null && !flightNumber.isEmpty()) {
                queryBuilder.append(" AND flight_number = ?");
            }

            if (tailNumber != null && !tailNumber.isEmpty()) {
                queryBuilder.append(" AND tail_number = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (raceId != null && !raceId.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(raceId));
            }

            if (flightNumber != null && !flightNumber.isEmpty()) {
                statement.setString(paramIndex++, flightNumber);
            }

            if (tailNumber != null && !tailNumber.isEmpty()) {
                statement.setString(paramIndex, tailNumber);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int fRaceId = resultSet.getInt("race_id");
                String fFlightNumber = resultSet.getString("flight_number");
                String fTailNumber = resultSet.getString("tail_number");

                AssignedAircraft assignedAircraft = new AssignedAircraft(fRaceId, fFlightNumber, fTailNumber);
                assignedAircraftList.add(assignedAircraft);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return assignedAircraftList;
    }

    public void deleteAssignedAircraft(ActionEvent actionEvent) {
        try {
            String raceId = sql_field51.getText();
            String flightNumber = sql_field52.getText();
            String tailNumber = sql_field53.getText();

            StringBuilder queryBuilder = new StringBuilder("DELETE FROM AssignedAircraft WHERE 1=1");

            if (raceId != null && !raceId.isEmpty()) {
                queryBuilder.append(" AND race_id = ?");
            }

            if (flightNumber != null && !flightNumber.isEmpty()) {
                queryBuilder.append(" AND flight_number = ?");
            }

            if (tailNumber != null && !tailNumber.isEmpty()) {
                queryBuilder.append(" AND tail_number = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (raceId != null && !raceId.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(raceId));
            }

            if (flightNumber != null && !flightNumber.isEmpty()) {
                statement.setString(paramIndex++, flightNumber);
            }

            if (tailNumber != null && !tailNumber.isEmpty()) {
                statement.setString(paramIndex, tailNumber);
            }

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private TextField sql_field61;
    @FXML
    private TextField sql_field62;
    @FXML
    private TextField sql_field63;

    @FXML
    private TableView<AircraftBeing> tAircraftBeing;

    @FXML
    public void refreshAircraftBeing(ActionEvent actionEvent) {
        TableColumn<AircraftBeing, ?> column = tAircraftBeing.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("aircraftId"));
        column = tAircraftBeing.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("tailNumber"));
        column = tAircraftBeing.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("shiftNumber"));

        List<AircraftBeing> aircraftBeingData = getAllAircraftBeing();

        tAircraftBeing.getItems().clear();

        for (AircraftBeing aircraftBeing : aircraftBeingData) {
            tAircraftBeing.getItems().add(aircraftBeing);
        }
    }

    public List<AircraftBeing> getAllAircraftBeing() {
        List<AircraftBeing> aircraftBeingList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM AircraftBeing");

            while (resultSet.next()) {
                int aircraftId = resultSet.getInt("aircraft_id");
                String tailNumber = resultSet.getString("tail_number");
                int shiftNumber = resultSet.getInt("shift_number");

                AircraftBeing aircraftBeing = new AircraftBeing(aircraftId, tailNumber, shiftNumber);
                aircraftBeingList.add(aircraftBeing);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aircraftBeingList;
    }

    public void insertAircraftBeing(ActionEvent actionEvent) {
        try {
            String aircraftId = sql_field61.getText();
            String tailNumber = sql_field62.getText();
            String shiftNumber = sql_field63.getText();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO AircraftBeing (aircraft_id, tail_number, shift_number) VALUES (?, ?, ?)");
            statement.setInt(1, Integer.parseInt(aircraftId));
            statement.setString(2, tailNumber);
            statement.setInt(3, Integer.parseInt(shiftNumber));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchAircraftBeing(ActionEvent actionEvent) {
        TableColumn<AircraftBeing, ?> column = tAircraftBeing.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("aircraftId"));
        column = tAircraftBeing.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("tailNumber"));
        column = tAircraftBeing.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("shiftNumber"));

        List<AircraftBeing> aircraftBeingData = getAircraftBeing();

        tAircraftBeing.getItems().clear();

        for (AircraftBeing aircraftBeing : aircraftBeingData) {
            tAircraftBeing.getItems().add(aircraftBeing);
        }
    }

    public List<AircraftBeing> getAircraftBeing() {
        List<AircraftBeing> aircraftBeingList = new ArrayList<>();

        try {
            String aircraftId = sql_field61.getText();
            String tailNumber = sql_field62.getText();
            String shiftNumber = sql_field63.getText();

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM AircraftBeing WHERE 1=1");

            if (aircraftId != null && !aircraftId.isEmpty()) {
                queryBuilder.append(" AND aircraft_id = ?");
            }

            if (tailNumber != null && !tailNumber.isEmpty()) {
                queryBuilder.append(" AND tail_number = ?");
            }

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                queryBuilder.append(" AND shift_number = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (aircraftId != null && !aircraftId.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(aircraftId));
            }

            if (tailNumber != null && !tailNumber.isEmpty()) {
                statement.setString(paramIndex++, tailNumber);
            }

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                statement.setInt(paramIndex, Integer.parseInt(shiftNumber));
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int fAircraftId = resultSet.getInt("aircraft_id");
                String fTailNumber = resultSet.getString("tail_number");
                int fShiftNumber = resultSet.getInt("shift_number");

                AircraftBeing aircraftBeing = new AircraftBeing(fAircraftId, fTailNumber, fShiftNumber);
                aircraftBeingList.add(aircraftBeing);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aircraftBeingList;
    }

    public void deleteAircraftBeing(ActionEvent actionEvent) {
        try {
            String aircraftId = sql_field61.getText();
            String tailNumber = sql_field62.getText();
            String shiftNumber = sql_field63.getText();

            StringBuilder queryBuilder = new StringBuilder("DELETE FROM AircraftBeing WHERE 1=1");

            if (aircraftId != null && !aircraftId.isEmpty()) {
                queryBuilder.append(" AND aircraft_id = ?");
            }

            if (tailNumber != null && !tailNumber.isEmpty()) {
                queryBuilder.append(" AND tail_number = ?");
            }

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                queryBuilder.append(" AND shift_number = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (aircraftId != null && !aircraftId.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(aircraftId));
            }

            if (tailNumber != null && !tailNumber.isEmpty()) {
                statement.setString(paramIndex++, tailNumber);
            }

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                statement.setInt(paramIndex, Integer.parseInt(shiftNumber));
            }

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private TextField sql_field71;
    @FXML
    private TextField sql_field72;
    @FXML
    private TextField sql_field73;

    @FXML
    private TableView<PreparingRunway> tPreparingRunway;

    @FXML
    public void refreshPreparingRunway(ActionEvent actionEvent) {
        TableColumn<PreparingRunway, ?> column = tPreparingRunway.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("shiftId"));
        column = tPreparingRunway.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("shiftNumber"));
        column = tPreparingRunway.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("runwayNumber"));

        List<PreparingRunway> preparingRunwayData = getAllPreparingRunway();

        tPreparingRunway.getItems().clear();

        for (PreparingRunway preparingRunway : preparingRunwayData) {
            tPreparingRunway.getItems().add(preparingRunway);
        }
    }

    public List<PreparingRunway> getAllPreparingRunway() {
        List<PreparingRunway> preparingRunwayList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PreparingRunway");

            while (resultSet.next()) {
                int shiftId = resultSet.getInt("shift_id");
                int shiftNumber = resultSet.getInt("shift_number");
                String runwayNumber = resultSet.getString("runway_number");

                PreparingRunway preparingRunway = new PreparingRunway(shiftId, shiftNumber, runwayNumber);
                preparingRunwayList.add(preparingRunway);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparingRunwayList;
    }

    public void insertPreparingRunway(ActionEvent actionEvent) {
        try {
            String shiftId = sql_field71.getText();
            String shiftNumber = sql_field72.getText();
            String runwayNumber = sql_field73.getText();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO PreparingRunway (shift_id, shift_number, runway_number) VALUES (?, ?, ?)");
            statement.setInt(1, Integer.parseInt(shiftId));
            statement.setInt(2, Integer.parseInt(shiftNumber));
            statement.setString(3, runwayNumber);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchPreparingRunway(ActionEvent actionEvent) {
        TableColumn<PreparingRunway, ?> column = tPreparingRunway.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("shiftId"));
        column = tPreparingRunway.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("shiftNumber"));
        column = tPreparingRunway.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("runwayNumber"));

        List<PreparingRunway> preparingRunwayData = getPreparingRunway();

        tPreparingRunway.getItems().clear();

        for (PreparingRunway preparingRunway : preparingRunwayData) {
            tPreparingRunway.getItems().add(preparingRunway);
        }
    }

    public List<PreparingRunway> getPreparingRunway() {
        List<PreparingRunway> preparingRunwayList = new ArrayList<>();

        try {
            String shiftId = sql_field71.getText();
            String shiftNumber = sql_field72.getText();
            String runwayNumber = sql_field73.getText();

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM PreparingRunway WHERE 1=1");

            if (shiftId != null && !shiftId.isEmpty()) {
                queryBuilder.append(" AND shift_id = ?");
            }

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                queryBuilder.append(" AND shift_number = ?");
            }

            if (runwayNumber != null && !runwayNumber.isEmpty()) {
                queryBuilder.append(" AND runway_number = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (shiftId != null && !shiftId.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(shiftId));
            }

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(shiftNumber));
            }

            if (runwayNumber != null && !runwayNumber.isEmpty()) {
                statement.setString(paramIndex, runwayNumber);
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int fShiftId = resultSet.getInt("shift_id");
                int fShiftNumber = resultSet.getInt("shift_number");
                String fRunwayNumber = resultSet.getString("runway_number");

                PreparingRunway preparingRunway = new PreparingRunway(fShiftId, fShiftNumber, fRunwayNumber);
                preparingRunwayList.add(preparingRunway);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparingRunwayList;
    }

    public void deletePreparingRunway(ActionEvent actionEvent) {
        try {
            String shiftId = sql_field71.getText();
            String shiftNumber = sql_field72.getText();
            String runwayNumber = sql_field73.getText();

            StringBuilder queryBuilder = new StringBuilder("DELETE FROM PreparingRunway WHERE 1=1");

            if (shiftId != null && !shiftId.isEmpty()) {
                queryBuilder.append(" AND shift_id = ?");
            }

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                queryBuilder.append(" AND shift_number = ?");
            }

            if (runwayNumber != null && !runwayNumber.isEmpty()) {
                queryBuilder.append(" AND runway_number = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (shiftId != null && !shiftId.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(shiftId));
            }

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(shiftNumber));
            }

            if (runwayNumber != null && !runwayNumber.isEmpty()) {
                statement.setString(paramIndex, runwayNumber);
            }

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private TextField sql_field81;
    @FXML
    private TextField sql_field82;
    @FXML
    private TextField sql_field83;

    @FXML
    private TableView<ShiftComposition> tShiftComposition;

    @FXML
    public void refreshShiftComposition(ActionEvent actionEvent) {
        TableColumn<ShiftComposition, ?> column = tShiftComposition.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("shiftId"));
        column = tShiftComposition.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("shiftNumber"));
        column = tShiftComposition.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("personnelNumber"));

        List<ShiftComposition> shiftCompositionData = getAllShiftComposition();

        tShiftComposition.getItems().clear();

        for (ShiftComposition shiftComposition : shiftCompositionData) {
            tShiftComposition.getItems().add(shiftComposition);
        }
    }

    public List<ShiftComposition> getAllShiftComposition() {
        List<ShiftComposition> shiftCompositionList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ShiftComposition");

            while (resultSet.next()) {
                int shiftId = resultSet.getInt("shift_id");
                int shiftNumber = resultSet.getInt("shift_number");
                int personnelNumber = resultSet.getInt("personnel_number");

                ShiftComposition shiftComposition = new ShiftComposition(shiftId, shiftNumber, personnelNumber);
                shiftCompositionList.add(shiftComposition);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shiftCompositionList;
    }

    public void insertShiftComposition(ActionEvent actionEvent) {
        try {
            String shiftId = sql_field81.getText();
            String shiftNumber = sql_field82.getText();
            String personnelNumber = sql_field83.getText();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO ShiftComposition (shift_id, shift_number, personnel_number) VALUES (?, ?, ?)");
            statement.setInt(1, Integer.parseInt(shiftId));
            statement.setInt(2, Integer.parseInt(shiftNumber));
            statement.setInt(3, Integer.parseInt(personnelNumber));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchShiftComposition(ActionEvent actionEvent) {
        TableColumn<ShiftComposition, ?> column = tShiftComposition.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("shiftId"));
        column = tShiftComposition.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("shiftNumber"));
        column = tShiftComposition.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("personnelNumber"));

        List<ShiftComposition> shiftCompositionData = getShiftComposition();

        tShiftComposition.getItems().clear();

        for (ShiftComposition shiftComposition : shiftCompositionData) {
            tShiftComposition.getItems().add(shiftComposition);
        }
    }

    public List<ShiftComposition> getShiftComposition() {
        List<ShiftComposition> shiftCompositionList = new ArrayList<>();

        try {
            String shiftId = sql_field81.getText();
            String shiftNumber = sql_field82.getText();
            String personnelNumber = sql_field83.getText();

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ShiftComposition WHERE 1=1");

            if (shiftId != null && !shiftId.isEmpty()) {
                queryBuilder.append(" AND shift_id = ?");
            }

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                queryBuilder.append(" AND shift_number = ?");
            }

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                queryBuilder.append(" AND personnel_number = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (shiftId != null && !shiftId.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(shiftId));
            }

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(shiftNumber));
            }

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                statement.setInt(paramIndex, Integer.parseInt(personnelNumber));
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int fShiftId = resultSet.getInt("shift_id");
                int fShiftNumber = resultSet.getInt("shift_number");
                int fPersonnelNumber = resultSet.getInt("personnel_number");

                ShiftComposition shiftComposition = new ShiftComposition(fShiftId, fShiftNumber, fPersonnelNumber);
                shiftCompositionList.add(shiftComposition);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shiftCompositionList;
    }

    public void deleteShiftComposition(ActionEvent actionEvent) {
        try {
            String shiftId = sql_field81.getText();
            String shiftNumber = sql_field82.getText();
            String personnelNumber = sql_field83.getText();

            StringBuilder queryBuilder = new StringBuilder("DELETE FROM ShiftComposition WHERE 1=1");

            if (shiftId != null && !shiftId.isEmpty()) {
                queryBuilder.append(" AND shift_id = ?");
            }

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                queryBuilder.append(" AND shift_number = ?");
            }

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                queryBuilder.append(" AND personnel_number = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (shiftId != null && !shiftId.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(shiftId));
            }

            if (shiftNumber != null && !shiftNumber.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(shiftNumber));
            }

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                statement.setInt(paramIndex, Integer.parseInt(personnelNumber));
            }

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private TextField sql_field91; // для поля personnelNumber
    @FXML
    private TextField sql_field92; // для поля fullName
    @FXML
    private TextField sql_field93; // для поля education
    @FXML
    private TextField sql_field94; // для поля position
    @FXML
    private TextField sql_field95; // для поля startDate

    @FXML
    private TableView<Employee> tEmployee;

    @FXML
    public void refreshEmployee(ActionEvent actionEvent) {
        TableColumn<Employee, ?> column = tEmployee.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("personnelNumber"));
        column = tEmployee.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        column = tEmployee.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("education"));
        column = tEmployee.getColumns().get(3);
        column.setCellValueFactory(new PropertyValueFactory<>("position"));
        column = tEmployee.getColumns().get(4);
        column.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        List<Employee> employeeData = getAllEmployees();

        tEmployee.getItems().clear();

        for (Employee employee : employeeData) {
            tEmployee.getItems().add(employee);
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Employee");

            while (resultSet.next()) {
                int personnelNumber = resultSet.getInt("personnel_number");
                String fullName = resultSet.getString("full_name");
                String education = resultSet.getString("education");
                String position = resultSet.getString("position");
                Date startDate = resultSet.getDate("start_date");

                Employee employee = new Employee(personnelNumber, fullName, education, position, startDate);
                employeeList.add(employee);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    public void insertEmployee(ActionEvent actionEvent) {
        try {
            String personnelNumber = sql_field91.getText();
            String fullName = sql_field92.getText();
            String education = sql_field93.getText();
            String position = sql_field94.getText();
            String startDate = sql_field95.getText();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO Employee (personnel_number, full_name, education, position, start_date) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, Integer.parseInt(personnelNumber));
            statement.setString(2, fullName);
            statement.setString(3, education);
            statement.setString(4, position);
            statement.setDate(5, java.sql.Date.valueOf(startDate));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchEmployee(ActionEvent actionEvent) {
        TableColumn<Employee, ?> column = tEmployee.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("personnelNumber"));
        column = tEmployee.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        column = tEmployee.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("education"));
        column = tEmployee.getColumns().get(3);
        column.setCellValueFactory(new PropertyValueFactory<>("position"));
        column = tEmployee.getColumns().get(4);
        column.setCellValueFactory(new PropertyValueFactory<>("startDate"));

        List<Employee> employeeData = getEmployees();

        tEmployee.getItems().clear();

        for (Employee employee : employeeData) {
            tEmployee.getItems().add(employee);
        }
    }

    public List<Employee> getEmployees() {
        List<Employee> employeeList = new ArrayList<>();

        try {
            String personnelNumber = sql_field91.getText();
            String fullName = sql_field92.getText();
            String education = sql_field93.getText();
            String position = sql_field94.getText();
            String startDate = sql_field95.getText();

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Employee WHERE 1=1");

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                queryBuilder.append(" AND personnel_number = ?");
            }

            if (fullName != null && !fullName.isEmpty()) {
                queryBuilder.append(" AND full_name = ?");
            }

            if (education != null && !education.isEmpty()) {
                queryBuilder.append(" AND education = ?");
            }

            if (position != null && !position.isEmpty()) {
                queryBuilder.append(" AND position = ?");
            }

            if (startDate != null && !startDate.isEmpty()) {
                queryBuilder.append(" AND start_date = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(personnelNumber));
            }

            if (fullName != null && !fullName.isEmpty()) {
                statement.setString(paramIndex++, fullName);
            }

            if (education != null && !education.isEmpty()) {
                statement.setString(paramIndex++, education);
            }

            if (position != null && !position.isEmpty()) {
                statement.setString(paramIndex++, position);
            }

            if (startDate != null && !startDate.isEmpty()) {
                statement.setDate(paramIndex, java.sql.Date.valueOf(startDate));
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int fPersonnelNumber = resultSet.getInt("personnel_number");
                String fFullName = resultSet.getString("full_name");
                String fEducation = resultSet.getString("education");
                String fPosition = resultSet.getString("position");
                Date fStartDate = resultSet.getDate("start_date");

                Employee employee = new Employee(fPersonnelNumber, fFullName, fEducation, fPosition, fStartDate);
                employeeList.add(employee);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    public void deleteEmployee(ActionEvent actionEvent) {
        try {
            String personnelNumber = sql_field91.getText();
            String fullName = sql_field92.getText();
            String education = sql_field93.getText();
            String position = sql_field94.getText();
            String startDate = sql_field95.getText();

            StringBuilder queryBuilder = new StringBuilder("DELETE FROM Employee WHERE 1=1");

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                queryBuilder.append(" AND personnel_number = ?");
            }

            if (fullName != null && !fullName.isEmpty()) {
                queryBuilder.append(" AND full_name = ?");
            }

            if (education != null && !education.isEmpty()) {
                queryBuilder.append(" AND education = ?");
            }

            if (position != null && !position.isEmpty()) {
                queryBuilder.append(" AND position = ?");
            }

            if (startDate != null && !startDate.isEmpty()) {
                queryBuilder.append(" AND start_date = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(personnelNumber));
            }

            if (fullName != null && !fullName.isEmpty()) {
                statement.setString(paramIndex++, fullName);
            }

            if (education != null && !education.isEmpty()) {
                statement.setString(paramIndex++, education);
            }

            if (position != null && !position.isEmpty()) {
                statement.setString(paramIndex++, position);
            }

            if (startDate != null && !startDate.isEmpty()) {
                statement.setDate(paramIndex, java.sql.Date.valueOf(startDate));
            }

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private TextField sql_field101;
    @FXML
    private TextField sql_field102;
    @FXML
    private TextField sql_field103;

    @FXML
    private TableView<CrewComposition> tCrewComposition;

    @FXML
    public void refreshCrewComposition(ActionEvent actionEvent) {
        TableColumn<CrewComposition, ?> column = tCrewComposition.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        column = tCrewComposition.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("crewNumber"));
        column = tCrewComposition.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("personnelNumber"));

        List<CrewComposition> crewCompositionData = getAllCrewComposition();

        tCrewComposition.getItems().clear();

        for (CrewComposition crewComposition : crewCompositionData) {
            tCrewComposition.getItems().add(crewComposition);
        }
    }

    public List<CrewComposition> getAllCrewComposition() {
        List<CrewComposition> crewCompositionList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CrewComposition");

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                int crewNumber = resultSet.getInt("crew_number");
                int personnelNumber = resultSet.getInt("personnel_number");

                CrewComposition crewComposition = new CrewComposition(employeeId, crewNumber, personnelNumber);
                crewCompositionList.add(crewComposition);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return crewCompositionList;
    }

    public void insertCrewComposition(ActionEvent actionEvent) {
        try {
            String employeeId = sql_field101.getText();
            String crewNumber = sql_field102.getText();
            String personnelNumber = sql_field103.getText();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO CrewComposition (employee_id, crew_number, personnel_number) VALUES (?, ?, ?)");
            statement.setInt(1, Integer.parseInt(employeeId));
            statement.setInt(2, Integer.parseInt(crewNumber));
            statement.setInt(3, Integer.parseInt(personnelNumber));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchCrewComposition(ActionEvent actionEvent) {
        TableColumn<CrewComposition, ?> column = tCrewComposition.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        column = tCrewComposition.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("crewNumber"));
        column = tCrewComposition.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("personnelNumber"));

        List<CrewComposition> crewCompositionData = getCrewComposition();

        tCrewComposition.getItems().clear();

        for (CrewComposition crewComposition : crewCompositionData) {
            tCrewComposition.getItems().add(crewComposition);
        }
    }

    public List<CrewComposition> getCrewComposition() {
        List<CrewComposition> crewCompositionList = new ArrayList<>();

        try {
            String employeeId = sql_field101.getText();
            String crewNumber = sql_field102.getText();
            String personnelNumber = sql_field103.getText();

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM CrewComposition WHERE 1=1");

            if (employeeId != null && !employeeId.isEmpty()) {
                queryBuilder.append(" AND employee_id = ?");
            }

            if (crewNumber != null && !crewNumber.isEmpty()) {
                queryBuilder.append(" AND crew_number = ?");
            }

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                queryBuilder.append(" AND personnel_number = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (employeeId != null && !employeeId.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(employeeId));
            }

            if (crewNumber != null && !crewNumber.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(crewNumber));
            }

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                statement.setInt(paramIndex, Integer.parseInt(personnelNumber));
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int fEmployeeId = resultSet.getInt("employee_id");
                int fCrewNumber = resultSet.getInt("crew_number");
                int fPersonnelNumber = resultSet.getInt("personnel_number");

                CrewComposition crewComposition = new CrewComposition(fEmployeeId, fCrewNumber, fPersonnelNumber);
                crewCompositionList.add(crewComposition);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return crewCompositionList;
    }

    public void deleteCrewComposition(ActionEvent actionEvent) {
        try {
            String employeeId = sql_field101.getText();
            String crewNumber = sql_field102.getText();
            String personnelNumber = sql_field103.getText();

            StringBuilder queryBuilder = new StringBuilder("DELETE FROM CrewComposition WHERE 1=1");

            if (employeeId != null && !employeeId.isEmpty()) {
                queryBuilder.append(" AND employee_id = ?");
            }

            if (crewNumber != null && !crewNumber.isEmpty()) {
                queryBuilder.append(" AND crew_number = ?");
            }

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                queryBuilder.append(" AND personnel_number = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (employeeId != null && !employeeId.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(employeeId));
            }

            if (crewNumber != null && !crewNumber.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(crewNumber));
            }

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                statement.setInt(paramIndex, Integer.parseInt(personnelNumber));
            }

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private TextField sql_field111;
    @FXML
    private TextField sql_field112;
    @FXML
    private TextField sql_field113;

    @FXML
    private TableView<Crew> tCrew;

    @FXML
    public void refreshCrew(ActionEvent actionEvent) {
        TableColumn<Crew, ?> column = tCrew.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("crewNumber"));
        column = tCrew.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("tailNumber"));
        column = tCrew.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("personnelNumber"));

        List<Crew> crewData = getAllCrew();

        tCrew.getItems().clear();

        for (Crew crew : crewData) {
            tCrew.getItems().add(crew);
        }
    }

    public List<Crew> getAllCrew() {
        List<Crew> crewList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Crew");

            while (resultSet.next()) {
                int crewNumber = resultSet.getInt("crew_number");
                String tailNumber = resultSet.getString("tail_number");
                int personnelNumber = resultSet.getInt("personnel_number");

                Crew crew = new Crew(crewNumber, tailNumber, personnelNumber);
                crewList.add(crew);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return crewList;
    }

    public void insertCrew(ActionEvent actionEvent) {
        try {
            String crewNumber = sql_field111.getText();
            String tailNumber = sql_field112.getText();
            String personnelNumber = sql_field113.getText();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO Crew (crew_number, tail_number, personnel_number) VALUES (?, ?, ?)");
            statement.setInt(1, Integer.parseInt(crewNumber));
            statement.setString(2, tailNumber);
            statement.setInt(3, Integer.parseInt(personnelNumber));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchCrew(ActionEvent actionEvent) {
        TableColumn<Crew, ?> column = tCrew.getColumns().get(0);
        column.setCellValueFactory(new PropertyValueFactory<>("crewNumber"));
        column = tCrew.getColumns().get(1);
        column.setCellValueFactory(new PropertyValueFactory<>("tailNumber"));
        column = tCrew.getColumns().get(2);
        column.setCellValueFactory(new PropertyValueFactory<>("personnelNumber"));

        List<Crew> crewData = getCrew();

        tCrew.getItems().clear();

        for (Crew crew : crewData) {
            tCrew.getItems().add(crew);
        }
    }

    public List<Crew> getCrew() {
        List<Crew> crewList = new ArrayList<>();

        try {
            String crewNumber = sql_field111.getText();
            String tailNumber = sql_field112.getText();
            String personnelNumber = sql_field113.getText();

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Crew WHERE 1=1");

            if (crewNumber != null && !crewNumber.isEmpty()) {
                queryBuilder.append(" AND crew_number = ?");
            }

            if (tailNumber != null && !tailNumber.isEmpty()) {
                queryBuilder.append(" AND tail_number = ?");
            }

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                queryBuilder.append(" AND personnel_number = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (crewNumber != null && !crewNumber.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(crewNumber));
            }

            if (tailNumber != null && !tailNumber.isEmpty()) {
                statement.setString(paramIndex++, tailNumber);
            }

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                statement.setInt(paramIndex, Integer.parseInt(personnelNumber));
            }

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int fCrewNumber = resultSet.getInt("crew_number");
                String fTailNumber = resultSet.getString("tail_number");
                int fPersonnelNumber = resultSet.getInt("personnel_number");

                Crew crew = new Crew(fCrewNumber, fTailNumber, fPersonnelNumber);
                crewList.add(crew);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return crewList;
    }

    public void deleteCrew(ActionEvent actionEvent) {
        try {
            String crewNumber = sql_field111.getText();
            String tailNumber = sql_field112.getText();
            String personnelNumber = sql_field113.getText();

            StringBuilder queryBuilder = new StringBuilder("DELETE FROM Crew WHERE 1=1");

            if (crewNumber != null && !crewNumber.isEmpty()) {
                queryBuilder.append(" AND crew_number = ?");
            }

            if (tailNumber != null && !tailNumber.isEmpty()) {
                queryBuilder.append(" AND tail_number = ?");
            }

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                queryBuilder.append(" AND personnel_number = ?");
            }

            String query = queryBuilder.toString();

            PreparedStatement statement = connection.prepareStatement(query);

            int paramIndex = 1;

            if (crewNumber != null && !crewNumber.isEmpty()) {
                statement.setInt(paramIndex++, Integer.parseInt(crewNumber));
            }

            if (tailNumber != null && !tailNumber.isEmpty()) {
                statement.setString(paramIndex++, tailNumber);
            }

            if (personnelNumber != null && !personnelNumber.isEmpty()) {
                statement.setInt(paramIndex, Integer.parseInt(personnelNumber));
            }

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
