package st40611.app.mytwitterapp;

import org.json.JSONObject;

import st40611.app.restclienttemplate.models.User;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		String screenname = (String) getIntent().getSerializableExtra("screenname");
		if (screenname == null) {
			loadProfileInfo();
		} else {
			loadProfileInfo(screenname);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	public void loadProfileInfo() {
		MyTwitterApp.getRestClient().getCurrentUser(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject jsonCurrentUser) {
				User currentUser = User.fromJson(jsonCurrentUser);
				getActionBar().setTitle("@" + currentUser.getScreenName());
				populateProfileHeader(currentUser);
			}
		});
	}
	
	public void loadProfileInfo(String screenname) {
		Log.v("Debug", screenname);
		MyTwitterApp.getRestClient().getUser(screenname, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject jsonUser) {
				Log.v("Debug", "hi");
				Log.v("Debug", jsonUser.toString());
				User currentUser = User.fromJson(jsonUser);
				getActionBar().setTitle("@" + currentUser.getScreenName());
				populateProfileHeader(currentUser);
			}
			@Override
			public void onFailure(Throwable e, JSONObject errorMsg) {
				Log.v("Debug", "is this doign anything");
				Log.v("Debug", errorMsg.toString());
			}
		});
	}
	
	public void populateProfileHeader(User user) {
		TextView tvName = (TextView) findViewById(R.id.tvName);
		TextView tvTagline = (TextView) findViewById(R.id.tvTagline);
		TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		ImageView ivProfileImage = (ImageView) findViewById(R.id.ivProfileImage);
		tvName.setText(user.getName());
		tvTagline.setText(user.getTagline());
		tvFollowers.setText(user.getFollowersCount() + " Followers");
		tvFollowing.setText(user.getFriendsCount() + " Following");
		ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), ivProfileImage);
	}
}
