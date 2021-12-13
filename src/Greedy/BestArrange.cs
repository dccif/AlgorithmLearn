using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AlgorithmLearn.src.Greedy
{
    internal class BestArrange
    {
        public class Program
        {
            public int start;
            public int end;
            public Program(int start, int end)
            {
                this.start = start;
                this.end = end;
            }
        }
        public class ProgramComparator : IComparer<Program>
        {
            public int Compare(Program o1, Program o2)
            {
                return o1.end - o2.end;
            }
        }

        public static int bestArrange(Program[] programs, int timePoint)
        {
            Array.Sort(programs, new ProgramComparator());
            int result = 0;
            for (int i = 0; i < programs.Length; i++)
            {
                if (timePoint <= programs[i].start)
                {
                    result++;
                    timePoint = programs[i].end;
                }
            }
            return result;
        }
    }
}
