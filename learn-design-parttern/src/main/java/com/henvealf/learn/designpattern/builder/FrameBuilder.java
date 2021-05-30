package com.henvealf.learn.designpattern.builder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrameBuilder extends Builder implements ActionListener{

	private JFrame frame = new JFrame();
	private Box box = new Box(BoxLayout.Y_AXIS);
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
	}

	@Override
	public void makeTitle(String title) {
		// TODO Auto-generated method stub
		frame.setTitle(title);
	}

	@Override
	public void makeString(String str) {
		// TODO Auto-generated method stub
		box.add(new JLabel(str));
	}

	@Override
	public void makeItems(String[] items) {
		// TODO Auto-generated method stub
		Box innerbox = new Box(BoxLayout.Y_AXIS);
		for(int i = 0; i < items.length; i++){
			JButton button = new JButton(items[i]);
			button.addActionListener(this);
			innerbox.add(button);
		}
		box.add(innerbox);
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		frame.getContentPane().add(box);
		frame.pack();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		return frame;
	}

}
