package org.example.lld.Logger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.lld.Logger.strategy.formatter.IFormatter;
import org.example.lld.Logger.repository.ISink;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
public class Configuration {
    private Level minLogLevel;
    private Map<Level, List<ISink>> sinks; //For each level there would be different sinks
    private String format; // [timestamp][threadname][level][content]
    private IFormatter formatterType;
    private String namespace;
}
