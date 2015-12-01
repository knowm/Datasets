package org.knowm.datasets.lshtc4;

import java.util.List;

import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
public class LSHTC4HierarchyDAO extends LSHTC4ParentDAO {

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS LSHTC4Hierarchy", null);
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE_HIERARCHY", null);
  }

  public static int insert(LSHTC4Hierarchy lSHTC4Hierarchy) {

    Object[] params = new Object[] {

        // @formatter:off
        lSHTC4Hierarchy.getParentid(), lSHTC4Hierarchy.getNodeid()
        // @formatter:on
    };
    String LSHTC4Hierarchy_INSERT = "INSERT INTO LSHTC4Hierarchy (parentid, nodeid) VALUES (?, ?)";
    return Yank.execute(LSHTC4Hierarchy_INSERT, params);

  }

  public static List<LSHTC4Hierarchy> selectAll() {

    String SELECT_ALL = "SELECT * FROM LSHTC4Hierarchy";

    return Yank.queryBeanList(SELECT_ALL, LSHTC4Hierarchy.class, null);
  }

  public static LSHTC4Hierarchy selectSingle(int id) {

    Object[] params = new Object[] { id };

    String SELECT_SINGLE = "SELECT * FROM LSHTC4Hierarchy WHERE nodeid = ?";

    return Yank.queryBean(SELECT_SINGLE, LSHTC4Hierarchy.class, params);
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM LSHTC4Hierarchy";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }
}
