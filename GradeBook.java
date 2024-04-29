import java.util.ArrayList; //import the Arraylist class
import java.util.Scanner; //for user inputs
import java.io.File; //import this class
import java.io.FileNotFoundException; //import this class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

//this is the main class that Java will execute
public class GradeBook {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Grade> grades = new ArrayList<Grade>(); // Create an ArrayList object
        int choice;
        Scanner scanner = new Scanner(System.in);
        //provide a menu for the program
        do
        {
            System.out.print("\033[H\033[2J");  //clears the screen
            System.out.flush(); //places the cursor at the top left
            System.out.println("Menu Options:");
            System.out.println("  1. Add a Score");  
            System.out.println("  2. Compute Grade");
            System.out.println("  3. Display Grades"); 
            System.out.println("  4. Save to File");
            System.out.println("  5. Load from File");
            System.out.println("  6. Quit");
            System.out.print("Select a choice from the menu: ");
            choice=scanner.nextInt(); //integer user input
            if (choice==1) {
                grades.add(AddGrade());
            } else if (choice==2) {
                ComputeGrade(grades);
            } else if (choice==3) {
                DisplayGrades(grades);
            } else if (choice==4) {
                SaveFile(grades);
            } else if (choice==5) {
                grades=OpenFile();
            }
        } while (choice!=6);
        scanner.close(); //close the scanner as a good programming practice
    }

    //create a Grade class and for adding to the arraylist
    public static Grade AddGrade() {
        Scanner scanner = new Scanner(System.in); //do not close this scanner since it will crash the program
        String type;
        double score, maxscore, weight;
        System.out.print("\033[H\033[2J"); //clear the screen 
        System.out.flush(); //place the cursor on the top left
        System.out.print("Enter Score: ");
        score = scanner.nextDouble(); //double user input
        System.out.print("Enter MaxScore: ");
        maxscore = scanner.nextDouble();
        System.out.print("Enter Weight: ");
        weight = scanner.nextDouble();
        System.out.print("Enter Type: ");
        scanner.nextLine(); //without this code the program crashes when transitioning to a nextLine from nextDouble
        type = scanner.nextLine();
        Grade grade=new Grade(score, maxscore, weight, type); //this runs the Grade constructor
        return grade; //this will return the grade class
    }

    //compute the grade and letter grade
    public static void ComputeGrade(ArrayList<Grade> grades) throws InterruptedException { // the throws is for the pausing of the thread
        double score=0, maxscore=0, grade;
        String letterGrade="";
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        for (Grade i : grades) {
            score = score + (i.getScore()*i.getWeight());
            maxscore = maxscore + (i.getMaxScore()*i.getWeight());
        }
        grade=score*100/maxscore;
        if (grade>97d) {
            letterGrade="A+";
        } else if (grade>93d) {
            letterGrade="A";
        } else if (grade>90d) {
            letterGrade="A-";
        } else if (grade>87d) {
            letterGrade="B+";
        } else if (grade>83d) {
            letterGrade="B";
        } else if (grade>80d) {
            letterGrade="B-";
        } else if (grade>77d) {
            letterGrade="C+";
        } else if (grade>73d) {
            letterGrade="C";
        } else if (grade>70d) {
            letterGrade="C-";
        } else if (grade>67d) {
            letterGrade="D+";
        } else if (grade>63d) {
            letterGrade="D";
        } else if (grade>60d) {
            letterGrade="D-";
        } else {
            letterGrade="F";
        }
        switch(letterGrade){
            case "A+":
                System.out.println("Woohooo! Congrats, smarty pants! You got an A+. Final Grade is " + grade);
                break;
            case "A":
                System.out.println("Congrats, smarty pants! You got an A. Final Grade is " + grade);
                break;
            case "A-":
                System.out.println("Congrats, you got an A-. Final Grade is " + grade);
                break;
            case "F":
                System.out.println("Sorry, you got an F. Final Grade is " + grade);
                break;
            default:
                System.out.println("You got an " + letterGrade + ". Final Grade is " + grade);
        }
        System.out.println("\n\nPausing. Program will resume in 3 seconds.");
        Thread.sleep(3000); //give the user time to read
    }

    //save the grades to a file. the thought here is that the user will save it per subject for later edits.
    public static void SaveFile(ArrayList<Grade> grades) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String fname;
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("Enter a filename: ");
        fname = scanner.nextLine();
        try {
            FileWriter myWriter = new FileWriter(fname);
            for (Grade i : grades) {
                myWriter.write(i.getScore() + "," + i.getMaxScore() + "," + i.getWeight() + "," + i.getType() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file: " + fname);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //System.out.println(fname);
        System.out.println("\n\nPausing. Program will resume in 3 seconds.");
        Thread.sleep(3000);
    }

    //provides a way to display the grades on the screen
    public static void DisplayGrades(ArrayList<Grade> grades) throws InterruptedException {
        double score=0, maxscore=0, grade;
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("Your grades are listed below:\n\n");
        System.out.println("Score\tMax\tWeight\tType");
        for (Grade i : grades) {
            score = score + (i.getScore()*i.getWeight());
            maxscore = maxscore + (i.getMaxScore()*i.getWeight());
            System.out.println(i.getScore() + "\t" + i.getMaxScore() + "\t" + i.getWeight() +"\t" + i.getType());
        }
        grade=score*100/maxscore;
        System.out.println("\nGrade: " + grade);
        System.out.println("\n\nPausing. Program will resume in 3 seconds.");
        Thread.sleep(3000);
    }

    //reads the file and storing it into the grades arraylist
    public static ArrayList<Grade> OpenFile() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String fname, type;
        double score, maxscore, weight;
        ArrayList<Grade> grades = new ArrayList<Grade>(); // Create an ArrayList object
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("Enter a filename: ");
        fname = scanner.nextLine();
        try {
            File myObj = new File(fname);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] parts = line.split(","); //split the string by each column in the csv
                score = Double.parseDouble(parts[0]); //convert the string to a double since casting is not allowed
                maxscore = Double.parseDouble(parts[1]);
                weight = Double.parseDouble(parts[2]);
                type = parts[3];
                Grade g=new Grade(score, maxscore, weight, type);
                grades.add(g);
            }
            System.out.println("\nSuccessfully read the file " + fname);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("\n\nPausing. Program will resume in 3 seconds.");
        Thread.sleep(3000);
        return grades;
    }
}
