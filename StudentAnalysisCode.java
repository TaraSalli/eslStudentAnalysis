import java.util.*;
import java.io.*;

public class StudentAnalysisCode {
    

    public static double totalNumELLStudents;
    public static double totalNumNativeEnglishStudents;
    public static double totalNumTotalStudents;

    public static String mostDiverseDistrict;
    public static ArrayList<String> leastDiverseDistricts;
    public static ArrayList<String> leastDiverseDistrictEquitableRank = new ArrayList<>();


// main method
    public static void main(String[] args) throws FileNotFoundException {
        // dataset files used for all 3 questions
        File f = new File("SchoolEquitableRank.csv");
        File m = new File ("studentenrollmentbyell2008-2021.csv");

        // question 1
        getTotalNumStudents(m);
        System.out.println("");
        // question 2
        getHighestAndLowestDistrict(m);
        System.out.println("");
        // question 3
        getDistrictEquitableValue(f, mostDiverseDistrict, leastDiverseDistricts);
        System.out.println("");
    }

// question 1: What percentgae of students in all of Connecticut are ELL students? What percentage are non-ELL students?
    public static void getTotalNumStudents(File m) throws FileNotFoundException {
        Scanner fileScan = new Scanner(m);
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
                ArrayList<String> lineAsAL = new ArrayList<>(Arrays.asList(line1));
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
        totalNumELLStudents = numEllStudents;
        totalNumNativeEnglishStudents = numNonEllStudents;
        totalNumTotalStudents = numEllStudents + numNonEllStudents;
        System.out.println("Connecticut state Ell student population: " + calcualateEllPercentage(totalNumELLStudents, totalNumTotalStudents) + "%\n"+ 
        "Connecticut state non-ELL student population: " + calcualateNonEllPercentage(totalNumNativeEnglishStudents, totalNumTotalStudents) + "%");
    }

// helper methods: calculate percentage of ell and native students out of total for either all of CT or district
    public static int calcualateEllPercentage(double ell, double total) {
        return (int)(ell/total*100);
    }
    public static int calcualateNonEllPercentage(double nonEll, double total) {
        return (int)(nonEll/total*100);
    }

// question 2: What is the most lingusitically diverse town in Connecticut? And what percentage of their students are ELL students? What are the least linguistically diverse towns in Connecticut? And what percentage of their students are ELL students?
    public static void getHighestAndLowestDistrict(File m) throws FileNotFoundException {
        leastDiverseDistricts = new ArrayList<>();
        Scanner fileScan = new Scanner(m);
        fileScan.nextLine();
        ArrayList <String> districts = new ArrayList<>();
        int greatestPercentage = 0;
        int lowestPercentage = 0;
        double totalStudents = 0.0;
        double districtNumELLStudents = 0.0;
        double districtNumNativeEnglishStudents = 0.0;
        double districtNumTotalStudents = 0.0;
        String[] header = fileScan.next().split(",");
        ArrayList<String> headerValues = new ArrayList<>(Arrays.asList(header));
        int yearIndex = headerValues.indexOf("Year");
        int numStudentsIndex = headerValues.indexOf("Value");
        int typeOfLearner = headerValues.indexOf("Learner");
        int districtIndex = headerValues.indexOf("District");
        Double numEllStudents = 0.0;
        Double numNonEllStudents = 0.0;
        while (fileScan.hasNextLine()) {
            String line = fileScan.nextLine();
            if (!line.equals("")) {
                String[] line1 = line.split(",");
                ArrayList<String> lineAsAL = new ArrayList<>(Arrays.asList(line1));
                if(lineAsAL.get(yearIndex).equals("2020-2021") && Double.parseDouble(lineAsAL.get(numStudentsIndex))>0) {
                    if (lineAsAL.get(typeOfLearner).equals("Non-English Language Learner")) {
                        numNonEllStudents = Double.parseDouble(lineAsAL.get(numStudentsIndex));
                        String[] line2 = fileScan.nextLine().split(",");
                        ArrayList<String> lineAsAL2 = new ArrayList<>(Arrays.asList(line2));
                        numEllStudents = Double.parseDouble(lineAsAL2.get(numStudentsIndex));
                        totalStudents = numNonEllStudents + numEllStudents;
                    } else {
                    fileScan.nextLine();
                }
                
                 if (calcualateEllPercentage(numEllStudents, totalStudents) > greatestPercentage) {
                    greatestPercentage = calcualateEllPercentage(numEllStudents, totalStudents);
                    mostDiverseDistrict = lineAsAL.get(districtIndex);
                } 
                else if (calcualateEllPercentage(numEllStudents, totalStudents) <= lowestPercentage) {
                    lowestPercentage = calcualateEllPercentage(numEllStudents, totalStudents);
                    leastDiverseDistricts.add(lineAsAL.get(districtIndex));
                }
            } else {
                fileScan.nextLine();
            }
        }
    }
        System.out.println("The most lingusitically diverse school district in Connecticut (as of 2020-2021) is: " + mostDiverseDistrict  + 
    ", where " + greatestPercentage + "% of the student population consists of ELL students.");

    System.out.println("The least lingusitically diverse school districts in Connecticut (as of 2020-2021) are: " + leastDiverseDistricts  + 
    ", where " + lowestPercentage + "% of the student populations consist of ELL students.");
    }


// question 3: What is the most linguistically diverse town in Connecticut ranked as in terms of education equity?
    public static void getDistrictEquitableValue(File f, String mostDiverseDistrict, ArrayList <String> leastDiverseDistricts) throws FileNotFoundException {
        Scanner fileScan = new Scanner(f);
        fileScan.nextLine();

        // header info
        String[] header = fileScan.next().split(",");
        ArrayList<String> headerValues = new ArrayList<>(Arrays.asList(header));
        int rankIndex = headerValues.indexOf("Rank");
        int districtNameIndex = headerValues.indexOf("School");

                String line = fileScan.nextLine();
                // checks that line is not empty
                if (!line.equals("")) {
                    while (fileScan.hasNextLine()) {
                    String[] line2 = fileScan.nextLine().split(",");
                    ArrayList<String> lineAsAL = new ArrayList<>(Arrays.asList(line2));
                    if (mostDiverseDistrict.equals(lineAsAL.get(districtNameIndex))) {
                        System.out.println("The lingusitically most diverse district: " + mostDiverseDistrict + ", is ranked as number " + lineAsAL.get(rankIndex) + " in CT in school funding equity");
                    }
                }   
        
    } 
    }
}

