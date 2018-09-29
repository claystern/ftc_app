package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Encapsulates the moving parts of the arm
 * Currently, this only includes the motor that moves the "elbow."
 */
public class Arm {

    private DcMotor elbow;

    public Arm(HardwareMap map){
        //Initialize elbow to the motor called "elbow" in Robot Configuration
        elbow = map.get(DcMotor.class, "elbow");
    }

    /**Set the power of the motor that bends the elbow.*/
    public void bend(double power){
        elbow.setPower(power);
    }

}
