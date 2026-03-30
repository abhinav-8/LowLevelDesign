package org.example.lld.Calendar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HybridLocationTypeData implements ILocationTypeData {
    private PhysicalLocationTypeData physicalLocation;
    private VirtualLocationTypeData virtualLocation;

    @Override
    public String getLocationDetails() {
        return physicalLocation.getLocationDetails() + " " + virtualLocation.getLocationDetails();
    }

    @Override
    public LocationType getLocationType() {
        return LocationType.HYBRID;
    }
}
