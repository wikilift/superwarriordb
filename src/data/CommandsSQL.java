package data;

public enum CommandsSQL {
	CREATE_QUERY(
	"CREATE TABLE {0} ( `NAME` VARCHAR(50) NOT NULL  , `DESCRIPTION` VARCHAR(200) NOT NULL  , "
	+ "`WIND` INT(1) NOT NULL  , `WATER` INT(1) NOT NULL  , "
	+ "`FIRE` INT(1) NOT NULL  , "
	+ "PRIMARY KEY (`NAME`(50))) ENGINE = InnoDB;"),
	INSERT_QUERY("INSERT INTO {0} VALUES(\"{1}\",\"{2}\",{3},{4},{5});"),										
	SELECT_QUERY("SELECT * FROM {0};"),
	SEARCH_QUERY("SELECT * FROM {0} WHERE name=\"{1}\";"),
	RESET_QUERY("UPDATE {0} SET fire=0,water=0,wind=0 WHERE name=\"{1}\" ;"),
	GIVE_POWER_QUERY("UPDATE {0} SET fire={1},water={2},wind={3} WHERE name=\"{4}\"; "),
	DELETE_QUERY("DELETE FROM {0} WHERE name=\"{1}\";"),
	DROP_TABLE_QUERY("DROP TABLE {0};");
//192.168.1.13:3306/acceso
	private final String text;

	CommandsSQL(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

}