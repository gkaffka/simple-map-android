package com.kaffka.simplemap.Models;

import java.util.List;

final public class Units {
    private final List<Unit> d;

    public Units(List<Unit> unidades) {
        this.d = unidades;
    }

    public List<Unit> getUnitList() {
        return d;
    }
}
