package org.pearadox5414.lib.control.pid;

public class PIDCoefficients {
  public double kP;
  public double kI;
  public double kD;
  public double kFF;
  public double kArbFF;

  public PIDCoefficients(double kP, double kI, double kD, double kFF, double kArbFF) {
    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
    this.kFF = kFF;
    this.kArbFF = kArbFF;
  }


}
