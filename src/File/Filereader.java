package File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Filereader {
	public static String[] Content;
	String filepath = "/tmp/SM/test.txt";
	int lines = 0;

	private String[] read(String filepath)
	{

		try {

			FileReader f = new FileReader(filepath);
			BufferedReader b = new BufferedReader(f);

			Content = new String[lines];

			for(int i = 0; i<lines;i++)
			{
				Content[i] = b.readLine().toString();
			}
			f.close();

			return Content;

		} catch (IOException e) {
			e.printStackTrace();
			System.err.print("Fehler! - Datei aus lesen");
			return null;
		}

	}

	public String[] getContent()
	{
		return read(filepath);
	}
	


	public Filereader(String path)
	{
		filepath = path;
		if(!new File(path).exists())
		{
			System.err.print("Fehler, die Datei exsitiert nicht! versuche zu erstellen.");
			try {
				new File(path).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				
				System.err.print("Fehler, Die Datei konnte nicht erstellt werden! - Shutingdown");
				System.exit(1);
			}
		}
		try {

			FileReader f;
			f = new FileReader(filepath);
			BufferedReader b = new BufferedReader(f);

			while(b.readLine() != null)
			{
				lines ++;
			}
			f.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.err.print("Fehler! - Datei lesen");
		}		
	}
}
