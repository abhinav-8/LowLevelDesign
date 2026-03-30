package org.example.lld.Calendar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class VirtualLocationTypeData implements ILocationTypeData {
    private String meetingUrl;
    private String platform;

    @Override
    public String getLocationDetails() {
        return ("Meeting platform: " + platform + " Meeting URL: " + meetingUrl);
    }

    @Override
    public LocationType getLocationType() {
        return LocationType.VIRTUAL;
    }
}
