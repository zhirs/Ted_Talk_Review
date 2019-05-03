package tedtalks.db;

import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.DerbyDatabase;


public class InitDatabase {
	public static void init() {
			DatabaseProvider.setInstance(new DerbyDatabase());
	}
}
