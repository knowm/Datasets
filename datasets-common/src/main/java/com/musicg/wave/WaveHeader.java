/**
 * Copyright (C) 2011 Jacquet Wong
 * Copyright (C) 2014 Xeiam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.musicg.wave;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jacquet Wong
 */
public class WaveHeader {

  private static final String RIFF_HEADER = "RIFF";
  private static final String WAVE_HEADER = "WAVE";
  private static final int HEADER_BYTE_LENGTH = 44; // 44 bytes for header

  private final String chunkId; // 4 bytes
  private final long chunkSize; // unsigned 4 bytes, little endian
  private final String format; // 4 bytes
  private final String subChunk1Id; // 4 bytes
  private final long subChunk1Size; // unsigned 4 bytes, little endian
  private final int audioFormat; // unsigned 2 bytes, little endian
  private final int channels; // unsigned 2 bytes, little endian
  private final long sampleRate; // unsigned 4 bytes, little endian
  private final long byteRate; // unsigned 4 bytes, little endian
  private final int blockAlign; // unsigned 2 bytes, little endian
  private final int bitsPerSample; // unsigned 2 bytes, little endian
  private final String subChunk2Id; // 4 bytes
  private final long subChunk2Size; // unsigned 4 bytes, little endian

  /**
   * Constructor
   * 
   * @param inputStream
   * @throws IOException
   */
  public WaveHeader(InputStream inputStream) throws IOException {

    byte[] headerBuffer = new byte[HEADER_BYTE_LENGTH];
    inputStream.read(headerBuffer);

    // read header
    int pointer = 0;
    chunkId = new String(new byte[] { headerBuffer[pointer++], headerBuffer[pointer++], headerBuffer[pointer++], headerBuffer[pointer++] });
    // little endian
    chunkSize = headerBuffer[pointer++] & 0xff | (long) (headerBuffer[pointer++] & 0xff) << 8 | (long) (headerBuffer[pointer++] & 0xff) << 16 | headerBuffer[pointer++] & 0xff << 24;
    format = new String(new byte[] { headerBuffer[pointer++], headerBuffer[pointer++], headerBuffer[pointer++], headerBuffer[pointer++] });
    subChunk1Id = new String(new byte[] { headerBuffer[pointer++], headerBuffer[pointer++], headerBuffer[pointer++], headerBuffer[pointer++] });
    subChunk1Size = headerBuffer[pointer++] & 0xff | (long) (headerBuffer[pointer++] & 0xff) << 8 | (long) (headerBuffer[pointer++] & 0xff) << 16 | (long) (headerBuffer[pointer++] & 0xff) << 24;
    audioFormat = (headerBuffer[pointer++] & 0xff) | (headerBuffer[pointer++] & 0xff) << 8;
    channels = (headerBuffer[pointer++] & 0xff) | (headerBuffer[pointer++] & 0xff) << 8;
    sampleRate = headerBuffer[pointer++] & 0xff | (long) (headerBuffer[pointer++] & 0xff) << 8 | (long) (headerBuffer[pointer++] & 0xff) << 16 | (long) (headerBuffer[pointer++] & 0xff) << 24;
    byteRate = headerBuffer[pointer++] & 0xff | (long) (headerBuffer[pointer++] & 0xff) << 8 | (long) (headerBuffer[pointer++] & 0xff) << 16 | (long) (headerBuffer[pointer++] & 0xff) << 24;
    blockAlign = (headerBuffer[pointer++] & 0xff) | (headerBuffer[pointer++] & 0xff) << 8;
    bitsPerSample = (headerBuffer[pointer++] & 0xff) | (headerBuffer[pointer++] & 0xff) << 8;
    subChunk2Id = new String(new byte[] { headerBuffer[pointer++], headerBuffer[pointer++], headerBuffer[pointer++], headerBuffer[pointer++] });
    subChunk2Size = (headerBuffer[pointer++] & 0xff) | (long) (headerBuffer[pointer++] & 0xff) << 8 | (long) (headerBuffer[pointer++] & 0xff) << 16 | (long) (headerBuffer[pointer++] & 0xff) << 24;
    // end read header

    if (bitsPerSample != 8 && bitsPerSample != 16) {
      throw new RuntimeException("WaveHeader: only supports bitsPerSample 8 or 16");
    }

    // check the format is support
    if (!(chunkId.toUpperCase().equals(RIFF_HEADER) && format.toUpperCase().equals(WAVE_HEADER) && audioFormat == 1)) {
      throw new RuntimeException("WaveHeader: Unsupported header format");
    }

  }

  public String getChunkId() {

    return chunkId;
  }

  public long getChunkSize() {

    return chunkSize;
  }

  public String getFormat() {

    return format;
  }

  public String getSubChunk1Id() {

    return subChunk1Id;
  }

  public long getSubChunk1Size() {

    return subChunk1Size;
  }

  public int getAudioFormat() {

    return audioFormat;
  }

  public int getChannels() {

    return channels;
  }

  public int getSampleRate() {

    return (int) sampleRate;
  }

  public int getByteRate() {

    return (int) byteRate;
  }

  public int getBlockAlign() {

    return blockAlign;
  }

  public int getBitsPerSample() {

    return bitsPerSample;
  }

  public String getSubChunk2Id() {

    return subChunk2Id;
  }

  public long getSubChunk2Size() {

    return subChunk2Size;
  }

  @Override
  public String toString() {

    StringBuffer sb = new StringBuffer();
    sb.append("chunkId: " + chunkId);
    sb.append("\n");
    sb.append("chunkSize: " + chunkSize);
    sb.append("\n");
    sb.append("format: " + format);
    sb.append("\n");
    sb.append("subChunk1Id: " + subChunk1Id);
    sb.append("\n");
    sb.append("subChunk1Size: " + subChunk1Size);
    sb.append("\n");
    sb.append("audioFormat: " + audioFormat);
    sb.append("\n");
    sb.append("channels: " + channels);
    sb.append("\n");
    sb.append("sampleRate: " + sampleRate);
    sb.append("\n");
    sb.append("byteRate: " + byteRate);
    sb.append("\n");
    sb.append("blockAlign: " + blockAlign);
    sb.append("\n");
    sb.append("bitsPerSample: " + bitsPerSample);
    sb.append("\n");
    sb.append("subChunk2Id: " + subChunk2Id);
    sb.append("\n");
    sb.append("subChunk2Size: " + subChunk2Size);
    return sb.toString();
  }
}