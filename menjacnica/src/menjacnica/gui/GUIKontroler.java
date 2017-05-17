package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import menjacnica.Menjacnica;
import menjacnica.MenjacnicaInterface;
import menjacnica.Valuta;

public class GUIKontroler {
	private static MenjacnicaInterface sistem;

	private static MenjacnicaGUI menjacnicaProzor;
	private static DodajKursGUI dodajKursProzor;
	private static ObrisiKursGUI obrisiKursProzor;
	private static IzvrsiZamenuGUI izvrsiZamenuProzor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sistem = new Menjacnica();
					menjacnicaProzor = new MenjacnicaGUI();
					menjacnicaProzor.setVisible(true);
					menjacnicaProzor.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void prikaziDodajKursProzor() {
		dodajKursProzor = new DodajKursGUI(menjacnicaProzor);
		dodajKursProzor.setLocationRelativeTo(menjacnicaProzor);
		dodajKursProzor.setVisible(true);
	}

	public static void prikaziObrisiKursProzor(Valuta valuta) {
		obrisiKursProzor = new ObrisiKursGUI(menjacnicaProzor, valuta);
		obrisiKursProzor.setLocationRelativeTo(null);
		obrisiKursProzor.setVisible(true);
	}

	public static void prikaziIzvrsiZamenuProzor(Valuta valuta) {
		izvrsiZamenuProzor = new IzvrsiZamenuGUI(menjacnicaProzor, valuta);
		izvrsiZamenuProzor.setLocationRelativeTo(null);
		izvrsiZamenuProzor.setVisible(true);
	}

	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(menjacnicaProzor.getContentPane(),
				"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak", JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public static LinkedList<Valuta> vratiKursnuListu() {
		return sistem.vratiKursnuListu();
	}

	public static void unesiKurs(Valuta valuta) {
		// Dodavanje valute u kursnu listu
		sistem.dodajValutu(valuta);

		// Osvezavanje glavnog prozora
		menjacnicaProzor.prikaziSveValute();

	}

	public static double izvrsiZamenuValute(Valuta valuta, double iznos, boolean selected) {
		return sistem.izvrsiTransakciju(valuta, selected, iznos);
	}

	public static void obrisiValutu(Valuta valuta) {
		sistem.obrisiValutu(valuta);
		menjacnicaProzor.prikaziSveValute();

	}

	public static void ucitajIzFajla() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(menjacnicaProzor.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				sistem.ucitajIzFajla(file.getAbsolutePath());
				menjacnicaProzor.prikaziSveValute();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(menjacnicaProzor.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void sacuvajUFajl() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(menjacnicaProzor.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				sistem.sacuvajUFajl(file.getAbsolutePath());
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(menjacnicaProzor.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void prikaziAboutProzor() {
		JOptionPane.showMessageDialog(menjacnicaProzor.getContentPane(), "Autor: Bojan Tomic, Verzija 1.0",
				"O programu Menjacnica", JOptionPane.INFORMATION_MESSAGE);
	}
}
