import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Database
 */
public class Database {
    public static void main(String[] args) throws IOException {
        String no, line;
        FileWriter fileWriter;
        BufferedReader bufferedReader;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Which operation do you want to perform?");
            System.out.println("1.\tInsert data\n2.\tDelete data\n3.\tSearch data\n4.\tDisplay data\n5.\tExit");
            System.out.print("Enter your choice:\t");
            switch (scanner.nextInt()) {
                case 1:
                    fileWriter = new FileWriter(new File("database.txt"), true);
                    System.out.print("Enter student roll no:\t");
                    int rollNo = scanner.nextInt();
                    System.out.print("Enter student GR no:\t");
                    int grno = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter student name:\t");
                    String name = scanner.nextLine();
                    System.out.print("Enter student division:\t");
                    String division = scanner.nextLine();
                    fileWriter.append(rollNo + "," + grno + "," + name + "," + division + "\n");
                    fileWriter.close();
                    break;
                case 2:
                    System.out.print("Enter a GR no:\t");
                    scanner.nextLine();
                    no = scanner.nextLine();
                    bufferedReader = new BufferedReader(new FileReader(new File("database.txt")));
                    String updatedData = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] splittedData = line.split(",");
                        if (splittedData.length == 4 && !splittedData[1].equals(no)) updatedData += line + "\n";
                    }
                    bufferedReader.close();
                    fileWriter = new FileWriter(new File("database.txt"));
                    fileWriter.write(updatedData);
                    fileWriter.close();
                    break;
                case 3:
                    System.out.print("Enter GR no:\t");
                    scanner.nextLine();
                    no = scanner.nextLine();
                    bufferedReader = new BufferedReader(new FileReader(new File("database.txt")));
                    boolean found = false;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (no.equals(line.split(",")[1])) {
                            String[] data = line.split(",");
                            System.out.println("\nData:\nRoll no:\t" + data[0]);
                            System.out.println("GR no:\t\t" + data[1]);
                            System.out.println("Name:\t\t" + data[2]);
                            System.out.println("Division:\t" + data[3]);
                            System.out.println();
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("\nNot found!\n");
                    bufferedReader.close();
                    break;
                case 4:
                    bufferedReader = new BufferedReader(new FileReader(new File("database.txt")));
                    System.out.println("Roll no\tGR no\t\tName\t\tDivision");
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line.replace(",", "\t"));
                    }
                    bufferedReader.close();
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }
    }
}