package core.commandscore.commands;

import java.io.File;

import core.Debugger;
import core.commandscore.Command;
import core.commandscore.Report;

public class InfoCommand implements Command{

	@Override
	public String get_name() {
		return "info";
	}

	@Override
	public Report run(String cmd_text) {
		return run(new CommandMessage(cmd_text));
	}

	@Override
	public Report run(CommandMessage cmd) {
		String[] props = {
				"user.home",
				"java.version",
				"os.arch",
				"user.name",
				"user.language",
				"os.name",
				"os.version"
			};
			
			int length_td1 = 0, length_td2 = 0;
			for(String prop : props) {
				if(prop.length() > length_td1) {
					length_td1 = prop.length();
				}
				if(System.getProperty(prop).length() > length_td2) {
					length_td2 = System.getProperty(prop).length();
				}
			} 

			length_td1 += 2;
			length_td2 += 2;
			
			System.out.println("╔" + Debugger.repeat("═", length_td1) + "╦" + Debugger.repeat("═", length_td2) + "╗");
			
			for(String prop : props) {
				String td_1 = prop, td_2 = System.getProperty(prop);
				
				
				if(td_1.length() % 2 == 1) td_1 = td_1 + " ";
				if(td_2.length() % 2 == 1) td_2 = td_2 + " ";
				
				td_1 = Debugger.repeat(" ", (int) (((length_td1 - td_1.length()) / 2) + 0.5)) + td_1 + Debugger.repeat(" ", (int) (((length_td1 - td_1.length()) / 2)+ 0.5));
				td_2 = Debugger.repeat(" ", (int) (((length_td2 - td_2.length()) / 2) + 0.5)) + td_2 + Debugger.repeat(" ", (int) (((length_td2 - td_2.length()) / 2)+ 0.5));
				
				System.out.println("║" + td_1 + " ║ " +td_2 + "║");
			}
			
			System.out.println("╚" + Debugger.repeat("═", length_td1) + "╩" + Debugger.repeat("═", length_td2) + "╝");
			
			
			 File[] roots = File.listRoots();
			    for (File root : roots) {
			      System.out.println("File system root: " + root.getAbsolutePath());
			      System.out.println("Total space (bytes): " + root.getTotalSpace());
			      System.out.println("Free space (bytes): " + root.getFreeSpace());
			      System.out.println("Usable space (bytes): " + root.getUsableSpace());
			    }
			
			return Report.Success;
	}

}
