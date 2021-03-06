package io.muic.ooc.fab.Factory;

import io.muic.ooc.fab.Field;
import io.muic.ooc.fab.Location;

import java.util.HashMap;
import java.util.Map;

public class AnimalFactory {
    private static Map<Species, Class> animalClassMap = new HashMap<Species, Class>() {{
        Species[] species = Species.values();
        for (Species value : species) {
            put(value, value.getAnimalClass());
        }
    }};
    public static Animal createAnimal(Species species, Field field, Location location) {
        Class animalClass = animalClassMap.get(species);
        return createAnimal(animalClass, field, location);
    }
    public static Animal createAnimal(Class animalClass, Field field, Location location) {
        if (animalClass != null) {
            try {
                Animal animal = (Animal) animalClass.newInstance();
                animal.initialise(true, field, location);
                return animal;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException();
    }
}
