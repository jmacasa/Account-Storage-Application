import java.util.*;
import java.io.*;

public class AccountApp {
    public static void main(String[] args) {
        new GUI();
        /*Scanner scnr = new Scanner(System.in);
        String username;
        String password;
        String location;
        boolean found;
        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<Account> tempAccounts = new ArrayList<>();
        File file = new File("accounts");

        if (file.exists() && file.length() != 0) {
            try {
                FileInputStream fis = new FileInputStream("accounts");
                ObjectInputStream ois = new ObjectInputStream(fis);

                accounts = (ArrayList) ois.readObject();

                ois.close();
                fis.close();
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
                scnr.close();
                return;
            }
            catch (ClassNotFoundException c) {
                System.out.println("Class not found");
                c.printStackTrace();
                scnr.close();
                return;
            }
        }
        
        System.out.println("Welcome! This program is designed to store all your accounts and their usernames and passwords.");
        
        while (true) {
            System.out.print("Enter a command: (a)dd new account, (l)ookup an account, (d)elete an account, (e)dit an account, (s)how all, or (q)uit: ");
            String command = scnr.nextLine();
            if (command.equals("q")) {
                break;
            }

            switch (command) {
                case "a":
                    System.out.print("Enter name of the website or location of the account to store: ");
                    location = scnr.nextLine();
                    System.out.print("Enter the username of the account: ");
                    username = scnr.nextLine();
                    System.out.print("Enter the password of the account: ");
                    password = scnr.nextLine();

                    Account newAccount = new Account(username, password, location);
                    System.out.println("New Account stored: ");
                    accounts.add(newAccount);
                    System.out.print(newAccount.getInfo());
                    break;

                case "l":
                    System.out.print("Enter name of location of account to lookup: ");
                    location = scnr.nextLine();
                    found = false;
                    for (int i = 0; i < accounts.size(); i++) {
                        if (accounts.get(i).getLocation().equals(location)) {
                            System.out.println("");
                            System.out.print(accounts.get(i).getInfo());
                            found = true;
                        }
                    }
                    
                    if (!found) {
                        System.out.println("Account not found!");
                    } 
                    break;

                case "d":
                    System.out.print("Enter name of location of account to delete: ");
                    location = scnr.nextLine();
                    for (int i = 0; i < accounts.size(); i++) {
                        if (accounts.get(i).getLocation().equals(location)) {
                            tempAccounts.add(accounts.get(i));
                        }
                    }

                    if (tempAccounts.size() == 0) {
                        System.out.println("Account not found!");
                    }
                    else if (tempAccounts.size() == 1) {
                        System.out.println("Account deleted:");
                        System.out.print(tempAccounts.get(0).getInfo());
                        for (int i = 0; i < accounts.size(); i++) {
                            if (accounts.get(i).equals(tempAccounts.get(0))) {
                                accounts.remove(i);
                            }
                        }
                    }
                    else {
                        System.out.println("There are multiple accounts associated with this location:");
                        System.out.println("");
                        for (int i = 0; i < tempAccounts.size(); i++) {
                            System.out.println(i + 1 + ".");
                            System.out.print(tempAccounts.get(i).getInfo());
                            System.out.println("");                            
                        }
                        
                        System.out.print("Which account would you like to delete (Enter number): ");
                        int choice = scnr.nextInt() - 1;

                        for (int i = 0; i < tempAccounts.size(); i++) {
                            if (i == choice) {
                                for (int j = 0; j < accounts.size(); j++) {
                                    if (accounts.get(j).equals(tempAccounts.get(i))) {
                                        accounts.remove(j);
                                        break;
                                    }
                                }
                                scnr.nextLine();
                                break;
                            }
                        }
                    }
                    tempAccounts.clear();
                    break;

                case "e":
                    System.out.print("Enter name of location of account to edit: ");
                    location = scnr.nextLine();
                    for (int i = 0; i < accounts.size(); i++) {
                        if (accounts.get(i).getLocation().equals(location)) {
                            tempAccounts.add(accounts.get(i));
                        }
                    }

                    if (tempAccounts.size() == 0) {
                        System.out.println("Account not found!");
                    }
                    else if (tempAccounts.size() == 1) {
                        System.out.print(tempAccounts.get(0).getInfo());
                        for (int i = 0; i < accounts.size(); i++) {
                            if (accounts.get(i).equals(tempAccounts.get(0))) {
                                System.out.print("Would you like to edit (l)ocation, (u)sername, or (p)assword: ");
                                String edit = scnr.nextLine();

                                if (edit.equals("l")) {
                                    System.out.print("Enter new location: ");
                                    location = scnr.nextLine();
                                    accounts.get(i).setLocation(location);
                                    break;
                                }
                                else if (edit.equals("u")) {
                                    System.out.print("Enter new username: ");
                                    username = scnr.nextLine();
                                    accounts.get(i).setUsername(username);
                                    break;
                                }
                                else if (edit.equals("p")) {
                                    System.out.print("Enter new password: ");
                                    password = scnr.nextLine();
                                    accounts.get(i).setPassword(password);
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        System.out.println("There are multiple accounts associated with this location:");
                        System.out.println("");
                        for (int i = 0; i < tempAccounts.size(); i++) {
                            System.out.println(i + 1 + ".");
                            System.out.print(tempAccounts.get(i).getInfo());
                            System.out.println("");                            
                        }
                        
                        System.out.print("Which account would you like to edit (Enter number): ");
                        int choice = scnr.nextInt() - 1;
                        scnr.nextLine();

                        for (int i = 0; i < tempAccounts.size(); i++) {
                            if (i == choice) {
                                for (int j = 0; j < accounts.size(); j++) {
                                    if (accounts.get(j).equals(tempAccounts.get(i))) {
                                        System.out.print(accounts.get(j).getInfo());
                                        System.out.print("Would you like to edit (l)ocation, (u)sername, or (p)assword: ");
                                        String field = scnr.nextLine();

                                        if (field.equals("l")) {
                                            System.out.print("Enter new location: ");
                                            location = scnr.nextLine();
                                            accounts.get(j).setLocation(location);
                                            break;
                                        }
                                        else if (field.equals("u")) {
                                            System.out.print("Enter new username: ");
                                            username = scnr.nextLine();
                                            accounts.get(j).setUsername(username);
                                            break;
                                        }
                                        else if (field.equals("p")) {
                                            System.out.print("Enter new password: ");
                                            password = scnr.nextLine();
                                            accounts.get(j).setPassword(password);
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                    tempAccounts.clear();
                    break;

                case "s":
                    if (accounts.size() == 0) {
                        System.out.println("No accounts stored!");
                    }
                    else {
                        System.out.println("");
                        for (int i = 0; i < accounts.size(); i++) {
                            System.out.print(accounts.get(i).getInfo());
                            System.out.println("");
                        }
                    }
                    break;
            }
        }
        scnr.close();

        try {
            FileOutputStream fos = new FileOutputStream("accounts");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(accounts);
            oos.close();
            fos.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }*/
    }
}
