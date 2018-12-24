package cy.its.service.data2db.BulkData.Utils;

import java.lang.reflect.Field;
import java.util.List;

public class InsertParam<T> {
	public String instSQL;
	public List<Field> fields;
	public int fieldSize;
	public Class<T> clazz;
	public String tableName;
}
