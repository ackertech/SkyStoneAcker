package org.firstinspires.ftc.teamcode.base.Robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.base.Drivetrains.MecanumDrive;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;



public class LabBot extends MecanumDrive {

    //Robot Hardware Constructors

    public Servo HoodLeft = null;
    public Servo HoodRight = null;
    public Servo RearLift = null;
    public HardwareMap hwBot  =  null;
    public RevBlinkinLedDriver blinkinLedDriver;
    public RevBlinkinLedDriver.BlinkinPattern pattern;



    //FTC SDK Requirement
    public LinearOpMode linearOp = null;
    public void setLinearOp (LinearOpMode Op) {
       linearOp = Op;
   }


    //LabBot Constructor

    public LabBot() {

    }

    public void initRobot(HardwareMap hwMap) {

        hwBot = hwMap;

        // Define Motors for Robot
        frontLeftMotor = hwBot.dcMotor.get("front_left_motor");
        frontRightMotor = hwBot.dcMotor.get("front_right_motor");
        rearLeftMotor = hwBot.dcMotor.get("rear_left_motor");
        rearRightMotor = hwBot.dcMotor.get("rear_right_motor");


        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        rearRightMotor.setDirection(DcMotor.Direction.REVERSE);


        //Initialize Motor Run Mode for Robot
        setMotorRunModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorRunModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Define & Initialize Servos
        HoodLeft = hwBot.get(Servo.class, "hood_left");
        HoodLeft.setDirection(Servo.Direction.FORWARD);

        HoodRight = hwBot.get(Servo.class, "hood_right");
        HoodLeft.setDirection(Servo.Direction.REVERSE);
        HoodInit();

        RearLift = hwBot.get(Servo.class, "rear_lift");
        RearLiftInit();

        //Define & Initialize LEDTester Lights
        blinkinLedDriver = hwBot.get(RevBlinkinLedDriver.class, "led_strip");
        pattern = RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_LAVA_PALETTE;
        blinkinLedDriver.setPattern(pattern);



    }



    public void HoodClose () {

        HoodLeft.setPosition(.31);
        HoodRight.setPosition(.31);
    }


    public void HoodInit () {

        HoodLeft.setPosition(.31);
        HoodRight.setPosition(.31);
    }

    public void HoodSmile () {

        HoodLeft.setPosition(.18);
        HoodRight.setPosition(.18);

    }


    public void HoodOpen () {

        HoodLeft.setPosition(.10);
        HoodRight.setPosition(.10);

    }



    public void RearLiftUp () {

        RearLift.setPosition(.20);
    }


    public void RearLiftInit () {

        RearLift.setPosition(.20);

    }

    public void RearLiftDown () {

        RearLift.setPosition(.50);


    }

    public void RearLiftMid () {

        RearLift.setPosition(.40);


    }


}
