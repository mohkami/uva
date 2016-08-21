package p10550;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Mason on 2016-08-16.
 */
public class Main
{
    private Utils inUtils;

    private final static int NUMBERS_COUNTS = 40;
    private final static int UNIT_DEGREE = 360 / NUMBERS_COUNTS;

    private void run(boolean runningLocally)
    {

        String lines1 = "";
        int[] lineValues = inUtils.getNextNDigitFromLine(4);
        while (!lastLine(lineValues))
        {
            int degreeSum = 2 * 360; //first two turns

            int start = lineValues[0];
            int first = lineValues[1];
            int second = lineValues[2];
            int third = lineValues[3];

            degreeSum += getDegreeBetweenNumbers(start, first, false);
            degreeSum += 360; // second full turn
            degreeSum += getDegreeBetweenNumbers(first, second, true);
            degreeSum += getDegreeBetweenNumbers(second, third, false);
            lines1 += degreeSum + "\n";

            lineValues = inUtils.getNextNDigitFromLine(4);
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

//        outUtils.printLine(lines1 + "\n --------- ");
//        outUtils.printLine(lines + "\n --------- ");
            outUtils.printLine(lines.equals(lines1) + "");
        } else
        {
            System.out.print(lines1);
        }
    }

    private int getDegreeBetweenNumbers(int first, int second, boolean clockwise)
    {
        int degree = 0;
        if (clockwise)
        {
            if (second < first)
            {
                second += NUMBERS_COUNTS;
            }
            degree = second - first;
        } else
        {
            if (second > first)
            {
                first += NUMBERS_COUNTS;
            }
            degree = first - second;
        }
        degree *= UNIT_DEGREE;
        return degree;
    }

    private boolean lastLine(int[] lineValues)
    {
        for (int value : lineValues)
        {
            if (value != 0)
            {
                return false;
            }
        }
        return true;
    }

    private Main(String inputFile)
    {
        inUtils = new Utils(inputFile);
    }

    public static void main(String[] arg)
    {
        String inputFile = "";
        if (arg.length > 0)
        {
            inputFile = arg[0];
        }
        Main m = new Main(inputFile);
        m.run(arg.length > 0);
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
