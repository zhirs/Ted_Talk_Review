package tedtalks.db;

import java.util.ArrayList;
import java.util.Scanner;

import tedtalkDB.model.Account;
import tedtalkDB.model.Professor;
import tedtalkDB.model.Student;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class addStudent {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter a username: ");
		String user = keyboard.nextLine();
		System.out.println("Enter a password: ");
		String pass = keyboard.nextLine();
		System.out.println("Enter an email address: ");
		String email = keyboard.nextLine();
		System.out.println("Enter a section: ");
		String section = keyboard.nextLine();
		
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Student> found = db.addStudent(user, pass, email, section);
		if (found.size() == 0){
			System.out.println("Creation error");
		}
		else {
			System.out.println("Student Account Made");
			System.out.println(found.get(0).getUserName() + " " + found.get(0).getprofID() + " " + found.get(0).getEmail());
			}			
		}
}