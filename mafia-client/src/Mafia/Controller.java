package Mafia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.yaml.snakeyaml.Yaml;

public class Controller {

	/**
	 * @param args
	 */
	private View _view;
	private Model _model;
	private Yaml yaml = new Yaml();
	public Controller(View v, Model m)
	{
		_view=v;
		_model=m;
		
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		// Send chat messages
		_view.send.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

					Map<String, Object> m = new HashMap<String, Object>();
					m.put("action", "chat");
					m.put("message", _model.user_name + ": "+ _view.message.getText() );
					_model.sendMessage(m);
				
			}});
		
		
		
		
		
		_view.active.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("action", "ready");
				_model.sendMessage(m);
				_view.active.setEnabled(false);
				_view.active.setText("Loading");
			}
		});
	}

}
