/**
 * InstitutePublisher.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package publishers;

public interface InstitutePublisher extends java.rmi.Remote {
    public boolean checkInstitutionAvialability(java.lang.String arg0) throws java.rmi.RemoteException;
    public publishers.DtClass[] listClassesDictationRanking() throws java.rmi.RemoteException;
    public void registerInstitution(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
    public publishers.DtInstitute chooseSportInstitute(java.lang.String arg0) throws java.rmi.RemoteException;
    public publishers.DtInstitute[] listSportInstitutes() throws java.rmi.RemoteException;
    public publishers.DtActivity[] listSportsActivitiesRanking() throws java.rmi.RemoteException;
    public publishers.DtClass chooseClassByName(java.lang.String arg0) throws java.rmi.RemoteException;
    public publishers.DtActivity getActivity(java.lang.String arg0) throws java.rmi.RemoteException;
    public publishers.DtClass[] chooseActivity(java.lang.String arg0) throws java.rmi.RemoteException;
    public publishers.DtActivity[] selectInstitution(java.lang.String arg0) throws java.rmi.RemoteException;
    public publishers.DtActivity[] getAllActivities() throws java.rmi.RemoteException;
    public void addNewSportActivity(publishers.DtActivity arg0, java.lang.String arg1) throws java.rmi.RemoteException;
    public boolean checkActivityAvialability(java.lang.String arg0) throws java.rmi.RemoteException;
    public void modiFySportInstitute(publishers.DtInstitute arg0) throws java.rmi.RemoteException;
    public boolean checkClassNameAvailability(java.lang.String arg0, publishers.DtEnrollment arg1) throws java.rmi.RemoteException;
    public void createSportClass(publishers.DtClass arg0, java.lang.String arg1, java.lang.String arg2) throws java.rmi.RemoteException;
}
