import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while(true) {
            System.out.println("Which file would you like to get parsed? (Type 'end' to close the program)");
            userInput = scanner.next();
            if (userInput.equals("end")) {
                System.exit(0);
            }else if(!userInput.equals("simple1.txt") && !userInput.equals("nothing.html") && !userInput.equals("sample1.html") && !userInput.equals("sample2.html") && !userInput.equals("sample3.html") && !userInput.equals("advanced.html")) {
                System.out.println("This file does not exist! Pleas try again");
            } else {
                break;
            }
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader(userInput));
            String currentLine;
            while ((currentLine = in.readLine()) != null) {
                char[] currentLineArray = currentLine.toCharArray();

                List<String> splitted = new LinkedList<>();
                List<String> startTags = new ArrayList<>();
                List<String> endTags = new ArrayList<>();

                for (int i = 0; i < currentLineArray.length - 1; i++) {
                    String startTag = "";
                    if (currentLineArray[i] == '<' && currentLineArray[i + 1] != '/') {
                        i++;
                        while (currentLineArray[i] != '>') {
                            startTag += Character.toString(currentLineArray[i]);
                            i++;
                        }
                        startTags.add(startTag);
                    }

                    String between = "";
                    while (currentLineArray[i] != '<' && currentLineArray[i] != '>' && i < currentLineArray.length - 1) {
                        between += Character.toString(currentLineArray[i]);
                        i++;
                    }
                    if (!between.equals("")) {
                        splitted.add(between);
                        i--;
                    }

                    String endTag = "";
                    if (currentLineArray[i] == '<' && currentLineArray[i + 1] == '/') {
                        i += 2;
                        while (currentLineArray[i] != '>') {
                            endTag += Character.toString(currentLineArray[i]);
                            i++;
                        }
                        endTags.add(endTag);
                    }
                }

                startTags.remove("ol");
                startTags.remove("ul");
                endTags.remove("ol");
                endTags.remove("ul");

                boolean found = false;
                boolean isNestedTagAlreadyUsed = false;
                for(int i = 0; i < startTags.size(); i++) {
                    for(int y = 0; y < endTags.size(); y++) {
                        if (startTags.get(i).equals(endTags.get(y))) {
                            found = true;
                            break;
                        }
                    }
                    if(!found) {
                        System.out.println("Fehler bei Tag " + startTags.get(i));
                        if(splitted.size() > 0) {
                            splitted.set(i, "");
                        }
                    } else if(startTags.size() >= 2 &&
                            !startTags.get(i).equals(endTags.get(i)) &&
                            !isNestedTagAlreadyUsed &&
                            splitted.size() > startTags.size()) {
                        splitted.set(i, splitted.get(i) + splitted.get(i+2));
                        splitted.set(i+2, "");
                        isNestedTagAlreadyUsed = true;
                    }
                }

                if(splitted.size() == 0 && startTags.size() != 0) {
                    System.out.println(startTags.get(startTags.size()-1) + " kein Inhalt.");
                }

                if(startTags.size() != endTags.size() || endTags.size() == 0 && !splitted.isEmpty() && !splitted.get(0).equals("")) {
                    for(int i = 0; i < splitted.size(); i++) {
                        System.out.println("Fehler bei dieser Zeile -> kein Tag");
                        splitted.set(i, "");
                    }
                }

                for(String string : splitted) {
                    if(!string.equals("")) {
                        System.out.println(string.trim());
                    }
                }
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
