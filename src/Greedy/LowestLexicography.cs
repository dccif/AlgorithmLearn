using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

internal class LowestLexicography
{
    class MyComparator : IComparer<String>
    {
        public int Compare(string o1, string o2)
        {
            return (o1 + o2).CompareTo(o2 + o1);
        }
    }

    public static String lowestString(String[] strs)
    {
        if (strs == null || strs.Length == 0)
        {
            return "";
        }
        Array.Sort(strs, new MyComparator());
        var res = new StringBuilder();
        for (int i = 0; i < strs.Length; i++)
        {
            res.Append(strs[i]);
        }
        return res.ToString();
    }
    public static void Main(String[] args)
    {
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        Console.WriteLine(lowestString(strs1));

        String[] strs2 = { "ba", "b" };
        Console.WriteLine(lowestString(strs2));
    }
}
