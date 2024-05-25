package RaPizza.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// @SuppressWarnings("serial")
public abstract class FramePopup extends JFrame {

	public FramePopup(String name) {
		super(name);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel.add(this.init());

		JPanel defaultButtons = new JPanel(new FlowLayout());
		JButton okButton = new JButton("OK");
		defaultButtons.add(okButton);
    JButton cancelButton = new JButton("Cancel");
		defaultButtons.add(cancelButton);

		panel.add(defaultButtons);
		this.add(panel);

    panel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

		this.pack();
		this.setVisible(true);

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
        if (quit(false)) dispose();
			}
		});
    cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
        quit(true);
        dispose();
			}
		});

	}

	protected abstract JPanel init();

	/** This method is call when the "OK" or "Cancel" button is pressed. (Does nothing when window naturally close)
	 *
	 * @param canceled
	 * @return True to close the window, false otherwise
	 */
	protected abstract boolean quit(boolean canceled);

}

// interface MapOperator { public Object op(Object o); }

// class Mapper {
// 	static Object[] map(Object[] ar, MapOperator op) {
// 		Object[] ret = new Object[ar.length];
// 		for (int i = 0; i < ar.length; i++)
// 			ret[i] = op.op(ar[i]);
// 		return ret;
// 	}
// }

