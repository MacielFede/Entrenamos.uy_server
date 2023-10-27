/**
 * DtProfessor.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publishers;

public class DtProfessor  extends publishers.DtUser  implements java.io.Serializable {
    private publishers.DtProfessorRelatedClassesEntry[] relatedClasses;

    private java.lang.String description;

    private java.lang.String biography;

    private java.lang.String webPage;

    public DtProfessor() {
    }

    public DtProfessor(
           java.lang.String nickname,
           java.lang.String name,
           java.lang.String lastName,
           java.lang.String email,
           java.util.Calendar bornDate,
           java.lang.String password,
           publishers.DtProfessorRelatedClassesEntry[] relatedClasses,
           java.lang.String description,
           java.lang.String biography,
           java.lang.String webPage) {
        super(
            nickname,
            name,
            lastName,
            email,
            bornDate,
            password);
        this.relatedClasses = relatedClasses;
        this.description = description;
        this.biography = biography;
        this.webPage = webPage;
    }


    /**
     * Gets the relatedClasses value for this DtProfessor.
     * 
     * @return relatedClasses
     */
    public publishers.DtProfessorRelatedClassesEntry[] getRelatedClasses() {
        return relatedClasses;
    }


    /**
     * Sets the relatedClasses value for this DtProfessor.
     * 
     * @param relatedClasses
     */
    public void setRelatedClasses(publishers.DtProfessorRelatedClassesEntry[] relatedClasses) {
        this.relatedClasses = relatedClasses;
    }


    /**
     * Gets the description value for this DtProfessor.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this DtProfessor.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the biography value for this DtProfessor.
     * 
     * @return biography
     */
    public java.lang.String getBiography() {
        return biography;
    }


    /**
     * Sets the biography value for this DtProfessor.
     * 
     * @param biography
     */
    public void setBiography(java.lang.String biography) {
        this.biography = biography;
    }


    /**
     * Gets the webPage value for this DtProfessor.
     * 
     * @return webPage
     */
    public java.lang.String getWebPage() {
        return webPage;
    }


    /**
     * Sets the webPage value for this DtProfessor.
     * 
     * @param webPage
     */
    public void setWebPage(java.lang.String webPage) {
        this.webPage = webPage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtProfessor)) return false;
        DtProfessor other = (DtProfessor) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.relatedClasses==null && other.getRelatedClasses()==null) || 
             (this.relatedClasses!=null &&
              java.util.Arrays.equals(this.relatedClasses, other.getRelatedClasses()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.biography==null && other.getBiography()==null) || 
             (this.biography!=null &&
              this.biography.equals(other.getBiography()))) &&
            ((this.webPage==null && other.getWebPage()==null) || 
             (this.webPage!=null &&
              this.webPage.equals(other.getWebPage())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getRelatedClasses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRelatedClasses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRelatedClasses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getBiography() != null) {
            _hashCode += getBiography().hashCode();
        }
        if (getWebPage() != null) {
            _hashCode += getWebPage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtProfessor.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publishers/", "dtProfessor"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relatedClasses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "relatedClasses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publishers/", ">>dtProfessor>relatedClasses>entry"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "entry"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("biography");
        elemField.setXmlName(new javax.xml.namespace.QName("", "biography"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("webPage");
        elemField.setXmlName(new javax.xml.namespace.QName("", "webPage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
