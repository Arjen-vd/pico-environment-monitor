package com.arjenvd.resources;

import com.arjenvd.model.Reading;
import com.arjenvd.services.ReadingService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/readings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReadingResource {
    private final ReadingService readingService;

    public ReadingResource () {
        this.readingService = new ReadingService();
    }

    @POST
    public Response SaveReading(Reading reading) {
        try {
            readingService.saveReading(reading);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
