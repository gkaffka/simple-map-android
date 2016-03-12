package com.kaffka.simplemap.Events;

import com.kaffka.simplemap.Models.Units;

final public class EventDataFound {
    final private Units units;

    public EventDataFound(Units units) {
        this.units = units;
    }

    public Units getUnits() {
        return units;
    }
}
