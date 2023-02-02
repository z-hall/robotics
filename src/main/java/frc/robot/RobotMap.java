/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name.
 * This provides flexibility changing wiring, makes checking the wiring easier and significantly
 * reduces the number of magic numbers floating around.
 */
public class RobotMap {

    /*
     * =================== Drivetrain ===================
     */

    public final static int FLSPEEDPORT = 7, FLANGLEPORT = 6;
    public final static int FRSPEEDPORT = 4, FRANGLEPORT = 3;
    public final static int BLSPEEDPORT = 9, BLANGLEPORT = 8;
    public final static int BRSPEEDPORT = 1, BRANGLEPORT = 2;

    // length and width of robot from each wheels center, units dont matter
    public final static double L = 29.75;
    public final static double W = 29.75;
    // Encoder counts per deg of rotation
    public final static double COUNTPERDEG = 16.2539;

    public final static double angleP = 1.03858, angleI = 0.004, angleD = 8, angleF = 0.51;
    // If using motion magic, also set angle[V/A]
    public final static int angleV = 4012, angleA = 4012;

    /*
     * =================== Joystick Configuration ===================
     */

    public final static double DEADZONE_XY = 0.1;
    public final static double DEADZONE_Z = 0.3;

    // How sensitive the Z axis is.
    // 0 = no effect
    // 1 = square the output
    // 2 = cube the output
    public final static int SENSITIVITY_Z = 2;

    // Reduces the maximum output for the Z axis.
    // 1 = full speed
    // 2 = half speed
    // etc
    public final static int REDUCER_Z = 2;



    /*
     * =================== Vision Configuration ===================
     */

    public static final double camDist = 3.5;

    public static final double transXMax = 50;
    public static final double transZMax = 100;
    public static final double yawMax = 45;
    public static final double targetYaw = 90;

    public static double pidXTarget;
    public static double pidXCurrent;
    public static double pidYTarget;
    public static double pidYCurrent;
    public static double pidZTarget;
    public static final double Z_OFFSET = 0;

    /*
     * =================== Other ===================
     */

    public final static int TIMEOUT = 0;
    public final static int COMPRESSOR = 0;

    /*
     * =================== Methods ===================
     */

    // Put SmartDashboard calls here for an easy way to access them
    // This should be called in autoPeriodic, teleopPeriodic, and testPeriodic
    public static void dashboardDisplay() {
        SmartDashboard.putNumber("gyro", Robot.swerve.getGyro());
    }
}
