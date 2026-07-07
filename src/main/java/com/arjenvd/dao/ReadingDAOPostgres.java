package com.arjenvd.dao;

import com.arjenvd.model.Reading;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReadingDAOPostgres implements ReadingDAO {

    private Connection connection;

    public ReadingDAOPostgres(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean saveReading(Reading reading) {
        String SQL = "INSERT INTO readings(reading_time, temperature, humidity) VALUES (?,?,?)";

        try (PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setLong(1, reading.getReadingTime());
            statement.setInt(2, reading.getTemperature());
            statement.setInt(3, reading.getHumidity());

            return statement.executeUpdate() > 1;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save reading " + e);
        }
    }

    @Override
    public List<Reading> findAllReadings() {
        String SQL = "SELECT * FROM readings";
        List<Reading> readings = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                readings.add(new Reading(
                        resultSet.getInt("id"),
                        resultSet.getLong("reading_time"),
                        resultSet.getInt("temperature"),
                        resultSet.getInt("humidity")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find readings " + e);
        }
        return readings;
    }
}
