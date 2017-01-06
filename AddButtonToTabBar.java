import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class AddButtonToTabBar extends JFrame {
	private JTabbedPane tp;
	private int tabCounter = 0;

	public AddButtonToTabBar() {
		super("Editor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JToolBar jtb = new JToolBar();
		JButton btnAdd = new JButton("Add Tab");
		ActionListener addTabl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTab();
			}
		};
		btnAdd.addActionListener(addTabl);
		jtb.add(btnAdd);

		JPanel pnlURL = new JPanel();
		tp = new JTabbedPane();
		addTab();
		getContentPane().add(jtb, BorderLayout.NORTH);
		getContentPane().add(tp, BorderLayout.CENTER);

		setSize(700, 700);
		setVisible(true);
	}

	void addTab() {
		// A Simple Text editor
		JEditorPane ep = new JEditorPane();
		ep.setEditable(true);
		tp.addTab("Tab - " + tabCounter, new JScrollPane(ep));

		//Preparing a button to add 
		JButton tabCloseButton = new JButton("X");
		tabCloseButton.setActionCommand("" + tabCounter);

		//ActionListener for what to do when the button is clicked, here is to close the particular tab
		ActionListener al = new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			JButton btn = (JButton) ae.getSource(); // It will get the button object which is clicked
			String s1 = btn.getActionCommand();
			for (int i = 1; i < tp.getTabCount(); i++) {
				JPanel pnl = (JPanel) tp.getTabComponentAt(i); // It will return the whole component which we had set in the Tab

				// It will get the button component. The parameter for the getComponent 
				// is index of the total number of component. Here 1 means the button and 0 means the lable.
				btn = (JButton) pnl.getComponent(1);
				String s2 = btn.getActionCommand();
				if (s1.equals(s2)) {
					tp.removeTabAt(i);
					break;
				}
			}
			}
		};
		tabCloseButton.addActionListener(al);

		// This condition is to make at least one tab to be in the application.
		if (tabCounter != 0) {
			JPanel pnl = new JPanel();
			pnl.setOpaque(false);
			pnl.add(new JLabel("Tab - "+tabCounter));
			pnl.add(tabCloseButton);
			tp.setTabComponentAt(tp.getTabCount() - 1, pnl);
			tp.setSelectedIndex(tp.getTabCount() - 1);
		}
		tabCounter++;
	}

	public static void main(String[] args) {
		new AddButtonToTabBar();
	}
}