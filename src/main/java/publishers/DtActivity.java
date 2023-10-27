/**
 * DtActivity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publishers;

public class DtActivity  implements java.io.Serializable {
    private java.lang.String name;

    private java.lang.String description;

    private java.lang.String imgName;

    private java.lang.Integer duration;

    private java.lang.Float price;

    private java.util.Calendar registryDate;

    private int classesQuantity;

    private publishers.DtActivityClassesEntry[] classes;

    public DtActivity() {
    }

    public DtActivity(
           java.lang.String name,
           java.lang.String description,
           java.lang.String imgName,
           java.lang.Integer duration,
           java.lang.Float price,
           java.util.Calendar registryDate,
           int classesQuantity,
           publishers.DtActivityClassesEntry[] classes) {
           this.name = name;
           this.description = description;
           this.imgName = imgName;
           this.duration = duration;
           this.price = price;
           this.registryDate = registryDate;
           this.classesQuantity = classesQuantity;
           this.classes = classes;
    }


    /**
     * Gets the name value for this DtActivity.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this DtActivity.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the description value for this DtActivity.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this DtActivity.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the imgName value for this DtActivity.
     * 
     * @return imgName
     */
    public java.lang.String getImgName() {
        return imgName;
    }


    /**
     * Sets the imgName value for this DtActivity.
     * 
     * @param imgName
     */
    public void setImgName(java.lang.String imgName) {
        this.imgName = imgName;
    }


    /**
     * Gets the duration value for this DtActivity.
     * 
     * @return duration
     */
    public java.lang.Integer getDuration() {
        return duration;
    }


    /**
     * Sets the duration value for this DtActivity.
     * 
     * @param duration
     */
    public void setDuration(java.lang.Integer duration) {
        this.duration = duration;
    }


    /**
     * Gets the price value for this DtActivity.
     * 
     * @return price
     */
    public java.lang.Float getPrice() {
        return price;
    }


    /**
     * Sets the price value for this DtActivity.
     * 
     * @param price
     */
    public void setPrice(java.lang.Float price) {
        this.price = price;
    }


    /**
     * Gets the registryDate value for this DtActivity.
     * 
     * @return registryDate
     */
    public java.util.Calendar getRegistryDate() {
        return registryDate;
    }


    /**
     * Sets the registryDate value for this DtActivity.
     * 
     * @param registryDate
     */
    public void setRegistryDate(java.util.Calendar registryDate) {
        this.registryDate = registryDate;
    }


    /**
     * Gets the classesQuantity value for this DtActivity.
     * 
     * @return classesQuantity
     */
    public int getClassesQuantity() {
        return classesQuantity;
    }


    /**
     * Sets the classesQuantity value for this DtActivity.
     * 
     * @param classesQuantity
     */
    public void setClassesQuantity(int classesQuantity) {
        this.classesQuantity = classesQuantity;
    }


    /**
     * Gets the classes value for this DtActivity.
     * 
     * @return classes
     */
    public publishers.DtActivityClassesEntry[] getClasses() {
        return classes;
    }


    /**
     * Sets the classes value for this DtActivity.
     * 
     * @param classes
     */
    public void setClasses(publishers.DtActivityClassesEntry[] classes) {
        this.classes = classes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DtActivity)) return false;
        DtActivity other = (DtActivity) obj;
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
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.imgName==null && other.getImgName()==null) || 
             (this.imgName!=null &&
              this.imgName.equals(other.getImgName()))) &&
            ((this.duration==null && other.getDuration()==null) || 
             (this.duration!=null &&
              this.duration.equals(other.getDuration()))) &&
            ((this.price==null && other.getPrice()==null) || 
             (this.price!=null &&
              this.price.equals(other.getPrice()))) &&
            ((this.registryDate==null && other.getRegistryDate()==null) || 
             (this.registryDate!=null &&
              this.registryDate.equals(other.getRegistryDate()))) &&
            this.classesQuantity == other.getClassesQuantity() &&
            ((this.classes==null && other.getClasses()==null) || 
             (this.classes!=null &&
              java.util.Arrays.equals(this.classes, other.getClasses())));
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
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getImgName() != null) {
            _hashCode += getImgName().hashCode();
        }
        if (getDuration() != null) {
            _hashCode += getDuration().hashCode();
        }
        if (getPrice() != null) {
            _hashCode += getPrice().hashCode();
        }
        if (getRegistryDate() != null) {
            _hashCode += getRegistryDate().hashCode();
        }
        _hashCode += getClassesQuantity();
        if (getClasses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getClasses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getClasses(), i);
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
        new org.apache.axis.description.TypeDesc(DtActivity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://publishers/", "dtActivity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("", "description"));
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
        elemField.setFieldName("duration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "duration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("", "price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registryDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "registryDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classesQuantity");
        elemField.setXmlName(new javax.xml.namespace.QName("", "classesQuantity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "classes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://publishers/", ">>dtActivity>classes>entry"));
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
