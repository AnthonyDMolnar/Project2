package edu.student.midlandstech.anthonydmolnar.project;

public class Measure {
    private int id;
    private String name;
    private double measure;

    public Measure (int newId, String newName, double newMeasure){
        id = newId;
        name = newName;
        measure = newMeasure;
    }

    public String getName() {return name; }
    public int getId() { return id;}
    public double getMeasure() { return measure;}

    public double toTeaspoons(double amount) { return measure*amount;}

    public double fromTeaspoons(double teaspoons) {return teaspoons/measure;}

    public String toString() { return id + " " + name + " " + measure;}
}
