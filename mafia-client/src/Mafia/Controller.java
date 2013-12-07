package Mafia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		_view.send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

				_model.sendMessage(_view.message.getText());
				
			}});
	}

}
