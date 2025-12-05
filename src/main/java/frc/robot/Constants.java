// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class DropperConstants{
    public static double kP = 1;
    public static double kD = 0.2;
    public static double kI = 0;
    public static double startAngle = -5 / 360; //From bucket vertical
    public static double sprocketRatio = 16/18; //Cause of course it isn't 1:1
    public static double gearRatio = 49;
  }
 
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
}

