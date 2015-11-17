package gov.state.history.test_generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 * Processes all files in a given directory with an XSLT transformation and produces result files - Java class sources - in another directory.
 */
public class GeneratorUsingXsl {
	/* One Transofmer object will not be used for more than this number of documents.
	 * This is introduced due to a memory leak in Saxon or something.
	 */
	private final int TRANSFORMER_USE_LIMIT = 10;
	private final String xsl;
	private final Path inDir;
	private final Path baseOutDir;
	private final String inExt;
	private final String outExt;
	private String packageName;
	private Path outDir;
	private TransformerFactory tf = null;
	private Transformer transformer = null;
	private int transformerUseCounter = 0;

	public GeneratorUsingXsl(String xsl, Path inDir, Path outDir, String packageName, String inExt, String outExt) {
		this.xsl = xsl;
		this.inDir = inDir;
		this.baseOutDir = outDir;
		this.packageName = packageName;
		this.inExt = inExt;
		this.outExt = outExt;
		this.outDir = this.baseOutDir.resolve(packageName.replace('.', '/'));
	}

	public GeneratorUsingXsl(String xsl, Path inDir, Path outDir, String packageName) {
		this(xsl, inDir, outDir, packageName, "xml", "java");
	}

	public void processAllFiles() throws TestGeneratorException {
		System.out.printf("%n--------%nProcessing files from: %s%nJava files written into: %s%nUsing transform: %s%n", inDir, outDir, xsl);
		try {
			Files.createDirectories(outDir);
			Files.walkFileTree(inDir, new Visitor());
		} catch (IOException e) {
			throw new TestGeneratorException("Error when recursively processing files", e);
		}
	}
	
	private void processOneFile(Path file) throws IOException {
		String fileName = file.getFileName().toString();
		// "Generated Selenium Test Case"
		String className = "GSTC_"
				+ fileName.replace("."+inExt, "").replace('-', '_');
		String outFileName = className + "." + outExt;
		
		System.out.printf("%s -> %s%n", fileName, outFileName);
		
		try {
			doXsl(file.toFile(), outDir.resolve(outFileName).toFile(), className);
		} catch (TransformerException e) {
			throw new IOException(e);
		}
	}

	private void doXsl(File input, File output, String className) throws TransformerException {
		if(tf == null) 
			tf = TransformerFactory.newInstance("net.sf.saxon.jaxp.SaxonTransformerFactory", ClassLoader.getSystemClassLoader());
		if(transformer == null) {
			StreamSource xslSource = new StreamSource(xsl);
			transformer = tf.newTransformer(xslSource);
			transformer.setParameter("package-name", packageName);
			transformerUseCounter = 0;
		}
		transformer.setParameter("class-name", className);
		StreamSource src = new StreamSource(input);
		StreamResult res = new StreamResult(output);
		transformer.transform(src, res);
		transformerUseCounter++;
		if(transformerUseCounter >= TRANSFORMER_USE_LIMIT) {
			transformer = null;
		}
	}

	private class Visitor extends SimpleFileVisitor<Path> {
		private PathMatcher matcher;
		
		Visitor() {
			matcher = FileSystems.getDefault().getPathMatcher("glob:**."+inExt);
		}
				
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			if(matcher.matches(file)) {
				processOneFile(file);
			}
			return FileVisitResult.CONTINUE;
		}
	}
}
