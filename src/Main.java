import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File myFile = null;
        Scanner fileScanner = null;

        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);

        if(returnVal == JFileChooser.APPROVE_OPTION) {
            System.out.println(chooser.getSelectedFile().getAbsolutePath());
            myFile = new File(chooser.getSelectedFile().getAbsolutePath());
        }
        try {
            DirectedGraph myGraph = FileParser.parseFile(myFile);
            myGraph.printGraph();
            System.out.print("ClassA");
            myGraph.dfsRecursion("ClassA");
        } catch (InvalidInputSyntax e) {
            System.out.println("syntax bad. ");
        } catch (FileNotFoundException e2) {
            System.out.println("File bad");
        }
    }
}
