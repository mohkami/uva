package p100;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by Mason on 2016-08-16.
 */
public class Main
{

    public static void main(String[] arg)
    {
        Main m = new Main();
        m.run();
    }

    private void run()
    {

        String input;
        StringTokenizer tokenizer;
        int a, b;

        while ((input = Utils.ReadLn(255)) != null)
        {
            tokenizer = new StringTokenizer(input);
            a = Integer.parseInt(tokenizer.nextToken());
            b = Integer.parseInt(tokenizer.nextToken());

            System.out.println(a + " " + b + " " + getMaxCycle(a, b));
        }
    }

    private int getMaxCycle(int a, int b)
    {
        int maxCycle = 0;
        if (a > b)
        {
            int temp = a;
            a = b;
            b = temp;
        }
        for (int i = a; i <= b; i++)
        {
            int cycleLength = getCycleLength(i);
            if (cycleLength > maxCycle)
            {
                maxCycle = cycleLength;
            }
        }
        return maxCycle;
    }

    private int getCycleLength(int i)
    {
        int count = 1;
        long iLong = i;
        while (iLong != 1)
        {
            count++;
            if ((iLong & 1) == 1)
            {
                iLong = iLong * 3 + 1;
            } else
            {
                iLong /= 2;
            }
        }
        return count;
    }

    private static class Utils
    {
        static String ReadLn(int maxLg)  // utility function to read from stdin
        {
            byte lin[] = new byte[maxLg];
            int lg = 0, car = -1;

            try
            {
                while (lg < maxLg)
                {
                    car = System.in.read();
                    if ((car < 0) || (car == '\n'))
                    {
                        break;
                    }
                    lin[lg++] += car;
                }
            } catch (IOException e)
            {
                return (null);
            }

            if ((car < 0) && (lg == 0))
            {
                return (null);  // eof
            }
            return (new String(lin, 0, lg));
        }
    }
}
