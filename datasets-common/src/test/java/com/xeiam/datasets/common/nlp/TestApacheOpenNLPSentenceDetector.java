/**
 * Copyright (c) 2013 M. Alexander Nugent Consulting <i@alexnugent.name>
 *
 * M. Alexander Nugent Consulting Research License Agreement
 * Non-Commercial Academic Use Only
 *
 * This Software is proprietary. By installing, copying, or otherwise using this
 * Software, you agree to be bound by the terms of this license. If you do not agree,
 * do not install, copy, or use the Software. The Software is protected by copyright
 * and other intellectual property laws.
 *
 * You may use the Software for non-commercial academic purpose, subject to the following
 * restrictions. You may copy and use the Software for peer-review and methods verification
 * only. You may not create derivative works of the Software. You may not use or distribute
 * the Software or any derivative works in any form for commercial or non-commercial purposes.
 *
 * Violators will be prosecuted to the full extent of the law.
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.xeiam.datasets.common.nlp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import opennlp.tools.util.InvalidFormatException;

import org.junit.Test;

/**
 * @author timmolter
 */
public class TestApacheOpenNLPSentenceDetector {

  public static void main(String[] args) throws InvalidFormatException, IOException {

    go();
  }

  public static void go() throws InvalidFormatException, IOException {

    String paragraph = "Hi. How are you? This isn't Mike. I wasn't born Dec. 3, 2009. The black and white cow jumped over the moon.";

    String[] sentences = OpenNLPBasicUtils.getSentences(paragraph);
    for (int i = 0; i < sentences.length; i++) {
      System.out.println(i + ": " + sentences[i]);
    }
  }

  @Test
  public void test() {

    String paragraph = "The black and white cow jumped over the moon. Even though Bitcoin has been through two price spikes and dips, it seems yet again we have people flocking to this subreddit circlejerking over the price. ";

    String[] sentences = OpenNLPBasicUtils.getSentences(paragraph);
    for (String a : sentences) {
      System.out.println(a);
    }
    assertThat(sentences.length, equalTo(2));
  }

}
