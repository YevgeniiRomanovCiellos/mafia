package Mafia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

public class Controller {

	/**
	 * @param args
	 */
	private View _view;
	private Model _model;

	public Controller(View v, Model m) {
		_view = v;
		_model = m;

		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setChatButtonClick();
		setReadyButtonClick();

	}

	public void killUser(String userid) {
		Card card = _view.cards.get(userid);
		_view.cards.remove(userid);
		_view.gamePanel.remove(card);
	}

	public void enableCards(boolean s) {
		for (Card value : View.cards.values()) {
			value.btn.setEnabled(s);
		}
	}

	public void enableVoteButtons(boolean s) {

		for (Card value : View.cards.values()) {
			value.vote.setEnabled(s);
		}
	}

	private void setChatButtonClick() {
		_view.send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				Map<String, Object> m = new HashMap<String, Object>();
				m.put("action", "chat");
				m.put("message",
						_model.user_name + ": " + _view.message.getText());
				_model.sendMessage(m);

			}
		});
	}

	private void setReadyButtonClick() {
		_view.active.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("action", "ready");
				_model.sendMessage(m);
				_view.active.setEnabled(false);
				_view.active.setText("Actived");
			}
		});
	}

}
