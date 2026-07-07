package com.arjenvd.dao;

import com.arjenvd.model.Reading;

import java.util.List;

public interface ReadingDAO {
    boolean saveReading(Reading reading);
    List<Reading> findAllReadings();
}
