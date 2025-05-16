
/**
 *     用类写一颗前缀树（动态版本） nexts数组当然可以用哈希表来替代 
 *     需要完成以下功能：
 *     1. 返回有多少个字符串str加入到了该结构中
 *     2. 返回有多少个字符串以str作为前缀
 *     3. 删除字符串str
 *     4. 查找字符串str
 */

public class Trie
{
    class TrieNode
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

    private TrieNode root;
    public Trie()
    {
        root = new TrieNode();
    }

    public void insert(String str)
    {
        TrieNode node = root;
        node.pass++;
        for(int i = 0; i < str.length(); i++)
        {
            int index = str.charAt(i) - 'a';
            if(node.nexts[index] == null)
            {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end++;
    }

    public int countWordsEqualTo(String str)
    {
        TrieNode node = root;
        for(int i = 0; i < str.length(); i++)
        {
            int index = str.charAt(i) - 'a';
            if(node.nexts[index] == null)
            {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }

    public int countWordsStartingWith(String str)
    {
        TrieNode node = root;
        for(int i = 0; i < str.length(); i++)
        {
            int index = str.charAt(i) - 'a';
            if(node.nexts[index] == null)
            {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.pass;
    }

    public boolean erase(String str)
    {
        if(countWordsEqualTo(str) > 0)
        {
            TrieNode node = root;
            node.pass--;
            for(int i = 0; i < str.length(); i++)
            {
                int index = str.charAt(i) - 'a';
                if(--node.nexts[index].pass == 0)
                {
                    node.nexts[index] = null;
                    return true;
                }
                node = node.nexts[index];
            }
            node.end--;
            return true;
        }
        return false;
    }

    public static void main(String[] args)
    {
        Trie trie = new Trie();
        trie.insert("abc");
        trie.insert("abc");
        trie.insert("abcd");
        trie.insert("abce");
        System.out.println(trie.countWordsEqualTo("abc"));
        System.out.println(trie.countWordsStartingWith("ab"));
        System.out.println(trie.erase("abc"));
        System.out.println(trie.erase("abc"));
        System.out.println(trie.countWordsEqualTo("abc"));
        System.out.println(trie.countWordsStartingWith("ab"));
        System.out.println(trie.erase("zzz"));
    }

}