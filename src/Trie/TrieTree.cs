using System;

public class TrieTree
{
    public class TrieNode
    {
        public int pass;
        public int end;
        public TrieNode[] nexts;

        public TrieNode()
        {
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }
    public class Trie
    {
        private TrieNode root;
        public Trie()
        {
            root = new TrieNode();
        }

        public void insert(String word)
        {
            if(word == null)
            {
                return;
            }
            char[] chs = word.ToCharArray();
            TrieNode node = root;
            node.pass++;
            int index = 0;
            for(int i = 0; i < chs.Length; i++)
            {
                index = chs[i] - 'a';
                if(node.nexts[index] == null)
                {
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word)
        {
            if (search(word) != 0)
            {
                char[] chs = word.ToCharArray();
                TrieNode node = root;
                node.pass--;
                int index = 0;
                for(int i = 0;i< chs.Length; i++)
                {
                    index = chs[i] - 'a';
                    if(--node.nexts[index].pass == 0)
                    {
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        public int search(String word)
        {
            if (word == null)
            {
                return 0;
            }
            char[] chs = word.ToCharArray();
            TrieNode node = root;
            int index = 0;
            for(int i = 0; i < chs.Length; i++)
            {
                index = chs[i] - 'a';
                if(node.nexts[index] == null)
                {
                    return 0;
                }
                node =node.nexts[index];
            }
            return node.end;
        }

        public int prefixNumber(String pre)
        {

            if (pre== null)
            {
                return 0;
            }
            char[] chs = pre.ToCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.Length; i++)
            {
                index = chs[i] - 'a';
                if (node.nexts[index] == null)
                {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }
    public static void Main(String[] args)
    {
        System.Diagnostics.Debug.WriteLine("This is in debug 2");
        Console.ReadLine();
    }
}
