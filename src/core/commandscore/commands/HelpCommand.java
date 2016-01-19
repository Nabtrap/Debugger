package core.commandscore.commands;

import core.Debugger;
import core.commandscore.Command;
import core.commandscore.Report;

public class HelpCommand implements Command{

	String[] commands = {
			"exit",
			"info",
			"insp",
			"help",
			"lock",
	};
	String[] texts = {
			"Close this programm",
			"Shows interesting information abaout this pc",
			"Show how often you do thinks on this computer",
			"Shows this help message",
			"Locks and unlocks some Funktions"
	};

	String message = "";
	
	@Override
	public Report run(String cmd_text) {
		return run(new CommandMessage(cmd_text));
	}
	
	@Override
	public Report run(CommandMessage cmd) {
		System.out.println(message);
		return Report.Success;
	}

	@Override
	public String get_name() {
		return "help";
	}

	public HelpCommand() {
		int length_cmds = 0, length_texts = 0;
		
		for(String cmd : commands) {
			if(cmd.length() > length_cmds) {
				length_cmds = cmd.length();
			}
		}
		for(String text : texts) {
			if(text.length() > length_texts) {
				length_texts = text.length();
			}
		}

		length_cmds += 1;
		length_texts += 2;

		message += ("╔" + Debugger.repeat("═", length_cmds) + "╤" + Debugger.repeat("═", length_texts) + "╗\n");

		for(int i = 0; i < commands.length ;i++) {
			String td_1 = commands[i], td_2 = texts[i];


			if(td_1.length() % 2 == 1) td_1 = td_1 + " ";
			if(td_2.length() % 2 == 1) td_2 = td_2 + " ";

			td_2 = Debugger.repeat(" ", (int) (((length_texts - td_2.length()) / 2) + 0.5)) + td_2 + Debugger.repeat(" ", (int) (((length_texts - td_2.length()) / 2)+ 0.5));

			message += "║" + td_1 + " │ " +td_2 + "║\n";
			if(i != commands.length-1) message += "╟" + Debugger.repeat("─", length_cmds) + "┼" + Debugger.repeat("─", length_texts) + "╢\n";
		}

		message += ("╚" + Debugger.repeat("═", length_cmds) + "╧" + Debugger.repeat("═", length_texts) + "╝\n");

	}
}
