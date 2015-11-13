package gov.state.history.test_generator;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FrusTestGenerator {
	//private static final String bibliographyXsl = "src/main/xsl/generateBibliographyTest.xsl";
	private static final String bibliographyXsl = "src/main/xsl/generateBibliographyHTMLUnitTest.xsl";
	private static final String bibliographyDir = "bibliography";
	private static final String volumeXsl = "src/main/xsl/generateVolumeTest.xsl";
	private static final String volumeHUXsl = "src/main/xsl/generateVolumeHUTest.xsl";
	private static final String volumeDir = "volumes";
	static final String defaultPackage = "gov.state.history.generated";
	static final String bibliographyPackage = "gov.state.history.generated.bibliography_tests";
	static final String volumePackage = "gov.state.history.generated.volume_tests";
	static final String volumeHUPackage = "gov.state.history.generated.volume_hu_tests";
	private final String frusBaseDir;
	private final String outDir;
	
	public FrusTestGenerator(String frusBaseDir, String outDir) {
		this.frusBaseDir = frusBaseDir;
		this.outDir = outDir;
	}

	public void close() {
	}

	/**
	 * @param args program arguments, which mean:
	 *   <ul>
	 *   <li>the base directory of frus subproject
	 *   <li>the base directory of generated sources (without package subfolders); optional, defaults to '.'
	 *   </ul> 
	 */
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Program arguments:");
			System.out.println("frus_dir (required) - the base directory of frus subproject");
			System.out.println("generated_sources_dir (optional, default = '.') - the base directory of generated sources (without package subfolders)");
			return;
		}
		final String frusDir = args[0];
		final String outDir = args.length >= 2 ? args[1] : ".";
		final FrusTestGenerator generator = new FrusTestGenerator(frusDir, outDir);
		try {
			generator.generateAllTests();
		} catch (TestGeneratorException e) {
			System.out.println("FRUS test generation failed");
			e.printStackTrace();
		} finally {
			generator.close();
		}
	}

	public void generateAllTests() throws TestGeneratorException {
		generateForBibliography();
		generateForVolumes();
	}

	private void generateForBibliography() throws TestGeneratorException {
		Path dir = Paths.get(frusBaseDir, bibliographyDir);
		Path outPath = Paths.get(outDir);
		//GeneratorUsingXsl xslGen = new GeneratorUsingXsl(bibliographyXsl, dir, outPath, bibliographyPackage);
		//xslGen.processAllFiles();
	}

	private void generateForVolumes() throws TestGeneratorException {
		Path dir = Paths.get(frusBaseDir, volumeDir);
		Path outPath = Paths.get(outDir);
		
		AvaliabilityTestGenerator atg = new AvaliabilityTestGenerator(dir, outPath, defaultPackage);
		atg.generateAvailabilityTest();
		GeneratorUsingXsl xslGen = new GeneratorUsingXsl(volumeXsl, dir, outPath, volumePackage);
		xslGen.processAllFiles();
		//xslGen = new GeneratorUsingXsl(volumeHUXsl, dir, outPath, volumeHUPackage);
		//xslGen.processAllFiles();
	}
}
