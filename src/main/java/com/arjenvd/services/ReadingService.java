package com.arjenvd.services;

import com.arjenvd.dao.ReadingDAO;
import com.arjenvd.dao.ReadingDAOPostgres;
import com.arjenvd.database.DbConnect;
import com.arjenvd.model.Reading;

public class ReadingService {

    private final ReadingDAO readingDAO;

    public ReadingService() {
        this.readingDAO = new ReadingDAOPostgres(DbConnect.getConnection());
    }

    public void saveReading(Reading reading) {
        // Set the reading time on server instead of raspberry pi.
        reading.setReadingTime(System.currentTimeMillis());
        readingDAO.saveReading(reading);
    }
}
