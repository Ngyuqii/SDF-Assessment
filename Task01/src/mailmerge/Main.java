package mailmerge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        
        //javac -sourcepath src -d classes src/mailmerge/*.java
        //java -cp classes mailmerge.Main <csv file> <template file>
        String custFile = args[0];
        String temFile = args[1];

        //Checks if files exist
        File fobj1 = Paths.get(custFile).toFile();
        File fobj2 = Paths.get(temFile).toFile();
        if(!(fobj1.exists() && fobj2.exists())) {
            System.out.println("One or both files cannot be found. Program mail merge cannot proceed.");
        }
        else {
            System.out.printf("Processing mail merge of %s into %s template.\n\n", custFile, temFile);
        }
        
        //Create a hashmap of variable name and customer details key-values
        HashMap <String, String> custMap = new HashMap<String, String>();

        try {
            //Read and process customer details csv file
            FileReader fr = new FileReader(custFile);
            BufferedReader br = new BufferedReader(fr);

            //Read csv header
            String headerLine = br.readLine();
            String[] headerFields = headerLine.trim().split(","); //split into header fields

            //Migrate each customer's details in a list
            List<String> allCust = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                allCust.add(line);
            }

            //All customers to be mapped
            for (int m=0; m < allCust.size(); m++) {

                String oneCust = allCust.get(m);
                String[] custDetails = oneCust.trim().split(","); //split into individual detail

                //Map value to each key
                for (int i =0; i < headerFields.length; i++) {
                    custMap.put(headerFields[i], custDetails[i]);
                }

                //Read and process customer details csv file
                List<String> text;
                text = Files.readAllLines(Paths.get(temFile));
                
                for(String liner : text) {
                    if (liner.isEmpty()) {
                        continue;
                    }
                    else {
                        for (String j : headerFields) {
                            if(liner.contains("<<"+j+">>")) {
                                liner = liner.replaceAll("<<"+j+">>",custMap.get(j));
                            }
                            else {
                                continue;
                            }
                        }
                    }

                System.out.println(liner);
                }
            
            System.out.println(""); //separate each email on terminal
            }
        
        br.close();
        }
        catch (IOException e) {
            System.out.println("Unable to read file.");
        }         

    }
    
}