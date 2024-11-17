import java.util.ArrayList;
import java.util.Scanner;

class Patient {
    int id;
    String name;
    int age;
    String ailment;

    Patient(int id, String name, int age, String ailment) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.ailment = ailment;
    }
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age + ", Ailment: " + ailment;
    }
}

class Doctor {
    int id;
    String name;
    String specialization;

    Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
    public String toString() {
        return "Doctor ID: " + id + ", Name: " + name + ", Specialization: " + specialization;
    }
}

class Appointment {
    int appointmentId;
    Patient patient;
    Doctor doctor;
    String date;

    Appointment(int appointmentId, Patient patient, Doctor doctor, String date) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

       public String toString() {
        return "Appointment ID: " + appointmentId + ", Patient: " + patient.name + ", Doctor: " + doctor.name + ", Date: " + date;
    }
}

public class Main {
    static ArrayList<Patient> patients = new ArrayList<>();
    static ArrayList<Doctor> doctors = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static int patientIdCounter = 1;
    static int doctorIdCounter = 1;
    static int appointmentIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Clinic Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. List Patients");
            System.out.println("3. Add Doctor");
            System.out.println("4. List Doctors");
            System.out.println("5. Schedule Appointment");
            System.out.println("6. View Appointments");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addPatient(scanner);
                    break;
                case 2:
                    listPatients();
                    break;
                case 3:
                    addDoctor(scanner);
                    break;
                case 4:
                    listDoctors();
                    break;
                case 5:
                    scheduleAppointment(scanner);
                    break;
                case 6:
                    viewAppointments();
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void addPatient(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter patient ailment: ");
        String ailment = scanner.nextLine();
        patients.add(new Patient(patientIdCounter++, name, age, ailment));
        System.out.println("Patient added successfully!");
    }

    static void listPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        }
    }

    static void addDoctor(Scanner scanner) {
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter doctor specialization: ");
        String specialization = scanner.nextLine();
        doctors.add(new Doctor(doctorIdCounter++, name, specialization));
        System.out.println("Doctor added successfully!");
    }

    static void listDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
        }
}



    static void scheduleAppointment(Scanner scanner) {
        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("Cannot schedule appointment. Ensure both patients and doctors are available.");
            return;
        }

        System.out.println("Select Patient by ID:");
        listPatients();
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Patient patient = patients.stream().filter(p -> p.id == patientId).findFirst().orElse(null);
        if (patient == null) {
            System.out.println("Invalid Patient ID.");
            return;
        }

        System.out.println("Select Doctor by ID:");
        listDoctors();
        int doctorId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Doctor doctor = doctors.stream().filter(d -> d.id == doctorId).findFirst().orElse(null);
        if (doctor == null) {
            System.out.println("Invalid Doctor ID.");
            return;
        }

        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        appointments.add(new Appointment(appointmentIdCounter++, patient, doctor, date));
        System.out.println("Appointment scheduled successfully!");
    }

    static void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }
}
