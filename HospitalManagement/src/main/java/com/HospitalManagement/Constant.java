package com.HospitalManagement;

public class Constant {
	public static final int OPD_Medicine_DayLimit=2;
	public static final int OPD_Medicine_dosePerDay=3;  
	public static final int AppointmentCharges=500;
	public static final int opd_status_Not_Treated=1;  //status(Not Treated =1, Treatment Done=2)
	public static final int opd_status_Treatment_Done=2; 
	public static final int ipd_status_admitted=1;  //status(admitted =1, discharged=2)
	public static final int ipd_status_discharged=2;
	public static final int Default_discharge_request_status=0; //status(discharge Not Requested By Patient =0, discharged Requested By Patient=1)
	public static final int discharge_request_status_True=1;
	public static final int Default_discharge_requested_from_doctor_id=0; //status(discharge Not Requested By doctor yet =0)
	public static final int discharge_approved_status_Not_Approved=0;  //status(discharge Not Approved =0, discharged Approved=1)
	public static final int discharge_approved_status_Approved=1; 
	public static final String discharge_approvedby="Not Approved Yet";
	public static final String Default_discharge_date=null;
	public static final int AdmitCharges=1000;
	public static final int Default_billpaid=0;
	public static final int MedicineMinimumStockCount=0;
}
