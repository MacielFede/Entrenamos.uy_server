/**
 * UserPublisher.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publishers;

public interface UserPublisher extends java.rmi.Remote {
    public java.lang.String[] listMembersByNickname() throws java.rmi.RemoteException;
    public publishers.DtClass[] getMemberEnrolledClasses(java.lang.String arg0) throws java.rmi.RemoteException;
    public publishers.DtUser chooseUser(java.lang.String arg0) throws java.rmi.RemoteException;
    public void updateUserInfo(publishers.DtUser arg0) throws java.rmi.RemoteException, publishers.FebruaryDayException, publishers.EmptyRequiredFieldException, publishers.SameYearException;
    public void addEnrollment(java.lang.String arg0, publishers.DtUser arg1, float arg2) throws java.rmi.RemoteException, publishers.Exception;
    public void newUser(publishers.DtUser arg0, java.lang.String arg1) throws java.rmi.RemoteException, publishers.AtributeAlreadyExists, publishers.FebruaryDayException, publishers.EmptyRequiredFieldException, publishers.SameYearException;
    public void newMember(publishers.DtUser arg0) throws java.rmi.RemoteException;
    public boolean existsNickname(java.lang.String arg0) throws java.rmi.RemoteException;
    public void newUserMemberUnreachable(publishers.DtMember arg0, java.lang.String arg1) throws java.rmi.RemoteException, publishers.AtributeAlreadyExists, publishers.FebruaryDayException, publishers.EmptyRequiredFieldException, publishers.SameYearException;
    public void newUserProfessorUnreachable(publishers.DtProfessor arg0, java.lang.String arg1) throws java.rmi.RemoteException, publishers.AtributeAlreadyExists, publishers.FebruaryDayException, publishers.EmptyRequiredFieldException, publishers.SameYearException;
    public boolean existsEmail(java.lang.String arg0) throws java.rmi.RemoteException;
}
