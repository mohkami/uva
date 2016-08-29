package p12015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Mason on 2016-08-16.
 */
public class Main
{
    private Utils inUtils;

    private void run(boolean runningLocally)
    {

        String resLines = "";
        int testCases = inUtils.readOneIntLine();

        for (int i = 0; i < testCases; i++)
        {
            int max = 0;
            String searchResults = "";
            for (int j = 0; j < 10; j++)
            {

                String line = inUtils.readLine();
                String[] words = inUtils.getWordsFromLine(line);
                String url = words[0] + "\n";
                int relevance = Integer.valueOf(words[1]);

                if (relevance >= max)
                {
                    searchResults = relevance == max ? (searchResults + url) : url;
                    max = relevance;
                }

            }
            resLines += "Case #" + (i + 1) + ":\n" + searchResults;

        }

        if (runningLocally)
        {
            Utils outUtils = new Utils("expected_output.txt");
            String line;
            String lines = "";
            while ((line = outUtils.readLine()) != null)
            {
                lines += line + "\n";
            }

//            outUtils.printLine(resLines + "\n --------- ");
//            outUtils.printLine(lines + "\n --------- ");
            outUtils.printLine(lines.equals(resLines) + "");
        } else
        {
            System.out.print(resLines);
        }
    }

    private Main(String inputFile)
    {
        inUtils = new Utils(inputFile);
    }

    public static void main(String[] arg)
    {
        long startTime = System.currentTimeMillis();

        String inputFile = "";
        if (arg.length > 0)
        {
            inputFile = arg[0];
        }
        Main m = new Main(inputFile);
        m.run(arg.length > 0);

        long finishTime = System.currentTimeMillis();

        if (arg.length > 0)
        {
            System.out.println("Total execution time: " + (finishTime - startTime) + "ms");
        }
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

        int readOneIntLine()
        {
            return getNextNDigitFromLine(1)[0];
        }

        int[] getNextNDigitFromLine(int n)
        {
            return getNDigitFromLine(inUtils.readLine(), n);
        }

        int[] getNDigitFromLine(String line, int n)
        {
            String[] words = getWordsFromLine(line);
            int[] values = new int[n];

            for (int i = 0; i < n; i++)
            {
                values[i] = Integer.valueOf(words[i]);
            }
            return values;
        }

        String[] getWordsFromLine(String line)
        {
            return line.split("\\s+");
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
