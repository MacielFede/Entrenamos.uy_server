/**
 * UserPublisherServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publishers;

public class UserPublisherServiceLocator extends org.apache.axis.client.Service implements publishers.UserPublisherService {

    public UserPublisherServiceLocator() {
    }


    public UserPublisherServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UserPublisherServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UserPublisherPort
    private java.lang.String UserPublisherPort_address = "http://localhost:8094/userController";

    public java.lang.String getUserPublisherPortAddress() {
        return UserPublisherPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UserPublisherPortWSDDServiceName = "UserPublisherPort";

    public java.lang.String getUserPublisherPortWSDDServiceName() {
        return UserPublisherPortWSDDServiceName;
    }

    public void setUserPublisherPortWSDDServiceName(java.lang.String name) {
        UserPublisherPortWSDDServiceName = name;
    }

    public publishers.UserPublisher getUserPublisherPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UserPublisherPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUserPublisherPort(endpoint);
    }

    public publishers.UserPublisher getUserPublisherPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            publishers.UserPublisherPortBindingStub _stub = new publishers.UserPublisherPortBindingStub(portAddress, this);
            _stub.setPortName(getUserPublisherPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUserPublisherPortEndpointAddress(java.lang.String address) {
        UserPublisherPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (publishers.UserPublisher.class.isAssignableFrom(serviceEndpointInterface)) {
                publishers.UserPublisherPortBindingStub _stub = new publishers.UserPublisherPortBindingStub(new java.net.URL(UserPublisherPort_address), this);
                _stub.setPortName(getUserPublisherPortWSDDServiceName());
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
        if ("UserPublisherPort".equals(inputPortName)) {
            return getUserPublisherPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://publishers/", "UserPublisherService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://publishers/", "UserPublisherPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UserPublisherPort".equals(portName)) {
            setUserPublisherPortEndpointAddress(address);
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
