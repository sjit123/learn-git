import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager {

    private ArrayList<Contact> contacts;
    private Scanner scanner;

    public ContactManager() {
        contacts = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addContact() {
        System.out.print("Enter contact name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String number = scanner.nextLine();
        contacts.add(new Contact(name, number));
        System.out.println("Contact added!");
    }

    public void displayContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts to display.");
            return;
        }
        System.out.println("Contacts:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }




public void searchContact() {
    System.out.print("Enter name to search: ");
    String query = scanner.nextLine();
    boolean found = false;
    for (Contact contact : contacts) {
        if (contact.getName().toLowerCase().contains(query.toLowerCase())) {
            System.out.println(contact);
            found = true;
        }
    }
    if (!found) {
        System.out.println("No contacts found matching '" + query + "'.");
    }
}

    public static void main(String[] args) {
        ContactManager manager = new ContactManager();
        Scanner inputScanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\nOptions:");
            System.out.println("1. Add contact");
            System.out.println("2. Display contacts");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = inputScanner.nextLine();

            switch (choice) {
                case "1":
                    manager.addContact();
                    break;
                case "2":
                    manager.displayContacts();
                    break;
                case "3":
                    manager.searchContact();
                    break;
                case "4":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("3"));
        inputScanner.close();
    }
}


class Contact {
    private String name;
    private String number;
    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Number: " + number;
    }
}

