import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeMap;
public class BinarySearch {

	private static int iterations=0;
	public static void binary_search(TreeMap<String, ArrayList<String>> t,String[] a,int first,int last,String search){
		
       
	   int mid=(first+last)/2;

	   String comparand=a[mid];
	   
	   if(first<=last&&comparand.toString().toLowerCase().startsWith(search.toLowerCase())){getFileName(t.get(a[mid]));iterations+=1;traverse(t,a,search,mid);}
	   else if(first<=last&&search.toString().compareToIgnoreCase(comparand)>0){binary_search(t,a,mid+1,last,search);}
	   else if(first<=last&&search.toString().compareToIgnoreCase(comparand)<0){binary_search(t,a,first,mid-1,search);}
	   
	   
	   
	}
    
	
	static void traverse(TreeMap<String, ArrayList<String>> t,String[]a,String search,int mid){
		int i=mid;
		
		while(i<a.length-1&&a[i+1].toLowerCase().startsWith(search.toLowerCase())){
			
			i++;getFileName(t.get(a[i]));
		}
		i=mid;
		
		while(i>0&&a[i-1].toLowerCase().startsWith(search.toLowerCase())){
			
			i--;getFileName(t.get(a[i]));
		}
		
		
		
	}
	public static void main(String args[]){
		Scanner input=new Scanner(System.in);
		TreeMap t=new TreeMap();
		System.out.println("Enter the number of FileRecord objects in LnkedList");int n=input.nextInt();
		String[] a=new String[10];
		
		System.out.println("Enter elements one by one");
		input.nextLine();
		//for(int i=0;i<n;i++)a.add(input.nextLine());
		System.out.println("Enter the int to be searched");String search=input.nextLine();
		binary_search(t,a,0,n-1,search);
		System.out.println(iterations);
	    input.close();
	}
	public static void alternate_search(TreeMap<String,ArrayList<String>>t,String[] a,String search){
		int iterations2=0;
		for(String c:a){
			 if(c.toLowerCase().contains(search.toLowerCase())){
				 iterations2+=1;
				 for(int i=0;i<t.get(c).size();i++){		 
					  String []k= t.get(c).get(i).split("\\\\(?=[^\\\\]+$)");
			          
			        	  System.out.println(k[k.length-1]);
			          
				   
			       
			     }
			 }
			 
		 }
	  if(iterations2==0)System.out.println("Found Nowhere");
	}//end alternate search
	
	
	public static void initBinarySearch(TreeMap<String, ArrayList<String>> list,String[] a,int first,int last,String search){
		binary_search(list,a,first,last,search);
		if(iterations==0)
		   alternate_search(list,a,search);
		iterations=0;
	}
   
   public static  void getFileName(ArrayList<String> arrayList){
	  for(int i=0;i<arrayList.size();i++){		 
		  String []k=arrayList.get(i).split("\\\\(?=[^\\\\]+$)");
          
        	  System.out.println(k[k.length-1]);
          
	   
       
     }
}	



}
