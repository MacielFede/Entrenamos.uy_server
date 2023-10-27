/**
 * DtClass.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publishers;

public class DtClass  implements java.io.Serializable {
    private java.lang.String name;

    private java.util.Calendar dateAndTime;

    private java.util.Calendar registerDate;

    private java.lang.String url;

    private java.lang.String imgName;

    private int enrollmentsQuantity;

    private publishers.DtClassEnrollmentsEntry[] enrollments;

    public DtClass() {
    }

    public DtClass(
           java.lang.String name,
           java.util.Calendar dateAndTime,
           java.util.Calendar registerDate,
           java.lang.String url,
           java.lang.String imgName,
           int enrollmentsQuantity,
           publishers.DtClassEnrollmentsEntry[] enrollments) {
           this.name = name;
           this.dateAndTime = dateAndTime;
           this.registerDate = registerDate;
           this.url = url;
           this.imgName = imgName;
           this.enrollmentsQuantity = enrollmentsQuantity;
           this.enrollments = enrollments;
    }


    /**
     * Gets the name value for this DtClass.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this DtClass.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the dateAndTime value for this DtClass.
     * 
     * @return dateAndTime
     */
    public java.util.Calendar getDateAndTime() {
        return dateAndTime;
    }


    /**
     * Sets the dateAndTime value for this DtClass.
     * 
     * @param dateAndTime
     */
    public void setDateAndTime(java.util.Calendar dateAndTime) {
        this.dateAndTime = dateAndTime;
    }


    /**
     * Gets the registerDate value for this DtClass.
     * 
     * @return registerDate
     */
    public java.util.Calendar getRegisterDate() {
        return registerDate;
    }


    /**
     * Sets the registerDate value for this DtClass.
     * 
     * @param registerDate
     */
    public void setRegisterDate(java.util.Calendar registerDate) {
        this.registerDate = registerDate;
    }


    /**
     * Gets the url value for this DtClass.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this DtClass.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the imgName value for this DtClass.
     * 
     * @return imgName
     */
    public java.lang.String getImgName() {
        return imgName;
    }


    /**
     * Sets the imgName value for this DtClass.
     * 
     * @param imgName
     */
    public void setImgName(java.lang.String imgName) {
        this.imgName = imgName;
    }


    /**
     * Gets the enrollmentsQuantity value for this DtClass.
     * 
     * @return enrollmentsQuantity
     */
    public int getEnrollmentsQuantity() {
        return enrollmentsQuantity;
    }


    /**
     * Sets the enrollmentsQuantity value for this DtClass.
     * 
     * @param enrollmentsQuantity
     */
    public void setEnrollmentsQuantity(int enrollmentsQuantity) {
        this.enrollmentsQuantity = enrollmentsQuantity;
    }


    /**
     * Gets the enrollments value for this DtClass.
     * 
     * @return enrollments
     */
    public publishers.DtClassEnrollmentsEntry[] getEnrollments() {
        return enrollments;
    }


    /**
     * Sets the enrollments value for this DtClass.
     * 
     * @param enrollments
     */
    public void setEnrollments(publishers.DtClassEnrollmentsEntry[] enrollments) {
        this.enrollments = enrollments;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtClass)) return false;
        DtClass other = (DtClass) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.dateAndTime==null && other.getDateAndTime()==null) || 
             (this.dateAndTime!=null &&
              this.dateAndTime.equals(other.getDateAndTime()))) &&
            ((this.registerDate==null && other.getRegisterDate()==null) || 
             (this.registerDate!=null &&
              this.registerDate.equals(other.getRegisterDate()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.imgName==null && other.getImgName()==null) || 
             (this.imgName!=null &&
              this.imgName.equals(other.getImgName()))) &&
            this.enrollmentsQuantity == other.getEnrollmentsQuantity() &&
            ((this.enrollments==null && other.getEnrollments()==null) || 
             (this.enrollments!=null &&
              java.util.Arrays.equals(this.enrollments, other.getEnrollments())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getDateAndTime() != null) {
            _hashCode += getDateAndTime().hashCode();
        }
        if (getRegisterDate() != null) {
            _hashCode += getRegisterDate().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        if (getImgName() != null) {
            _hashCode += getImgName().hashCode();
        }
        _hashCode += getEnrollmentsQuantity();
        if (getEnrollments() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEnrollments());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEnrollments(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtClass.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publishers/", "dtClass"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateAndTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dateAndTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registerDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "registerDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("", "url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imgName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "imgName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enrollmentsQuantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enrollmentsQuantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enrollments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enrollments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publishers/", ">>dtClass>enrollments>entry"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "entry"));
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
