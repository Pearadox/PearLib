package org.pearadox5414.lib.control.pid;

import edu.wpi.first.wpilibj.Timer;

public class PearPIDController {

  private PIDCoefficients gains;
  private double setpoint;
  private double error;
  private double lastError;
  private double totalError;
  private double changeError;
  private double lastTimestamp;

  /**
   * Makes a PearPIDController with all gains of 0.
   */
  public PearPIDController() {
    this(0, 0, 0, 0, 0);
  }

  /**
   *
   * @param kP proportional
   * @param kI integral term
   * @param kD derivative term
   * @param kFF feedforward term
   * @param kArbFF arbitrary feedforward term
   */
  public PearPIDController(double kP, double kI, double kD, double kFF, double kArbFF) {
    gains = new PIDCoefficients(kP, kI, kD, kFF, kArbFF);
    lastError = 0;
    lastTimestamp = 0;
    totalError = 0;
    setpoint = 0;
  }

  /**
   * Call this every iteration of the robot loop in order to update errors
   * @param measurement measurement
   */
  public void update(double measurement) {
    update(measurement, setpoint);
  }

  public void update(double measurement, double setpoint) {
    update(measurement, setpoint, Timer.getFPGATimestamp() - lastTimestamp);
  }

  public void update(double measurement, double setpoint, double dt) {
    lastError = error;
    totalError += error * dt;
    this.setpoint = setpoint;
    error = setpoint - measurement;
    changeError = (error - lastError) / dt;
    lastTimestamp = Timer.getFPGATimestamp();
  }

  /**
   * Call this after you call update()
   * @return calculated output
   */
  public double calculate() {
    return gains.kP * error +
        gains.kI * totalError +
        gains.kD * changeError +
        gains.kFF * setpoint +
        gains.kArbFF;
  }

  public double getError() {
    return error;
  }
}
