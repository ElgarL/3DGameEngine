//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.01 at 01:50:23 PM CET 
//


package com.base.engine.core.formats.ogre.mesh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "face")
public class OgreFace {

    @XmlAttribute(name = "v1", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String v1;
    @XmlAttribute(name = "v2")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String v2;
    @XmlAttribute(name = "v3")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String v3;

    /**
     * Gets the value of the v1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getV1() {
        return v1;
    }

    /**
     * Sets the value of the v1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setV1(String value) {
        this.v1 = value;
    }

    /**
     * Gets the value of the v2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getV2() {
        return v2;
    }

    /**
     * Sets the value of the v2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setV2(String value) {
        this.v2 = value;
    }

    /**
     * Gets the value of the v3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getV3() {
        return v3;
    }

    /**
     * Sets the value of the v3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setV3(String value) {
        this.v3 = value;
    }

}
