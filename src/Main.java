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

        boolean opened = false;
        while ((input = Utils.ReadLn()) != null)
        {
            for (int i = 0; i < input.length(); i++)
            {
                if (input.charAt(i) == '\"')
                {
                    if (!opened)
                    {
                        System.out.print("``");
                        opened = true;
                    } else
                    {
                        System.out.print("''");
                        opened = false;
                    }
                } else
                {
                    System.out.print(input.charAt(i));
                }
            }
            System.out.print("\n");
        }
    }

    private static class Utils
    {
        static String ReadLn()  // utility function to read from stdin
        {
            int maxLg = 255;
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

        static int getOnlyDigitFromLine()
        {
            String nLine = Utils.ReadLn();
            StringTokenizer tokenizer = new StringTokenizer(nLine);
            return Integer.parseInt(tokenizer.nextToken());
        }

    }
}
