package com.generic.retailer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CLIApplication {

  public static void main(String[] args) {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
    Trolley trolley = Trolley.getInstance();
    try(Cli cli = Cli.create(reader, writer)) {
      cli.run(trolley);
    } catch (Exception e) {
      System.exit(1);
    }
  }

}
