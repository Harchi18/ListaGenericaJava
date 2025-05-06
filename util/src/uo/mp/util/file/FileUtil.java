package uo.mp.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A utility class to read/write text lines from/to a text file
 */
public class FileUtil extends BaseFileUtil {
	@Override
	protected BufferedReader createReaderChain(String fileName) throws FileNotFoundException {
		return new BufferedReader(new FileReader(fileName));
	}

	@Override
	protected BufferedWriter createWriterChain(String fileName) throws IOException {
		return new BufferedWriter(new FileWriter(fileName));
	}

}
