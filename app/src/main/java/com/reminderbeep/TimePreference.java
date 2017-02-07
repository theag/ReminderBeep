package com.reminderbeep;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by nbp184 on 2017/02/07.
 */
public class TimePreference extends DialogPreference {

    private static final String DEFAULT_VALUE = "8:0";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("H:m");
    private static final SimpleDateFormat display = new SimpleDateFormat("h:mm aa");

    private String currentValue;
    private TimePicker timePicker;
    private TextView textView;

    public TimePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDialogLayoutResource(R.layout.dialog_time);
        setPositiveButtonText(R.string.ok);
        setNegativeButtonText(R.string.cancel);
        setWidgetLayoutResource(R.layout.prefenrence_time);
        setDialogIcon(null);
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        timePicker = (TimePicker)view.findViewById(R.id.timePicker);
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(currentValue));
            timePicker.setHour(cal.get(Calendar.HOUR_OF_DAY));
            timePicker.setMinute(cal.get(Calendar.MINUTE));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return a.getString(index);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        textView = (TextView)view.findViewById(R.id.lbl_time);
        try {
            textView.setText(display.format(sdf.parse(currentValue)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        if(restorePersistedValue) {
            currentValue = getPersistedString(DEFAULT_VALUE);
        } else {
            currentValue = (String)defaultValue;
            persistString(currentValue);
        }
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if(positiveResult) {
            currentValue = timePicker.getHour() +":" +timePicker.getMinute();
            persistString(currentValue);
            try {
                textView.setText(display.format(sdf.parse(currentValue)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    /*private static class SavedState extends BaseSavedState {
        // Member that holds the setting's value
        // Change this data type to match the type saved by your Preference
        String value;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source) {
            super(source);
            // Get the current preference's value
            value = source.readString();  // Change this to read the appropriate data type
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            // Write the preference's value
            dest.writeString(value);  // Change this to write the appropriate data type
        }

        // Standard creator object using an instance of this class
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {

                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        // Check whether this Preference is persistent (continually saved)
        if (isPersistent()) {
            // No need to save instance state since it's persistent,
            // use superclass state
            return superState;
        }

        // Create instance of custom BaseSavedState
        final SavedState myState = new SavedState(superState);
        // Set the state's value with the class member that holds current
        // setting value
        myState.value = currentValue;
        return myState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        // Check whether we saved the state in onSaveInstanceState
        if (state == null || !state.getClass().equals(SavedState.class)) {
            // Didn't save the state, so call superclass
            super.onRestoreInstanceState(state);
            return;
        }

        // Cast state to custom BaseSavedState and pass to superclass
        SavedState myState = (SavedState) state;
        super.onRestoreInstanceState(myState.getSuperState());

        // Set this Preference's widget to reflect the restored state
       // mNumberPicker.setValue(myState.value);
    }*/

}
