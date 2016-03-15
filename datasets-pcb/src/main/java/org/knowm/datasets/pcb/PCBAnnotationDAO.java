package org.knowm.datasets.pcb;

import java.util.List;

import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
public class PCBAnnotationDAO extends PCBParentDAO {

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS PCB_ANNOTATIONS", null);
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE_PCB_ANNOTATIONS", null);
  }

  public static int insert(PCBAnnotation pcbAnnotation) {

    Object[] params = new Object[] {

        // @formatter:off
        pcbAnnotation.getId(),
        pcbAnnotation.getX(),
        pcbAnnotation.getY(),
        pcbAnnotation.getWidth(),
        pcbAnnotation.getHeight(),
        pcbAnnotation.getRotation(),
        pcbAnnotation.getName()
        // @formatter:on
    };

    String PCB_INSERT = "INSERT INTO PCB_ANNOTATIONS (id, x, y, width, height, rotation, name) VALUES (?, ?, ?, ?, ?, ?, ?)";
    return Yank.execute(PCB_INSERT, params);
  }

  public static List<PCBAnnotation> selectList(int id) {

    Object[] params = new Object[] { id };

    String SELECT = "SELECT * FROM PCB_ANNOTATIONS WHERE id = ?";

    return Yank.queryBeanList(SELECT, PCBAnnotation.class, params);
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM PCB_ANNOTATIONS";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }
}
