package core.commandscore;

import java.util.ArrayList;
import java.util.List;

import core.commandscore.commands.ExitCommand;
import core.commandscore.commands.HelpCommand;
import core.commandscore.commands.InfoCommand;
import core.commandscore.commands.InspCommand;
import core.commandscore.commands.LockCommand;

public class Commandcore {

	List<Command> interpreter = new ArrayList<Command>();

	public Commandcore() {
		System.out.print(">");

		interpreter.add(new HelpCommand());
		interpreter.add(new ExitCommand());
		interpreter.add(new InfoCommand());
		interpreter.add(new InspCommand());
		interpreter.add(new LockCommand());
	}	


	public void run_command(String input_line) {
		Report report = Report.Error_cmd_not_found;

		if(input_line.length() > 0) {
			String command = input_line.trim().split(" ", 2)[0].toLowerCase().trim();

			for( Command cmd : interpreter) {
				if(cmd.get_name().equalsIgnoreCase(command)) {
					report = cmd.run(input_line);
				}
			}
			if(report == Report.Error_cmd_not_found) {
				System.out.println("Command not found! Sorry!");
			}
		}
		if(report != Report.Exit) {
			System.out.print(">");
		}
	}
}
