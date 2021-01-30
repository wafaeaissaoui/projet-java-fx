package sample.DataValidation;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.*;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Datavalidation {

/*
    public static boolean textDataLength(TextField inputTextField, Label inputLabel, String validationText, String requiredLength) {
        boolean rightDataLength = true;

        String validationString = null;

        //\b\w{8} & \ 'escapes' it.
        if (!inputTextField.getText().matches("\\b\\w" + "{" + requiredLength + "}")) {
            rightDataLength = false;
            validationString = validationText;

            inputLabel.setText(validationString);

        }

        return rightDataLength;


    }
   */

    public static boolean dataLength(TextField inputTextField, Label inputLabel, String validationText, String requiredLength) {
        boolean isDataLength = true;
        String validationString = null;

        if (!inputTextField.getText().matches("\\b\\w" + "{" + requiredLength + "}")) {
            isDataLength = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isDataLength;

    }
    public static boolean textAlphabet(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isAlphabet = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[a-z A-Z]+")) {
            isAlphabet = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);

        System.out.println(inputTextField.getText().matches("[a-z A-Z]"));
        return isAlphabet;

    }

    public static boolean textNumeric(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isNumeric = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[0-9]+")) {
            isNumeric = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isNumeric;

    }

    public static boolean emailFormat(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isEmail = true;
        String validationString = null;

        if (!inputTextField.getText().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com")) {
            isEmail = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isEmail;

    }

    public static boolean zID(TextField inputTextField, Label inputLabel, String validationText) {
        boolean isZID = true;
        String validationString = null;

        if (!inputTextField.getText().matches("\\z[0-9]{7}")) {
            isZID = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);

        return isZID;

    }

    //Regular Expressions: zMail: \z[0-9]{7}
    public static boolean textFieldIsNull(JFXTextField inputTextField, Label inputLabel, String validationText) {
        boolean isNull = false;
        String validationString = null;

        System.out.println("*******************************************************");

        //point out difference between null and isEmpty() *FIND OUT WHEN TO USE NULL
        if (inputTextField.getText().isEmpty()) {
            isNull = true;
            validationString = validationText;

        }

        String isEmpty = Boolean.toString(inputTextField.getText().isEmpty());
        String nil = Boolean.toString(inputTextField.getText() == null);

        inputLabel.setText(validationString);

        System.out.println("Label Should be Set to: " + validationString);
        System.out.println("Input TextField: " + inputTextField.getText());
        System.out.println("Null: " + nil + " isEmpty: " + isEmpty);

        return isNull;

    }
    public static boolean textAriaIsNull(TextArea inputTextField, Label inputLabel, String validationText) {
        boolean isNull = false;
        String validationString = null;

        System.out.println("*******************************************************");

        //point out difference between null and isEmpty() *FIND OUT WHEN TO USE NULL
        if (inputTextField.getText().isEmpty()) {
            isNull = true;
            validationString = validationText;

        }

        String isEmpty = Boolean.toString(inputTextField.getText().isEmpty());
        String nil = Boolean.toString(inputTextField.getText() == null);

        inputLabel.setText(validationString);

        System.out.println("Label Should be Set to: " + validationString);
        System.out.println("Input TextField: " + inputTextField.getText());
        System.out.println("Null: " + nil + " isEmpty: " + isEmpty);

        return isNull;

    }

    public static boolean PasswordNull(PasswordField inputTextField, Label inputLabel, String validationText) {
        boolean isNull = false;
        String validationString = null;

        System.out.println("*******************************************************");

        //point out difference between null and isEmpty() *FIND OUT WHEN TO USE NULL
        if (inputTextField.getText().isEmpty()) {
            isNull = true;
            validationString = validationText;

        }

        String isEmpty = Boolean.toString(inputTextField.getText().isEmpty());
        String nil = Boolean.toString(inputTextField.getText() == null);

        inputLabel.setText(validationString);

        System.out.println("Label Should be Set to: " + validationString);
        System.out.println("Input TextField: " + inputTextField.getText());
        System.out.println("Null: " + nil + " isEmpty: " + isEmpty);

        return isNull;

    }
    public static boolean ComboxdNull(ComboBox inputTextField, Label inputLabel, String validationText) {
        boolean isNull = false;
        String validationString = null;

        System.out.println("*******************************************************");

        //point out difference between null and isEmpty() *FIND OUT WHEN TO USE NULL
        if (inputTextField.getSelectionModel().isEmpty()) {
            isNull = true;
            validationString = validationText;

        }

        String isEmpty = Boolean.toString(inputTextField.getSelectionModel().isEmpty());
        String nil = Boolean.toString(inputTextField.getValue() == null);

        inputLabel.setText(validationString);

        System.out.println("Label Should be Set to: " + validationString);
        System.out.println("Input TextField: " + inputTextField.getVisibleRowCount());
        System.out.println("Null: " + nil + " isEmpty: " + isEmpty);

        return isNull;

    }
    // public static boolean DateNull(Date inputTextField, Label inputLabel, String validationText) {
    //        boolean isNull = false;
    //        String validationString = null;
    //
    //        System.out.println("*******************************************************");
    //
    //        //point out difference between null and isEmpty() *FIND OUT WHEN TO USE NULL
    //        if (inputTextField.getDate().isEmpty()) {
    //            isNull = true;
    //            validationString = validationText;
    //
    //        }
    //
    //        String isEmpty = Boolean.toString(inputTextField.getSelectionModel().isEmpty());
    //        String nil = Boolean.toString(inputTextField.getValue() == null);
    //
    //        inputLabel.setText(validationString);
    //
    //        System.out.println("Label Should be Set to: " + validationString);
    //        System.out.println("Input TextField: " + inputTextField.getVisibleRowCount());
    //        System.out.println("Null: " + nil + " isEmpty: " + isEmpty);
    //
    //        return isNull;
    //
    //    }




}
