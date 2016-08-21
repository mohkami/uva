package p494;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Mason on 2016-08-16.
 */
public class Main
{
    private Utils utils;

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

    private void run()
    {
        String line;
        while ((line = utils.readLine()) != null)
        {
            String[] words = line.split("([^a-zA-Z])+");
            int wordsCount = words.length;
            for (String word : words)
            {
                wordsCount -= word.equals("") ? 1 : 0;
            }
            System.out.println(wordsCount);
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
    }
}
