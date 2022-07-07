package trie;

import java.util.List;

/**
 * @author Croy Qian
 * @createDate 2022/7/7
 * @Description 单词替换
 * 在英语中，我们有一个叫做 词根(root) 的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 * 现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * 你需要输出替换之后的句子。
 * @see <a href="https://leetcode.cn/problems/replace-words/"></a>
 */
public class ReplaceWord {
    class Trie {
        boolean isEnd;
        Trie[] children;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }
    }

    Trie root = new Trie();

    public void insertTrie(String words) {
        Trie p = root;
        for (int i = 0; i < words.length(); i++) {
            int index = words.charAt(i) - 'a';
            if (p.children[index] == null) {
                p.children[index] = new Trie();
            }
            p = p.children[index];
        }
        p.isEnd = true;
    }

    public String query(String s) {
        Trie p = root;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (p.children[index] == null) {
                break;
            }
            if (p.children[index].isEnd) {
                return s.substring(0, i+1);
            }
            p = p.children[index];
        }
        return s;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        for (String words : dictionary) {
            insertTrie(words);
        }
        StringBuilder sb = new StringBuilder();
        for (String str : sentence.split(" ")) {
            sb.append(query(str)).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

}
