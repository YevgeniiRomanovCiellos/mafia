package Mafia;

import javax.swing.JFrame;

public class Controller {

	/**
	 * @param args
	 */
	private View _view;
	private Model _model;
	public Controller(View v, Model m)
	{
		_view=v;
		_model=m;
		
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
