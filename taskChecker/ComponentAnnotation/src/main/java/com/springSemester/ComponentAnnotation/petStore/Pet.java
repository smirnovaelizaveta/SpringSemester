package com.springSemester.ComponentAnnotation.petStore;

import org.springframework.stereotype.Component;

public abstract class Pet {

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
