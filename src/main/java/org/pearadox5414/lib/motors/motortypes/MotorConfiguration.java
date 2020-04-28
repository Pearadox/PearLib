package org.pearadox5414.lib.motors.motortypes;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.SpeedController;

public abstract class MotorConfiguration {
  public static class FeedbackSensor {
    public final FeedbackDevice device;
    public final int CPR;

    public FeedbackSensor(FeedbackDevice device, int CPR) {
      this.device = device;
      this.CPR = CPR;
    }
  }

  boolean brushed;
  boolean coast;
  boolean inverted;
  public FeedbackSensor feedbackDevice;
  int feedbackCPR;
  SpeedController master;
  int stallLimit;
  int freeLimit;
  int stallThreshold;
  int peakCurrentTime;

  /**
   *
   * @param coast True if motor is in coast mode, false if motor is in brake mode.
   * @return A copy of the configuration with the selected idle mode.
   */
  public abstract MotorConfiguration setIdleMode(boolean coast);

  /**
   *
   * @param inverted True if motor should be inverted, false if regular.
   * @return A copy of the configuration with the selected inversion.
   */
  public abstract MotorConfiguration setInverted(boolean inverted);

  /**
   *
   * @param master set a master motor controller. Null if no master.
   * @return A copy of the configuration with the selected master.
   */
  public abstract MotorConfiguration withMaster(SpeedController master);

  /**
   *
   * @param feedbackDevice The sensor used in conjunction with the motor controller. null if no sensor.
   * @return A copy of the configuration with the selected idle mode.
   */
  public abstract MotorConfiguration withFeedbackDevice(FeedbackSensor feedbackDevice);

  public boolean isBrushed() {
    return brushed;
  }

  public boolean isCoast() {
    return coast;
  }

  public boolean isInverted() {
    return inverted;
  }

  public FeedbackSensor getFeedbackDevice() {
    return feedbackDevice;
  }

  public int getFeedbackCPR() {
    return feedbackCPR;
  }

  public SpeedController getMaster() {
    return master;
  }

  public int getStallLimit() {
    return stallLimit;
  }

  public int getFreeLimit() {
    return freeLimit;
  }

  public int getStallThreshold() {
    return stallThreshold;
  }

  public int getPeakCurrentTime() {
    return peakCurrentTime;
  }
}