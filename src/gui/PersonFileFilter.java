package gui;

import java.io.File;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PersonFileFilter extends FileFilter {
	
	private FileNameExtensionFilter perFilter;
	
	public PersonFileFilter(){
		setPerFilter(new FileNameExtensionFilter(getDescription(), getExtension()));
	}

	public boolean accept(File file) {

		if (file.isDirectory()) {
			return true;
		}
		String name = file.getName();
		String extension = Utils.getFileExtension(name);
		System.out.println(name);
		if (extension.equals("per")) {
			return true;
		}
		return false;
	}

	public String getExtension() {
		return "per";
	}

	public String getDescription() {
		return "Person database file (*.per)";
	}

	public FileNameExtensionFilter getPerFilter() {
		return perFilter;
	}

	public void setPerFilter(FileNameExtensionFilter perFilter) {
		this.perFilter = perFilter;
	}


}
