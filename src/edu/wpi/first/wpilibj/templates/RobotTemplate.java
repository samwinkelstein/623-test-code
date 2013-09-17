/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. 
/*
* Sams code V1;
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
   
    private CANJaguar frontLeftJag;
    private CANJaguar backLeftJag;
    private CANJaguar frontRightJag;
    private CANJaguar backRightJag;
    private RobotDrive myDrive;
    
    private Joystick driveStick;
    
    
    public void robotInit() {
        try {    
            frontLeftJag = new CANJaguar(1);
            backLeftJag = new CANJaguar(2);
            frontRightJag = new CANJaguar(3);
            backRightJag = new CANJaguar(4);
            
            
            
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
            
            
        }
        driveStick = new Joystick(1);
       myDrive = new RobotDrive(frontLeftJag,backLeftJag,frontRightJag,backRightJag);
        
    }
            
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
       drive();
    }
    // Drive and Joystick section *****************************
    private void drive()
    { 
        double magnitude = applyMagnitudeSquared(applyMagnitudeDeadband(driveStick.getMagnitude()));
        double direction = driveStick.getDirectionDegrees();
        double rotation = applyRotationalDeadband(driveStick.getTwist());

        
        myDrive.mecanumDrive_Polar(magnitude, direction, rotation);
    }
    // squares driveStick magnitude 
    
      private double applyMagnitudeSquared(double magnitude) {
        return magnitude * magnitude;
    }
      
    // joystick Deadbands*****************************
    
    double applyMagnitudeDeadband(double magnitude)
    { 
        double newMagnitude = magnitude;
        if (newMagnitude > .05)
        {
            newMagnitude = newMagnitude -.05;
        }
        else if(newMagnitude > 1)
        {
           newMagnitude = 1;
        }
        return newMagnitude;
    }
    
    double applyRotationalDeadband(double Twist)
    {
       double newTwist = Twist;
       
       if (newTwist > .1)
       {
         newTwist = newTwist -.1;  
       }
       else if ( newTwist < -.1)
       {
           newTwist = newTwist + .1;
       }
       return newTwist;     
    }
   
    //*************************************************
    
     /* This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
