import java.util.Arrays;

/**
 * 
 * 
 * 
 */

public class Trie_Array {
    public static final int MAXN = 100001; // 字符串的最大长度
    public static int[][] trie = new int[MAXN][26];
    public static int[] pass = new int[MAXN];
    public static int[] end = new int[MAXN];
    public static int count;

    public static void build()
    {
        count = 1;
    }

    public static void insert(String s) {
       int cur = 1;
       for(int i = 0; i < s.length(); i++)
       {
           int index = s.charAt(i) - 'a';
           if(trie[cur][index] == 0)
           {
               trie[cur][index] = ++count;
           }
           cur = trie[cur][index];
           pass[cur]++;
       } 
       end[cur]++;
    }

    public static int countWordsEqualTo(String s) {
        int cur = 1;
        for(int i = 0; i < s.length(); i++)
        {
            int index = s.charAt(i) - 'a';
            if(trie[cur][index] == 0)
            {
                return 0;
            }
            cur = trie[cur][index];
        }
        return end[cur];
    }

    public static int countWordsStartingWith(String s) {
        int cur = 1;
        for(int i = 0; i < s.length(); i++)
        {
            int index = s.charAt(i) - 'a';
            if(trie[cur][index] == 0)
            {
                return 0;
            }
            cur = trie[cur][index];
        } 
        return pass[cur];
    }

    public static boolean erase(String s) {
        if(countWordsEqualTo(s) > 0)
        {
            int cur = 1;
            pass[cur]--;
            for(int i = 0; i < s.length(); i++) 
            {
                int index = s.charAt(i) - 'a';
                if(--pass[trie[cur][index]] == 0)
                {
                    trie[cur][index] = 0;
                    return true;
                }
                cur = trie[cur][index];
            }
            end[cur]--;
        }
        return false; 
    }

    public static void clear()
    {
        for(int i = 0; i <= count; i++)
        {
            Arrays.fill(trie[i], 0);
            pass[i] = 0;
            end[i] = 0;
        }
    }

    public static void main(String[] args) {
        
    }
}
