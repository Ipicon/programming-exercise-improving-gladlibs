import java.util.*;
import edu.duke.*;
/**
 * Write a description of codonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class codonCount {
    private HashMap<String,Integer> map;
    public codonCount() {
        map = new HashMap<String,Integer>();
    }
    public void buildCodonMap(int start,String dna) {
        map.clear();
        System.out.println(dna);
        for(int i=start ; i<dna.length(); i+=3) {
            if((dna.length()-i) >= 3) {
                String codon = dna.substring(i, i+3).toUpperCase();
                if(!map.containsKey(codon)) {
                    map.put(codon,1);
                }
                else{
                    map.put(codon,map.get(codon)+1);
                }
            }
        }
        System.out.println("number of unique codon is: " + map.size());
        for(String s : map.keySet()) {
            System.out.println(s + "\t" + map.get(s));
        }
    }
    public String getMostCommonCodon() {
        String bigest = "";
        int bigestCounter = 0;
        for(String s : map.keySet()) {
            if(map.get(s) > bigestCounter) {
                bigest = s;
                bigestCounter = map.get(s);
            }
        }
        return bigest;
    }
    public void printCodonCounts(int start, int end) {
        System.out.println("Codons with number of occurrence between " + start  + " and " + end + " are:");
        for(String s : map.keySet()) {
            if(map.get(s) >= start && map.get(s) <= end) {
                System.out.println(s + "\t" + map.get(s));
            }
        }
    }
    public void tester() {
        FileResource fr = new FileResource();
        buildCodonMap(1,fr.asString().trim());
        System.out.println("Most common codon is " + getMostCommonCodon() + "\t" + map.get(getMostCommonCodon()));
        printCodonCounts(1,5);
    }
}
