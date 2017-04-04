package iit.du.ac.bd.misubeimp.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.github.javaparser.JavaParser;

public class CodeChecker {

	public static void checkCode(String filePath) throws Exception {
		File file = new File(filePath);
		if (file.exists()) {
			try {
				JavaParser.parse(file);
			} catch (Exception e) {
				System.out.println(file.getName());
				writeToFile(file.getAbsolutePath());
				// e.printStackTrace();
			}
		}

	}

	private static  void writeToFile(String text) throws IOException {
		Writer output;
		String targetFilePath = "D:\\Masters\\PaperDataset\\2014_SCAM_An Empirical Analysis of Bug Patterns\\projects-srcparserexception.txt";
		output = new BufferedWriter(new FileWriter(targetFilePath, true));
		output.append(text + '\n');
		output.close();
	}
}
