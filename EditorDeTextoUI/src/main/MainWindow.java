package main;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private Fichero file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// To access object from listeners

		frame = new JFrame();
		frame.setBounds(100, 100, 560, 393);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTextArea textArea = new JTextArea();
		textArea.setEnabled(false);
		textArea.setBounds(0, 0, 544, 332);
		frame.getContentPane().add(textArea);

		JMenuBar menuBarMain = new JMenuBar();
		frame.setJMenuBar(menuBarMain);

		JMenu menuBarArchivo = new JMenu("Archivo");
		menuBarMain.add(menuBarArchivo);
		
		JMenuItem menuItemNuevo = new JMenuItem("Nuevo");
		ActionListener eventoNuevo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Evento nuevo
				textArea.setEnabled(true);
				file = null;
			}
		};
		menuItemNuevo.addActionListener(eventoNuevo);
		menuBarArchivo.add(menuItemNuevo);

		JMenuItem menuItemAbrir = new JMenuItem("Abrir");
		menuItemAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Evento Abrir
				textArea.setEnabled(true);

				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int returnValue = jfc.showOpenDialog(frame);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					file = new Fichero(selectedFile.getAbsolutePath());
					System.out.println("Open file: " + selectedFile.getAbsolutePath());
				} else {
					JOptionPane.showMessageDialog(frame, "Could not open file.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		menuBarArchivo.add(menuItemAbrir);

		JMenuItem menuItemGuardar = new JMenuItem("Guardar");
		ActionListener eventoGuardar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Evento Guardar
				boolean status = false;
				if(file != null)
					status = file.escribirFichero(file.getNombreF());
				else {
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					int returnValue = jfc.showSaveDialog(frame);

					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jfc.getSelectedFile();
						file.setNombreF(selectedFile.getAbsolutePath());
						status = file.escribirFichero(selectedFile.getAbsolutePath());
						System.out.println("Open file: " + selectedFile.getAbsolutePath());
					} else {
						JOptionPane.showMessageDialog(null, "Could not save that file.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				if(status)
					JOptionPane.showMessageDialog(null, "Guardado perfectamente");
				else
					JOptionPane.showMessageDialog(null, "Could not save that file.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		};
		menuItemGuardar.addActionListener(eventoGuardar);
		menuBarArchivo.add(menuItemGuardar);

		JMenuItem menuItemGuardarComo = new JMenuItem("Guardar Como...");
		menuItemGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Evento Guardar Como
				boolean status = false;
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int returnValue = jfc.showSaveDialog(frame);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					file.setNombreF(selectedFile.getAbsolutePath());
					status = file.escribirFichero(selectedFile.getAbsolutePath());
					System.out.println("Open file: " + selectedFile.getAbsolutePath());
				}
				
				if(status)
					JOptionPane.showMessageDialog(null, "Guardado perfectamente");
				else
					JOptionPane.showMessageDialog(null, "Could not save that file.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		menuBarArchivo.add(menuItemGuardarComo);

		JMenu menuBarEditor = new JMenu("Editor");
		menuBarMain.add(menuBarEditor);

		JMenu menuFuente = new JMenu("Fuentes");
		menuBarEditor.add(menuFuente);

		JMenuItem menuItemFuenteSherif = new JMenuItem("Sherif , 12");
		menuItemFuenteSherif.setFont(new Font("Serif", Font.PLAIN, 12));
		menuItemFuenteSherif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Evento para cambiar fuente
				textArea.setFont(new Font("Serif", Font.PLAIN, 12));
			}
		});
		menuFuente.add(menuItemFuenteSherif);

		JMenuItem menuItemFuenteCalibri = new JMenuItem("Calibri, 12");
		menuItemFuenteCalibri.setFont(new Font("Calibri", Font.PLAIN, 12));
		menuItemFuenteCalibri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Evento para cambiar fuente
				textArea.setFont(new Font("Calibri", Font.PLAIN, 12));
			}
		});
		menuFuente.add(menuItemFuenteCalibri);

		JMenuItem menuItemCortar = new JMenuItem("Cortar");
		menuBarEditor.add(menuItemCortar);

		JMenuItem menuItemCopiar = new JMenuItem("Copiar");
		menuBarEditor.add(menuItemCopiar);

		JMenuItem menuItemPegar = new JMenuItem("Pegar");
		menuBarEditor.add(menuItemPegar);

		JButton btnSave = new JButton("");
		btnSave.addActionListener(eventoGuardar);
		menuBarMain.add(btnSave);
		btnSave.setIcon(new ImageIcon(
				"C:\\Users\\Ragger\\Pictures\\Desarollo interfaces\\save-black-18dp\\1x\\baseline_save_black_18dp.png"));

		JButton btnCreate = new JButton("");
		btnCreate.addActionListener(eventoNuevo);
		btnCreate.setIcon(new ImageIcon(
				"C:\\Users\\Ragger\\Pictures\\Desarollo interfaces\\note_add-black-18dp\\1x\\outline_note_add_black_18dp.png"));
		menuBarMain.add(btnCreate);

		JButton btnOpenFile = new JButton("");
		btnOpenFile.setIcon(new ImageIcon(
				"C:\\Users\\Ragger\\Pictures\\Desarollo interfaces\\folder_open-black-18dp\\1x\\baseline_folder_open_black_18dp.png"));
		menuBarMain.add(btnOpenFile);

		JButton btnCut = new JButton("");
		btnCut.setIcon(new ImageIcon(
				"C:\\Users\\Ragger\\Pictures\\Desarollo interfaces\\content_cut-black-18dp\\1x\\baseline_content_cut_black_18dp.png"));
		menuBarMain.add(btnCut);

		JButton btnPaste = new JButton("");
		btnPaste.setIcon(new ImageIcon(
				"C:\\Users\\Ragger\\Pictures\\Desarollo interfaces\\content_paste-black-18dp\\1x\\baseline_content_paste_black_18dp.png"));
		menuBarMain.add(btnPaste);
	}

}
