package publishers;

public class InstitutePublisherProxy implements publishers.InstitutePublisher {
  private String _endpoint = null;
  private publishers.InstitutePublisher institutePublisher = null;
  
  public InstitutePublisherProxy() {
    _initInstitutePublisherProxy();
  }
  
  public InstitutePublisherProxy(String endpoint) {
    _endpoint = endpoint;
    _initInstitutePublisherProxy();
  }
  
  private void _initInstitutePublisherProxy() {
    try {
      institutePublisher = (new publishers.InstitutePublisherServiceLocator()).getInstitutePublisherPort();
      if (institutePublisher != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)institutePublisher)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)institutePublisher)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (institutePublisher != null)
      ((javax.xml.rpc.Stub)institutePublisher)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public publishers.InstitutePublisher getInstitutePublisher() {
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    return institutePublisher;
  }
  
  public boolean checkInstitutionAvialability(java.lang.String arg0) throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    return institutePublisher.checkInstitutionAvialability(arg0);
  }
  
  public publishers.DtClass[] listClassesDictationRanking() throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    return institutePublisher.listClassesDictationRanking();
  }
  
  public void registerInstitution(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    institutePublisher.registerInstitution(arg0, arg1, arg2);
  }
  
  public publishers.DtInstitute chooseSportInstitute(java.lang.String arg0) throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    return institutePublisher.chooseSportInstitute(arg0);
  }
  
  public publishers.DtInstitute[] listSportInstitutes() throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    return institutePublisher.listSportInstitutes();
  }
  
  public publishers.DtActivity[] listSportsActivitiesRanking() throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    return institutePublisher.listSportsActivitiesRanking();
  }
  
  public publishers.DtClass chooseClassByName(java.lang.String arg0) throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    return institutePublisher.chooseClassByName(arg0);
  }
  
  public publishers.DtActivity getActivity(java.lang.String arg0) throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    return institutePublisher.getActivity(arg0);
  }
  
  public publishers.DtClass[] chooseActivity(java.lang.String arg0) throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    return institutePublisher.chooseActivity(arg0);
  }
  
  public publishers.DtActivity[] selectInstitution(java.lang.String arg0) throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    return institutePublisher.selectInstitution(arg0);
  }
  
  public publishers.DtActivity[] getAllActivities() throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    return institutePublisher.getAllActivities();
  }
  
  public void addNewSportActivity(publishers.DtActivity arg0, java.lang.String arg1) throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    institutePublisher.addNewSportActivity(arg0, arg1);
  }
  
  public boolean checkActivityAvialability(java.lang.String arg0) throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    return institutePublisher.checkActivityAvialability(arg0);
  }
  
  public void modiFySportInstitute(publishers.DtInstitute arg0) throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    institutePublisher.modiFySportInstitute(arg0);
  }
  
  public boolean checkClassNameAvailability(java.lang.String arg0, publishers.DtEnrollment arg1) throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    return institutePublisher.checkClassNameAvailability(arg0, arg1);
  }
  
  public void createSportClass(publishers.DtClass arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException{
    if (institutePublisher == null)
      _initInstitutePublisherProxy();
    institutePublisher.createSportClass(arg0, arg1, arg2);
  }
  
  
}