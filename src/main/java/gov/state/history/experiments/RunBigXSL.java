package gov.state.history.experiments;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class RunBigXSL {

	public static void main(String[] args) {
		final String xsl = "/home/patryk/praca/exist/hsg/hsg-project/repos/hsg-test/src/xsl/generateVolumeTest.xsl";
		final String xml = "/home/patryk/praca/exist/hsg/hsg-project/repos/frus/volumes/frus1952-54v08.xml";
		final String out = "/home/patryk/praca/exist/hsg/hsg-project/repos/hsg-test/src/generated-tests/java/gov/state/history/generated/volume_tests/volumes/frus1952_54v08.java";
		
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer(new StreamSource(xsl));
			StreamSource src = new StreamSource(xml);
			StreamResult res = new StreamResult(out);
			t.transform(src, res);
			System.out.println("done");
		} catch (TransformerFactoryConfigurationError | TransformerException e) {
			e.printStackTrace();
		}
		
	}

}
