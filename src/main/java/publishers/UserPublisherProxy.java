package publishers;

public class UserPublisherProxy implements publishers.UserPublisher {
  private String _endpoint = null;
  private publishers.UserPublisher userPublisher = null;
  
  public UserPublisherProxy() {
    _initUserPublisherProxy();
  }
  
  public UserPublisherProxy(String endpoint) {
    _endpoint = endpoint;
    _initUserPublisherProxy();
  }
  
  private void _initUserPublisherProxy() {
    try {
      userPublisher = (new publishers.UserPublisherServiceLocator()).getUserPublisherPort();
      if (userPublisher != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)userPublisher)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)userPublisher)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (userPublisher != null)
      ((javax.xml.rpc.Stub)userPublisher)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public publishers.UserPublisher getUserPublisher() {
    if (userPublisher == null)
      _initUserPublisherProxy();
    return userPublisher;
  }
  
  public java.lang.String[] listMembersByNickname() throws java.rmi.RemoteException{
    if (userPublisher == null)
      _initUserPublisherProxy();
    return userPublisher.listMembersByNickname();
  }
  
  public publishers.DtClass[] getMemberEnrolledClasses(java.lang.String arg0) throws java.rmi.RemoteException{
    if (userPublisher == null)
      _initUserPublisherProxy();
    return userPublisher.getMemberEnrolledClasses(arg0);
  }
  
  public publishers.DtUser chooseUser(java.lang.String arg0) throws java.rmi.RemoteException{
    if (userPublisher == null)
      _initUserPublisherProxy();
    return userPublisher.chooseUser(arg0);
  }
  
  public void updateUserInfo(publishers.DtUser arg0) throws java.rmi.RemoteException, publishers.FebruaryDayException, publishers.EmptyRequiredFieldException, publishers.SameYearException{
    if (userPublisher == null)
      _initUserPublisherProxy();
    userPublisher.updateUserInfo(arg0);
  }
  
  public void addEnrollment(java.lang.String arg0, publishers.DtUser arg1, float arg2) throws java.rmi.RemoteException, publishers.Exception{
    if (userPublisher == null)
      _initUserPublisherProxy();
    userPublisher.addEnrollment(arg0, arg1, arg2);
  }
  
  public void newUser(publishers.DtUser arg0, java.lang.String arg1) throws java.rmi.RemoteException, publishers.AtributeAlreadyExists, publishers.FebruaryDayException, publishers.EmptyRequiredFieldException, publishers.SameYearException{
    if (userPublisher == null)
      _initUserPublisherProxy();
    userPublisher.newUser(arg0, arg1);
  }
  
  public void newMember(publishers.DtUser arg0) throws java.rmi.RemoteException{
    if (userPublisher == null)
      _initUserPublisherProxy();
    userPublisher.newMember(arg0);
  }
  
  public boolean existsNickname(java.lang.String arg0) throws java.rmi.RemoteException{
    if (userPublisher == null)
      _initUserPublisherProxy();
    return userPublisher.existsNickname(arg0);
  }
  
  public void newUserMemberUnreachable(publishers.DtMember arg0, java.lang.String arg1) throws java.rmi.RemoteException, publishers.AtributeAlreadyExists, publishers.FebruaryDayException, publishers.EmptyRequiredFieldException, publishers.SameYearException{
    if (userPublisher == null)
      _initUserPublisherProxy();
    userPublisher.newUserMemberUnreachable(arg0, arg1);
  }
  
  public void newUserProfessorUnreachable(publishers.DtProfessor arg0, java.lang.String arg1) throws java.rmi.RemoteException, publishers.AtributeAlreadyExists, publishers.FebruaryDayException, publishers.EmptyRequiredFieldException, publishers.SameYearException{
    if (userPublisher == null)
      _initUserPublisherProxy();
    userPublisher.newUserProfessorUnreachable(arg0, arg1);
  }
  
  public boolean existsEmail(java.lang.String arg0) throws java.rmi.RemoteException{
    if (userPublisher == null)
      _initUserPublisherProxy();
    return userPublisher.existsEmail(arg0);
  }
  
  
}