package core.commandscore.commands;

import core.Debugger;
import core.Events.Event;
import core.Events.Event.EventType;
import core.commandscore.Command;
import core.commandscore.Report;

public class ExitCommand implements Command{

	@Override
	public String get_name() {
		return "exit";
	}

	@Override
	public Report run(String cmd_text) {
		return run(new CommandMessage(cmd_text));
	}
	
	@Override
	public Report run(CommandMessage cmd) {
			Debugger.run = false;
		System.out.println("Bye!");
		Event e = new Event();
		e.type = EventType.Exit;
		Debugger.eventmanager.fire(e);
		return Report.Exit;
	}

}
