import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Target;
import java.lang.ref.PhantomReference;
import java.text.StringCharacterIterator;
import java.util.Map;
import java.util.Scanner;

import javax.management.openmbean.OpenDataException;
import javax.swing.InputMap;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;
import javax.swing.text.StyledEditorKit.ForegroundAction;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }

    public int compareTo (K that) {
        // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
        MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}
class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class CBHT<K extends Comparable<K>, E> {

    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K,E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K,E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is
        // equal
        // to targetKey. Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
                return curr;
        }
        return null;
    }

    public void insert(K key, E val) {      // Insert the entry <key, val> into this CBHT.
        MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
            if (key.equals(((MapEntry<K, E>) curr.element).key)) {
                // Make newEntry replace the existing entry ...
                curr.element = newEntry;
                return;
            }
        }
        // Insert newEntry at the front of the 1WLL in bucket b ...
        buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
            if (key.equals(((MapEntry<K,E>) curr.element).key)) {
                if (pred == null)
                    buckets[b] = curr.succ;
                else
                    pred.succ = curr.succ;
                return;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
                temp += curr.element.toString() + " ";
            }
            temp += "\n";
        }
        return temp;
    }
}
public class FrekfentenString {
    public static void main(String[] args) throws NumberFormatException, IOException {
    Scanner sc=new Scanner(System.in);
    String stringce=sc.nextLine();
    CBHT<String,Integer> ht=new CBHT<String,Integer>(3000);
    int brojac=0;


    for(int i=0;i<=stringce.length();i++){

        for(int j=i+1;j<=stringce.length();j++){
        SLLNode<MapEntry<String,Integer>> pok=ht.search(stringce.substring(i,j));
            if(pok==null){
                ht.insert(stringce.substring(i,j),1);
}else{
                brojac=pok.element.value+1;
                ht.insert(stringce.substring(i,j),brojac);}}
    }

    int max=0;
    String najdolga="";
        for(int i=0;i<=stringce.length();i++){
            for(int j=i+1;j<=stringce.length();j++){
                SLLNode<MapEntry<String,Integer>> pok=ht.search(stringce.substring(i,j));
                if(pok.element.value>max){
                    max=pok.element.value;
                    najdolga=pok.element.key;}

                if(pok.element.value==max){
                    if(pok.element.key.length()>najdolga.length()){
                         najdolga=pok.element.key;}
                    else if(pok.element.key.length()==najdolga.length()){
                        if(pok.element.key.compareTo(najdolga)<0){
                            najdolga=pok.element.key;}
                    }}}}
    System.out.println(najdolga);
    }}