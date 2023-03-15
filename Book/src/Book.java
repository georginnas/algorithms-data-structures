import java.util.Scanner;

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

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
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

public class Book {
    public static void main(String [] args){
        Scanner sc=new Scanner(System.in);
        String line;
        CBHT<String,Integer> ht=new CBHT<String,Integer>(1000);
        while(!((line=sc.nextLine()).equals("#"))){
            String niza[]=line.toLowerCase().split(", ");
            int n=Integer.parseInt(niza[1]);
            String []iminja=niza[0].split(" ");
            ht.insert(niza[0],n);
            if(iminja.length!=1){
            for(int i=0;i<iminja.length;i++){
                ht.insert(iminja[i],n);}}}
        int m=Integer.parseInt(sc.nextLine());
        for(int i=0;i<m;i++){
            String linija=sc.nextLine();
            String []key=linija.toLowerCase().split(" ");
            SLLNode<MapEntry<String,Integer>> pok=ht.search(linija.toLowerCase());
            if(pok!=null){
                System.out.println(pok.element.value);
                continue;
            }else{
                if(key.length!=1){
                    System.out.println("Not found");
                }else {
                    SLLNode<MapEntry<String,Integer>> pok1=ht.search(key[0]);
                    if(pok1!=null){
                        System.out.println(pok1.element.value);
                    }else System.out.println("Not found");
                }
            }}}}

/*
import java.util.Scanner;

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

    public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
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

public class Book {
    public static void main(String [] args){
        Scanner sc=new Scanner(System.in);
        String line;
        CBHT<String,Integer> ht=new CBHT<String,Integer>(1000);
        while(!((line=sc.nextLine()).equals("#"))){
            String niza[]=line.toLowerCase().split(", ");
            int n=Integer.parseInt(niza[1]);
            String []iminja=niza[0].split(" ");
            ht.insert(niza[0],n);
            if(iminja.length!=1){
            for(int i=0;i<iminja.length;i++){
                ht.insert(iminja[i],n);}}}
        int m=Integer.parseInt(sc.nextLine());
        for(int i=0;i<m;i++){
            String linija=sc.nextLine();
            String []key=linija.toLowerCase().split(" ");
            SLLNode<MapEntry<String,Integer>> pok=ht.search(key[0]);
            if(pok!=null){
                System.out.println(pok.element.value);
            }else System.out.println("Not found");
        }
    }
}

 */
Mongol Invasions of Japan, 21
        diplomatic relations, 46
        Shelter for the Indigent, 29
        Vehicle storage, 166
        Soldiers, 265
        Soviet Union, 193
        Engineer-Building Workers team, 288
        Medical services, 300
        Temporary Rules, 147
        Teutonic Knights, 130
        Travel Pass, 271
        World War II, 236
        #
        6
        diplomatic relations
        Japan
        Cloud
        mongol japan
        eNgineer-building Workers team
        MEDICAL
        46
        21
        Not found
        Not found
        288
        300
        qsort function, 12
        qualifier type, 19
        recursive-descent parser, 29
        right shift operator, 33
        putchar library function, 45
        registers, 92
        push, 115
        pop, 117
        SBRK system call, 56
        relational operators, 34
        #
        8
        LIBRARY
        sbrk call
        Qualifier Type
        right shift
        recursiVE-dEscent
        Registers
        right
        type
        45
        Not found
        19
        Not found
        29
        92
        33
        19