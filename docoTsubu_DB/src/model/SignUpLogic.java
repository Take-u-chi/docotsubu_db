package model;

import dao.AccountDAO;

public class SignUpLogic {

		public boolean isValidName(String name) {
			String regex = "^[a-zA-Z0-9]{4,20}$";
			return name.matches(regex);
		}
		public boolean isValidPass(String pass) {
			String regex = "^[a-z0-9]{8,16}$";
			return pass.matches(regex);
		}
	 public boolean isSignUp(User user) {
			 AccountDAO dao = new AccountDAO();
			 return dao.signUp(user);
				 }

}

