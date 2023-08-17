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
	    if(searchMutterList != null) {
	    return searchMutterList;
        }else {
        }return null;
}
}