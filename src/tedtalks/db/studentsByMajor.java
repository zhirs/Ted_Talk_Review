package tedtalks.db;

import java.util.ArrayList;
import java.util.Scanner;

import tedtalkDB.model.Student;
import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.IDatabase;

public class studentsByMajor {
	public static void main(String[] args) throws Exception {
		
		Scanner keyboard = new Scanner(System.in);
		InitDatabase.init();
		// Create the default IDatabase instance
		System.out.print("Enter a major to search for in students");
		String major = keyboard.nextLine();
		
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Student> found = db.studentsByMajor(major);
		if (found.size() == 0){
			System.out.println("No Students Found");
		}
		else {
			for(int i = 0; i < found.size(); i++){
				System.out.println(found.get(i).getUserName());
			}
			}
		keyboard.close();
	}
}