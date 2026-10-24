import java.util.*;

class Customer {

    int acno;
    String cname;
    String email;
    String accounttype;
    int bal;

    Customer(int acno, String cname, String email, String accounttype, int bal) {
        this.acno = acno;
        this.cname = cname;
        this.email = email;
        this.accounttype = accounttype;
        this.bal = bal;
    }

    public int getAcno() {
        return acno;
    }

    public void setAcno(int acno) {
        this.acno = acno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public int getBal() {
        return bal;
    }

    public void setBal(int bal) {
        this.bal = bal;
    }
}

class Transactions extends Customer {

    int tid;
    String transaction_type;

    int transaction_date;

    Transactions(int acno, String cname, String email, String accounttype,
            int bal, int tid, String transaction_type, int transaction_date) {
        super(acno, cname, email, accounttype, bal);
        this.tid = tid;
        this.transaction_type = transaction_type;
        this.transaction_date = transaction_date;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public int getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(int transaction_date) {
        this.transaction_date = transaction_date;
    }

}
class InvalidChoice extends Exception {
    InvalidChoice(String message) {
        super(message);
    }
}
class InvalidAccount extends Exception {
    InvalidAccount(String message) {
        super(message);
    }
}

class InsufficientFunds extends Exception {
    InsufficientFunds(String message) {
        super(message);
    }
}

class InvalidAmt extends Exception {
    InvalidAmt(String message) {
        super(message);
    }
}

public class CRUD {
    public static void main(String[] args) {

        ArrayList<Transactions> list = new ArrayList<Transactions>();
        Scanner sc = new Scanner(System.in);
        int ch;
        int password = 1234;
        System.out.println("Enter the password to access the system:");
        int inputPassword = sc.nextInt();
        if (inputPassword != password) {
            System.out.println("Invalid password. Access denied.");
            return;
        }
        do {
            System.out.println("1. Create Account\r\n" +
                    "2. View Accounts\r\n" +
                    "3. Search Account\r\n" +
                    "4. Deposit\r\n" +
                    "5. Withdraw\r\n" +
                    "6. Delete Account\r\n" +
                    "7. Exit");
            ch = sc.nextInt();
            try {
                if (ch < 1 || ch > 7) {
                    throw new InvalidChoice("Invalid choice. Please enter a number between 1 and 7.");
                }
                switch (ch) {
                    
                    case 1:
                        System.out.println("Select Account Type:: 1.Current 2.Saving");
                    
                    int type = sc.nextInt();

                    String accounttype;

                    switch (type) {
                        case 1:
                            accounttype = "Current";
                            break;
                        case 2:
                            accounttype = "Savings";
                            break;
                        default:
                            accounttype = "Invalid";
                            break;
                    }
                    System.out.print(
                            "Enter :-- Account No, Customer Name, Email, Balance, Transaction ID, Transaction Type, Transaction Date: ");
                    int acno = sc.nextInt();
                    String cname = sc.next();
                    String email = sc.next();
                    // String accounttype = sc.next();
                    int bal = sc.nextInt();
                    int tid = sc.nextInt();
                    String transaction_type = sc.next();
                    int transaction_date = sc.nextInt();
                    list.add(new Transactions(acno, cname, email, accounttype, bal, tid, transaction_type,
                            transaction_date));
                    System.out.println("Account added succesfully");
                    break;

                case 2:
                    System.out.println(
                            "Displaying Customer details::-- Account No, Customer Name, Email, Account Type, Balance, Transaction ID, Transaction Type, Transaction Date");
                    for (Transactions t : list) {
                        System.out.println("Account No: " + t.getAcno()
                                + ", Customer Name: " + t.getCname()
                                + ", Email: " + t.getEmail()
                                + ", Account Type: " + t.getAccounttype()
                                + ", Balance: " + t.getBal()
                                + ", Transaction ID: " + t.getTid()
                                + ", Transaction Type: " + t.getTransaction_type()
                                + ", Transaction Date: " + t.getTransaction_date());
                    }
                    break;
                case 3:
                    System.out.print("Enter Account No to search: ");
                    int acc = sc.nextInt();
                    try {
                    boolean flag = false;
                    ListIterator<Transactions> it = list.listIterator();
                    while ((it.hasNext())) {
                        Transactions account = it.next();
                        if (account.getAcno() == acc) {
                            System.out.println("Account No: " + account.getAcno()
                                    + ", Customer Name: " + account.getCname()
                                    + ", Email: " + account.getEmail()
                                    + ", Account Type: " + account.getAccounttype()
                                    + ", Balance: " + account.getBal()
                                    + ", Transaction ID: " + account.getTid()
                                    + ", Transaction Type: " + account.getTransaction_type()
                                    + ", Transaction Date: " + account.getTransaction_date());

                            flag = true;
                            break;

                        }
                    }
                    if (!flag) {
                        throw new InvalidAccount("Entered Account No. is Invalid");

                    }
                    } catch (InvalidAccount e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                    System.out.println("Enter an account no.:");
                    acc = sc.nextInt();
                    System.out.println("Enter a deposit amount");
                    int deposit = sc.nextInt();
                    if (deposit <= 0) {
                        throw new InvalidAmt("Deposit amount should be greater than 0");
                    }

                    boolean flag1 = false;
                    for (Transactions T : list) {
                        if (T.getAcno() == acc) {
                            T.setBal(T.getBal() + deposit);
                            System.out.println("Deposit successful: Your New balance is : "
                                    + T.getBal());
                            flag1 = true;
                            break;

                        }

                    }
                    if (!flag1) {
                        throw new InvalidAccount("Invalid account no. Entered");

                    }
                    } catch (InvalidAmt e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidAccount e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    try {
                    System.out.println("Enter an account no.:");
                    acc = sc.nextInt();
                    System.out.println("Enter a withdrawal amount");
                    int withdraw = sc.nextInt();
                    if (withdraw <= 0) {
                        throw new InvalidAmt("Withdraw amount should be greater than 0");
                    }

                    boolean flag2 = false;
                    for (Transactions T : list) {
                        if (acc == T.getAcno()) {
                            flag2 = true;
                            if (withdraw > T.getBal()) {
                                throw new InsufficientFunds("Insufficient fund");
                            } else {
                                T.setBal(T.getBal() - withdraw);
                                System.out.println("Withdrawal successful: Your New balance is : "
                                        + T.getBal());
                                
                            }

                            break;

                        }

                    }
                    if (!flag2) {
                        throw new InvalidAccount("Invalid account no. Entered");

                    }
                    } catch (InvalidAmt e) {
                        System.out.println(e.getMessage());
                    } catch (InsufficientFunds e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidAccount e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    try {
                    System.out.println("Enter an account no. to delete:");
                    int deleteAcc = sc.nextInt();
                    boolean flag3 = false;
                    ListIterator<Transactions> it1 = list.listIterator();
                    while (it1.hasNext()) {
                        Transactions Accnt = it1.next();
                        if (deleteAcc == Accnt.getAcno()) {
                            it1.remove();
                            flag3 = true;
                            System.out.println("Account deleted successfully");
                            break;
                        }
                    }
                    if (!flag3) {
                        throw new InvalidAccount("Invalid account no. Entered");
                    }
                    } catch (InvalidAccount e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                }
            } catch (InvalidChoice e) {
                System.out.println(e.getMessage());
            }
        } while (ch != 7);

    }
}
