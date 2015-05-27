//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.01 at 01:50:23 PM CET 
//


package com.base.engine.core.formats.ogre.mesh;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "face"
})
@XmlRootElement(name = "lodfacelist")
public class Lodfacelist {

    @XmlAttribute(name = "submeshindex", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String submeshindex;
    @XmlAttribute(name = "numfaces", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String numfaces;
    @XmlElement(required = true)
    protected List<OgreFace> face;

    /**
     * Gets the value of the submeshindex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubmeshindex() {
        return submeshindex;
    }

    /**
     * Sets the value of the submeshindex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubmeshindex(String value) {
        this.submeshindex = value;
    }

    /**
     * Gets the value of the numfaces property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumfaces() {
        return numfaces;
    }

    /**
     * Sets the value of the numfaces property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumfaces(String value) {
        this.numfaces = value;
    }

    /**
     * Gets the value of the face property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the face property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OgreFace }
     * 
     * 
     */
    public List<OgreFace> getFace() {
        if (face == null) {
            face = new ArrayList<OgreFace>();
        }
        return this.face;
    }

}