package com.jacob_hayes;

public class Driver {

    public static void main(String[] args) {
        Driver test = new Driver();
    }

    public Driver() {
        //People tests
        testPerson();
        //Doctor tests
        testDoctor();
    }

    private void testPerson() {
        System.out.println("**Testing Person class!**");
        Person[] people = new Person[3];

        people[0] = new Person();
        people[1] = new Person("Bob");
        people[2] = new Person("Jacob");

        for (Person p : people) {
            p.writeOutput();
        }
        System.out.println("people[0].hasSameName(people[1]): " + people[0].hasSameName(people[1]));
        people[0].setName("Bob");
        System.out.println("people[0].hasSameName(people[1]): " + people[0].hasSameName(people[1]));

        for (Person p : people) {
            p.writeOutput();
        }
    }

    private void testDoctor() {
        System.out.println("**Testing Doctor class!**");
        Doctor[] doctors = new Doctor[4];
        doctors[0] = new Doctor("Rich People's General Practitioner", "George", 5499.99);
        doctors[1] = new Doctor("Poor man's Amputational Surgeon", "Joey");
        doctors[2] = new Doctor();
        doctors[3] = new Doctor();

        for (Doctor doc : doctors) {
            doc.writeOutput();
        }

        System.out.println("doctors[2].hasSameName(doctors[3]): " + doctors[2].hasSameName(doctors[3]));
        doctors[2].setName("Bob");
        doctors[3].setName("bob");
        System.out.println("doctors[2].hasSameName(doctors[3]): " + doctors[2].hasSameName(doctors[3]));

        for (Doctor doc : doctors) {
            doc.writeOutput();
        }

        System.out.println("doctors[2].hasSameSpecialty(doctors[3]): " + doctors[2].hasSameSpecialty(doctors[3]));
        doctors[2].setSpecialty("Dentist");
        doctors[3].setSpecialty("Oral Surgeon");
        System.out.println("doctors[2].hasSameSpecialty(doctors[3]): " + doctors[2].hasSameSpecialty(doctors[3]));

        for (Doctor doc : doctors) {
            doc.writeOutput();
        }

        System.out.println("doctors[2].hasSameSpecialty(doctors[3]): " + doctors[2].hasSameSpecialty(doctors[3]));
        doctors[2].setSpecialty(doctors[3].getSpecialty());
        System.out.println("doctors[2].hasSameSpecialty(doctors[3]): " + doctors[2].hasSameSpecialty(doctors[3]));

        for (Doctor doc : doctors) {
            doc.writeOutput();
        }
    }
}
