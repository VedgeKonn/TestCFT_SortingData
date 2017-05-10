
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SortingData {

    // Constants for checking
    static final int argNumbers = 4;             //Actual numbers of parameters for running application
    static final String argFileType = ".txt";    //Support type of files
    static final String argTypeInt = "-i";       //Argument for strings of numbers
    static final String argTypeString = "-s";    //Argument for strings of chars
    static final String argSortByAsc = "-a";     //Ascending sorting
    static final String argSortByDesc = "-d";    //Descending sorting
    // Arguments
    static String inputFileName;                 //1st argument, input file name
    static String outputFileName;                //2nd argument, output file name
    static String typeOfInformation;             //3rd argument, type of data
    static String sortBy;                        //4th argument, sorting by asc or desc
    // Variables
    static String[] inputData;                   //Array for input stream
    static int[] dataInt;                        //Array for integer data 
    static String[] dataString;                  //Array for strings
    static String outputData;                    //String for a output file
    //Checking folder of working
    static File path = new File(SortingData.class.getProtectionDomain().getCodeSource().getLocation().getPath());

    //Checking parameters
    static void checkArguments(String[] args) {
        if (args.length != 4) {
            System.out.println("Incorrect numbers of parameters");
            System.exit(0);
        } else if (!args[0].contains(argFileType) || !args[1].contains(argFileType)) {
            System.out.println("Input file: " + args[0]);
            System.out.println("Output file: " + args[1]);
            System.out.println("Don't know type of files. Please check parameters");
            System.exit(0);
        } else if (!args[2].equals(argTypeInt) && !args[2].equals(argTypeString)) {
            System.out.println("Argument: " + args[2]);
            System.out.println("Don't know type of information. Please check parameters");
            System.exit(0);
        } else if (!args[3].equals(argSortByAsc) && !args[3].equals(argSortByDesc)) {
            System.out.println("Argument: " + args[3]);
            System.out.println("Don't know type of sorting. Please check parameters");
            System.exit(0);
        } else {
            inputFileName = args[0];
            outputFileName = args[1];
            typeOfInformation = args[2];
            sortBy = args[3];
        }
    }

    //Reading data from the input file 
    static void readFile(String inputFileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(inputFileName), StandardCharsets.UTF_8);
        int k = 0;
        for (String line : lines) {
            k++;
        }
        inputData = new String[k];
        k = 0;
        for (String line : lines) {
            inputData[k] = line;
            k++;
        }
        if (typeOfInformation.equals(argTypeInt)) {
            dataToInt(inputData);
        } else {
            dataString = inputData;
        }
    }

    //Write data to integer array if file contents numbers
    static void dataToInt(String[] inputData) {
        int[] data = new int[inputData.length];
        try {
            for (int i = 0; i < inputData.length; i++) {
                String st;
                int k = 0;
                if (i == 0) {
                    if (inputData[i].length() > 1) {
                        st = Character.toString(inputData[i].charAt(1));
                        if (inputData[i].length() > 2) {
                            for (int j = 2; j < inputData[i].length(); j++) {
                                st += Character.toString(inputData[i].charAt(j));
                            }
                        }
                    } else {
                        st = Character.toString(inputData[i].charAt(0));
                    }
                    k = Integer.valueOf(st);
                } else {
                    k = Integer.valueOf(inputData[i]);
                }

                data[i] = k * 1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Incorrect type of arrray of numbers. Please, check data or parameters");
            System.exit(0);
        }
        dataInt = data;
    }

    //Write output data to the file
    static void writeFile(String outputFileName, String data) {
        File file = new File(outputFileName);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(data);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Sorting integer values in the array by ascending
    static void intAscSort(int[] dataInt) {
        int temp, j;
        for (int i = 0; i < dataInt.length - 1; i++) {
            if (dataInt[i] > dataInt[i + 1]) {
                temp = dataInt[i + 1];
                dataInt[i + 1] = dataInt[i];
                j = i;
                while (j > 0 && temp < dataInt[j - 1]) {
                    dataInt[j] = dataInt[j - 1];
                    j--;
                }
                dataInt[j] = temp;
            }
        }
    }
    
    //Sorting integer values in the array by descending
    static void intDescSort(int[] dataInt) {
        int temp, j;
        for (int i = dataInt.length - 1; i > 0; i--) {
            if (dataInt[i] > dataInt[i - 1]) {
                temp = dataInt[i - 1];
                dataInt[i - 1] = dataInt[i];
                j = i;
                while (j < dataInt.length - 1 && temp < dataInt[j + 1]) {
                    dataInt[j] = dataInt[j + 1];
                    j++;
                }
                dataInt[j] = temp;
            }
        }
    }

    //Sorting sting values in the array by ascending
    static void charAscSort(String[] dataString) {
        String temp;
        int k;
        for (int i = 0; i < dataString.length - 1; i++) {
            if (dataString[i].compareTo(dataString[i + 1]) > 0) {
                temp = dataString[i + 1];
                dataString[i + 1] = dataString[i];
                k = i;
                while (k > 0 && temp.compareTo(dataString[k - 1]) < 0) {
                    dataString[k] = dataString[k - 1];
                    k--;
                }
                dataString[k] = temp;
            }
        }
    }

    //Sorting sting values in the array by descending
    static void charDescSort(String[] dataString) {
        String temp;
        int k;
        for (int i = dataString.length - 1; i > 0; i--) {
            if (dataString[i].compareTo(dataString[i - 1]) > 0) {
                temp = dataString[i - 1];
                dataString[i - 1] = dataString[i];
                k = i;
                while (k < dataString.length - 1 && temp.compareTo(dataString[k + 1]) < 0) {
                    dataString[k] = dataString[k + 1];
                    k++;
                }
                dataString[k] = temp;
            }
        }
    }

    //Making string to the output file. For string values
    static void outputData(String[] dataString) {
        outputData = dataString[0] + "\n";
        for (int i = 1; i < dataString.length; i++) {
            if (i < dataString.length - 1) {
                outputData += dataString[i] + "\n";
            } else {
                outputData += dataString[i];
            }
        }
    }

    //Making string to the output file. For integer values
    static void outputData(int[] dataInt) {
        outputData = String.valueOf(dataInt[0]) + "\n";
        for (int i = 1; i < dataInt.length; i++) {
            if (i < dataInt.length - 1) {
                outputData += String.valueOf(dataInt[i]) + "\n";
            } else {
                outputData += String.valueOf(dataInt[i]);
            }
        }
    }
    
    //Checking and run correct type of sorting
    static void sorting() {
        if (typeOfInformation.equals(argTypeInt)) {
            if (sortBy.equals(argSortByAsc)) {
                intAscSort(dataInt);
            } else {
                intDescSort(dataInt);
            }
            outputData(dataInt);
        } else if (sortBy.equals(argSortByAsc)) {
            charAscSort(dataString);
            outputData(dataString);
        } else {
            charDescSort(dataString);
            outputData(dataString);
        }
    }

    //Main method
    public static void main(String[] args) throws IOException {

        checkArguments(args);                                        //Run checking parameters
        inputFileName = path + "\\" + inputFileName;                 //Making full path of the input file
        outputFileName = path + "\\" + outputFileName;               //Making full path of the output file
        readFile(inputFileName);                                     //Reading the input file
        sorting();                                                   //Sorting data in the file and preparing to write it
        writeFile(outputFileName, outputData);                       //Writing data to the output file
        System.out.println("Sorting complete. Check the file");      //Writing goodbye to user
        System.exit(0);                                              //Exit from application
    }
}
