Πληροφορίες ομάδας

	Η ομάδα μας αποτελείται από τα άτομα:
	•	Στεφανία Πάτσου με ΑΜ: 1115201400156
	•	Αντωνία Αθανασάκου με ΑΜ: 1115201400004

Υλοποίηση

	Περιβάλλον
	Η υλοποίηση έγινε στο πρόγραμμα eclipse oxygen 2017 με tomcat v8.0.

	Εκτέλεση
	1.	Αλλαγή διαπιστευτηρίων βάσης στην κλάση ConnectionManager στο Package: JavaFiles.
	2.	Η εκτέλεση είναι πολύ απλή, δηλαδή με επιλογή του φακέλου πρότζεκτ και Run as->Run on Server.

	Τρόπος Υλοποίησης
	Αρχικά δημιουργήσαμε τα html αρχεία και έπειτα υλοποιήσαμε τα αντίστοιχα Servlets, μέσω των οποίων πραγματοποιούμε σύνδεση, συλλογή ή προσθήκη εγγραφών, με την βοήθεια των κλάσεων στο Package: JavaFiles, σε μία βάση δεδομένων που έχουμε κατασκευάσει.

	Βάση Δεδομένων
	Η βάση δεδομένων βρίσκεται κάτω από τον φάκελο BookingApp->WebContent->sql, η οποία είναι σε μορφή .mwb. Η βάση δεδομένων έχει όνομα mydb, ενώ το μοντέλο λέγεται BookARoomDatabase. Με βάση το μοντέλο και το workbench , κάνουμε Forward Engineering .

Χωρισμός Εργασίας
	Στεφανία Πάτσου
	Επέλεξα να υλοποιήσω τις σελίδες με τα αντίστοιχα ServLets και κλάσεις Java του JavaFiles:
	•	WelcomePage.html
	•	admin_handle.html
	•	admin_page.html
	•	approve_user.html
	•	change_profile_admin.html
	•	change_profile_host.html
	•	change_profile.html
	•	export_data.html
	•	host_page.html
	•	hostDetails.html
	•	register_done.html
	•	register.html
	•	user_page.html

	Αντωνία Αθανασάκου
	Επέλεξε να υλοποιήσει τις σελίδες με τα αντίστοιχα ServLets και κλάσεις Java του JavaFiles:
	•	apartmentResultsGeneral.html
	•	apartmentWithDetails.html
	•	aptChangeDone.html
	•	aptInputDone.html
	•	aptWithChange.html
	•	hostApartmentDetails.html
	•	inputApt.html
	•	listOfApartments.html
	Ανά τακτά χρονικά διαστήματα, υπήρξε συγχρονισμός με τα δεδομένα της άσκησης και επίλυση αποριών μέσω συνεδριών στο ίντερνετ.

	
Σχόλια/Παρατηρήσεις
	•	Το 9ο υποερώτημα δεν υλοποιήθηκε λόγω περιορισμένου χρόνου.
	•	Έχει αποδειχθεί ότι η ανανέωση της σελίδας επιλύει ζητήματα λειτουργικότητας.
	•	Ενώ η αποθήκευση των εικόνων στην βάση γίνεται με επιτυχία, το ανέβασμα για populate είναι ανεπιτυχές.
	•	Έχουμε σχολιασμένους κώδικες τους οποίους δεν μπορέσαμε, λόγω έλλειψης χρόνου να υλοποιήσουμε σωστά.
	•	Δεν έχουμε υλοποιήσει την σελιδοποίηση.
	•	Υπάρχουν ServLets στα οποία δεν έχουμε πάρει μέσω Cookie διάφορα στοιχεία όπως host_id, room_id κ.λπ. λόγω περιορισμένου χρόνου. Σε αυτά τα σημεία συμπληρώσαμε από μόνες μας κάποιες τιμές, για να φανεί η υπόλοιπη υλοποίηση.
	
	

Αρχεία που τροποποιήθηκαν
	•	WEB-INF/web.xml
	•	META-INF/persistence.xml
	•	Server/Tomcat/web.xml
	•	Server/Tomcat/server.xml
