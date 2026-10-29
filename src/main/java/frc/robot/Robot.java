// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;

/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private final DifferentialDrive m_robotDrive;
  private final CommandPS4Controller drivController = new CommandPS4Controller(0);

  private final SparkMax left1 = new SparkMax(1, MotorType.kBrushless);
  private final SparkMax left2 = new SparkMax(2, MotorType.kBrushless);
  // private SparkMaxConfig left = new SparkMaxConfig().follow(0);
  private final SparkMax right1 = new SparkMax(3, MotorType.kBrushless);
  private final SparkMax right2 = new SparkMax(4, MotorType.kBrushless);


  /** Called once at the beginning of the robot program. */
  public Robot() {
    // group the motors to follow motors #1
    left2.configure(new SparkMaxConfig().follow(1), com.revrobotics.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    right2.configure(new SparkMaxConfig().follow(3), com.revrobotics.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    right1.configure(new SparkMaxConfig().inverted(true), com.revrobotics.ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    m_robotDrive = new DifferentialDrive(left1::set, right1::set);


    SendableRegistry.addChild(m_robotDrive, left1);
    SendableRegistry.addChild(m_robotDrive, right1);
  }

  @Override
  public void teleopPeriodic() {
    // m_robotDrive.tankDrive(drivController.getLeftY(), drivController.getRightY());
    m_robotDrive.arcadeDrive(-drivController.getLeftY(), drivController.getLeftX(), true);
    // left1.set(drivController.getRightX());
  }
}
