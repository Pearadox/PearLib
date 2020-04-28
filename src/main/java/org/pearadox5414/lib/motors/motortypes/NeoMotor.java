package org.pearadox5414.lib.motors.motortypes;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.SpeedController;

public class NeoMotor extends MotorConfiguration {

  NeoMotor() { this(false, false, null, new FeedbackSensor(FeedbackDevice.IntegratedSensor, 42)); }

  NeoMotor(boolean coast, boolean inverted, SpeedController master, FeedbackSensor feedbackDevice) {
    brushed = false;
    stallLimit = 60;
    freeLimit = 80;
    stallThreshold = 30;
    peakCurrentTime = 1000;
    this.coast = coast;
    this.inverted = inverted;
    this.master = master;
    this.feedbackDevice = feedbackDevice;
  }

  @Override
  public MotorConfiguration setIdleMode(boolean coast) {
    return new NeoMotor(coast, inverted, master, feedbackDevice);
  }

  @Override
  public MotorConfiguration setInverted(boolean inverted) {
    return new NeoMotor(coast, inverted, master, feedbackDevice);
  }

  @Override
  public MotorConfiguration withMaster(SpeedController master) {
    return new NeoMotor(coast, inverted, master, feedbackDevice);
  }

  @Override
  public MotorConfiguration withFeedbackDevice(FeedbackSensor device) {
    return new NeoMotor(coast, inverted, master, device);
  }
}