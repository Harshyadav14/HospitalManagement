# HospitalManagement

ADMIN API’S
1)	Signup API
POST - localhost:4000/HospitalManagement/Signup
RequestBody-
{
    "user_name": "Ayush Yadav",
    "phone_number": "8658963245",
    "email_id": "ayush@exm.com",
    "designation": "Doctor",
    "password": "Ayush@yadav#45",
    "gender": "Male"
},

Applied All most all possible VALIDATIONS & EXCEPTION HANDLING
 	a) user_name- Must be of 3 - 30 characters
	b) phone_number- Must be of 10 digits
	c) email_id- Only accept email with domain @exm.com
	d) designation- designation must be one of the following.. admin,doctor,pharmacist,receptionist,other
	e) password- Password must contain at least one digit [0-9]. " + 
			"Password must contain at least one lowercase Latin character [a-z]. " + 
			"Password must contain at least one uppercase Latin character [A-Z]. " + 
			"Password must contain at least one special character like ! @ # & ( ). " + 
			"Password must contain a length of at least 8 characters and a maximum of 20 characters.
  	f)gender- Gender Must be either Male or Female

   These Input validation are  used to detect unauthorized input and exceptions are properly handled.

 
3)	Login
GET - localhost:4000/HospitalManagement/Login
 
 
4)	View All Users
GET - localhost:4000/HospitalManagement/View_Users
 

5)	Find User by User Id
GET - localhost:4000/HospitalManagement/findUserBy_UserId/106
 
6)	Find User by User Designation or Role Like:Doctor,Receptionist
GET- localhost:4000/HospitalManagement/findUserListBy_Designation/receptionist
 
7)	Update User Details
PUT - localhost:4000/HospitalManagement/Update_User

 

8)	Delete User
DELETE - localhost:4000/HospitalManagement/DeleteUserBy_UserId/114
 
User Table In DataBase
 

8)	Total Earning of Hospital
GET - localhost:4000/HospitalManagement/TotalEarning
 





		

Receptionist API’s
1)	Create Appointment
POST - localhost:4000/HospitalManagement/Create_Appointment
{
    "receptionist_id":"86",
    "patient_name": "pulkit",
    "age":38,
    "phone_number": "9111254789",
    "email_id": "pulkit@exm.com",
    "blood_group": "O+",
    "doctor_name": "Harsh Yadav",
    "appointment_date":"2023-07-23",
    "appointment_time":"16:30",
    "gender": "Male"
}
 
Applied All most all possible VALIDATIONS & EXCEPTION HANDLING

 




2)	Find Appointment BY OPD ID
GET - localhost:4000/HospitalManagement/find_Appointment/116
 

3)	 View All Appointments
GET - localhost:4000/HospitalManagement/View_All_Appointments

 




4)	Update Appointment
PUT - localhost:4000/HospitalManagement/Update_Appointment

 




5)	Delete Appointment
DELETE - localhost:4000/HospitalManagement/Delete_Appointment/110
 
Appointment 	Table in Database
 




6)	Admit Patient
POST - localhost:4000/HospitalManagement/Admit_Patient
{
    "patient_name": "Tarun",
    "age": 54,
    "phone_number": "6985622258",
    "gender": "female",
    "blood_group": "A+",
    "room_no": 56,
    "doctor_name": "Harsh Yadav",
    "admit_date": "2023-07-23"
}

 
7)	View Admited Patients
GET - localhost:4000/HospitalManagement/View_Admited_Patients
 









8)	Find IPD Patient by IPD ID
GET - localhost:4000/HospitalManagement/find_Admited_PatientsBy_ID/117
  


9)	Find Patient by Admit Date
GET –  localhost:4000/HospitalManagement/find_Admited_PatientsBy_AdmitDate/2023-07-23
 








10)	Find Patient by Discharge Date
GET –  localhost:4000/HospitalManagement/find_Discharge_PatientsBy_DischargeDate/2023-07-25
 

11)	Find Patient by IPD Status (1=Admitted Not Discharged Yet, 2= Discharged From Hospital)
GET - localhost:4000/HospitalManagement/findAllpatientsBy_Status/1
 





12)	Find Patient by Blood Group
GET - localhost:4000/HospitalManagement/findAllpatientsBy_BloodGroup/O+
 
13)	Update Admit Patient Record
PUT – localhost:4000/HospitalManagement/Update_Admited_Patient

 




14)	Delete Admitted Patient
DELETE - localhost:4000/HospitalManagement/Delete_Admited_Patient/117
  
15)	Patient Discharge Request
GET - localhost:4000/HospitalManagement/Patient_DischargeRequest/112/106

 




16)	Discharge Patient From Hospital
GET –  localhost:4000/HospitalManagement/DischargePatient/112/2023-07-25
 
 





17)	Paying Hospital bills
GET – localhost:4000/HospitalManagement/Payment/118/500
 
 
Doctor API’s
1)	Show All Doctors
GET- localhost:4000/HospitalManagement/Show_All_Doctors
 
2)	Treat OPD Appointments
POST- localhost:4000/HospitalManagement/Treat_OPD_Patients/116/119-76/

 
 
3)	Medicine Prescription of IPD patients
POST – localhost:4000/HospitalManagement/MedicinePrescription/118/76-77-49/
 
 
4)	Discharge Request Approve
GET – localhost:4000/HospitalManagement/Approve_DischargeRequest/112/106
 
			
		Pharmacist API’s

1)	Add Medicine
POST – localhost:4000/HospitalManagement/Add_Medicine
{
    "name": "Paracetamol",
    "stock": 54,
    "details": "Paracetamol (Panadol, Calpol, Alvedon) is an analgesic and antipyretic drug that is used to temporarily relieve mild-to-moderate pain and fever. It is commonly included as an ingredient in cold and flu medications and is also used on its own. Paracetamol is exactly the same drug as acetaminophen (Tylenol).",
    "price": 10
}


 
2)	View All Medicines
GET –  localhost:4000/HospitalManagement/Show_All_Medicines
  
3)	Find Medicine By Id
GET – localhost:4000/HospitalManagement/findMedicineById/58
 
4)	Update Medicine
PUT –  localhost:4000/HospitalManagement/Update_Medicine
 
5)	Delete Medicine
DELETE –  localhost:4000/HospitalManagement/Delete_Medicine/120
 
