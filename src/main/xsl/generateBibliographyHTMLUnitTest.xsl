<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	
	<xsl:output method="text" />

	<xsl:param name="baseurl" select="'http://localhost:8080/exist/apps/hsg-shell/'"/>
	<xsl:param name="filedir" />
	<xsl:param name="filename" />
	<xsl:param name="package-name" select="'generated.bibliography_tests'" />
	<xsl:param name="class-name" select="translate($document-id, '-', '_')"/>
	
	<xsl:variable name="document-id" select="volume/@id"/>

	<xsl:template match="/">	
	<xsl:text>package </xsl:text><xsl:value-of select="$package-name"/><xsl:text>;</xsl:text>
	
	<xsl:text>

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;

import gov.state.history.test_utils.HSGUtils;

</xsl:text>
	
		<xsl:text>public class </xsl:text>
		<xsl:value-of select="$class-name" />
		<xsl:text> { </xsl:text>
		
		<xsl:call-template name="field-declarations"/>
		<xsl:call-template name="setup"/>
		<xsl:call-template name="teardown"/>
		
		<xsl:call-template name="MainTest"/>
		
		<xsl:text>}</xsl:text>
	</xsl:template>
	
	
	<xsl:template name="field-declarations"><![CDATA[
  private WebClient wc;
  private String documentId = "]]><xsl:value-of select="$document-id"/><![CDATA[";
  private String mainUrl = HSGUtils.getApplicationUrl() + "/historicaldocuments/" + documentId;
]]></xsl:template>

	<xsl:template name="setup"><![CDATA[
  @Before
  public void setUp() throws Exception {
    wc = new WebClient();
  }
]]></xsl:template>

	<xsl:template name="teardown"><![CDATA[
  @After
  public void tearDown() throws Exception {
    wc.close();
  }
]]></xsl:template>

	<xsl:template name="MainTest">
<xsl:text><![CDATA[
  @Test
  public void checkStatusCode() {
	try {
		WebRequest req = new WebRequest(new URL(mainUrl));
		WebResponse resp = wc.loadWebResponse(req);
		assertThat(resp.getStatusCode()).isEqualTo(200);
	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
]]></xsl:text>
  	</xsl:template>
	
</xsl:stylesheet>
