package org.pearadox5414.lib.motors.motorcontrollers;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import org.pearadox5414.lib.control.pid.PIDCoefficients;
import org.pearadox5414.lib.motors.motortypes.MotorConfiguration;

public class PearVictorSPX implements SmartCANMC<WPI_VictorSPX> {

  @Override
  public MotorConfiguration getMotorConfiguration() {
    return null;
  }

  @Override
  public WPI_VictorSPX getBaseController() {
    return null;
  }

  /**
   * Set the raw percent output of the motor controller.
   *
   * @param percent percent output.
   */
  @Override
  public void set(double percent) {

  }

  /**
   * Sets output to zero.
   */
  @Override
  public void stop() {

  }

  /**
   * Sets output voltage
   *
   * @param voltage voltage output.
   */
  @Override
  public void setVoltage(double voltage) {

  }

  /**
   * Follow another smart motor controller.
   *
   * @param master the leader to follow
   */
  @Override
  public void follow(SmartCANMC<SpeedController> master) {

  }

  @Override
  public double getInputVoltage() {
    return 0;
  }

  @Override
  public double getInputCurrent() {
    return 0;
  }

  @Override
  public double getOutputVoltage() {
    return 0;
  }

  @Override
  public double getOutputCurrent() {
    return 0;
  }

  @Override
  public void setNeutralMode(NeutralMode mode) {

  }

  @Override
  public int getCANID() {
    return 0;
  }

  @Override
  public void wipeSettings() {

  }

  @Override
  public double getSensorPosition() {
    return 0;
  }

  @Override
  public double getSensorVelocity() {
    return 0;
  }

  @Override
  public void setTargetPosition(double position) {

  }

  @Override
  public void setTargetVelocity(double velocity) {

  }

  @Override
  public void configurePID(PIDCoefficients coefficients) {

  }
}
