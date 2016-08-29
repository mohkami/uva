package p11942;

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

        String resLines = "Lumberjacks:\n";
        int testCases = inUtils.readOneIntLine();

        for (int i = 0; i < testCases; i++)
        {
            int[] lengths = inUtils.getNextNDigitFromLine(10);
            boolean ascending = lengths[0] < lengths[1];
            int j = 2;
            for (; j < 10; j++)
            {
                if (ascending && lengths[j - 1] > lengths[j])
                {
                    break;
                } else if (!ascending && lengths[j - 1] < lengths[j])
                {
                    break;
                }
            }
            resLines += j == 10 ? "Ordered" : "Unordered";
            resLines += "\n";
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
            String[] words = line.split("\\s+");
            int[] values = new int[n];

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
