package algorithm.tree;

public class Trie {

    /*
    字典树，根节点不放数据，除根节点外所有节点只存放一个字符，且有26个子节点（可以为空）,从root到任意节点的路径表示存在该字符串
     */

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }

    class Node{
        char c;
        Node[] kids;
        boolean isEnd=false;
        Node(char c){this.c=c;kids=new Node[26];}
    }

    private Node root;

    /** Initialize your data structure here. */
    public Trie() {
        root=new Node('0');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur=root;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if(cur.kids[c-'a']==null) cur.kids[c-'a']=new Node(c);
            cur=cur.kids[c-'a'];
        }
        cur.isEnd=true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node cur=root;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if(cur.kids[c-'a']==null) return false;
            cur=cur.kids[c-'a'];
        }
        return cur.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node cur=root;
        for(int i=0;i<prefix.length();i++){
            char c=prefix.charAt(i);
            if(cur.kids[c-'a']==null) return false;
            cur=cur.kids[c-'a'];
        }
        return true;
    }
}


