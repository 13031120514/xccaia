package com.xccaia.oracle;

import com.xccaia.DataBaseDriver;

/**
 * @ Author     ：xccaia
 * @ Date       ：2020-04-11
 * @ Description：
 */
public class OracleDataBaseDriver implements DataBaseDriver {

  @Override
  public void connect(String host) {
    System.out.println("Build connect with oracle");
  }
}
