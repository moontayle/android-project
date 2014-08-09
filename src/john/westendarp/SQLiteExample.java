package john.westendarp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener {

	Button sqlUpdate, sqlView, sqlGetInfo, sqlEdit, sqlDelete;
	EditText sqlName, sqlHotness, sqlRow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqliteexample);
		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		sqlName = (EditText) findViewById(R.id.etSQLName);
		sqlHotness = (EditText) findViewById(R.id.etSQLHotness);

		sqlView = (Button) findViewById(R.id.bSQLView);
		sqlView.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);

		sqlRow = (EditText) findViewById(R.id.etSQLNumberId);
		sqlEdit = (Button) findViewById(R.id.bSQLEditEntry);
		sqlDelete = (Button) findViewById(R.id.bSQLDeleteEntry);
		sqlGetInfo = (Button) findViewById(R.id.bSQLGetInfo);
		sqlEdit.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.bSQLUpdate:
			boolean work = true;
			try {
				String name = sqlName.getText().toString();
				String hotness = sqlHotness.getText().toString();

				HotOrNot entry = new HotOrNot(SQLiteExample.this);
				entry.open();
				entry.createEntry(name, hotness);
				entry.close();

			} catch (Exception e) {
				work = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Did not work!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (work) {
					Dialog d = new Dialog(this);
					d.setTitle("Heck Yea!");
					TextView tv = new TextView(this);
					tv.setText("Success!");
					d.setContentView(tv);
					d.show();
				}
			}
			break;

		case R.id.bSQLView:
			Intent i = new Intent("john.westendarp.SQLVIEW");
			break;
		case R.id.bSQLGetInfo:
			try {
				String s = sqlRow.getText().toString();
				long l = Long.parseLong(s);
				HotOrNot hon = new HotOrNot(this);
				hon.open();
				String returnedName = hon.getName(l);
				String returnHotness = hon.getHotness(l);
				hon.close();

				sqlName.setText(returnedName);
				sqlHotness.setText(returnHotness);
			} catch (Exception e) {
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Did not work!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			break;
		case R.id.bSQLEditEntry:
			try {
				String sRow = sqlRow.getText().toString();
				long lRow = Long.parseLong(sRow);
				String eName = sqlName.getText().toString();
				String eHotness = sqlHotness.getText().toString();

				HotOrNot ex = new HotOrNot(this);
				ex.open();
				ex.updateEntry(lRow, eName, eHotness);
				ex.close();
			} catch (Exception e) {
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Did not work!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			break;
		case R.id.bSQLDeleteEntry:
			try {
				String dlRow = sqlRow.getText().toString();
				long dRow = Long.parseLong(dlRow);

				HotOrNot dx = new HotOrNot(this);

				dx.open();
				dx.deleteEntry(dRow);
				dx.close();
			} catch (Exception e) {
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Did not work!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			break;
		}
	}

}
