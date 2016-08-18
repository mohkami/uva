package p101;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Mason on 2016-08-16.
 * TODO MOHS needs efficiency
 */
public class Main
{

    Map<Integer, Pile> pileNumberMap = new HashMap<Integer, Pile>();
    Map<Integer, Block> blockNumberMap = new HashMap<Integer, Block>();
    int n;

    public static void main(String[] arg)
    {
        Main m = new Main();
        m.run();
    }

    private void run()
    {

        String input;
        StringTokenizer tokenizer;

        n = Utils.getOnlyDigitFromLine();
        initializeSettings(n);

        while (!"quit".equals(input = Utils.ReadLn()))
        {
            tokenizer = new StringTokenizer(input);
            String action = tokenizer.nextToken();
            int a = Integer.parseInt(tokenizer.nextToken());
            String position = tokenizer.nextToken();
            int b = Integer.parseInt(tokenizer.nextToken());

            Block blockA = blockNumberMap.get(a);
            Block blockB = blockNumberMap.get(b);

            if (blockA.pileBelongsTo.equals(blockB.pileBelongsTo))
            {
                continue;
            }

            if ("move".equals(action))
            {
                if ("onto".equals(position))
                {
                    blockA.moveOnto(blockB);
                } else
                {
//                    over
                    blockA.moveOver(blockB);
                }
            } else
            {
//                pile
                if ("onto".equals(position))
                {
                    blockA.pileOnto(blockB);
                } else
                {
//                    over
                    blockA.pileOver(blockB);

                }
            }
        }
        printResult();
    }

    private void printResult()
    {

        String res = "";
        for (int i = 0; i < n; i++)
        {
            res += pileNumberMap.get(i) + "\n";
        }

        System.out.print(res);
    }

    private void initializeSettings(int n)
    {
        for (int i = 0; i < n; i++)
        {
            Pile pile = new Pile(i);
            pileNumberMap.put(i, pile);

            Block block = new Block(i, pile);
            blockNumberMap.put(i, block);

            pile.setFirstBlock(block);
            pile.setLastBlock(block);
        }
    }

    private class Block
    {
        int number;
        Pile pileBelongsTo;
        Block next;
        Block prev;

        Block(int number, Pile pile)
        {
            this.number = number;
            next = null;
            prev = null;
            pileBelongsTo = pile;
        }

        private void setNext(Block next)
        {
            this.next = next;

            if (next != null)
            {
                next.setPrev(this);
            }

            while (next != null)
            {
                next.setPileBelongsTo(pileBelongsTo);
                next = next.next;
            }
        }

        void moveOnto(Block b)
        {
            resetStackedBlocks();
            b.resetStackedBlocks();

            b.setNext(this);
        }

        void moveOver(Block blockB)
        {
            resetStackedBlocks();
            if (prev != null)
            {
                prev.setNext(null);
            }

            blockB.pileBelongsTo.getLastBlock().setNext(this);

            blockB.pileBelongsTo.setLastBlock(this);
        }

        void pileOnto(Block blockB)
        {
            blockB.resetStackedBlocks();

            if (prev != null)
            {
                prev.setNext(null);
            }

            blockB.pileBelongsTo.setLastBlock(pileBelongsTo.getLastBlock());
            pileBelongsTo.setLastBlock(prev);

            blockB.setNext(this);
        }


        private void pileOver(Block blockB)
        {
            if (prev != null)
            {
                prev.next = null;
            }

            pileBelongsTo.setLastBlock(prev);

            blockB.pileBelongsTo.getLastBlock().setNext(this);

            blockB.pileBelongsTo.setLastBlock(pileBelongsTo.getLastBlock());
        }

        void resetStackedBlocks()
        {
            Block nextBlock = next;
            while (nextBlock != null)
            {
                nextBlock.prev.next = null;
                nextBlock.prev = null;

                Pile pileOfNext = pileNumberMap.get(nextBlock.number);
                pileOfNext.setFirstBlock(nextBlock);
                nextBlock.setPileBelongsTo(pileOfNext);

                nextBlock = nextBlock.next;
            }
            setNext(null);
        }

        void setPileBelongsTo(Pile pileBelongsTo)
        {
            if (this.pileBelongsTo.firstBlock == this)
            {
                this.pileBelongsTo.firstBlock = null;
                this.pileBelongsTo.lastBlock = null;
            }
            this.pileBelongsTo = pileBelongsTo;

            if (next == null)
            {
                pileBelongsTo.setLastBlock(this);
            }
        }

        @Override
        public boolean equals(Object obj)
        {
            if (!(obj instanceof Block))
            {
                return false;
            }
            return ((Block) obj).number == this.number;
        }

        @Override
        public String toString()
        {
            return String.valueOf(number);
        }

        private void setPrev(Block prev)
        {
            this.prev = prev;
        }
    }

    private class Pile
    {
        int number;
        Block firstBlock;
        Block lastBlock;

        Pile(int number)
        {
            this.number = number;
        }

        void setFirstBlock(Block firstBlock)
        {
            this.firstBlock = firstBlock;
            if (lastBlock == null)
            {
                lastBlock = firstBlock;
            }
        }

        private void setLastBlock(Block lastBlock)
        {
            this.lastBlock = lastBlock;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (!(obj instanceof Pile))
            {
                return false;
            }
            return ((Pile) obj).number == this.number;
        }

        @Override
        public String toString()
        {
            String blocks = "";
            Block nextBlock = firstBlock;
            while (nextBlock != null)
            {
                blocks += " " + nextBlock.number;
                nextBlock = nextBlock.next;
            }
            return number + ":" + blocks;
        }

        Block getLastBlock()
        {
            return lastBlock;
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
