package uo.mp.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import uo.mp.util.check.ArgumentChecks;

public abstract class BaseFileUtil {
	public List<String> readLines(String pathToTheFile) throws FileNotFoundException {
		ArgumentChecks.isNotBlank(pathToTheFile);
		try {
			BufferedReader reader = createReaderChain(pathToTheFile);
			try {
				List<String> res = new LinkedList<>();
				String line;
				while ((line = reader.readLine()) != null) {
					res.add(line);
				}
				return res;
			} finally {
				reader.close();
			}
		} catch (FileNotFoundException fnfe) {
			throw new FileNotFoundException("Fichero no encontrado: " + pathToTheFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract BufferedReader createReaderChain(String fileName) throws FileNotFoundException, IOException;
	protected abstract BufferedWriter createWriterChain(String fileName) throws  IOException;

	
	public void writeLines(String outFileName, List<String> lines) {
		ArgumentChecks.isNotBlank(outFileName);
		ArgumentChecks.isNotNull(lines);
		try {
			BufferedWriter out = createWriterChain(outFileName);
			try {
				for (String line : lines) {
					out.write(line);
					out.newLine();
				}
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
