import controller.IController;
import controller.doctorController;
import gui.IView;
import gui.View;
import persistence.IRepository;
import persistence.Repository;

public class Main {

    public static void main(String[] args) {
        IRepository rep = new Repository("src/pat.txt", "src/cons.txt");
        IController dc = new doctorController(rep);        // it's on!
        IView view = new View(dc);
        view.run2();

//	Repository rep = new Repository("pat.txt", "cons.txt");
//	doctorController dc = new doctorController(rep);		// it's on!
//	View view = new View(dc);
//		view.run();
    }


}