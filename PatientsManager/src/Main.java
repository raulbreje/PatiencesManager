import controller.doctorController;
import gui.View;
import persistence.Repository;



public class Main {
	
	public static void main(String[] args) 
	{
	
	Repository rep = new Repository("pat.txt", "cons.txt");
	doctorController dc = new doctorController(rep);		// it's on!
	View view = new View(dc);
		view.run();
	
	}
	

}