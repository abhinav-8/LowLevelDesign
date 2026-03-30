package org.example.lld.Calendar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PhysicalLocationTypeData implements ILocationTypeData {
    private double lat;
    private double lon;
    private String address;

    @Override
    public String getLocationDetails() {
        return ("Lat: " + lat + " Lon: " + lon + " Address: " + address);
    }

    @Override
    public LocationType getLocationType() {
        return LocationType.PHYSICAL;
    }
}
