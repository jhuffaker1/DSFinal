import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class DirectedGraph {
    ArrayList<String> myClassList = new ArrayList<String>();
    ArrayList<ArrayList<String>> adjList = new ArrayList<ArrayList<String>>();
    int depth = 1;
    ArrayList<String> visited = new ArrayList<String>();


    public DirectedGraph() {

    }

    static void addEdge(ArrayList<ArrayList<String>> adjList, int u, String dependentClass) {
        adjList.add(new ArrayList<String>());
        adjList.get(u).add(dependentClass);
    }

    public void ascend() {
        this.depth -= 1;
    }

    public void descend() {
        this.depth += 1;
    }
    public void parseFile(File inputFile) throws InvalidInputSyntax, FileNotFoundException {
        Scanner fileScanner = null;

        fileScanner = new Scanner(inputFile);
        int counter = 0;
        while (fileScanner.hasNext()) {
            String currentLine = fileScanner.nextLine();
            String[] myClasses = currentLine.split(" ");
            myClassList.add(myClasses[0]);
            for (int i=1; i < myClasses.length; i++) {
                this.addEdge(this.adjList, counter, myClasses[i]);
            }
            counter++;
        }
    }


    void dfsRecursion(String start) throws InvalidInputSyntax {
        if (visited.contains(start)) {
            return;
        }

        visited.add(start);

        int positionInArray = -1;
        for (int j = 0; j < myClassList.size(); j++) {
            if (myClassList.get(j).equals(start)) {
                positionInArray = j;
            }
        }
        if (positionInArray == -1) {
            return;
        }
        for (int k = 0; k < adjList.get(positionInArray).size(); k++) {
            String dest = adjList.get(positionInArray).get(k);
            if (visited.contains(dest)) {
                System.out.print("*");
                return;
            } else {
                System.out.println();
                for (int i = 0; i < depth; i++) {
                    System.out.print("\t");
                }
                System.out.print(dest);
            }
            descend();
            dfsRecursion(dest);
            ascend();
            visited.clear();
        }
    }


    public void printGraph() {
        int src_vertex = 0;

        System.out.println("The contents of the graph:");
        System.out.println(this.adjList.size());
        try {
            while (src_vertex < this.adjList.size()) {
                //traverse through the adjacency list and print the edges
                System.out.print(myClassList.get(src_vertex) + " >> ");
                for (int i = 0; i < adjList.get(src_vertex).size(); i++) {
                    System.out.print(" \t " + adjList.get(src_vertex).get(i));
                }

                System.out.println();
                src_vertex++;
            }
        } catch (IndexOutOfBoundsException e) {

        }
    }
}
