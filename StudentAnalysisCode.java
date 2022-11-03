import java.util.*;
import java.io.*;

public class StudentAnalysisCode {
    
    public static double totalNumELLStudents;
    public static double totalNumNativeEnglishStudents;
    public static double totalNumTotalStudents;

    public static String mostDiverseDistrict;
    public static ArrayList<String> leastDiverseDistricts = new ArrayList<>();



    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("SchoolEquitableRank.csv");
        File m = new File ("studentenrollmentbyell2008-2021.csv");
        Scanner cmtResultsFileScan = new Scanner(f);
        getTotalNumStudents(m);
        getHighestDistrict(m);
    }


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


    public static int calcualateEllPercentage(double ell, double total) {
        return (int)(ell/total*100);
    }

    public static int calcualateNonEllPercentage(double nonEll, double total) {
        return (int)(nonEll/total*100);
    }


    public static void getHighestDistrict(File m) throws FileNotFoundException {
        Scanner fileScan = new Scanner(m);
        fileScan.nextLine();
        ArrayList <String> districts = new ArrayList<>();
        //mostDiverseDistrict = "";
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



    public static void getDistrictEquitableValue(File m, String mostDiverseDistrict) throws FileNotFoundException {
        Scanner fileScan = new Scanner(m);
        fileScan.nextLine();
        ArrayList <String> districts = new ArrayList<>();
        //mostDiverseDistrict = "";
        int greatestPercentage = 0;
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
            } else {
                fileScan.nextLine();
            }
        }
    }
        System.out.println("The most lingusitically diverse school district in Connecticut (as of 2020-2021) is: " + mostDiverseDistrict  + 
    ", where " + greatestPercentage + "% of the student population consists of ELL students.");
    }

}