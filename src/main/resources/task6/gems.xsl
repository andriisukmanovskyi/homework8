<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:table-layout="http://www.w3.org/1999/xhtml">
    <xsl:template match="/">
        <html>
            <body>
                <h1>Gems</h1>
                <table border="1" table-layout="auto" width="100%" cellspacing="0">
                    <tr>
                        <th>Name</th>
                        <th>Preciousness</th>
                        <th>Origin</th>
                        <th>Visual params</th>
                        <th>Value</th>
                    </tr>
                    <xsl:for-each select="gems/gem">
                        <xsl:sort select="value"/>
                        <tr>
                            <td>
                                <xsl:value-of select="name"/>
                            </td>
                            <td>
                                <xsl:value-of select="preciousness"/>
                            </td>
                            <td>
                                <xsl:value-of select="origin"/>
                            </td>
                            <td>
                                <xsl:for-each select="visualParams">
                                    <table border="1px" table-layout="auto" width="100%" cellspacing="0">
                                        <tr>
                                            <th>Color</th>
                                            <th>Transparency</th>
                                            <th>FacesNumber</th>
                                        </tr>
                                        <tr>
                                            <td>
                                                <xsl:value-of select="color"/>
                                            </td>
                                            <td>
                                                <xsl:value-of select="transparency"/>
                                            </td>
                                            <td>
                                                <xsl:value-of select="facesNumber"/>
                                            </td>
                                        </tr>
                                    </table>
                                </xsl:for-each>
                            </td>
                            <td>
                                <xsl:value-of select="value"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>