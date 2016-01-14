package org.knowm.datasets.common;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

/**
 * @author timmolter
 */
public class JoinerSplitterTest {

  @Test
  public void test1() {

    String[] stringArray = new String[] { "foo", "bar", "blah" };
    List<String> stringList = new ArrayList<String>(Arrays.asList(stringArray));
    String csv = Joiner.join(",", stringList);
    assertThat(csv).isEqualTo("foo,bar,blah");

    Iterable iterable = Splitter.split(",", csv);
    Iterator<String> itr = iterable.iterator();
    assertThat(itr.next()).isEqualTo("foo");
    assertThat(itr.next()).isEqualTo("bar");
    assertThat(itr.next()).isEqualTo("blah");

  }

}
