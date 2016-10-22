package me.neo_0815.json.frame;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import me.neo_0815.json.file.BUILD_FileManager;
import me.neo_0815.json.file.JSON_FileManager;

public class JSON_Writer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField modid;
	private JTextField name;
	private JTextField path;

	public JSON_Writer() {
		setForeground(SystemColor.desktop);
		setTitle("JSON-Writer");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JRadioButton rdbtnItem = new JRadioButton("Item");
		rdbtnItem.setBounds(8, 239, 50, 24);
		contentPane.add(rdbtnItem);

		JRadioButton rdbtnBlock = new JRadioButton("Block");
		rdbtnBlock.setBounds(8, 206, 57, 24);
		contentPane.add(rdbtnBlock);

		ButtonGroup btngroup = new ButtonGroup();
		btngroup.add(rdbtnItem);
		btngroup.add(rdbtnBlock);

		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();

				if(source == btnGenerate) {
					if(!path.getText().isEmpty() && !modid.getText().isEmpty() && !name.getText().isEmpty()) {
						if(rdbtnItem.isSelected()) JSON_FileManager.createItemFile(modid.getText(), name.getText(), new File(path.getText() + name.getText() + ".json"));
						else if(rdbtnBlock.isSelected()) JSON_FileManager.createBlockFile(modid.getText(), name.getText(), new File(path.getText() + name.getText() + "(block_item).json"), new File(path.getText() + name.getText() + ".json"), new File(path.getText() + name.getText() + "(blockstate).json"));

						BUILD_FileManager.setModID(path.getText(), modid.getText());
					}
				}
			}
		});
		btnGenerate.setBackground(UIManager.getColor("Button.toolBarBorderBackground"));
		btnGenerate.setBounds(334, 233, 98, 26);
		contentPane.add(btnGenerate);

		modid = new JTextField();
		modid.setBounds(81, 99, 114, 20);
		contentPane.add(modid);

		JLabel lblModId = new JLabel("MOD ID:");
		lblModId.setBounds(8, 101, 55, 16);
		contentPane.add(lblModId);

		JLabel lblUnlocalizedRegistry = new JLabel("Unlocalized / Registry Name:");
		lblUnlocalizedRegistry.setBounds(8, 129, 168, 16);
		contentPane.add(lblUnlocalizedRegistry);

		name = new JTextField();
		name.setBounds(194, 127, 200, 20);
		contentPane.add(name);

		JLabel lblPath = new JLabel("Path:");
		lblPath.setBounds(39, 12, 37, 16);
		contentPane.add(lblPath);

		path = new JTextField();
		path.setBounds(94, 10, 267, 20);
		contentPane.add(path);

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Zielordner festlegen", "neo_0815");
		chooser.setFileFilter(filter);

		JButton btnFile = new JButton("...");
		btnFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();

				if(source == btnFile) {
					int value = chooser.showOpenDialog(btnFile);

					if(value == JFileChooser.APPROVE_OPTION) {
						File file = chooser.getSelectedFile();
						path.setText(file.getPath());
						path.setText(path.getText().replaceAll("build.neo_0815", ""));
						modid.setText(BUILD_FileManager.getModID(path.getText()));
					}
				}
			}
		});
		btnFile.setBounds(366, 10, 25, 18);
		contentPane.add(btnFile);

		redraw();
	}

	private void redraw() {
		setVisible(false);
		setVisible(true);
	}
}
