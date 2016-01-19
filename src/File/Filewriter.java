package File;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Filewriter {

	String filepath = "/tmp/SM/test.txt";
	
	public void write(String[] content)
	{
		try {
			
			FileWriter fw = new FileWriter(filepath);
			for(String str : content)
			{
				fw.write(str + "\n");
			}
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public Filewriter(String path)
	{
		filepath = path;
		
		if(!new File(path).exists())
		{
			System.err.print("Datei Wird erstellt.\n");
			try {
				new File(path).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.print("Fehler, Die Datei konnte nicht erstellt werden! - Shutingdown\n");
				System.exit(1);
			}
		}
	}
}
