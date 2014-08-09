package john.westendarp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartingPoint extends Activity {

	int counter;
	Button add, sub;
	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting_point);
		counter = 0;
		add = (Button) findViewById(R.id.bAdd);
		sub = (Button) findViewById(R.id.bSub);
		display = (TextView) findViewById(R.id.tvDisplay);
		add.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// When Add button is pressed, the total is increased by 1
				counter++;
				display.setText("Your total is " + counter);

			}
		});
		sub.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// When Subtract button is pressed, the total is decreased by 1
				counter--;
				display.setText("Your total is " + counter);
			}
		});

	}

}
