/**
 * (The MIT License)
 *
 * Copyright 2015 Knowm Inc. (http://knowm.org) and contributors.
 * Copyright 2013-2015 Xeiam LLC (http://xeiam.com) and contributors.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.knowm.datasets.hjabirdsong;

import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.knowm.datasets.common.Splitter;

/**
 * Copyright (C) 2013-2014 Xeiam LLC http://xeiam.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * @author timmolter
 */
public class HJABirdSong implements Serializable {

  private int bagid;
  private String labels;
  private String wavfilename;
  private Blob wavbytes;

  private static final Map<Integer, String> label2WordMap = new HashMap<Integer, String>();

  static {
    label2WordMap.put(1, "Brown Creeper");
    label2WordMap.put(2, "Winter Wren");
    label2WordMap.put(3, "Pacific-slope Flycatcher");
    label2WordMap.put(4, "Red-breasted Nuthatch");
    label2WordMap.put(5, "Dark-eyed Junco");
    label2WordMap.put(6, "Olive-sided Flycatcher");
    label2WordMap.put(7, "Hermit Thrush");
    label2WordMap.put(8, "Chestnut-backed Chickadee");
    label2WordMap.put(9, "Varied Thrush");
    label2WordMap.put(10, "Hermit Warbler");
    label2WordMap.put(11, "Swainson's Thrush");
    label2WordMap.put(12, "Hammond's Flycatcher");
    label2WordMap.put(13, "Western Tanager");
  }

  public int getBagid() {

    return bagid;
  }

  public void setBagid(int bagid) {

    this.bagid = bagid;
  }

  public String getLabels() {

    return labels;
  }

  public List<Integer> getLabelsAsArray() {

    List<Integer> labelsAsInts = new ArrayList<Integer>();
    Iterable<String> lables = Splitter.split(",", labels);
    for (String label : lables) {
      labelsAsInts.add(Integer.parseInt(label));
    }
    return labelsAsInts;
  }

  public void setLabels(String labels) {

    this.labels = labels;
  }

  public String getWavfilename() {

    return wavfilename;
  }

  public void setWavfilename(String wavfilename) {

    this.wavfilename = wavfilename;
  }

  public Blob getWavbytes() {

    return wavbytes;
  }

  public void setWavbytes(Blob wavbytes) {

    this.wavbytes = wavbytes;
  }

  @Override
  public String toString() {

    return "HJABirdSong [bagid=" + bagid + ", labels=" + labels + ", wavfilename=" + wavfilename + "]";
  }

}
