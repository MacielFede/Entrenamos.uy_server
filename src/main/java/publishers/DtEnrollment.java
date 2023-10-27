/**
 * DtEnrollment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publishers;

public class DtEnrollment  implements java.io.Serializable {
    private publishers.DtUser user;

    private java.lang.Float cost;

    private java.util.Calendar enrollmentDate;

    public DtEnrollment() {
    }

    public DtEnrollment(
           publishers.DtUser user,
           java.lang.Float cost,
           java.util.Calendar enrollmentDate) {
           this.user = user;
           this.cost = cost;
           this.enrollmentDate = enrollmentDate;
    }


    /**
     * Gets the user value for this DtEnrollment.
     * 
     * @return user
     */
    public publishers.DtUser getUser() {
        return user;
    }


    /**
     * Sets the user value for this DtEnrollment.
     * 
     * @param user
     */
    public void setUser(publishers.DtUser user) {
        this.user = user;
    }


    /**
     * Gets the cost value for this DtEnrollment.
     * 
     * @return cost
     */
    public java.lang.Float getCost() {
        return cost;
    }


    /**
     * Sets the cost value for this DtEnrollment.
     * 
     * @param cost
     */
    public void setCost(java.lang.Float cost) {
        this.cost = cost;
    }


    /**
     * Gets the enrollmentDate value for this DtEnrollment.
     * 
     * @return enrollmentDate
     */
    public java.util.Calendar getEnrollmentDate() {
        return enrollmentDate;
    }


    /**
     * Sets the enrollmentDate value for this DtEnrollment.
     * 
     * @param enrollmentDate
     */
    public void setEnrollmentDate(java.util.Calendar enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtEnrollment)) return false;
        DtEnrollment other = (DtEnrollment) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.cost==null && other.getCost()==null) || 
             (this.cost!=null &&
              this.cost.equals(other.getCost()))) &&
            ((this.enrollmentDate==null && other.getEnrollmentDate()==null) || 
             (this.enrollmentDate!=null &&
              this.enrollmentDate.equals(other.getEnrollmentDate())));
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
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getCost() != null) {
            _hashCode += getCost().hashCode();
        }
        if (getEnrollmentDate() != null) {
            _hashCode += getEnrollmentDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtEnrollment.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publishers/", "dtEnrollment"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("", "user"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publishers/", "dtUser"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cost");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cost"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enrollmentDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "enrollmentDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
