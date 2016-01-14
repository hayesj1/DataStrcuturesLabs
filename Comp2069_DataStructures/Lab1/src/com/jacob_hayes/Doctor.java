package com.jacob_hayes;

/**
 * Created by hayesj3 on 1/7/2016.
 */
public class Doctor extends Person {

    String specialty;
    double coPay;

    public Doctor() { this("No name", "No specialty!"); }
    public Doctor(String specialty, String name) { this(specialty, name, 0.0); }
    public Doctor(String specialty, String name, double coPay) {
        super(name);
        this.specialty = specialty;
        this.coPay = coPay;
    }

    @Override
    public void writeOutput() {
        super.writeOutput();
        System.out.println("Specialty: " + this.specialty);
    }

    public boolean hasSameSpecialty(Doctor other) {
        return (this.getName().equalsIgnoreCase(other.getName()));
    }

    private boolean checkEqual(Doctor other) {
        if (!this.hasSameName(other)) { return false; }
        if (!this.hasSameSpecialty(other)) { return false; }
        if (this.getCoPay() != other.getCoPay()) { return false; }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }
        if (other == null) { return false; }

        if (other instanceof Doctor) {
            Doctor doc = (Doctor)other;
            return this.checkEqual(doc);
        }
        return false;
    }

    public double getCoPay() { return coPay; }
    public void setCoPay(double coPay) { this.coPay = coPay; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
}
