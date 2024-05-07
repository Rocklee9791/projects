import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class FeePaymentSystem {
    private String studentName;
    private double feeAmount;
    private double paidAmount;

    public FeePaymentSystem(String studentName, double feeAmount) {
        this.studentName = studentName;
        this.feeAmount = feeAmount;
        this.paidAmount = 0.0;
    }

    public String getStudentName() {
        return studentName;
    }

    public double getFeeAmount() {
        return feeAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public double getRemainingAmount() {
        return feeAmount - paidAmount;
    }

    public void makePayment(double amount) {
        if (getRemainingAmount() == 0) {
            System.out.println("Fee has already been paid in full.");
            return;
        }

        double remainingAmount = getRemainingAmount();

        if (amount >= remainingAmount) {
            paidAmount += remainingAmount;
            System.out.println("Payment successful! Remaining amount: $" + (feeAmount - paidAmount));
        } else {
            paidAmount += amount;
            System.out.println("Partial payment of $" + amount + " made. Remaining amount: $" + (feeAmount - paidAmount));
        }
    }
}

public class FeePaymentSystemProject {
    private static Map<String, FeePaymentSystem> studentRecords = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("***** Welcome to Fee payment and Management System *****");
            System.out.println("1. Add Student");
            System.out.println("2. Make Payment");
            System.out.println("3. Display Student Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    makePayment(scanner);
                    break;
                case 3:
                    displayStudentDetails(scanner);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void addStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String studentName = scanner.next();

        if (studentRecords.containsKey(studentName)) {
            System.out.println("Student already exists.");
            return;
        }

        System.out.print("Enter fee amount: ");
        double feeAmount = scanner.nextDouble();

        FeePaymentSystem feePayment = new FeePaymentSystem(studentName, feeAmount);
        studentRecords.put(studentName, feePayment);

        System.out.println("Student added successfully!");
    }

    public static void makePayment(Scanner scanner) {
        System.out.print("Enter student name: ");
        String studentName = scanner.next();

        if (!studentRecords.containsKey(studentName)) {
            System.out.println("Student not found.");
            return;
        }

        FeePaymentSystem feePayment = studentRecords.get(studentName);

        if (feePayment.getRemainingAmount() == 0) {
            System.out.println("Fee has already been paid in full.");
            return;
        }

        System.out.print("Enter payment amount: ");
        double paymentAmount = scanner.nextDouble();

        feePayment.makePayment(paymentAmount);
    }

    public static void displayStudentDetails(Scanner scanner) {
        System.out.print("Enter student name: ");
        String studentName = scanner.next();

        if (!studentRecords.containsKey(studentName)) {
            System.out.println("Student not found.");
            return;
        }

        FeePaymentSystem feePayment = studentRecords.get(studentName);

        System.out.println("Student Name: " + feePayment.getStudentName());
        System.out.println("Fee Amount: $" + feePayment.getFeeAmount());
        System.out.println("Paid Amount: $" + feePayment.getPaidAmount());
        System.out.println(" Amount: $" + feePayment.getRemainingAmount());
    }
}
