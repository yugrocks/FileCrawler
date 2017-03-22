import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
@SuppressWarnings("unused")

public class FileCrawler  {
    
	
	static TreeMap<String, ArrayList<String>> list=new TreeMap<String, ArrayList<String>>();
    private static ObjectOutputStream output;
	private static ObjectInputStream input;
	
	
	
	private static void Crawl(String dir) {
		
		File root=new File(dir);
		String[]cd=dir.split("\\\\(?=[^\\\\]+$)");
        String b[]=cd[cd.length-1].replaceAll("[^A-Za-z0-9]", " ").split(" ");
        
        for(String c:b){
        	if(list.containsKey(c)){
        		list.get(c).add(dir);
        		continue;
        		
        	}
        	ArrayList<String> h=new ArrayList<String>();h.add(dir);
        	list.put(c,h);
        }
        
		try{String[] listdir=root.list();
			for(int itr=0;itr<listdir.length;itr++){
			    String entity=listdir[itr];
			if(new File(dir+"\\"+entity).isDirectory()){
				Crawl(dir+"\\"+entity);		
			}
			else {	
				for(String c:entity.replaceAll("[^A-Za-z0-9]", " ").split(" ")){
					if(list.containsKey(c)){
						list.get(c).add(dir+"\\"+entity);
						continue;
					}
		        	   ArrayList<String> h=new ArrayList<String>();h.add(dir+"\\"+entity);  	
		               list.put(c,h);
				}
			}		
		}}catch(NullPointerException e){} 
		root=null;
	}//end Crawl
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public static void main (String args[]) throws InterruptedException{
		
        Crawl("__Disk_path/label__");
		System.out.println("done scanning");
		
		//output functionality
		try {
			output=new ObjectOutputStream(new FileOutputStream("Database.ser"));//serialize the data
			output.writeObject(list);
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//output functionality ends here
		
		
		
		//input functionality ahead
		try {
			input=new ObjectInputStream(new FileInputStream("Database.ser"));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		FileRecord f;
		
		
			try {
				list=(TreeMap<String,ArrayList<String>>) input.readObject();
				
			} catch(EOFException endOfFileException){

				}
			catch(IOException e){}catch(ClassNotFoundException c){}
    	//inputting ends here
		
		Scanner in=new Scanner(System.in);
		String[] keyset=list.keySet().toArray(new String[list.size()]);
		Arrays.sort(keyset,new Word());
		
	   
		
	    while(true){
	    	 
	    	 System.out.println("Enter the keyword to search for:");
	    	
	    	 BinarySearch.initBinarySearch(list,keyset, 0, list.size(), in.nextLine());
	     }
		
		
		
	    }//end main





	
	
	
}	//end class FileCrawler

class Word implements Comparator<String>{

	@Override
	public int compare(String f1, String f2) {
		return -f2.compareToIgnoreCase(f1);	}
	}//end comparator class
	
	
	
	
	




