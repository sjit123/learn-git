# GIT Basics
Learn git basics by creating a simple java project.
## Create Directory
```
mkdir ContactManager
cd ContactManager
```

## Initialize a git repo
```
git init
```

## Add code
Create a java file called ContactManager.java with two classes: ContactManager and Contact. Simple Java project to store and retrieve contacts in memory.


```java
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

//Class Contact
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
```

## add .gitignore file
###### .gitignore tells Git which files and directories to ignore when tracking changes.
```
*.class: We don't want to track compiled Java bytecode files.
/out/: This is a common directory for build output. We generally don't version-control build artifacts.
```


## stage changes
*git add . stages all the new and modified files in the current directory (and its subdirectories) for the next commit.  It's like adding files to a "staging area" before you pack them up (commit them). It's crucial for preparing your changes.*
``` 
git add .
```
## Commit Changes
*The -m flag allows you to provide a commit message, which briefly describes the changes.*

```console
git commit -m "Initial commit"
```

## Make New Code Changes: Adding new features.
### Create a new branch
```
git checkout -b add-search-feature
```
*git checkout -b <branch_name> creates a new branch and immediately switches to it.*  
*Branches are essential for isolating work on new features or bug fixes without affecting the main codebase (usually the main or master branch). This allows for parallel development.*
*Branches can have types also. Example: Feature branch, Hotfix, bug etc*
*If using a code editor like Vscode or Intellij, notice how git branch changed to the new branch we created 'add-search-feature'*


### Update to existing code
*Add a new method in ContactManager Class to search for contacts*
```java
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
```
*Add an option in the switch case for searching contacts*
```java
   case "3":
        manager.searchContact();
        break;
    case "4":
        System.out.println("Exiting...");
        break;
```
## Stage and commit new changes
```
git add .
git commit -m "Add search functionality"
```
*We're staging and committing the changes, but this time they're associated with the add-search-feature branch unlike main earlier*

## Switch to Main branch
```
git checkout main
```
*You ll notice the changes you just dod to add search function is gone, since it was added in the feature branch*

## Merge the feature branch
Lets merge our feature branch to main branch. 
```console
git merge add-search-feature
```
*git merge takes the changes from the specified branch (add-search-feature) and integrates them into the current branch (which is main in this case).  This combines the work done on the feature branch into the main line of development.*

Notice how the additional method we added in feature branch is back in the ContactManager class.

## Delete the branch
Lets delete the feature branch now. It's a good practice to delete branches after they've been merged to keep your repository clean.
```
git branch -d add-search-feature
```
*The git branch -d <branch_name> command deletes the specified branch locally*

## Create a github repo
Go to https://github.com/ and create a new, empty repository.( Sign up if you dont have an account.) Don't add a README, .gitignore, or license yet (we've already created those locally).  Give it a name like "ContactManager-Git-Example".

### Connect local git repo to remote git repo
Now we will connect our local git repo we were using to a remote repo we just created in github.com . Generally github provides instructions. Select the one under "…or push an existing repository from the command line". It will look like:
```
git remote add origin https://github.com/sjit123/learn-git.git #replace with your git remote url
git branch -M main
git push -u origin main
```
- git remote add origin <repository_url>: This command adds a "remote" named origin (a common convention) that points to your GitHub repository's URL. A remote is a connection to another Git repository.
- git branch -M main: This command renames the local master branch, if that is the current name of your main branch, to main to align with GitHub's default branch naming.
- git push -u origin main: This is the crucial command.
** git push: This pushes your local commits to a remote repository.
** -u origin main: This sets up a tracking relationship. It tells Git that your local main branch should track the main branch on the origin remote (your GitHub repository). After this first push, you can simply use git push without arguments.

