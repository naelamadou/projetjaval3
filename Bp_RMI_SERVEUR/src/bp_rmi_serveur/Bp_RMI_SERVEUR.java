package bp_rmi_serveur;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.cours.Dao.AgenceDB;
import com.cours.Dao.ClientDao;
import com.cours.Dao.CompteDao;
import com.cours.Dao.EmployeDB;
import com.cours.Dao.IAgence;
import com.cours.Dao.IClient;
import com.cours.Dao.ICompte;
import com.cours.Dao.IEmploye;
import com.cours.Dao.IOperation;
import com.cours.Dao.IUser;
import com.cours.Dao.OperationDao;
import com.cours.Dao.UserDB;

public class Bp_RMI_SERVEUR {

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(1246);
			IUser iuser = new UserDB();
			ICompte icompte = new CompteDao();
			IClient iclient = new ClientDao();
			IAgence iagence = new AgenceDB();
			IEmploye iemploye = new EmployeDB();
			IOperation ioperation= new OperationDao();

			registry.bind("user_db", iuser);
			registry.bind("compte_db", icompte);
			registry.bind("client_db", iclient);
			registry.bind("agence_db", iagence);
			registry.bind("employe_db", iemploye);
			registry.bind("operation_db", ioperation);
			System.out.println("tous les objets distants sonts enregistres!!!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
