package org.knowm.datasets.hjabirdsong;

import org.knowm.yank.Yank;

/**
 * @author timmolter
 */
public class HJABirdsongDAO extends HJABirdsongParentDAO {

  public static int dropTable() {

    return Yank.execute("DROP TABLE IF EXISTS BIRD_SONGS", null);
  }

  public static int createTable() {

    return Yank.executeSQLKey("KEY_CREATE_TABLE", null);
  }

  public static int insert(HJABirdSong hJABirdSong) {

    Object[] params = new Object[] {

        // @formatter:off
        hJABirdSong.getBagid(), hJABirdSong.getLabels(), hJABirdSong.getWavfilename(), hJABirdSong.getWavbytes()
        // @formatter:on
    };
    String BIRD_SONGS_INSERT = "INSERT INTO BIRD_SONGS (bagid, labels, wavfilename, wavbytes) VALUES (?, ?, ?, ?)";
    return Yank.execute(BIRD_SONGS_INSERT, params);
  }

  public static HJABirdSong selectSingle(int bagid) {

    Object[] params = new Object[] { bagid };

    String SELECT_SINGLE = "SELECT * FROM BIRD_SONGS WHERE bagid = ?";

    return Yank.queryBean(SELECT_SINGLE, HJABirdSong.class, params);
  }

  public static long selectCount() {

    String SELECT_COUNT = "SELECT COUNT(*) FROM BIRD_SONGS";

    return Yank.queryScalar(SELECT_COUNT, Long.class, null);
  }
}
