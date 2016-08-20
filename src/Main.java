import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public static void main(String[] arg) throws IOException
    {
        String inputFile = "";
        if (arg.length > 0)
        {
            inputFile = arg[0];
        }
        Main m = new Main(inputFile);
        m.run();
    }

    private void run() throws IOException
    {
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
    }
}