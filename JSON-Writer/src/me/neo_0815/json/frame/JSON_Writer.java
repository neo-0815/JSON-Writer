package me.neo_0815.json.frame;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import me.neo_0815.json.file.BUILD_FileManager;
import me.neo_0815.json.file.JSON_FileManager;

public class JSON_Writer extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;
	private final JTextField modid;
	private final JTextField name;
	private final JTextField path;

	private final String succesText = " was created succesfully!";
	private final Font succesFont = new Font("Consolas", Font.LAYOUT_RIGHT_TO_LEFT, 13);

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

		final JLabel lblSucces = new JLabel();
		lblSucces.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSucces.setBounds(81, 215, 351, 16);
		lblSucces.setFont(succesFont);
		lblSucces.setForeground(Color.GREEN);
		contentPane.add(lblSucces);

		final JRadioButton rdbtnItem = new JRadioButton("Item");
		rdbtnItem.setBounds(8, 239, 50, 24);
		contentPane.add(rdbtnItem);

		final JRadioButton rdbtnBlock = new JRadioButton("Block");
		rdbtnBlock.setBounds(8, 211, 57, 24);
		contentPane.add(rdbtnBlock);

		final ButtonGroup btngroup = new ButtonGroup();
		btngroup.add(rdbtnItem);
		btngroup.add(rdbtnBlock);

		final JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final Object source = e.getSource();

				if(source == btnGenerate) if(!path.getText().isEmpty() && !modid.getText().isEmpty() && !name.getText().isEmpty() && (rdbtnItem.isSelected() || rdbtnBlock.isSelected())) {
					if(rdbtnItem.isSelected()) JSON_FileManager.createItemFile(modid.getText(), name.getText(), new File(path.getText() + name.getText() + ".json"));
					else if(rdbtnBlock.isSelected()) JSON_FileManager.createBlockFile(modid.getText(), name.getText(), new File(path.getText() + name.getText() + "(block_item).json"), new File(path.getText() + name.getText() + ".json"), new File(path.getText() + name.getText() + "(blockstate).json"));

					lblSucces.setText(name.getText() + succesText);
					name.setText(null);

					BUILD_FileManager.setModID(path.getText(), modid.getText());
				}
			}
		});
		btnGenerate.setBackground(UIManager.getColor("Button.toolBarBorderBackground"));
		btnGenerate.setBounds(334, 233, 98, 26);
		contentPane.add(btnGenerate);

		modid = new JTextField();
		modid.setBounds(81, 99, 114, 20);
		contentPane.add(modid);

		final JLabel lblModId = new JLabel("MOD ID:");
		lblModId.setBounds(8, 101, 55, 16);
		contentPane.add(lblModId);

		final JLabel lblUnlocalizedRegistry = new JLabel("Unlocalized / Registry Name:");
		lblUnlocalizedRegistry.setBounds(8, 129, 168, 16);
		contentPane.add(lblUnlocalizedRegistry);

		name = new JTextField();
		name.setBounds(194, 127, 200, 20);
		contentPane.add(name);

		final JLabel lblPath = new JLabel("Path:");
		lblPath.setBounds(39, 12, 37, 16);
		contentPane.add(lblPath);

		path = new JTextField();
		path.setBounds(94, 10, 267, 20);
		contentPane.add(path);

		final JFileChooser chooser = new JFileChooser();
		final FileNameExtensionFilter filter = new FileNameExtensionFilter("Zielordner festlegen", "neo_0815");
		chooser.setFileFilter(filter);

		final JButton btnFile = new JButton("...");
		btnFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final Object source = e.getSource();

				if(source == btnFile) {
					final int value = chooser.showOpenDialog(btnFile);

					if(value == JFileChooser.APPROVE_OPTION) {
						final File file = chooser.getSelectedFile();
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
