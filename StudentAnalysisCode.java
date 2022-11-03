import java.util.*;
import java.io.*;

public class StudentAnalysisCode {
    
    public static double numELLStudents;
    public static double numNativeEnglishStudents;
    public static double numTotalStudents;


    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("cmtresults (1).csv");
        File m = new File ("studentenrollmentbyell2008-2021.csv");
        Scanner cmtResultsFileScan = new Scanner(f);
        Scanner languageStudentsFileScan = new Scanner(m);
        getNumStudents(languageStudentsFileScan);
    }


    public static void getNumStudents(Scanner fileScan) throws FileNotFoundException {
        fileScan.nextLine();
        String[] header = fileScan.next().split(",");
        ArrayList<String> headerValues = new ArrayList<>(Arrays.asList(header));
        int yearIndex = headerValues.indexOf("Year");
        int numStudentsIndex = headerValues.indexOf("Value");
        int typeOfLearner = headerValues.indexOf("Learner");
        Double numEllStudents = 0.0;
        Double numNonEllStudents = 0.0;
        while (fileScan.hasNextLine()) {
            String line = fileScan.nextLine();
            if (!line.equals("")) {
                String[] line1 = line.split(",");
                //System.out.println(line1);
                ArrayList<String> lineAsAL = new ArrayList<>(Arrays.asList(line1));
                //System.out.println(lineAsAL);
                if(lineAsAL.get(yearIndex).equals("2020-2021") && Double.parseDouble(lineAsAL.get(numStudentsIndex))>0) {
                    if (lineAsAL.get(typeOfLearner).equals("Non-English Language Learner")) {
                        numNonEllStudents += Double.parseDouble(lineAsAL.get(numStudentsIndex));
                    }
                    else if (lineAsAL.get(typeOfLearner).equals("English Language Learner")) {
                        numEllStudents += Double.parseDouble(lineAsAL.get(numStudentsIndex));
                     }
                 } else {
                    fileScan.nextLine();
                 }
                } 
        }
        numELLStudents = numEllStudents;
        numNativeEnglishStudents = numNonEllStudents;
        numTotalStudents = numEllStudents + numNonEllStudents;
        System.out.println(numTotalStudents);
    }
}