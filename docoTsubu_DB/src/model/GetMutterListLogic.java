package model;

import java.util.List;

import dao.MutterDAO;

//つぶやきの「取得」に関する処理を行うモデル（DAOを利用）
public class GetMutterListLogic {
	public List<Mutter>execute(){
		MutterDAO dao = new MutterDAO();
		List<Mutter>mutterList = dao.findAll();
		return mutterList;
	}

public List<Mutter>searchMutter(String searchText){
		MutterDAO dao = new MutterDAO();
	    List<Mutter>searchMutterList = dao.mutterSearch(searchText);

	    return searchMutterList;
      }
public boolean deleteMutter(String id) {
		MutterDAO dao = new MutterDAO();
		if(dao.deleteMutter(id)) {
			return true;
		}else {
	     return false;
		}
}
public List<Mutter>searchMyMutter(String name){
	MutterDAO dao = new MutterDAO();
	List<Mutter>myMutterList = dao.myMutterFindAll(name);

	return myMutterList;

}
}