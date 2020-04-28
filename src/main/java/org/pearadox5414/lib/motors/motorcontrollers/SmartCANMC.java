package org.pearadox5414.lib.motors.motorcontrollers;

import org.pearadox5414.lib.control.pid.PIDCoefficients;
import org.pearadox5414.lib.motors.motortypes.MotorConfiguration;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Inspired by GrouchLib.
 */
public interface SmartCANMC<T extends SpeedController> {

  enum NeutralMode {
    COAST,
    BRAKE
  }

  MotorConfiguration getMotorConfiguration();

  T getBaseController();

  /**
   * Set the raw percent output of the motor controller.
   * @param percent percent output.
   */
  void set(double percent);

  /**
   * Sets output to zero.
   */
  void stop();

  /**
   * Sets output voltage
   * @param voltage voltage output.
   */
  void setVoltage(double voltage);

  /**
   * Follow another smart motor controller.
   * @param master the leader to follow
   */
  void follow(SmartCANMC<SpeedController> master);

  double getInputVoltage();

  double getInputCurrent();

  double getOutputVoltage();

  double getOutputCurrent();

  void setNeutralMode(NeutralMode mode);

  int getCANID();

  void wipeSettings();

  double getSensorPosition();

  double getSensorVelocity();

  void setTargetPosition(double position);

  void setTargetVelocity(double velocity);

  void configurePID(PIDCoefficients coefficients);
}
