package org.example.lld.Logger.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Level {
   DEBUG(1),
   INFO(2),
   WARN(3),
   ERROR(4);

   private final int value;
}
