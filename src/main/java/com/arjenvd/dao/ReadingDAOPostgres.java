package com.arjenvd.dao;

import com.arjenvd.model.Reading;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            throw new RuntimeException("Failed to save reading");
        }
    }
}
