<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:tei="http://www.tei-c.org/ns/1.0"
	xmlns:frus="http://history.state.gov/ns/1.0"
	>
	
	<xsl:output method="text" />

	<xsl:param name="baseurl" select="'http://localhost:8080/exist/apps/hsg-shell/'"/>
	<xsl:param name="filedir" />
	<xsl:param name="filename" />
	<xsl:param name="package-name" select="'generated.volume_tests'" />
	<xsl:param name="class-name" select="translate($document-id, '-', '_')"/>
	
	<xsl:variable name="document-id" select="volume/@id"/>

	<xsl:template match="/">	
	<xsl:text>package </xsl:text><xsl:value-of select="$package-name"/><xsl:text>;</xsl:text>
	
	<xsl:text>

import static org.assertj.core.api.Assertions.*;
import org.junit.*;
import org.openqa.selenium.*;
import gov.state.history.test_utils.AbstractSeleniumTest;

</xsl:text>
	
		<xsl:text>public class </xsl:text>
		<xsl:value-of select="$class-name" />
		<xsl:text> extends AbstractSeleniumTest { </xsl:text>
		
		<xsl:call-template name="field-declarations"/>
		
		<xsl:call-template name="MainTest"/>
		
		<xsl:text>}</xsl:text>
	</xsl:template>
	
	
	<xsl:template name="field-declarations"><![CDATA[
  private String mainUrl = "]]><xsl:value-of select="concat($baseurl, 'historicaldocuments/', $document-id)"/><![CDATA[";
]]></xsl:template>

	<xsl:template name="MainTest">
		<xsl:variable name="expected-html-title" select="'history.state.gov 3.0 shell'"/>
		<xsl:variable name="titleStmt" select="/tei:TEI/tei:teiHeader/tei:fileDesc/tei:titleStmt"/>
		<xsl:variable name="expected-title-src"
			select="concat(string($titleStmt/tei:title[@type='complete']), string($titleStmt/tei:title[@type='volume']))"/>
 		<xsl:variable name="expected-title"
			select="replace(replace($expected-title-src, '\s+', ' '), '&quot;', '\\&quot;' )"/>

<xsl:text><![CDATA[
  @Test
  public void mainTest() {
]]></xsl:text>
		
<xsl:text><![CDATA[    driver.get(mainUrl);
    assertThat(driver.getTitle()).isEqualToIgnoringWhitespace("]]></xsl:text>
<xsl:value-of select="$expected-html-title"/>
<xsl:text><![CDATA[");
    final String titleText = driver.findElement(By.cssSelector("h1.titleStmt1")).getText();
    assertThat(titleText).isEqualToIgnoringWhitespace("]]></xsl:text>
<xsl:value-of select="$expected-title"/>    
<xsl:text><![CDATA[".toUpperCase());
  }
]]></xsl:text>
  	</xsl:template>
	
</xsl:stylesheet>
