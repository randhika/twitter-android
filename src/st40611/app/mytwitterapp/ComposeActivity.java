package st40611.app.mytwitterapp;

import org.json.JSONObject;

import st40611.app.restclienttemplate.models.User;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ComposeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
		MyTwitterApp.getRestClient().getCurrentUser(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject jsonCurrentUser) {
				User currentUser = User.fromJson(jsonCurrentUser);
				TextView tvCurrentUserName = (TextView) findViewById(R.id.tvName);
				tvCurrentUserName.setText(currentUser.getName());
				TextView tvCurrentUserTagline = (TextView) findViewById(R.id.tvScreenname);
				tvCurrentUserTagline.setText(currentUser.getScreenName());
				ImageView image = (ImageView) findViewById(R.id.ivProfileImage);
				ImageLoader.getInstance().displayImage(currentUser.getProfileImageUrl(), image);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.action_compose:
	            tweet();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void tweet() {
		String status = ((TextView) findViewById(R.id.etBody)).getText().toString();
		MyTwitterApp.getRestClient().tweet(status, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject result) {
				
			}
		});
		Toast.makeText(this, "Tweet sent!", Toast.LENGTH_SHORT).show();
		finish();
	}

}
