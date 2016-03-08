package urm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Deneir Uy
 */
public class URM {

    static ArrayList<Integer> values;   //contains values to be manipulated
    static ArrayList<String> commands;  //contains the several lines of commands to be executed
    static PrintWriter writer;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        URM urm = new URM();
        commands = new ArrayList<>();
        values = new ArrayList<>();

        //file reading
        Scanner scan = new Scanner(new File("mp1.in"));
        String valuesString = scan.nextLine();
        String valuesSplit[] = valuesString.split(" ");

        //splits the first line of the input file (i.e. initial values) into individual integers to be manipulated
        for (int i = 0; i < valuesSplit.length; i++) {
            values.add(Integer.parseInt(valuesSplit[i]));
        }

        //add commands to array list while removing spaces
        while (scan.hasNextLine()) {
            commands.add(scan.nextLine().replaceAll(" ", ""));
        }

        //output file
        writer = new PrintWriter("mp1.out", "UTF-8");

        //write initial value into file
        for (int i = 0; i < values.size(); i++) {
            writer.print(values.get(i) + " ");
        }
        writer.println("");

        //start executing commands one by one
        for (int i = 0; i < commands.size(); i++) {
            String command = commands.get(i);
            int m = Character.getNumericValue(command.charAt(1));

            //switch-case determines what type of operation to execute based on first char of each command line
            switch (command.charAt(0)) {
                case 'S':   //successor
                    if (command.length() > 2) {

                        int tensPlace = Character.getNumericValue(command.charAt(1));
                        int onesPlace = Character.getNumericValue(command.charAt(2));
                        m = tensPlace * 10 + onesPlace;
                    }
                    urm.successor(m);
                    break;

                case 'Z':   //zero
                    if (command.length() > 2) {

                        int tensPlace = Character.getNumericValue(command.charAt(1));
                        int onesPlace = Character.getNumericValue(command.charAt(2));
                        m = tensPlace * 10 + onesPlace;
                    }
                    urm.zero(m);
                    break;

                case 'J':   //jump
                    int n = Character.getNumericValue(command.charAt(2));
                    int j = Character.getNumericValue(command.charAt(3));
                    if (values.get(m) == values.get(n)) {
                        if (command.length() > 4) {

                            int tensPlace = Character.getNumericValue(command.charAt(3));
                            int onesPlace = Character.getNumericValue(command.charAt(4));
                            j = tensPlace * 10 + onesPlace;
                        }
                        i = j - 2;
                    }
                    urm.print();
                    break;

                case 'C':   //copy
                    n = Character.getNumericValue(command.charAt(2));
                    urm.copy(m, n);
                    break;

                default:
                    break;
            }

        }
        writer.close();
        System.out.println("Output file created!");
    }

    public void successor(int index) {
        int increment = values.get(index);
        increment++;
        values.set(index, increment);
        print();

    }

    public void zero(int index) {
        values.set(index, 0);
        print();
    }

    public void copy(int i1, int i2) {
        values.set(i2, values.get(i1));
        print();
    }
    
    //write into file
    public void print() {
        for (int i = 0; i < values.size(); i++) {
            writer.print(values.get(i) + " ");
        }
        writer.println();
    }
}
