/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved. */
/* Open Source Software - may be modified and shared by FRC teams. The code */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.utility.autonomous;

/**
 * Add your docs here.
 */
public class RobotMath {
    public static double deg2rad(double deg) {
        return (Math.PI / 180) * deg;
    }

    public static double rad2deg(double rad) {
        return (180 / Math.PI) * rad;
    }
}
