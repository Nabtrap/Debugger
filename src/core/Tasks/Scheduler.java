package core.Tasks;

import java.util.HashMap;
import java.util.Map;

public class Scheduler {
	static Map<Task, Boolean> tasks = new HashMap<Task,Boolean>();
	
	
	public int schedule_task(Task t) {
		tasks.put(t, false);
		return tasks.size()-1;
	}
	
	public int schedule_repeating_task(Task t) {
		tasks.put(t, true);
		return tasks.size()-1;
	}
	
	public void stop_task(int i) {

		Task[] a = new Task[tasks.size()];
		Task to_del  = tasks.keySet().toArray(a)[i];
		tasks.remove(to_del);
	}
	
	public static void main(String[] args) {
		boolean run = true;
		int step = 0;
		while (run) {
			
			if((System.nanoTime() / 100) % 10 == 0) {//alle 0.1 sec
				if(step >= tasks.size()) {
					step = 0;
					if(tasks.size() == 0) {
						for(int i = -999999999; i < 999999999; i++) {
							
						}
					}
				}
				
				Task[] a = new Task[tasks.size()];
				Task current = tasks.keySet().toArray(a)[step];
				
				if(!tasks.get(current)) {
					//remove
					tasks.remove(current);
				}
				
				step = step++;
			}
		}
	}
}