import java.io.Serializable;

public class FileRecord implements Serializable{
	private String word;
	private String path;
	
	public FileRecord(String word,String paths )
	{
		this.word=word;this.path=paths;
	}
	public String getWord(){return word;}
   
    public String getPath(){return path;}
     

   
}
