package model;

import dao.MutterDAO;

//つぶやきの「投稿」に関する処理を行うモデル（DAOを利用）
public class PostMutterLogic {
 public void execute(Mutter mutter) {
	 MutterDAO dao = new MutterDAO();
	 dao.create(mutter);
 }
}
