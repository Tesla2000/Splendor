package edu.ib.splendor;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CommunicationController {

    public static void waitForJavaTurn() throws IOException {
        String st;
        boolean stop = false;
        do {
            File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\communication.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((st = reader.readLine()) != null) {
                if (st.equals("java")) stop = true;
            }
            reader.close();
        } while (!stop);
    }

    public static void respondToPython(double[] scores) throws IOException {
        File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\response.txt");
        FileWriter writer = new FileWriter(file);
        StringBuilder builder = new StringBuilder();
        for (double score : scores) builder.append(score).append(",");
        String st = builder.toString();
        st = st.substring(0, st.length() - 1);
        writer.write(st);
        writer.close();
    }

    public static void passToPython() throws IOException {
        File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\communication.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("python");
        writer.close();
    }
}
