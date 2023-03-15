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
class ChemicalElement implements Comparable<ChemicalElement> {
    private char sym1, sym2; // The two letters of the chemical symbol.

    public ChemicalElement (String symbol) {
        if (symbol.length() >= 1)
            sym1 = Character.toUpperCase(symbol.charAt(0));
        else
            sym1 = ' '; // Should really fail.
        if (symbol.length() >= 2)
            sym2 = Character.toLowerCase(symbol.charAt(1));
        else
            sym2 = ' ';
    }
    public int hashCode () {
// funkcija koja naoga hash kod t.e. reden broj na prvata bukva
        return sym1 - 'A';
    }

    @Override
    public int compareTo(ChemicalElement chemicalElement) {
        return 0;
    }
    /*public static void main (String[] args) {
        CBHT<ChemicalElement,Integer> table1 = new
                CBHT<ChemicalElement,Integer>(26);
        table1.insert(new ChemicalElement("F"), new Integer(9));
        table1.insert(new ChemicalElement("Ne"), new Integer(10));
        table1.insert(new ChemicalElement("Cl"), new Integer(17));
        table1.insert(new ChemicalElement("Ar"), new Integer(18));
        table1.insert(new ChemicalElement("Br"), new Integer(35));
        table1.insert(new ChemicalElement("Kr"), new Integer(36));
        table1.insert(new ChemicalElement("I"), new Integer(53));
        table1.insert(new ChemicalElement("Xe"), new Integer(54));
        System.out.println ("Tabelata od slajd 5");
        System.out.println(table1);
    }

     */
    public static void main (String[] args) {
        CBHT<ChemicalElement,Integer> table2 = new
                CBHT<ChemicalElement,Integer>(26);
        table2.insert(new ChemicalElement("H"), new Integer(1));
        table2.insert(new ChemicalElement("He"), new Integer(2));
        table2.insert(new ChemicalElement("Li"), new Integer(3));
        table2.insert(new ChemicalElement("Be"), new Integer(4));
        table2.insert(new ChemicalElement("Na"), new Integer(11));
        table2.insert(new ChemicalElement("Mg"), new Integer(12));
        table2.insert(new ChemicalElement("K"), new Integer(19));
        table2.insert(new ChemicalElement("Ca"), new Integer(20));
        table2.insert(new ChemicalElement("Rb"), new Integer(37));
        table2.insert(new ChemicalElement("Sr"), new Integer(38));
        table2.insert(new ChemicalElement("Cs"), new Integer(55));
        table2.insert(new ChemicalElement("Ba"), new Integer(56));
        System.out.println ("Tabelata od slajd 6");
        System.out.println(table2);
}}

