/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.utility;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.automation.Automation_hypAlign;
import frc.robot.automation.Automation_yawAlign;
import frc.robot.automation.Swerve_Controller;

/**
 * This class should contain all the code for displaying information to the Shuffleboard
 */
public class DashboardDisplay {

    /**
     * Contains the calls to SmartDashboard that are for putting information onto the Shuffleboard
     */
    public static void displayText() {
        SmartDashboard.putNumber("gyro", Robot.swerve.getGyro());
        SmartDashboard.putNumber("gyroYaw", Robot.swerve.getYaw());

        SmartDashboard.putNumber("TranslationalX", Robot.vision.getTranslationalX());
        SmartDashboard.putNumber("TranslationalY", Robot.vision.getTranslationalY());
        SmartDashboard.putNumber("TranslationalZ", Robot.vision.getTranslationalZ());
        SmartDashboard.putNumber("TranslationalPitch", Robot.vision.getTranslationalPitch());
        SmartDashboard.putNumber("TranslationalYaw", Robot.vision.getTranslationalYaw());
        SmartDashboard.putNumber("TranslationalYawAdj",
                Robot.vision.convertYaw(Robot.vision.getTranslationalYaw()));
        SmartDashboard.putNumber("TranslationalRoll", Robot.vision.getTranslationalRoll());
        SmartDashboard.putNumber("TranslationalHyp",
                Math.sqrt(Math.pow(Robot.vision.getTranslationalX(), 2)
                        + Math.pow(Robot.vision.getTranslationalZ(), 2)));
    }

    /**
     * Contains any code that involves the Shuffleboard and needs to be run once (such as
     * initializers)
     */
    public static void initRun() {
        SmartDashboard.putData(new Automation_yawAlign());
        SmartDashboard.putData(new Automation_hypAlign());
        SmartDashboard.putData(new Swerve_Controller());
    }
}
