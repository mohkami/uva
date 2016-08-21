package p10420;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Mason on 2016-08-16.
 */
public class Main
{
    private Utils utils;

    Map<String, Integer> countryCounterMap = new HashMap<>();

    private void run()
    {
        int linesNumber = utils.readOneIntLine();
        for (int i = 0; i < linesNumber; i++)
        {
            String line = utils.readLine().trim();
            String[] words = line.split("\\s+");
            String countryName = words[0];

            countryCounterMap.put(countryName, countryCounterMap.containsKey(countryName) ? countryCounterMap.get(countryName) + 1 : 1);
        }
        List<String> sortedCountries = new ArrayList<>(countryCounterMap.keySet());
        Collections.sort(sortedCountries);

        for (String country : sortedCountries)
        {
            utils.printLine(country + " " + countryCounterMap.get(country));
        }
    }

    private Main(String inputFile)
    {
        utils = new Utils(inputFile);
    }

    public static void main(String[] arg)
    {
        String inputFile = "";
        if (arg.length > 0)
        {
            inputFile = arg[0];
        }
        Main m = new Main(inputFile);
        m.run();
    }

    private class Utils
    {
        Scanner scanner;

        Utils(String inputFile)
        {
            if (!inputFile.isEmpty())
            {
                File file = new File(getCurrentDirectory() + inputFile);

                try
                {
                    scanner = new Scanner(file);
                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                    System.out.print("File not found");
                }
            } else
            {
                scanner = new Scanner(System.in);
            }
        }

        private String getCurrentDirectory()
        {
            return System.getProperty("user.dir") + "\\src\\";
        }

        int readOneIntLine(){
            return getNDigitFromLine(1)[0];
        }

        int[] getNDigitFromLine(int n)
        {
            int[] values = new int[n];
            String[] words = utils.readLine().split("\\s+");
            for (int i = 0; i < n; i++)
            {
                values[i] = Integer.valueOf(words[i]);
            }
            return values;
        }

        String readLine()
        {
            String line = null;
            if (scanner.hasNext())
            {
                line = scanner.nextLine();
            }
            return line;
        }

        void printLine(String line)
        {
            System.out.println(line);
        }
    }
}
