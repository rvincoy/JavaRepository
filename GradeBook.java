import java.util.ArrayList; //import the Arraylist class
import java.util.Scanner; //for user inputs
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class GradeBook {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Grade> grades = new ArrayList<Grade>(); // Create an ArrayList object
        int choice;
        Scanner scanner = new Scanner(System.in);
        do
        {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println("Menu Options:");
            System.out.println("  1. Add a Score");  
            System.out.println("  2. Compute Grade");
            System.out.println("  3. Display Grades"); 
            System.out.println("  4. Save to File");
            System.out.println("  5. Load from File");
            System.out.println("  6. Quit");
            System.out.print("Select a choice from the menu: ");
            choice=scanner.nextInt();
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
        scanner.close();
    }

    public static Grade AddGrade() {
        Scanner scanner = new Scanner(System.in);
        String type;
        double score, maxscore, weight;
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.print("Enter Score: ");
        score = scanner.nextDouble();
        System.out.print("Enter MaxScore: ");
        maxscore = scanner.nextDouble();
        System.out.print("Enter Weight: ");
        weight = scanner.nextDouble();
        System.out.print("Enter Type: ");
        scanner.nextLine();
        type = scanner.nextLine();
        Grade grade=new Grade(score, maxscore, weight, type);
        return grade;
    }

    public static void ComputeGrade(ArrayList<Grade> grades) throws InterruptedException {
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
        Thread.sleep(3000);
    }

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
        System.out.println(fname);
        System.out.println("\n\nPausing. Program will resume in 3 seconds.");
        Thread.sleep(3000);
    }

    public static void DisplayGrades(ArrayList<Grade> grades) throws InterruptedException {
        double score=0, maxscore=0, grade;
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        System.out.println("Your grades are listed below:\n\n");
        for (Grade i : grades) {
            score = score + (i.getScore()*i.getWeight());
            maxscore = maxscore + (i.getMaxScore()*i.getWeight());
            System.out.println(i.getScore() + " / " + i.getMaxScore() + "\t" + i.getType());
        }
        grade=score*100/maxscore;
        System.out.println("\nGrade: " + grade);
        System.out.println("\n\nPausing. Program will resume in 3 seconds.");
        Thread.sleep(3000);
    }

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
                String[] parts = line.split(",");
                score = Double.parseDouble(parts[0]);
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
