package p499;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Mason on 2016-08-16.
 */
public class Main
{
    private Utils utils;

    private void run()
    {
        String line;
        while ((line = utils.readLine()) != null)
        {
            int[] letterCount = new int[125];
            int max = 0;
            for (int i = 0; i < line.length(); i++)
            {
                char letter = line.charAt(i);
                if (Character.isAlphabetic(letter))
                {
                    letterCount[(int) letter]++;
                    if (letterCount[(int) letter] > max)
                    {
                        max = letterCount[(int) letter];
                    }
                }
            }
            String mostFrequentLetters = getSortedAlphabetList(letterCount, max);
            utils.printLine(mostFrequentLetters + " " + max);
        }
    }

    private String getSortedAlphabetList(int[] countArray, int max)
    {
        List<Character> resArrayList = new ArrayList<>();
        for (int i = (int) 'A'; i < (int) 'Z'; i++)
        {
            if (countArray[i] == max)
            {
                resArrayList.add((char) i);
            }
        }
        for (int i = (int) 'a'; i < (int) 'z'; i++)
        {
            if (countArray[i] == max)
            {
                resArrayList.add((char) i);
            }
        }
        Collections.sort(resArrayList);
        String res = "";
        for (char letter : resArrayList)
        {
            res += letter;
        }
        return res;
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

        Utils(String inputFIle)
        {
            if (!inputFIle.isEmpty())
            {
                File file = new File(getCurrentDirectory() + inputFIle);

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

        int[] getNDigitFromLine(int n)
        {
            int[] values = new int[n];
            for (int i = 0; i < n; i++)
            {
                values[i] = scanner.nextInt();
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
