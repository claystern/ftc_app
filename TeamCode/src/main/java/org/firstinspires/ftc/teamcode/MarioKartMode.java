package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Vaguely-mario kart style driving mode
 * If the left trigger is pressed, the amount to which it is pressed is how backwards it's going
 * If the right trigger is pressed, the amount to which it is pressed is how forwards it's going
 * Left joystick: if left/right, change the appropriate motor to make that happen.
 */
@TeleOp
public class MarioKartMode extends LinearOpMode {

    //Declare drive to be of type DriveBase
    private DriveBase drive;

    /** Run when Init is pressed */
    @Override
    public void runOpMode(){

        //Set drive to a DriveBase object, which lets us control the motors that move the whole
        //robot. Pass hardwareMap as an argument so that DriveBase can find the motors.
        drive = new DriveBase(hardwareMap);

        //Set the driver station to say "Meep! Initialized!"
        telemetry.addData("Status", "Meep! Initialized!");
        telemetry.update();

        //Wait until play is pressed
        waitForStart();

        //Do this until you click stop
        while(opModeIsActive()){
            //What the forward-going wheel or wheels will
            double power = 0;
            boolean goingForward = false;
            boolean goingBackward = false;
            //If the left trigger is pressed and the right isn't
            if(gamepad1.left_trigger != 0 && gamepad1.right_trigger == 0){
                //go backwards the amount left trigger is pressed
                goingForward = true;
                power = -gamepad1.left_trigger;
            }
            //If the other way around
            else if(gamepad1.left_trigger == 0 && gamepad1.right_trigger != 0){
                //go forwards the amount left trigger is pressed
                goingBackward = true;
                power = gamepad1.right_trigger;
            }

            double leftPower = power;
            double rightPower = power;

            if(gamepad1.left_stick_x < 0 && (goingForward || goingBackward)){
                rightPower = power * (gamepad1.left_stick_x + 0.5) * 2;
            }
            if(gamepad1.left_stick_x > 0 && (goingForward || goingBackward)){
                leftPower = power * (gamepad1.left_stick_x - 0.5) * -2;
            }

            drive.set(leftPower, rightPower);
            //Print these out on the drivers' console.
            telemetry.addData("Power", power);
            telemetry.addData("Left Trigger", gamepad1.left_trigger);
            telemetry.addData("Right Trigger", gamepad1.right_trigger);
            telemetry.addData("Left Joy X", gamepad1.left_stick_x);
            telemetry.addData("Left Power", leftPower);
            telemetry.addData("Right Power", rightPower);
            telemetry.update();
        }
    }
}
