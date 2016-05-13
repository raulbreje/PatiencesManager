package com.breje.pm;

import com.breje.pm.controller.Controller;
import com.breje.pm.controller.impl.ControllerImpl;
import com.breje.pm.gui.View;
import com.breje.pm.gui.impl.ViewImpl;
import com.breje.pm.persistance.Repository;
import com.breje.pm.persistance.impl.ConsultationRepository;
import com.breje.pm.persistance.impl.PatientRepository;
import com.breje.pm.persistance.impl.RepositoryImpl;

public class Starter {

	public static void main(String[] args) {
//		Repository repository = new RepositoryImpl("persistance/pat.txt", "persistance/cons.txt");
		// Controller controller = new ControllerImpl(repository);

		Repository patientRepository = new PatientRepository("persistance/pat.txt");
		Repository consultationsRepository = new ConsultationRepository("persistance/cons.txt");
		Controller controller = new ControllerImpl(patientRepository, consultationsRepository);

		View view = new ViewImpl(controller);
		view.run();

		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("Beans.xml");
		// IView view = (IView) context.getBean("view");
		// IView view = new View(dc);
		// view.run2();
	}

}
