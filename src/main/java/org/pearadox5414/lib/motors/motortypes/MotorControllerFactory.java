package org.pearadox5414.lib.motors.motortypes;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Class to create motor controllers from predefined configurations.
 */
public class MotorControllerFactory {
  public static CANSparkMax createSparkMax(int canID, MotorConfiguration config) {
    var motor = new CANSparkMax(canID, config.brushed ? MotorType.kBrushed : MotorType.kBrushless);
    motor.setIdleMode(config.coast ? IdleMode.kCoast : IdleMode.kBrake);
    motor.setInverted(config.inverted);
    motor.setSmartCurrentLimit(config.stallLimit, config.freeLimit, config.stallThreshold);
    if (config.master != null) {
      if (!(config.master instanceof CANSparkMax)) {
        throw new IllegalArgumentException("Master must be another Spark MAX");
      }
      motor.follow((CANSparkMax) config.master);
      if (config.feedbackDevice.device == FeedbackDevice.IntegratedSensor) {
        if (config.brushed) {
          throw new IllegalArgumentException("Only Neos can have integrated sensors");
        }
        return motor;
      } else if (config.feedbackDevice.device == FeedbackDevice.QuadEncoder) {
        return motor;
      }
      throw new IllegalArgumentException("Only the built-in hall sensor and external quadrature encoders are allowed");
    }
    return motor;
  }

  public static WPI_TalonSRX createTalonSRX(int canID, MotorConfiguration config) {
    if (!config.brushed) { throw new IllegalArgumentException("Cannot run brushless motors off a Talon SRX"); }
    var motor = new WPI_TalonSRX(canID);
    motor.setNeutralMode(config.coast ? NeutralMode.Coast : NeutralMode.Brake);
    motor.configSelectedFeedbackSensor(config.feedbackDevice.device);
    motor.configSelectedFeedbackCoefficient(1.0 / config.feedbackDevice.CPR);
    if (config.master != null) {
      if (!(config.master instanceof IMotorController)) {
        throw new IllegalArgumentException("Master must be a CTRE Motor Controller");
      }
      motor.follow((IMotorController) config.master);
    }
    return motor;
  }

  public static WPI_VictorSPX createVictorSPX(int canID, MotorConfiguration config) {
    if (!config.brushed) { throw new IllegalArgumentException("Cannot run brushless motors off a Victor SPX"); }
    var motor = new WPI_VictorSPX(canID);
    motor.setNeutralMode(config.coast ? NeutralMode.Coast : NeutralMode.Brake);
    motor.configSelectedFeedbackSensor(config.feedbackDevice.device);
    motor.configSelectedFeedbackCoefficient(1.0 / config.feedbackDevice.CPR);
    if (config.master != null) {
      if (!(config.master instanceof IMotorController)) {
        throw new IllegalArgumentException("Master must be a CTRE Motor Controller");
      }
      motor.follow((IMotorController) config.master);
    }
    return motor;
  }

}