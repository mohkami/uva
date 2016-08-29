package p11559;

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
        String inLine = inUtils.readLine();

        while (inLine != null)
        {

            int[] firstLines = inUtils.getNDigitFromLine(inLine, 4);
            int participants = firstLines[0];
            int budget = firstLines[1];
            int hotels = firstLines[2];
            int weeks = firstLines[3];

            boolean hotelFound = false;
            int eventsCost = 0;

            for (int i = 0; i < hotels; i++)
            {
                int hotelPrice = inUtils.readOneIntLine();
                int hotelCost = hotelPrice * participants;
                int[] weeksAvailability = inUtils.getNextNDigitFromLine(weeks);

                boolean hotelCanAccommodate = canHotelAccommodate(weeksAvailability,participants,weeks);

                if (hotelCanAccommodate && hotelCost < budget)
                {
                    if (!hotelFound)
                    {
                        eventsCost = hotelCost;
                    } else
                    {
                        eventsCost = eventsCost > hotelCost ? hotelCost : eventsCost;
                    }
                    hotelFound = true;
                }
            }

            resLines += hotelFound ? eventsCost + "\n" : "stay home\n";

            inLine = inUtils.readLine();
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

    private boolean canHotelAccommodate(int[] weeksAvailability, int participants, int weeks)
    {
        int j = 0;
        for (; j < weeks; j++)
        {
            if (weeksAvailability[j] >= participants)
            {
                break;
            }
        }
        return j!=weeks;
    }

    private int updateEventCost(int eventsCost, int participants, boolean hotelFound)
    {


        return eventsCost;
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
