/**
 * InstitutePublisherServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publishers;

public class InstitutePublisherServiceLocator extends org.apache.axis.client.Service implements publishers.InstitutePublisherService {

    public InstitutePublisherServiceLocator() {
    }


    public InstitutePublisherServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InstitutePublisherServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for InstitutePublisherPort
    private java.lang.String InstitutePublisherPort_address = "http://localhost:8094/instituteController";

    public java.lang.String getInstitutePublisherPortAddress() {
        return InstitutePublisherPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InstitutePublisherPortWSDDServiceName = "InstitutePublisherPort";

    public java.lang.String getInstitutePublisherPortWSDDServiceName() {
        return InstitutePublisherPortWSDDServiceName;
    }

    public void setInstitutePublisherPortWSDDServiceName(java.lang.String name) {
        InstitutePublisherPortWSDDServiceName = name;
    }

    public publishers.InstitutePublisher getInstitutePublisherPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InstitutePublisherPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInstitutePublisherPort(endpoint);
    }

    public publishers.InstitutePublisher getInstitutePublisherPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            publishers.InstitutePublisherPortBindingStub _stub = new publishers.InstitutePublisherPortBindingStub(portAddress, this);
            _stub.setPortName(getInstitutePublisherPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInstitutePublisherPortEndpointAddress(java.lang.String address) {
        InstitutePublisherPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (publishers.InstitutePublisher.class.isAssignableFrom(serviceEndpointInterface)) {
                publishers.InstitutePublisherPortBindingStub _stub = new publishers.InstitutePublisherPortBindingStub(new java.net.URL(InstitutePublisherPort_address), this);
                _stub.setPortName(getInstitutePublisherPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("InstitutePublisherPort".equals(inputPortName)) {
            return getInstitutePublisherPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://publishers/", "InstitutePublisherService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://publishers/", "InstitutePublisherPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("InstitutePublisherPort".equals(portName)) {
            setInstitutePublisherPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
