package uk.co.mobileappdev.firstandroidapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FirstAppActivity extends Activity {
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_greeting);
		TextView greeting = (TextView) findViewById(R.id.txtVw_greeting);

		Date now = null;
		try {
			now = timeFormat.parse(timeFormat.format(System.currentTimeMillis()));
		} catch (ParseException e) {
			System.out.println("Error parsing current time.");
		}
		if (now != null) {
			if (itIsMorning(now)) {
				System.out.println("it's morning!");
				greeting.setText("Hello User! Good Morning.");
			} else if (itIsAfternoo(now)) {
				System.out.println("it's afternoon!");
				greeting.setText("Hello User! Good Afternoon.");
			} else {
				System.out.println("it's evening!");
				greeting.setText("Hello User! Good Evening.");
			}
		}
		
		Button submit = (Button) findViewById(R.id.btnSubmit);
		//inline implementation of OnClickListener
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				submitButtonClick();
				
			}
		});
		
		/**
		 * another way to implement 'OnClickListener' would be to implement the interface at the 
		 * class declaration, passing in 'this' argument in the 'setOnClickListener(this)' method 
		 * and having the 'onClick(View arg0)' method declaration separately somewhere in the code.
		 */
		
	}
	

	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_greeting, menu);
		return true;
	}
	*/
	
	private boolean itIsMorning(Date now) {
		try {
			Date morningStart = timeFormat.parse("00:00");
			Date morningEnd = timeFormat.parse("12:00");

			if (now.after(morningStart) && now.before(morningEnd))
				return true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean itIsAfternoo(Date now) {
		try {
			Date afternooStart = timeFormat.parse("12:00");
			Date afternooEnd = timeFormat.parse("17:00");

			if (now.after(afternooStart) && now.before(afternooEnd))
				return true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void displayConfirmScreen(){
		
		EditText txtFldFirstName = (EditText) findViewById(R.id.txtFld_FirstName);
		String firstName = txtFldFirstName.getText().toString();
		
		System.out.println("Entered First Name: "+firstName);
		setContentView(R.layout.confirm_user_name);
	}


	private void submitButtonClick() {
		EditText firstName = (EditText) findViewById(R.id.txtFld_FirstName);
		firstName.setText("Submit pressed!");
	}

}
