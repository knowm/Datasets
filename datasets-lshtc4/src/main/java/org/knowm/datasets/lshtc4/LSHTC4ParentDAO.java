package org.knowm.datasets.lshtc4;

import org.knowm.datasets.common.business.DatasetsDAO;

/**
 * @author timmolter
 */
public class LSHTC4ParentDAO extends DatasetsDAO {

  public static void init(String dataFilesDir) {

    String dataFileID = "0ByP7_A9vXm17bFdZVzEyMWhsVFk";
    String propsFileID = "0ByP7_A9vXm17Zkl0ajF6LXlrYUE";
    String scriptFileID = "0ByP7_A9vXm17ZnljLU1ybS16c2c";

    init("DB_LSHTC4", dataFilesDir, dataFileID, propsFileID, scriptFileID, null, true);
  }

}
