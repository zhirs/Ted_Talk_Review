package tedtalks.db;

import tedtalkDB.persist.DatabaseProvider;
import tedtalkDB.persist.DerbyDatabase;
import tedtalkDB.persist.FakeDatabase;


public class InitDatabase {
	public static void init() {
			DatabaseProvider.setInstance(new DerbyDatabase());
	}
}
