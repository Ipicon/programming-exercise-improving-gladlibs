import edu.duke.*;
import java.util.*;
import java.io.*;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String,ArrayList<String>> map;
    public WordsInFiles(){
        map = new HashMap<String,ArrayList<String>>();
    }
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        for(String s : fr.words()) {
            if(map.containsKey(s)) {
                ArrayList<String> fileArr = map.get(s);
                if(!fileArr.contains(fileName)) {
                 fileArr.add(fileName);
                 map.put(s,fileArr);
                }
            }
            else{
                ArrayList<String> fileArr = new ArrayList<String>();
                fileArr.add(fileName);
                map.put(s,fileArr);
            }
        }
    }
    public void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    public int maxNumber () {
        int biggest = 0;
        for(String s : map.keySet()) {
            if(map.get(s).size() > biggest) 
                biggest = map.get(s).size();
        }
        return biggest;
    }
    public ArrayList<String> wordsInNumFiles (int number) {
        ArrayList<String> words = new ArrayList<String>();
        for(String s : map.keySet()) {
            if(map.get(s).size() == number)
                words.add(s);
        }
        return words;
    }
    public void printFilesIn (String name) {
        if(map.containsKey(name)) {
            ArrayList<String> fileNames = map.get(name);
            for(int i = 0 ; i<fileNames.size();i++) {
                System.out.println(fileNames.get(i));
            }
        }
    }
    public void tester() {
        buildWordFileMap();
        int maxNumber = maxNumber();
        ArrayList<String> wordsInNumFiles = wordsInNumFiles(4);
        System.out.println("Max number:\t" + maxNumber);
        for(int i = 0 ; i<wordsInNumFiles.size();i++) {
            System.out.println("The " + maxNumber + " files that the word \"" + wordsInNumFiles.get(i) + "\" occures are:");
            printFilesIn(wordsInNumFiles.get(i));
        }
        System.out.println(wordsInNumFiles.size());
        /*checks everything
          for(int p = 1; p <maxNumber+1 ; p++) {
            ArrayList<String> wordsInNumFiles = wordsInNumFiles(p);
            for(int i = 0 ; i<wordsInNumFiles.size();i++) {
                System.out.println("The " + p + " files that the word \"" + wordsInNumFiles.get(i) + "\" occures are:");
                printFilesIn(wordsInNumFiles.get(i));
            }
        }*/
    }
}
