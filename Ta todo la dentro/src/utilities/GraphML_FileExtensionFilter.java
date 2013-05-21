package utilities;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import magicNumbers.Values;

public class GraphML_FileExtensionFilter extends FileFilter{
	
	@Override
	public boolean accept(File file) {
		if (file.getName().endsWith(Values.file_extension)) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		return Values.file_extension;
	}

}
