import java.util.Scanner;

// ---------------- SUPER CLASS ----------------
class CashTree {
    String name, codeno, location;

    public CashTree(String name, String codeno, String location) {
        this.name = name;
        this.codeno = codeno;
        this.location = location;
    }

    public void viewBalance(double balance) {
        System.out.println("Current Balance: " + balance);
    }

    public double withdraw(double balance, double amount) {
        System.out.println("Withdraw in CashTree");
        return balance;
    }

    public double deposit(double balance, double amount) {
        System.out.println("Deposit in CashTree");
        return balance;
    }
}

// ---------------- SBI BANK (Child Class) ----------------
class SBI_Bank extends CashTree {
    String cust_name;
    int accno;
    double balance;
    double serviceCharge = 5;
    double interest_rate = 0.06;

    public SBI_Bank(String name, String codeno, String location,
                    String cust_name, int accno, double balance) {
        super(name, codeno, location);
        this.cust_name = cust_name;
        this.accno = accno;
        this.balance = balance;
    }

    @Override
    public double withdraw(double balance, double amount) {
        System.out.println("SBI Withdraw: Service charge ₹5 applied.");
        if (amount + serviceCharge > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance = balance - amount - serviceCharge;
            System.out.println("Withdrawn: " + amount);
        }
        return balance;
    }

    @Override
    public double deposit(double balance, double amount) {
        System.out.println("SBI Deposit: Interest 6% applied.");
        balance = balance + amount + (amount * interest_rate);
        return balance;
    }
}

// ---------------- HDFC BANK (Child Class) ----------------
class HDFC_Bank extends CashTree {
    String cust_name;
    int accno;
    double balance;
    double serviceCharge = 10;
    double interest_rate = 0.05;

    public HDFC_Bank(String name, String codeno, String location,
                     String cust_name, int accno, double balance) {
        super(name, codeno, location);
        this.cust_name = cust_name;
        this.accno = accno;
        this.balance = balance;
    }

    @Override
    public double withdraw(double balance, double amount) {
        System.out.println("HDFC Withdraw: Service charge ₹10 applied.");
        if (amount + serviceCharge > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance = balance - amount - serviceCharge;
            System.out.println("Withdrawn: " + amount);
        }
        return balance;
    }

    @Override
    public double deposit(double balance, double amount) {
        System.out.println("HDFC Deposit: Interest 5% applied.");
        balance = balance + amount + (amount * interest_rate);
        return balance;
    }
}


// ---------------- MAIN CLASS ----------------
public class ATMSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose Bank:\n1. SBI Bank\n2. HDFC Bank");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Account Number: ");
        int accno = sc.nextInt();

        System.out.print("Enter Opening Balance: ");
        double bal = sc.nextDouble();

        CashTree atm = null;

        // POLYMORPHISM
        if (choice == 1) {
            atm = new SBI_Bank("SBI ATM", "SBI001", "City Center", name, accno, bal);
        } else if (choice == 2) {
            atm = new HDFC_Bank("HDFC ATM", "HDFC001", "City Center", name, accno, bal);
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        double balance = bal;
        int option;

        do {
            System.out.println("\n------ ATM MENU ------");
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    atm.viewBalance(balance);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double w = sc.nextDouble();
                    balance = atm.withdraw(balance, w);
                    break;

                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double d = sc.nextDouble();
                    balance = atm.deposit(balance, d);
                    break;

                case 4:
                    System.out.println("Thank you for using ATM!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (option != 4);
    }
}
