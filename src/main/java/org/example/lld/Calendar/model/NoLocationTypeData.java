package org.example.lld.Calendar.model;

public class NoLocationTypeData implements ILocationTypeData{

    @Override
    public String getLocationDetails() {
        return "";
    }

    @Override
    public LocationType getLocationType() {
        return LocationType.NO_LOCATION;
    }
}
