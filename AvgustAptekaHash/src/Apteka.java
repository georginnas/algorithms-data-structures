import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

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
class Ime implements Comparable<Ime> {
    protected String ime;

    public Ime(String ime) {
        this.ime = ime.toUpperCase();
    }

    @Override
    public String toString() {
        return ime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ime ime1 = (Ime) o;
        return Objects.equals(ime, ime1.ime);
    }

    @Override
    public int hashCode() {
        return (29 * (29 * (29 * 0 + ime.charAt(0)) + ime.charAt(1))+ ime.charAt(2)) % 102780;
    }

    @Override
    public int compareTo(Ime ime) {
        return 0;
    }
}
class Lek{
    protected String ime;
    protected String lista;
    protected int cena;
    protected  int broj;

    public Lek(String ime, String lista, int cena, int broj) {
        this.ime = ime;
        this.lista = lista;
        this.cena = cena;
        this.broj = broj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lek lek = (Lek) o;
        return lista == lek.lista &&
                cena == lek.cena &&
                broj == lek.broj &&
                Objects.equals(ime, lek.ime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, lista, cena, broj);
    }

    @Override
    public String toString() {
        return ime +"\n"+lista +"\n"+cena +"\n"+broj;
    }
}
public class Apteka {


    public static void main(String [] args){
Scanner sc=new Scanner(System.in);
int n=Integer.parseInt(sc.nextLine());
CBHT<Ime,Lek> ht=new CBHT<Ime,Lek>(2*n);
for(int i=0;i<n;i++){
String line=sc.nextLine();
String [] niza=line.split(" ");
Ime ime=new Ime(niza[0]);
if(Integer.parseInt(niza[1])==0){
    niza[1]="NEG";
}else niza[1]="POZ";
Lek lek=new Lek(niza[0],niza[1],Integer.parseInt(niza[2]),Integer.parseInt(niza[3]));
ht.insert(ime,lek);
}
String line;
        while(!((line=sc.nextLine()).equals("KRAJ"))){
            int m=Integer.parseInt(sc.nextLine());
            Ime ime=new Ime(line);
            SLLNode<MapEntry<Ime,Lek>> pok=ht.search(ime);
            if(pok==null){
                System.out.println("Nema takov lek");
            }else{
                if(m<=pok.element.value.broj){
                    System.out.println(pok.element.value);
                    System.out.println("Napravena narachka");
                    pok.element.value.broj-=m;
                }else{
                    System.out.println("Nema dovolno lekovi");
                }
            }
        }

    }
}
