/**
 * DtMember.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publishers;

public class DtMember  extends publishers.DtUser  implements java.io.Serializable {
    private publishers.DtMemberRelatedClassesEntry[] relatedClasses;

    public DtMember() {
    }

    public DtMember(
           java.lang.String nickname,
           java.lang.String name,
           java.lang.String lastName,
           java.lang.String email,
           java.util.Calendar bornDate,
           java.lang.String password,
           publishers.DtMemberRelatedClassesEntry[] relatedClasses) {
        super(
            nickname,
            name,
            lastName,
            email,
            bornDate,
            password);
        this.relatedClasses = relatedClasses;
    }


    /**
     * Gets the relatedClasses value for this DtMember.
     * 
     * @return relatedClasses
     */
    public publishers.DtMemberRelatedClassesEntry[] getRelatedClasses() {
        return relatedClasses;
    }


    /**
     * Sets the relatedClasses value for this DtMember.
     * 
     * @param relatedClasses
     */
    public void setRelatedClasses(publishers.DtMemberRelatedClassesEntry[] relatedClasses) {
        this.relatedClasses = relatedClasses;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtMember)) return false;
        DtMember other = (DtMember) obj;
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
              java.util.Arrays.equals(this.relatedClasses, other.getRelatedClasses())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DtMember.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publishers/", "dtMember"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relatedClasses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "relatedClasses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publishers/", ">>dtMember>relatedClasses>entry"));
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
