package gov.state.history.test_generator;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AvaliabilityTestGenerator {
	private final static String LIST_FILE = "volumes_list.txt";
	private static final String DOTEXT = ".xml";
	private static final String JAVA_FILE = "TestAllVolumesAvailable.java";
	private static final String RESOURCES = "src/main/resources";
	private Path dir;
	private Path baseOutPath;
	private Path outPath;
	private String packageName;
	
	public AvaliabilityTestGenerator(Path dir, Path outPath, String packageName) {
		this.dir = dir;
		this.baseOutPath = outPath;
		this.packageName = packageName;
	}

	public void generateAvailabilityTest() throws TestGeneratorException {
		outPath = baseOutPath.resolve(packageName.replace('.', '/'));
		copyJavaFile();
		createListingFile();
	}

	private void copyJavaFile() throws TestGeneratorException {
		try {
			Path from = Paths.get(RESOURCES, JAVA_FILE);
			List<String> programLines = Files.readAllLines(from);
			List<String> lines = new ArrayList<>(programLines.size()+1);
			lines.add("package " + packageName + ";");
			lines.addAll(programLines);
			Files.createDirectories(outPath);
			Files.write(outPath.resolve(JAVA_FILE), lines, StandardOpenOption.CREATE);
		} catch (IOException e) {
			throw new TestGeneratorException("Error when generating the volumes availability test", e);
		}
	}

	private void createListingFile() throws TestGeneratorException {
		try {
			List<String> files = new LinkedList<>();
			DirectoryStream<Path> ds = Files.newDirectoryStream(dir, "*" + DOTEXT);
			for (Path file : ds) {
				files.add(file.getFileName().toString().replace(DOTEXT, ""));
			}
			Files.write(outPath.resolve(LIST_FILE), files, StandardOpenOption.CREATE);
		} catch (IOException e) {
			throw new TestGeneratorException("Error when generating the volumes availability test", e);
		}
	}
}
