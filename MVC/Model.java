package com.library.MVC;

import com.library.Database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class Model{
    String name;
    String[] properties;
    String primaryKey;
    private static final Connection connection
            = new Database("root", "yourPassword", "library").getConnection();

    public Model(String name, String[] properties, String primaryKey) {
        this.name = name;
        this.properties = properties;
        this.primaryKey = primaryKey;
    }

    public String[] getProperties() {
        return properties;
    }

    public void add() {
        Class<?> model = getClass();
        Iterator<String> fields = Arrays.stream(properties).iterator();
        String sql = "INSERT INTO " + name + " (" + String.join(",", properties) + ") " +
                "VALUES (" + "?,".repeat(properties.length - 1) + "?)";
        int i = 1;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            while (fields.hasNext()) {
                String field = fields.next();
                String nameOfMethod = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
                System.out.print(field + "=" + model.getMethod(nameOfMethod).invoke(this) + (fields.hasNext() ? "," : ""));
                statement.setObject(i++, model.getMethod(nameOfMethod).invoke(this));
            }
            statement.executeUpdate();
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | SQLException e) {
            System.out.println("Addition failed");
//            e.printStackTrace();
        }
    }

    public void delete() {
        Class<?> model = getClass();
        try {
            Field field = model.getDeclaredField(this.primaryKey);
            field.setAccessible(true);
            Object id = field.get(this);
            String sql = "DELETE FROM " + name + " WHERE " + this.primaryKey + " = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (NoSuchFieldException | IllegalAccessException | SQLException e) {
            System.out.println("Delete failed");
            e.printStackTrace();
        }
    }

    public void update() {
        Class<?> model = getClass();
        Field idField;
        String preparedValues = String.join("=?,", this.properties) + "=?";
        String sql = "UPDATE " + name + " SET " + preparedValues + " WHERE " + this.primaryKey + "=?";
        try {
            idField = model.getDeclaredField(this.primaryKey);
            idField.setAccessible(true);
            Object id = idField.get(this);
            Iterator<String> fields = Arrays.stream(properties).iterator();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(properties.length + 1, id);
            int i = 1;
            while (fields.hasNext()) {
                String field = fields.next();
                String nameOfMethod = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
                statement.setObject(i++, model.getMethod(nameOfMethod).invoke(this));
            }
            System.out.println(statement);
            statement.executeUpdate();
        } catch (NoSuchFieldException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | SQLException e) {
            System.out.println("Update failed");
            e.printStackTrace();
        }
    }

    public <T> List<T> query(String... args) {

        try {
            String sql = "SELECT * FROM " + name + (args.length > 0 ? " WHERE " + String.join(" AND ", args) : "");
            System.out.println(sql);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            int columns = results.getMetaData().getColumnCount();
            @SuppressWarnings("unchecked")
            Class<T> model = (Class<T>) getClass();
            List<T> objects = new ArrayList<>();
            while (results.next()) {
                T newObject = model.getConstructor().newInstance();
                for (var i = 1; i <= columns; i++) {
                    String field = results.getMetaData().getColumnName(i);
                    String nameOfMethod = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
                    Class<?> type = model.getMethod(nameOfMethod).getReturnType();
                    nameOfMethod = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
                    model.getMethod(nameOfMethod, type).invoke(newObject, results.getObject(i));
                }
                objects.add(newObject);
            }
            System.out.println(sql);
            return objects;
        } catch (SQLException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
